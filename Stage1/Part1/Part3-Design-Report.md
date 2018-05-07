**1.Inception: clarify the details of the problem:**

a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

**先检查能否能Jump，如果将要跳入的格子是花，则可以，如果是石头，则不可以，继续判断能否移动一格。**

b. What will a jumper do if the location two cells in front of the jumper is out of the grid?

**检查可不可以移动一格，如果可以则移动，不可以则转向**。

c. What will a jumper do if it is facing an edge of the grid?

**转向**。

d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

**转向。**

e. What will a jumper do if it encounters another jumper in its path?

**如果它先执行跳的判断，则只能移动一格，如果遇到的那个Jumper先跳到不影响第一个Jumper 的格子，则第一个Jumper也能继续跳。如果遇到的那个Jumper不能跳或移动，则第一个Jumper不跳。**

f. Are there any other tests the jumper needs to make?

**先判断跳，不能跳再判断移动，不能移动则转向。**