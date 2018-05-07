# MazeBug

## 走迷宫部分

1. 继承Bug类，使虫子的方向只有东南西北四个方向，在遇到红色石头时自动停止。

- 重写getValid函数，使其寻找的方向为东南西北

- 对每一个方向判断是否为空或者是花，是则为合法移动位置，不是则判断是否为红色的Rock，是则置isEnd为True，在下一次act函数里判断isEnd

  ​

2. 深度优先算法


- branch为一个ArrayList的节点，用于存放当前的位置，如果下一个要移动的合法位置大于1个，则将当前位置压入crossLocation栈。并新生成一个ArrayList，用于判断是否需要回退。
- 当没有合法位置可以进行移动时，判断branch是否为空，即上一步的合法位置是否为多个，为空则从crossLocation栈中弹出上一步的位置，并根据当前位置和上一步的位置判断上一步的选择方向进行权值更新。如果branch不为空，则将当前位置作为next即下一步位置再执行move函数。
- branch不重新生成的条件是每一步将要移动的合法位置有且仅有一个，才可以不断更新保证当前路径。



3.增加方向概率统计

- 在回退时判断方向，此方向的权值减1。
- 在选择方向时加入public Location bestDirection(ArrayList<Location> locs)函数，首先得到每个方向的权值然后选择最大的那个作为最佳方向。
- 关键函数

```java
public Location bestDirection(ArrayList<Location> locs)；
```

