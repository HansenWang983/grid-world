/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.*;//contains ArrayList and LinkedList


/**
 * A <code>SparseGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */

public class SparseGrid<E> extends AbstractGrid<E>{
	//use the second method with a helper class LinkedList
	private ArrayList<LinkedList<OccupantInCol>> occupantArray;
	private int row,col;

	/**
	* Constructs an empty bounded grid with the given dimensions.
	* (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
	* @param rows number of rows in SparseGrid
	* @param cols number of columns in SparseGrid
	*/

	public SparseGrid(int r,int c){
		if (r <= 0){
            throw new IllegalArgumentException("rows <= 0");
		}
        if (c <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
		row=r;
		col=c;
		occupantArray = new ArrayList<LinkedList<OccupantInCol>>();

		for(int i=0;i<r;i++){
			LinkedList<OccupantInCol> l= new LinkedList<OccupantInCol>();
			occupantArray.add(l);
		}
	}


	public int getNumRows(){
		return row;
	}

	public int getNumCols(){
		return col;
	}

	public boolean isValid(Location l){
		return 0 <= l.getRow() && l.getRow() < getNumRows()
            	&& 0 <= l.getCol() && l.getCol() < getNumCols();
	}

	public ArrayList<Location> getOccupiedLocations(){
		ArrayList<Location> theLocations = new ArrayList<Location>();
		 // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
        	LinkedList<OccupantInCol> list = occupantArray.get(r);
            for(int i=0;i<list.size();i++){
            	OccupantInCol obj = list.get(i);
            	//Do not need to judge the location whether is null or not.
            	theLocations.add(new Location(r,obj.getCol()));
            }
        }
        return theLocations;
	}

	public E get(Location location){
		if (!isValid(location))
            throw new IllegalArgumentException("Location " + location
                    + " is not valid");
        if (location == null)
            throw new NullPointerException("location == null");
		LinkedList<OccupantInCol> list = occupantArray.get(location.getRow());
		for(int i=0;i<list.size();i++){
			OccupantInCol obj = list.get(i);
			if(obj.getCol()==location.getCol())
				return (E)obj.getObject();
		}
		return null;
	}

	public E put(Location location,E obj){
		if (!isValid(location)){
            throw new IllegalArgumentException("Location " + location
                    + " is not valid");
		}
        if (location == null){
            throw new NullPointerException("location == null");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }
		E old =get(location);
		OccupantInCol object;
		LinkedList<OccupantInCol> list = occupantArray.get(location.getRow());
		for(int i=0;i<list.size();i++){
			object = list.get(i);
			if(object.getCol() == location.getCol()){
				object.setObject(obj);
				return old;
			}
		}
		object = new OccupantInCol(obj,location.getCol());
		list.add(object);
		return old;
	}

	public E remove(Location location){
		if (!isValid(location)){
            throw new IllegalArgumentException("Location " + location
                    + " is not valid");
		}
        if (location == null){
            throw new NullPointerException("location == null");
        }
		E old= get(location);
		LinkedList<OccupantInCol> list = occupantArray.get(location.getRow());
		for(int i=0;i<list.size();i++){
			OccupantInCol object = list.get(i);
			if(object.getCol()==location.getCol()){
				list.remove(i);
			}
		}
		return old;
	}
}

