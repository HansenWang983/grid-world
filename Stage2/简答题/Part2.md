1. What is the role of the instance variable sideLength?

   用于初始化BoxBug，为其一个数据成员，代表在同一个方向移动的最大步长。

   ```java
     // @file: grid world/GridWorldCode/projects/boxBug/BoxBug.java
     // @line: 45-47
      45:         if (steps < sideLength && canMove())
      46          {
      47              move();
   ```

2. What is the role of the instance variable steps?

   BoxBug的另一个数据成员，代表在当前方向上移动的步数，如果转向，则置0。

   ```java
     // @file: grid world/GridWorldCode/projects/boxBug/BoxBug.java
     // @line: 45-47,50-55
      45:         if (steps < sideLength && canMove())
      46          {
      47              move();
   ```

3. Why is the turn method called twice when steps becomes equal to sideLength?

   BoxBug的特点就是当sideLength等于steps时转向90度，或者不能移动时也转向90度，而每个turn()函数只转向45度，所以要调用两次。

   ```java
     // @file: grid world/GridWorldCode/projects/boxBug/BoxBug.java
     // @line: 50-55
      50          else{
      ..
      52              turn();
      53              turn();
      54:             steps = 0;
      55          }
   ```

4. Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

   继承了父类Bug的move()函数。

   ```java
   // @file: grid world/GridWorldCode/framework/info/gridworld/actor/Bug.java
   // @line: 71-73

    71:     public void move()
    72      {
    73          Grid<Actor> gr = getGrid();
    ..
   ```

5. After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

   是的，方形的大小和sideLength有关，当它被初始化后就不会改变。

   ```java
   // @file: grid world/GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 34-38
   34:     public BoxBug(int length)
   35      {
   36          steps = 0;  
   37    	    sideLength = length;
   38      }
   ```

6. Can the path a BoxBug travels ever change? Why or why not?

   如果有另一个Rock或者Bug对象在它前进方向的前面，它就会转向。如果为花，则不转。

   ```java
   // @file: grid world/GridWorldCode/projects/boxBug/BoxBug.java
   // @line: 45-47
   45:         if (steps < sideLength && canMove())
   46          {
   47              move();
       
   // @file: grid world/GridWorldCode/framework/info/gridworld/actor/Bug.java
   // @line: 71-73
   91：       public boolean canMove()
   92         {
   ...     
   100               Actor neighbor = gr.get(next);
   101               return (neighbor == null) || (neighbor instanceof Flower);
   ```

7. When will the value of steps be zero?

   其值与sideLength相等时，或者转向时。

   ```java
     // @file: grid world/GridWorldCode/projects/boxBug/BoxBug.java
     // @line: 45-47,50-55
      45:         if (steps < sideLength && canMove())
      46          {
      47              move();
      50          else{
      ..
      52              turn();
      53              turn();
      54:             steps = 0;
      55          }
   ```

   ​