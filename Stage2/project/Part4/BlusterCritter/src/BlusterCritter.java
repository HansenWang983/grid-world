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
 * A <code>BlusterCritter</code> looks at a limited 
 * set of neighbors when it eats and moves.
 * <br />
 */
public class BlusterCritter extends Critter {
    
    private int courage;
    
    /**
     * Construct a BlusterCritter with a c courage
     */
    public BlusterCritter (int c) {
        super();
        courage = c;
    }

    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy grid locations within two steps of its current location.
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        Location loc = getLocation();
        for (int row = loc.getRow() - 2; row <= loc.getRow() + 2; row++) {
            for (int col = loc.getCol() - 2; col <= loc.getCol() + 2; col++) {
                if (getGrid().isValid(new Location(row, col))) {
                    Actor temp = getGrid().get(new Location(row, col));
                    if (temp != null && temp != this) {
                        actors.add(temp);
                    }
                }
            }
        }
        return actors;
    }

    /**
     * Process the actors. Implement to count the number of critters
     * that occupy grid location within two stpes of this critter's
     * location. If number is fewer than courage, the critter's
     * color brighten, otherwise, darken
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
        int count = 0;
        for (Actor temp : actors) {
            if (temp instanceof Critter) {
                count++;
            }
        }
        if (count < courage) {
            changeColor(2);
        } else {
            changeColor(-2);
        }
    }

    /**
     * Change the color of the BlusterCritter
     * @param factor the color change factor
     */
    private void changeColor(int factor) {
        Color c = getColor();
        int[] color = { c.getRed(), c.getGreen(), c.getBlue()};
        for (int i = 0; i < 3; ++i) {
            if (color[i] + factor <= 255 && color[i] + factor >= 0) {
                color[i] += factor;
            }
        }
        setColor(new Color(color[0], color[1], color[2]));
    }
}