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
 * A <code>SparseGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseGrid2<E> extends AbstractGrid<E>{

    private HashMap<Location, E> occupantMap;
    private int row, col;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid2
     * @param cols number of columns in SparseBoundedGrid2
     */
    public SparseGrid2(int r,int c){
		row=r;
		col=c;
        occupantMap = new HashMap<Location, E>();
    }

    public int getNumRows(){
        return row;
    }

    public int getNumCols(){
        return col;
    }

    public boolean isValid(Location location){
        return 0 <= location.getRow() && location.getRow() < getNumRows()
                && 0 <= location.getCol() && location.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
        return a;
    }

    public E get(Location location)
    {
        if (!isValid(location)){
            throw new IllegalArgumentException("Location " + location
                    + " is not valid");
        }
        if (location == null){
            throw new NullPointerException("loc == null");
        }
        return occupantMap.get(location);
    }

    public E put(Location location, E obj)
    {
        if (!isValid(location)){
            throw new IllegalArgumentException("Location " + location
                    + " is not valid");
        }
        if (location == null){
            throw new NullPointerException("loc == null");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }
        return occupantMap.put(location, obj);
    }

    public E remove(Location location)
    {
        if (!isValid(location)){
            throw new IllegalArgumentException("Location " + location
                    + " is not valid");
        }
        if (location == null){
            throw new NullPointerException("loc == null");
        }
        return occupantMap.remove(location);
    }
}