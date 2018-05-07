package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.util.Comparator;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
	private Vector<JigsawNode> exploreVector;	// 用以保存已发现但未访问的节点
    private Vector<JigsawNode> visitedVector;	// 用以保存已发现的节点
    private int searchedNodesNum=0;
	/**
	 * 拼图构造函数
	 */
	public Solution() {
	}

	/**
	 * 拼图构造函数
	 * @param bNode - 初始状态节点
	 * @param eNode - 目标状态节点
	 */
	public Solution(JigsawNode bNode, JigsawNode eNode) {
		super(bNode, eNode);
	}

	/**
	 *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
	 * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
	 * @return 搜索成功时为true,失败为false
	 */
	public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
		exploreVector = new Vector<JigsawNode>();
		visitedVector = new Vector<JigsawNode>();
		beginJNode = new JigsawNode(bNode);
		endJNode = new JigsawNode(eNode);
		currentJNode = null;	
		super.reset();
		exploreVector.add(beginJNode);

        // estimateValue(beginJNode);

        // for (int i = 0; i < exploreVector.size(); i++) {
        //     if (beginJNode.getEstimatedValue() < exploreVector.elementAt(i).getEstimatedValue()){
        //         exploreVector.insertElementAt(beginJNode,i);
        //     }
        // }

        while(!exploreVector.isEmpty()){
        	searchedNodesNum++;
            currentJNode = exploreVector.elementAt(0);
            if (currentJNode.equals(endJNode)){
                getPath();
                break;
            }

            exploreVector.removeElementAt(0);
            visitedVector.addElement(currentJNode);
            // searchedNodesNum++;

   //      	ArrayList<JigsawNode> adjacentNodes = new ArrayList<JigsawNode>();
			// // for(int i=0;i<4;i++){
   // //     			adjacentNodes.add(currentJNode);
   // //     		}
       		for(int i=0;i<4;i++){
				JigsawNode temp= new JigsawNode(currentJNode);
       			if(temp.move(i)&&!exploreVector.contains(temp)
       				&&!visitedVector.contains(temp)){
       					exploreVector.add(temp);
       				}
       		}
            // while (!adjacentNodes.isEmpty()) {
            //     // estimateValue(adjacentNodes.elementAt(0));
            //     for (int i = 0; i < exploreVector.size(); i++) {
            //         if (adjacentNodes.elementAt(0).getEstimatedValue() < exploreVector.elementAt(i).getEstimatedValue()){
            //             exploreVector.insertElementAt(adjacentNodes.elementAt(0),i);
            //         }
            //     }
            //     adjacentNodes.removeElementAt(0);
            // }
        }
		System.out.println("Jigsaw AStar Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
    	System.out.println("Solution Path: ");
        System.out.println(this.getSolutionPath());
        System.out.println("Total number of searched nodes:" + searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());
        return this.isCompleted();
	}


	/**
	 *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
	 * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
	 * @param jNode - 要计算代价估计值的节点
	 */
	public void estimateValue(JigsawNode jNode) {
		int s = 0; // 后续节点不正确的数码个数
		// int dimension = JigsawNode.getDimension();
		// for (int index = 1; index < dimension * dimension; index++) {
		// 	if (jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index + 1]) {
		// 		s++;
		// 	}
		// }
		// jNode.setEstimatedValue(s);

		int misplacedTiles = 0; // 放错位的数码个数
		int manhattanDistance = 0; // 曼哈顿距离
		int dimension = JigsawNode.getDimension();
		for(int index = 1; index < dimension*dimension; index++){
			// 横向后续节点不正确
			if(jNode.getNodesState()[index] + 1 != jNode.getNodesState()[index+1])
				s++;
		}
		for (int index = 1; index < dimension * (dimension - 1); index++) {
			// 纵向后续节点不正确
			if (jNode.getNodesState()[index] + dimension != jNode.getNodesState()[index + dimension]) {
				s++;
			}
		}
		for(int index = 1; index <= dimension*dimension; index++){
			if(jNode.getNodesState()[index] != index)
				misplacedTiles++;
		}
		for(int index = 1; index <= dimension*dimension; index++){
			int realNum = jNode.getNodesState()[index];
			if (realNum != index && realNum != 0) {
				int realX = (realNum - 1) % dimension;
				int realY = (realNum - 1) / dimension;
				int indexX = (index - 1) % dimension;
				int indexY = (index - 1) / dimension;
				int distance = Math.abs(realX - indexX) + Math.abs(realY - indexY);
				manhattanDistance += distance;
			}	
		}
		jNode.setEstimatedValue(6 * s + 1 * misplacedTiles + 10 * manhattanDistance + jNode.getNodeDepth());
	}
}
