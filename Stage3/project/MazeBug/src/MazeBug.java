// package info.gridworld.maze;
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
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;


	public boolean visited[][];
	ArrayList<Location> branch;
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
			visited[next.getRow()][next.getCol()]=true;
			move();
			//increase step count when move 
			stepCount++;
		} 
		else {
			if(branch.isEmpty()){
				branch = crossLocation.pop();
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
			}
			int random = (int)(Math.random() * (locs.size() - 1));
			last = getLocation();
			next = locs.get(random);

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
}
