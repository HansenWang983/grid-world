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

import java.util.ArrayList;
import java.awt.Color;
/**
 * A ChameleonKid changes its color to the color of one of 
 * the actors immediately in front or behind. 
 * If there is no actor in either of these locations, 
 * then the ChameleonKid darkens like the modified ChameleonCritter.
 */
public class ChameleonKid extends ChameleonCritter
{
    /**
     * Randomly selects a neighbor and changes this critter's color to be the
     * same as that neighbor's. If there are no neighbors, no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0){
        // the list of actors to process is empty, the color 
        //of the ChameleonKid will darken (like a flower).
            Color col=getColor();
            int red = (int) (col.getRed() * (1 - 0.2));
            int green = (int) (col.getGreen() * (1 - 0.2));
            int blue = (int) (col.getBlue() * (1 - 0.2));
            setColor(new Color(red, green, blue));
        }
        else{
            int r = (int) (Math.random() * n);
            Actor other = actors.get(r);
            setColor(other.getColor());
        }
    }

    /**
     * Turns towards the new location as it moves.
     */
    public void makeMove(Location loc)
    {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }


    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_CIRCLE };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null){
                actors.add(a);//get the front or behind of it
            }
        }

        return actors;
    }

     public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();//get the current locations 
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d); //get the left or right locations
            if (gr.isValid(neighborLoc)){
                locs.add(neighborLoc);
            }
        }
        return locs;
    }    
}
