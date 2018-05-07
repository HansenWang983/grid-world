**Set 10**

The source code for the AbstractGrid class is in Appendix D.

1. Where is the isValid method specified? Which classes provide an implementation of this method?

   在Grid接口中声明，在Bounded和Unbounded类中实现。

   ```java
   // @file: info/gridworld/grid/Grid.java
   // @line: 50
   boolean isValid(Location loc);
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 60-64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   // @file: info/gridworld/grid/UnboundedGrid.java
   // @line: 53-56
   public boolean isValid(Location loc)
   {
       return true;
   }
   ```

2. Which AbstractGrid methods call the isValid method? Why don't the other methods need to call it?

   getValidAdjacentLocations。因为当调用getNeighbors时，它首先调用 getOccupiedAdjacentLocations

   然后调用getValidAdjacentLocations，最后才是isValid。

   ```java
   // @file: info/gridworld/grid/AbstractGrid.java
   // @line: 36-49
   public ArrayList<Location> getValidAdjacentLocations(Location loc){
       ArrayList<Location> locs = new ArrayList<Location>();
       int d = Location.NORTH;
       for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
       {
           Location neighborLoc = loc.getAdjacentLocation(d);
           if (isValid(neighborLoc))
               locs.add(neighborLoc);
           d = d + Location.HALF_RIGHT;
       }
       return locs;
   }
   ```

3. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

   调用在Grid中的get和getOccupiedAdjacentLocations。AbstractGrid实现了getOccupiedAdjacentLocations，Bounded和Unbounded实现了get。

   ```java
   // @file: info/gridworld/grid/AbstractGrid.java
   // @line: 28-34 
   public ArrayList<E> getNeighbors(Location loc)
   {
       ArrayList<E> neighbors = new ArrayList<E>();
       for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
           neighbors.add(get(neighborLoc));
       return neighbors;
   }
   ```

4. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

   get会返回在格子中的对象。如果为空，则返回null，getEmptyAdjacentLocations则会根据获得的格子的是否为null判断位置是否为空。

   ```java
   // @file: info/gridworld/grid/AbstractGrid.java
   // @line: 51-60
   public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
       for (Location neighborLoc : getValidAdjacentLocations(loc))
       {
           if (get(neighborLoc) == null)
           	locs.add(neighborLoc);
       }
       return locs;
   }
   ```

5. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

   可能出现的合法相邻位置从最多的8个变为4个。变为当前位置的正东南西北。

   ```java
   // @file: info/gridworld/grid/AbstractGrid.java
   // @line: 36-49 
   public ArrayList<Location> getValidAdjacentLocations(Location loc){
       ...
       for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
       {
           Location neighborLoc = loc.getAdjacentLocation(d);
           if (isValid(neighborLoc))
               locs.add(neighborLoc);
           d = d + Location.HALF_RIGHT;
       }
       ...
   }
   ```

   ​







**Set 11**

The source code for the BoundedGrid class is in Appendix D.

1. What ensures that a grid has at least one valid location?

   BoundedGrid的构造函数会抛出IllegalArgumentException如果行数或列数小于0

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 39-46
   public BoundedGrid(int rows, int cols)
   {
       if (rows <= 0)
           throw new IllegalArgumentException("rows <= 0");
       if (cols <= 0)
           throw new IllegalArgumentException("cols <= 0");
       occupantArray = new Object[rows][cols];
   }
   ```

2. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

   occupantArray[0].length。getNumCols会返回occupantArray[0]即第一行的列数。BoundedGrid的构造函数确保每个对象都有行和列。

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 53-58
   public int getNumCols()
   {
       // Note: according to the constructor precondition, numRows() > 0, so
       // theGrid[0] is non-null.
       return occupantArray[0].length;
   }
   ```

3. What are the requirements for a Location to be valid in a BoundedGrid?

   行号和列号大于等于0并且小于Grid的行数和列数

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 60-64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ```

   ​

    In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

4. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

   ArrayList<Location>。遍历Grid的复杂度O(r * c)，插入ArrayList的复杂度O(1)。

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 66-83
   public ArrayList<Location> getOccupiedLocations()
   {
      	...
       for (int r = 0; r < getNumRows(); r++)
       {
           for (int c = 0; c < getNumCols(); c++)
           {
               // If there's an object at this location, put it in the array.
               Location loc = new Location(r, c);
               if (get(loc) != null)
                   theLocations.add(loc);
           }
       }
       ...
   }
   ```

5. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

   泛型E。参数为一个Location对象。复杂度为O(1)。

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 85-91
   public E get(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
   }
   ```

6. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

   传进来的Location对象非法，IllegalArgumentException会被抛出。或者要添加的对象为null， NullPointerException 会被抛出。复杂度为O(1)。

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 93-105
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                               + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
   ```

7. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

   泛型E（代表BoundedGrid对象的任意类型）。如果Locaiton对象为空，会返回null，并且不会抛出异常错误。复杂度为O(1)。

   ```java
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 107-117
   public E remove(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       // Remove the object from the grid.
       E r = get(loc);
       occupantArray[loc.getRow()][loc.getCol()] = null;
       return r;
   }
   ```

8. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

   是的。只有 getOccupiedLocations的复杂度为O(r*c),其他函数均为O(1)。但是BoundedGrid会存储位置为空的格子。

   ​







**Set 12** The source code for the UnboundedGrid class is in Appendix D.

1. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

   Location类需要实现hashCode和equals，当equals返回真时，两个相同位置的哈希值必须相同。TreeMap需要实现要Comparable的接口中的CompareTo。

   ```java
   // @file: info/gridworld/grid/Location.java
   // @line: 205-212
   public boolean equals(Object other)
   {
       if (!(other instanceof Location))
           return false;

       Location otherLoc = (Location) other;
       return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
   }
   // @file: info/gridworld/grid/Location.java
   // @line: 218-221
   public int hashCode()
   {
       return getRow() * 3737 + getCol();
   }
   // @file: info/gridworld/grid/Location.java
   // @line: 234-246
   public int compareTo(Object other)
   {
       Location otherLoc = (Location) other;
       if (getRow() < otherLoc.getRow())
           return -1;
       if (getRow() > otherLoc.getRow())
           return 1;
       if (getCol() < otherLoc.getCol())
           return -1;
       if (getCol() > otherLoc.getCol())
           return 1;
       return 0;
   }
   ```

2. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

   因为空位置同样会存储在UnBoundedGrid中的数据结构里，即null可以作为key存储在HahsMap中，所以其isValid函数总是返回真。但在Grid中空位置的对象是非法的，所以必须要在get, put和remove中检测Location对象是否为null。

   而在BoundedGrid中，数据结构为二维矩阵，在进行下一步操作时要调用getRow()和getCol()，所以要先调用isValid判断其位置合法性。

   ```java
   // @file: info/gridworld/grid/UnboundedGrid.java
   // @line: 66-71
   public E get(Location loc)
   {
       if (loc == null)
           throw new NullPointerException("loc == null");
       return occupantMap.get(loc);
   }
   // @file: info/gridworld/grid/BoundedGrid.java
   // @line: 85-91
   public E get(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
   }
   ```

   ​

3. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

   O(1)。用TreeMap的复杂度为O(log n)，n代表格子中被占据的位置数量。

4. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

   返回格子中被占据的位置的顺序会改变，因为在HashMap中，位置的哈希值(下标)是通过哈希函数计算的，而在TreeMap中，则是用中序遍历二叉平衡树的方式存储的。

   ```java
   // @file: gridworld/GridWorldCode/Part5/SparseGrid2.java
   // @line: 61-67
   public ArrayList<Location> getOccupiedLocations()
   {
       ArrayList<Location> a = new ArrayList<Location>();
       for (Location loc : occupantMap.keySet())
           a.add(loc);
       return a;
   }
   ```

5. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

   是的。getOccupiedLocations的复杂度会变为O(n)，n为格子中的物体数量。如果格子中的对象过多，则需要比较大的内存存放每个对象。因为每个Map值不仅要存放对象还要存放他们的位置。而二维矩阵则只存放对象。











**[Coding Exercises]**

2.Consider using a HashMap or TreeMap to implement the SparseBoundedGrid. How could you use the UnboundedGrid class to accomplish this task? Which methods of UnboundedGrid could be used without change?

getOccupiedLocations，get，put，remove不需要改变。

Fill in the following chart to compare the expected Big-Oh efficiencies for each implementation of the SparseBoundedGrid.

Let r = number of rows, c = number of columns, and n = number of occupied locations

| Methods                      | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version |
| ---------------------------- | ---------------------- | --------------------------------- | --------------- | --------------- |
| getNeighbors                 | O(c)                   | O(c)                              | O(1)            | O(log n)        |
| getEmptyAdjacentLocations    | O(c)                   | O(c)                              | O(1)            | O(log n)        |
| getOccupiedAdjacentLocations | O(c)                   | O(c)                              | O(1)            | O(log n)        |
| getOccupiedLocations         | O(r+n)                 | O(r+n)                            | O(n)            | O(n)            |
| get                          | O(c)                   | O(c)                              | O(1)            | O(log n)        |
| put                          | O(c)                   | O(c)                              | O(1)            | O(log n)        |
| remove                       | O(c)                   | O(c)                              | O(1)            | O(log n)        |

3.Consider an implementation of an unbounded grid in which all valid locations have non-negative row and column values. The constructor allocates a 16 x 16 array. When a call is made to the put method with a row or column index that is outside the current array bounds, double both array bounds until they are large enough, construct a new square array with those bounds, and place the existing occupants into the new array.

Implement the methods specified by the Grid interface using this data structure. What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

get的复杂度为O(1)，put在未进行扩展grid的时候复杂度为O(1)，在进行扩展后必须进行将旧的格子复制到新的格子中，复杂度为O(n*n)，n代表之前的行数和列数。

```java
// @file: gridworld/GridWorldCode/Part5/UnboundedGrid.java
// @line: 106-114
public E put(Location loc, E obj)
{
    ...
    while (row > newSize - 1 || col > newSize - 1){
        newSize *= 2;
    }
    if (newSize != currentSize){
        extendGrid(newSize);
    }
	...
}
// @file: gridworld/GridWorldCode/Part5/UnboundedGrid.java
// @line: 134-143
public void extendGrid(int newSize) {
    Object[][] newoccupantArray = new Object[newSize][newSize];
    for (int i = 0; i < currentSize; i++) {
        System.arraycopy(occupantArray[i], 0, newoccupantArray[i], 0, currentSize);
    }
    occupantArray = newoccupantArray;
    currentSize = newSize;
}
```

