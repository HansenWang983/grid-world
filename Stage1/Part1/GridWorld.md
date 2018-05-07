# Part 1: Observing and Experimenting with GridWorld

## Step1 Running the Demo

Not always,it will adjust direction if it is facing the edges of grid or a rock.

Initially,the bug faces north.When it encounter a rock or a bound,it will adjust its direction around clockwise.In other word,it will rotate 45 degrees each times until the direction of next move does not have any rock or bound. 

It will rotate 45 degrees around clockwise.

It will leave its footprints,which represent by the flower object.The color of the flower is the same color as the bug at the beginnning.The color will get darker as the time goes by.

When the bug is facing the edge,either directly or in 45 degress position.It will rotate 45 degrees each time.
While in the other conditions,it will move to the next position.

It will rotate 45 degrees in clockwise until the next position is clear.

No,it doesn't.

Certainly,we can get the color and the location of it.
Besides,we can set the color,location and direction of it.
And we can remove it from the grid.

No. A rock stays in its location and does not appear to have any other behaviors when the step or run button is used. 

No,only one actor is occupied the grid one time.

## Step2 Exploring Actor State and Behavior

| Degrees | Compass Direction |
| :-----: | :---------------: |
|    0    |       North       |
|   45    |     Northeast     |
|   90    |       East        |
|   135   |     Southeast     |
|   180   |       South       |
|   225   |     Southwest     |
|   270   |       West        |
|   315   |     Northwest     |
|   360   |       North       |

Remain its initially direction and does not change its direction.
Any valid position is ok.
Any invalid position setting will cause a IllegalArgumentException to be thrown.

The setColor method.

It will first disappear and occupied by the rock,when the rock is moved to another valid position,the bug is no longer in the original position.



