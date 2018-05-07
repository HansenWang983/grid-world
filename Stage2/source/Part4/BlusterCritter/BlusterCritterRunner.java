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

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains 
 * BlusterCritterRunner critters. 
 */
public class BlusterCritterRunner {
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        for (int i = 2; i <= 4; ++i) {
            for (int j = 3; j <= 6; ++j) {
                if (!((i == 3 && j == 4) || (i == 3 && j == 6))) {
                    world.add(new Location(i, j), new Rock());
                }
            }
        }
        world.add(new Location(3, 4), new BlusterCritter(0));
        world.add(new Location(3, 6), new BlusterCritter(3));
        world.show();
    }
}