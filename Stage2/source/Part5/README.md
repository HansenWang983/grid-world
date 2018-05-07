# 1.SparseBoundedGrid

The “sparse array” is an array list of linked lists. Each linked list entry holds both a grid occupant and a column index. Each entry in the array list is a linked list or is null if that row is empty.

## 1.第一种用邻接链表存储

```java
private ArrayList<LinkedList<OccupantInCol>> occupantArray;
```

with a helper class:

```java
public class OccupantInCol {
    private Object occupant;
    private int col;
    ...
}
```

## 2.第二种用HashMap存储

```java
private HashMap<Location, E> occupantMap;
```

## 3.第三种用TreeMap存储

```java
private Map<Location, E> occupantMap;
```

# 2.UnboundedGrid

The constructor allocates a 16 x 16 array. When a call is made to the put method with a row or column index that is outside the current array bounds, double both array bounds until they are large enough, construct a new square array with those bounds, and place the existing occupants into the new array. 

初始为16x16的object矩阵

```java
private Object[][] occupantArray; 
```

返回的行和列都为-1

```java
public int getNumRows(){
  return -1;
}

public int getNumCols(){
  return -1;
}

public boolean isValid(Location loc){
  return 0 <= loc.getRow() && 0 <= loc.getCol();
}
```

改写put函数，当obj的添加超过边界时，扩充矩阵

```java
while (row > newSize - 1 || col > newSize - 1){
	newSize *= 2;
}

if (newSize != Length){
	extend(newSize);
}
```

扩充函数

```java
public void extend(int newSize) {
  Object[][] newoccupantArray = new Object[newSize][newSize];

  //copy the old to the new
  for (int i = 0; i < Length; i++) {
  	System.arraycopy(occupantArray[i], 0, newoccupantArray[i], 0, Length);
  }
  occupantArray = newoccupantArray;
  Length = newSize;
}
```

# 3.GUI

SparseGridRunner.java

```java
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
/**
 * This class runs a world with additional grid choices.
 */
public class SparseGridRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("SparseBoundedGrid3");
        world.addGridClass("UnboundedGrid2");
        world.add(new Location(2, 2), new Critter());
        world.show();
    }
}
```

