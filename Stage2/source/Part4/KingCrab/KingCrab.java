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
 * A KingCrab gets the actors to be processed in the same way 
 * a CrabCritter does. A KingCrab causes each actor that it 
 * processes to move one location further away from the KingCrab. 
 * If the actor cannot move away, the KingCrab removes it from the 
 * grid. When the KingCrab has completed processing the actors, 
 * it moves like a CrabCritter.
 */
public class KingCrab extends CrabCritter
{
    public void processActors(ArrayList<Actor> actors){
        Grid gr = getGrid();
        Location loc=getLocation();

        for(Actor a : actors){
            if(!(a instanceof Critter)){
                //move the actor away along the direction of the carb towards.
                Location l = a.getLocation().getAdjacentLocation(loc.getDirectionToward(a.getLocation()));
                if(gr.isValid(l)){
                    a.moveTo(l);
                }
                else{
                    a.removeSelfFromGrid();//eaten by the crab
                }
            }
        }

    }
}
