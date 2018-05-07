Set 7 The source code for the Critter class is in the critters directory

1. What methods are implemented in Critter?

   act，getActors， processActors， getMoveLocations，selectMoveLocation，makeMove。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 31，38,56,7188,104,125
   public class Critter extends Actor
   {
   	public void act()；
   	public ArrayList<Actor> getActors()；
   	public void processActors(ArrayList<Actor> actors)；
   	public ArrayList<Location> getMoveLocations()；
   	public Location selectMoveLocation(ArrayList<Location> locs)；
   	public void makeMove(Location loc)；
   }
   ```

2. What are the five basic actions common to all critters when they act?

   getActors， processActors， getMoveLocations，selectMoveLocation，makeMove。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 38-47
   public void act()
   {
       if (getGrid() == null)
       return;
       ArrayList<Actor> actors = getActors();
       processActors(actors);
       ArrayList<Location> moveLocs = getMoveLocations();//returns all empty adjacent locations.
       Location loc = selectMoveLocation(moveLocs);//selects an empty location at random
       makeMove(loc);
   }
   ```

3. Should subclasses of Critter override the getActors method? Explain.

   是的。因为如果Critter的子类如果从不同的位置，而不是近邻的位置获得Actors，则必须要重写此函数。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 56-59
   public ArrayList<Actor> getActors()
   {
   	return getGrid().getNeighbors(getLocation());
   }
   ```

4. Describe the way that a critter could process actors.

   可以像ChameleonCritter一样使其变成和周围Actor一样的颜色，或者像CrabCritter一样吃掉周围的Actor（除了Rock和非Critter类的对象）。

   ```java
   // @file: grid world/GridWorldCode/projects/Critter/ChameleonCritter.java
   // @line: 36-53
   public void processActors(ArrayList<Actor> actors)
   {
       ...
       else{
   		int r = (int) (Math.random() * n);
   		Actor other = actors.get(r);
   		setColor(other.getColor());
   	}
   }

   // @file: info/gridworld/actor/Critter.java
   // @line: 71-78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

5. What three methods must be invoked to make a critter move? Explain each of these methods.

   getMoveLocations，selectMoveLocation，makeMove。

   首先通过getMoveLocations获得Critter周围的空位置，然后通过selectMoveLocation随机选择其中一个位置。如果没有空余位置，则返回自己的位置，然后传递给makeMove进行移动。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 88-91
   public ArrayList<Location> getMoveLocations()
   {
   	return getGrid().getEmptyAdjacentLocations(getLocation());
   }

   // @file: info/gridworld/actor/Critter.java
   // @line: 104-111
   public Location selectMoveLocation(ArrayList<Location> locs)
   {
       int n = locs.size();
       if (n == 0)
           return getLocation();
       int r = (int) (Math.random() * n);
       return locs.get(r);
   }

   // @file: info/gridworld/actor/Critter.java
   // @line: 125-131
   public void makeMove(Location loc)
   {
       if (loc == null)
           removeSelfFromGrid();
       else
           moveTo(loc);
   }
   ```

6. Why is there no Critter constructor?

   Critter继承了Actor，Actor有缺省构造函数，Java会为为声明构造函数的类添加缺省构造函数。然后Critter的缺省构造函数会调用基类的缺省构造函数。通过Actor的构造函数，Critter会创建一个蓝色且方向为北的对象。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 31
   public class Critter extends Actor
   ```

   ​





Set 8 The source code for the ChameleonCritter class is in the critters directory

1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

   因为act调用了五个函数，分别为getActors， processActors， getMoveLocations，selectMoveLocation和makeMove。Chameleon重写了processActors和makeMove，所以Chameleon的行为和Crtitter的行为不同。Critter会在processActors中移掉周围的Actor（不包括Rock和Critter），在makeMove中不改变方向直接移动，而Chameleon则变成和周围一样的颜色，并且在makeMove中先朝向将要移动的位置，然后再移动。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 71-78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   // @file: info/gridworld/actor/Critter.java
   // @line: 125-131
   public void makeMove(Location loc)
   {
       if (loc == null)
           removeSelfFromGrid();
       else
           moveTo(loc);
   }
   ```

2. Why does the makeMove method of ChameleonCritter call super.makeMove?

   因为它首先改变它的方向，使其朝向将要移动的位置，然后再同Critter一样移动，所以调用super.makeMove()。

   ```java
   // @file: grid world/GridWorldCode/projects/Critter/ChameleonCritter.java
   // @line: 59-63
   public void makeMove(Location loc)
   {
       setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
   }
   ```

3. How would you make the ChameleonCritter drop flowers in its old location when it moves?

   记录当前Chameleon的位置，当其移动后，将花放上去，如果不移动，则不放花。

   ```java
   public void makeMove(Location loc) {
       Location old = getLocation(); 
       setDirection(getLocation().getDirectionToward(loc)); 		
       super.makeMove(loc); 
       if(!old.equals(loc)){
   		Flower flo = new Flower(getColor());
      	 	flo.putSelfInGrid(getGrid(), oldLoc); 
   	}
   }
   ```

4. Why doesn't ChameleonCritter override the getActors method?

   因为它获得Actors的方式和Critter相同，不需要定义新的行为。

5. Which class contains the getLocation method?

   Actor。

   ```java
   // @file: info/gridworld/actor/Actor.java:
   // @line: 69-72
   public int getDirection()
   {
   	return direction;
   }
   ```

6. How can a Critter access its own grid?

   继承来自Actor的getGrid函数。

   ```java
   // @file: info/gridworld/actor/Actor.java:
   // @line: 92-95
   public Grid<Actor> getGrid()
   {
   	return grid;
   }
   ```

   ​







Set 9 The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.

1. Why doesn't CrabCritter override the processActors method?

   因为它处理Actors的方式和Critter相同，都是吃掉周围的Actor，不需要定义新的行为。

2. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

   CrabCritter只会获得正前方和左右两边斜前方的Actor。

   ```java
   // @file: grid world/GridWorldCode/projects/Critter/CrabCritter.java
   // @line: 44-55
   public ArrayList<Actor> getActors()
   {
       ArrayList<Actor> actors = new ArrayList<Actor>();
       int[] dirs ={ Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
       for (Location loc : getLocationsInDirections(dirs)){
           Actor a = getGrid().get(loc);
           if (a != null)
               actors.add(a);//get the front,right-front,or the left-front of it
       }
       return actors;
   }
   ```

3. Why is the getLocationsInDirections method used in CrabCritter?

   该函数接受一个方向数组，包括了可以获得Actor的方向。然后返回此Critter在这些方向上合法的近邻位置。

   ```java
   // @file: grid world/GridWorldCode/projects/Critter/CrabCritter.java
   // @line: 101-112
   public ArrayList<Location> getLocationsInDirections(int[] directions)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
       Grid gr = getGrid();
       Location loc = getLocation();//get the current locations 
       for (int d : directions){
           Location neighborLoc = loc.getAdjacentLocation(getDirection() + d); 
           if (gr.isValid(neighborLoc))
               locs.add(neighborLoc);
       }
       return locs;
   }    
   ```

4. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

   (4,3)，(4,4)和(4,5)

5. What are the similarities and differences between the movements of a CrabCritter and a Critter?

   相同点：随机选择可以移动的位置，且在移动过程中不改变方向。

   不同点：CrabCritter只会横向移动，且在不能移动的时候转向，Critter则会在8个近邻位置随机选择，在不能移动的时候不转向。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 125-131
   public void makeMove(Location loc){
       if (loc == null)
       removeSelfFromGrid();
       else
       moveTo(loc);
   }

   // @file: grid world/GridWorldCode/projects/Critter/CrabCritter.java
   // @line: 77-90
   public void makeMove(Location loc){
   	...
           if (r < 0.5)
           	angle = Location.LEFT;
           else
           	angle = Location.RIGHT;
           setDirection(getDirection() + angle);
       ...
   }
   ```

6. How does a CrabCritter determine when it turns instead of moving?

   如果传入makeMove函数的位置是当前CrabCritter自己的位置，则其转向不移动。

   ```java
   // @file: grid world/GridWorldCode/projects/Critter/CrabCritter.java
   // @line: 77-90
   public void makeMove(Location loc){
   	if (loc.equals(getLocation())){
           ...
           setDirection(getDirection() + angle);
       	}
       else
       	super.makeMove(loc);
   }
   ```

7. Why don't the CrabCritter objects eat each other?

   继承Critter的processActor，它只会移掉周围不是Rock和Critter的物体，所以不会吃掉同类。

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 71-78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ```

   ​