# Brick-Breaker
The Project includes mainly 3 files namely
1.Bricks.java
2.Client.java
3.Main.java

Main.java

I have defined some integers and boolean that i will be using in my code.
boolean play describes whether the game has started or not.Initially,it is false because the game has not been started.
delay is passed as a parameter to the constructor of Timer which returns an object called time.
When we write time.start() in the Main constructor,it means that the timer is started and after every 30(delay) units of time,action performed function will be called.

Constructor
ballXposition and ballYposition defines the initial position of ball.
ballYdir and ballXdir defines how many units will the ball travel in x and y direction after every 'delay' time so that it appears moving.
Called the contructor of class bricks which returned an object 'br' by forming 4rows and 6 columns 
Then, i set few properties setTitle,setResizable,setVisible,setDefaultCloseOperation,setFocusable,setFocusTraversalKeysEnabled.
we start the timer.

paint
This function overrides the paint function present in the JFrame class.
In this function,we set the font and the color of the background,board,ball and borders.For bricks,i call the draw function which is defined in the bricks class and pass a Graphics2D object to it.

ActionPerformed 
This function is called after every 'delay' units of time.
It checks if during the play,if ball intersects with the board,it changes the y direction of the ball
if ballYpos>540,i.e. the ball has missed the board and it has sunk down which means the game gets over.(positions are measured from the top left corner )
Now,all unbroken bricks are checked one by one whether they intersect with the ball,br.map[i][j]>0 means that the brick exists.
If any brick intersects with the ball,score is increased,no. of bricks is decreased.If total bricks left becomes 0,game is stopped and a message appears whether we want to restart the game or not and if bricks are left , then after striking the brick,the direction of motion of ball is changed.
keyPressed function is overriden to add feature of moving the board with left and right arrow keys.




Bricks.java

constructor
a 2D array of row*column is created,row and column parameters are passed by the Main.All places in the array are filled with 1
function draw which takes an object of Graphics2D as parameter paints the bricks

Client.java

This creates an object of class Main so that the constructor of main is called and the game starts.
