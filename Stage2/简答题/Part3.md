Set 3 Assume the following statements when answering the following questions.

`Location loc1 = new Location(4, 3);` `Location loc2 = new Location(3, 4);`

1. How would you access the row value for loc1?

   ```java
   loc1.getRow();

   // @file: info/gridworld/grid/Location.java
   // @line: 28-31
   28: 	public class Location implements Comparable
   29    	{
   30    		private int row; // row location in grid
   31    		private int col; // column location in grid

   // @file: info/gridworld/grid/Location.java
   // @line: 110-113
   110:     public int getRow()
   111      {
   112          return row;
   113.     }
   ```

2. What is the value of b after the following statement is executed?

`boolean b = loc1.equals(loc2);`

false

```Java
// @file: info/gridworld/grid/Location.java
// @line: 205-212
public boolean equals(Object other)
{
    if (!(other instanceof Location))
        return false;

    Location otherLoc = (Location) other;
    return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
}
```

3. What is the value of loc3 after the following statement is executed?

`Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);`

(4,4)

```Java
// @file: info/gridworld/grid/Location.java
// @line: 131-169

public Location getAdjacentLocation(int direction)
{	
     ...
     int dc = 0;
     int dr = 0;
     ...
     else if (adjustedDirection == SOUTH)
          dr = 1;
     ...
     return new Location(getRow() + dr, getCol() + dc);
}
```

4. What is the value of dir after the following statement is executed?

`int dir = loc1.getDirectionToward(new Location(6, 5));`

135 (degrees)------Southeast

```java
// @file: info/gridworld/grid/Location.java
// @line: 178-195

public int getDirectionToward(Location target)
{
    int dx = target.getCol() - getCol();
    int dy = target.getRow() - getRow();
    int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
    int compassAngle = RIGHT - angle;
    compassAngle += HALF_RIGHT / 2;
    if (compassAngle < 0)
        compassAngle += FULL_CIRCLE;
    return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
}
```

5. How does the getAdjacentLocation method know which adjacent location to return?

它的参数表明了要找近邻位置的方向，然后函数根据该方向寻找合适的位置并返回。

```java
// @file: info/gridworld/grid/Location.java
// @line: 131-169
public Location getAdjacentLocation(int direction)
{
    int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
    if (adjustedDirection < 0)
        adjustedDirection += FULL_CIRCLE;
    adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
    int dc = 0;
    int dr = 0;
    return new Location(getRow() + dr, getCol() + dc);
}
```





Set 4

1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

gr代表一个在网格中的对象实例

gr.getOccupiedLocations().size()会返回网格中任意类型的对象总数

gr.getNumRows()*gr.getNumCols() - gr.getOccupiedLocations().size() 会返回空余的格子数量

```java
// @file: info/gridworld/grid/BoundedGrid.java:
// @line: 66-83
public ArrayList<Location> getOccupiedLocations(){
    ArrayList<Location> theLocations = new ArrayList<Location>();
    for (int r = 0; r < getNumRows(); r++){
        for (int c = 0; c < getNumCols(); c++){
            Location loc = new Location(r, c);
            if (get(loc) != null)
                theLocations.add(loc);
        }
    }
    return theLocations;
}

// @file: info/gridworld/grid/BoundedGrid.java:
// @line: 48-58
public int getNumRows(){
    return occupantArray.length;
}
public int getNumCols(){
    // Note: according to the constructor precondition, numRows() > 0, so
    // theGrid[0] is non-null.
    return occupantArray[0].length;
}
```

2. How can you check if location (10,10) is in a grid?


gr.isValid(new Location(10,10))

```java
// @file: info/gridworld/grid/BoundedGrid.java:
// @line: 60-64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
        && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

3. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

Grid是一个接口类，即只声明了函数，而函数的实现在其他类，如AbstractGrid，BoundedGrid，UnboundedGrid。而AbstractGrid是BoundedGrid和UnboundedGrid的抽象基类，只实现了部分Grid的接口，剩余的接口需要BoundedGrid和UnboundedGrid继承并实现。好处在于这两个子类可以重写不同的实现以实现不同的需求。

```java
// @file: info/gridworld/grid/Grid.java:
// @line: 29
public interface Grid<E>

// @file: info/gridworld/grid/AbstractGrid.java:
// @line: 26
public abstract class AbstractGrid<E> implements Grid<E>

// @file: info/gridworld/grid/BoundedGrid.java:
// @line: 29
public class BoundedGrid<E> extends AbstractGrid<E>

// @file: info/gridworld/grid/UnBoundedGrid.java:
// @line: 31
public class UnboundedGrid<E> extends AbstractGrid<E>
```

4. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

ArrayList即可以像数组一样用下标访问，也可以在初始化的时候像链表一样不用定义长度而是每次插入时自动添加，效率高。因为如果用数组，需要在初始化的时候每次计算一下当前格子中对象的数量，以便得到数组的长度，效率低。如果在初始化数组时可以事先得到要保存的总数，这样就可以得到数组的长度，这样效率就和ArrayList一样了。

```java
// @file: info/gridworld/grid/AbstractGrid.java:
// @line: 28，36，51，62
public ArrayList<E> getNeighbors(Location loc)
public ArrayList<Location> getValidAdjacentLocations(Location loc)
public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
```






Set 5

1. Name three properties of every actor.

   grid表示它在网格中的对象，location为其位置，direction为其方向，color为其颜色。


```java
// @file: info/gridworld/actor/Actor.java:
// @line: 29-34
public class Actor
{
    private Grid<Actor> grid;
    private Location location;
    private int direction;
    private Color color;
    ...
}
```

2. When an actor is constructed, what is its direction and color?

初始化颜色为蓝色，初始化方向为北

```java
// @file: info/gridworld/actor/Actor.java:
// @line: 39-45
public Actor()
{
    color = Color.BLUE;
    direction = Location.NORTH;
    grid = null;
    location = null;
}
```

3. Why do you think that the Actor class was created as a class instead of an interface?

类可以实例化对象并且实现成员函数，而接口只能声明函数。

4. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

不行。当执行old.putSelfInGrid(old.getGrid(),old.getLocation())时会报错 "This actor is already contained in a grid."

```java
// @file: info/gridworld/actor/Actor.java:
// @line: 115-127
public void putSelfInGrid(Grid<Actor> gr, Location loc){
    if (grid != null)
        throw new IllegalStateException(
        "This actor is already contained in a grid.");
    Actor actor = gr.get(loc);
    if (actor != null)
        actor.removeSelfFromGrid();
    gr.put(loc, this);
    grid = gr;
    location = loc;
}
```

不行。当执行b.removeSelfFromGrid();两次时会报错 "This actor is not contained in a grid."

```java
// @file: info/gridworld/actor/Actor.java:
// @line: 133-146
public void removeSelfFromGrid(){
    if (grid == null)
        throw new IllegalStateException(
        "This actor is not contained in a grid.");
    if (grid.get(location) != this)
        throw new IllegalStateException(
        "The grid contains a different actor at location "
        + location + ".");
    grid.remove(location);
    grid = null;
    location = null;
}
```

可以。



5. How can an actor turn 90 degrees to the right?

setDirection(getDirection() + Location.RIGHT) 或者 setDirection(getDirection() + 90);

```java
// @file: info/gridworld/actor/Actor.java:
// @line: 80-85
public void setDirection(int newDirection)
{
    direction = newDirection % Location.FULL_CIRCLE;
    if (direction < 0)
    direction += Location.FULL_CIRCLE;
}
```






Set 6

1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

   ```java
   // @file: info/gridworld/actor/Bug.java:
   // @line: 98-99
    if (!gr.isValid(next))
               return false;

   // @file: info/gridworld/grid/BoundedGrid.java:
   // @line: 60-64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

   ```java
   // @file: info/gridworld/actor/Bug.java:
   // @line: 100-101
   Actor neighbor = gr.get(next);
   return (neighbor == null) || (neighbor instanceof Flower);
   ```

3. Which methods of the Grid interface are invoked by the canMove method and why?

   isValid和get。isValid判断下一个位置是否越界，get则获得下一个要移动的位置的格子的对象，然后判断是否为空或者是可以被代替的对象，如花。

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java:
   // @line: 60-64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
       && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }

   // @file: info/gridworld/grid/BoundedGrid.java:
   // @line: 85-91
   public E get(Location loc)
   {
       if (!isValid(loc))
       throw new IllegalArgumentException("Location " + loc
       + " is not valid");
       return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
   }
   ```

4. Which method of the Location class is invoked by the canMove method and why?

   getAdjacentLocation。

   在当前Bug的方向上去获得相邻的位置。

   ```java
   // @file: info/gridworld/grid/Location.java
   // @line: 131-169
   public Location getAdjacentLocation(int direction)
       ...
   ```

5. Which methods inherited from the Actor class are invoked in the canMove method?

   getLocation, getDirection, getGrid。

   ```java
   // @file: info/gridworld/actor/Actor.java:
   // @line: 102-105
   public Location getLocation()
   {
   	return location;
   }

   // @file: info/gridworld/actor/Actor.java:
   // @line: 69-72
   public int getDirection()
   {
   	return direction;
   }

   // @file: info/gridworld/actor/Actor.java:
   // @line: 92-95
   public Grid<Actor> getGrid()
   {
   	return grid;
   }
   ```

6. What happens in the move method when the location immediately in front of the bug is out of the grid?

   Bug会将自己移除网格。

   ```java
   // @file: info/gridworld/actor/Bug.java:
   // @line: 77-81
   if (gr.isValid(next))
       moveTo(next);
   else
       removeSelfFromGrid();
   ```

7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

   是的，它保存了当前Bug的位置，当Bug移动后，在其旧位置即loc上会添加一朵花。

   ```java
   // @file: info/gridworld/actor/Bug.java:
   // @line: 82-83
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
   ```

8. Why do you think the flowers that are dropped by a bug have the same color as the bug?

   在花枯萎（褪色）之前，可以清楚地知道这朵花是那个Bug留下的。

   ```java
   // @file: info/gridworld/actor/Bug.java:
   // @line: 82-83
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);

   // @file: info/gridworld/actor/Actor.java:
   // @line: 51-54
   public Color getColor()
   {
       return color;
   }
   ```

9. When a bug removes itself from the grid, will it place a flower into its previous location?

   在Bug的move函数中就先移除旧位置上的自己然后添加花。

   ```java
   // @file: info/gridworld/actor/Bug.java:
   // @line: 78-83
   if (gr.isValid(next))
   	moveTo(next);
   else
       removeSelfFromGrid();
   Flower flower = new Flower(getColor());
   flower.putSelfInGrid(gr, loc);
   ```

10. Which statement(s) in the move method places the flower into the grid at the bug's previous location?

    ```java
    // @file: info/gridworld/actor/Bug.java:
    // @line: 82-83
     Flower flower = new Flower(getColor());
     flower.putSelfInGrid(gr, loc);
    ```

11. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    4次。

    ```java
    // @file: info/gridworld/actor/Bug.java:
    // @line: 62-65
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    ```

    ​