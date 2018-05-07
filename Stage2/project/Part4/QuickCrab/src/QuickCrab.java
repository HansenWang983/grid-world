/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
/**
 * A QuickCrab processes actors the same way a QuickCrab does. 
 * A QuickCrab moves to one of the two locations, randomly selected, 
 * that are two spaces to its right or left, if that location and the 
 * intervening location are both empty. Otherwise, a QuickCrab moves 
 * like a QuickCrab.
 */
public class QuickCrab extends CrabCritter{

    public QuickCrab(){
        setColor(Color.RED);
    }

    /**
     * A crab gets the actors in the three locations immediately in front, to its
     * front-right and to its front-left
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors(){
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs)){
            Actor a = getGrid().get(loc);
            if (a != null){
                actors.add(a);//get the front,right-front,or the left-front of it
            }
        }

        return actors;
    }

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations(){
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location left = getLocation().getAdjacentLocation(getDirection() + Location.LEFT);
        if (gr.isValid(left) && gr.get(left) == null){
            int[] nextLeft = { Location.LEFT };
            for (Location l : getLocationsInDirections(left,nextLeft)){
                if (getGrid().get(l) == null){
                    locs.add(l);
                }
            }
        }
        Location right = getLocation().getAdjacentLocation(getDirection() + Location.RIGHT);
        if (gr.isValid(right) && gr.get(right) == null){
            int[] nextRight = { Location.RIGHT };
            for (Location l : getLocationsInDirections(right,nextRight)){
                if (getGrid().get(l) == null){
                    locs.add(l);
                }
            }
        }
        if(locs.size()==0)
         return super.getMoveLocations();

        return locs;
    }
    
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param current,a location in the grid
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(Location l,int []directions){
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid(); 
    
        for (int d : directions)
        {
            Location neighborLoc = l.getAdjacentLocation(getDirection() + d); //get the left or right locations
            if (gr.isValid(neighborLoc)){
                locs.add(neighborLoc);
            }
        }
        return locs;
    }    
}
