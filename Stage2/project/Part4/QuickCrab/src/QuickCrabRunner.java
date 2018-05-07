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
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains QuickCrab. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrabRunner{

    static final int ONE=1,TWO=2,THREE=3,FOUR=4,FIVE=5,SIX=6,SEVEN=7,EIGHT=8;

    private QuickCrabRunner(){}

    public static void main(String[] args){
        ActorWorld world = new ActorWorld();
        world.add(new Location(SEVEN,FIVE), new Rock());
        world.add(new Location(FIVE,FOUR), new Rock());
        world.add(new Location(FIVE,SEVEN), new Rock());
        world.add(new Location(SEVEN,THREE), new Rock());
        world.add(new Location(SEVEN,EIGHT), new Flower());
        world.add(new Location(TWO,TWO), new Flower());
        world.add(new Location(THREE,FIVE), new Flower());
        world.add(new Location(THREE,EIGHT), new Flower());
        world.add(new Location(SIX,FIVE), new Bug());
        world.add(new Location(FIVE,THREE), new Bug());
        world.add(new Location(FOUR,FIVE), new QuickCrab());
        world.add(new Location(SIX,ONE), new QuickCrab());
        world.add(new Location(SEVEN,FOUR), new QuickCrab());
        world.show();
    }
}