两个Test

第一个Test测试一系列跳的动作，忽略障碍或者出界的情况。

```java
Jumper a = new Jumper(Color.RED);

world.add(new Location(2,0), a);
a.act();
assertEquals(0, a.getLocation().getRow());
assertEquals(0, a.getLocation().getCol());
assertEquals(Location.NORTH, a.getDirection());
```

第二个Test测试特殊情况

1.前方一格为一个Jumper，前方两格为空，则跳。

```java
world.add(new Location(3,3),j);
world.add(new Location(2,3),r);
j.act();
assertEquals(1,j.getLocation().getRow());
assertEquals(3,j.getLocation().getCol());
assertEquals(Location.NORTH,j.getDirection());

```

2.前方两格为一个障碍，则转向。

```java
j.moveTo(new Location(5,5));
r.moveTo(new Location(3,5));
j.act();
assertEquals(4,j.getLocation().getRow());
assertEquals(5,j.getLocation().getCol());
assertEquals(Location.NORTH,j.getDirection());
```

3.两个Jumper面对面，先跳的则移动一格

```java
j.moveTo(new Location(7,7));
j2.moveTo(new Location(5,7));
j2.setDirection(Location.HALF_CIRCLE);
j2.act();
j.act();
assertEquals(6,j2.getLocation().getRow());
assertEquals(7,j2.getLocation().getCol());
assertEquals(Location.SOUTH,j2.getDirection());
assertEquals(5,j.getLocation().getRow());
assertEquals(7,j.getLocation().getCol());
assertEquals(Location.NORTH,j.getDirection());
```

4.前面两格为花，则跳。
```java
 //world.add(new Location(3,3),j);
            j.moveTo(new Location(3,3));
            world.add(new Location(2,3),f);
            //f.moveTo(new Location(2,3));
            j.act();
            assertEquals(1,j.getLocation().getRow());
            assertEquals(3,j.getLocation().getCol());
            assertEquals(Location.NORTH,j.getDirection());
```
