import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug2</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;//记录下一步要行走到的位置
	public Location last;//记录上一步的位置，便于在走到死路尽头时返回
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();//记录树的节点的栈
	public Integer stepCount = 0;//记录本迷宫走到出口所用的步数
	public boolean hasShown = false;//final message has been shown


	public boolean visited[][];//访问矩阵
	ArrayList<Location> branch;//栈顶节点和已经访问的路径节点
	int []weights;//左右上下的权值
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);

		visited = new boolean[100][100];
		for(int i=0;i<100;i++){
			for(int j=0;j<100;j++){
				visited[i][j]=false;
			}
		}

		Location loc = getLocation();
		branch = new ArrayList<Location>();
		branch.add(loc);

		weights = new int[4];
		for(int i=0;i<4;i++){
			weights[i]=1;
		}
	}

	/**
	 * Moves to the next location of the square.
	 * 虫子行动函数，每走一步会加一点步数，找到出口时显示步数
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			visited[next.getRow()][next.getCol()]=true;//访问下一个节点
			move();
			//increase step count when move 
			stepCount++;
		} 
		else {//回溯
			if(branch.isEmpty()){
				branch = crossLocation.pop();
				Location loc=branch.get(branch.size()-1);
				int dir=getLocation().getDirectionToward(loc);
				if(dir==0){
					weights[2]--;
				}
				else if(dir==90){
					weights[1]--;
				}
				else if(dir==180){
					weights[3]--;
				}
				else if(dir==270){
					weights[0]--;
				}
			}
			next = branch.remove(branch.size()-1);
			move();
			stepCount++;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null){
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		
		int[] dirs ={ Location.AHEAD, Location.LEFT, Location.RIGHT, Location.HALF_CIRCLE };

		for(int d:dirs){
			Location neighbor= loc.getAdjacentLocation(getDirection() + d);
			if(gr.isValid(neighbor)){
				Actor a = gr.get(neighbor);
				if((a==null||a instanceof Flower)&&!visited[neighbor.getRow()][neighbor.getCol()]){
					valid.add(neighbor);
				}
				else if(a instanceof Rock){
					if(Color.RED.equals(a.getColor())){
						isEnd = true;
					} 
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> locs=getValid(getLocation());
		if(locs.isEmpty()){
			return false;
		}
		else{
			branch.add(getLocation());
			if(locs.size()>1){
				crossLocation.push(branch);
				branch = new ArrayList<Location>();
				next = bestDirection(locs);
			}
			else{
				next=locs.get(0);
			}
			last = getLocation();
		}
		return true;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null){
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else{
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	public Location bestDirection(ArrayList<Location> locs){
		int dir=0;
		int leftD=0,rightD=0,aheadD=0,behindD=0;
		for(Location loc:locs){
			dir=getLocation().getDirectionToward(loc);
			if(dir==0){
				aheadD=weights[2];
			}
			else if(dir==90){
				rightD=weights[1];
			}
			else if(dir==180){
				behindD=weights[3];
			}
			else if(dir==270){
				leftD=weights[0];
			}
		}
		int random= 1 + (int)(Math.random() * (leftD + rightD + aheadD + behindD));
		//范围为1～四个方向权值总和+1
		if (random <= leftD) {
            dir = 270;
            weights[0]++;
         } else if (random <= (leftD + rightD)) {
            dir = 90;
            weights[1]++;
         } else if (random <= (leftD + rightD + aheadD)) {
			dir = 0;
			weights[2]++;
         } else {
			dir =180;
			weights[3]++;
         }
         Location bestloc=null;
         for(Location loc:locs){
         	if(dir==getLocation().getDirectionToward(loc)){
         		bestloc=loc;
         	}
         }
         return bestloc;
	}
}
