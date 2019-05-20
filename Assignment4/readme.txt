Name: Yash Avlani
UTA ID: 1001670008

Programming language: Java 

======>>>>Code Structure:

Class GameBoard:
It creates the gameboard structure for the game and displays it after every move and present it before every move so a user know where to play the next move.


Class maxconnect4 :
This class calculates and ask for the next move for human player to play by asking the column number and it automatically finds the best optimal route for computer to play and plays that


Class AiPlayer :
This class consist find the best play function. 

Class minimax :
This class include the method for finding min and max and contains utility function in it.


======>>>>LIST OF FUNCTION & ROLE

int maxium_value(GameBoard state, int alpha, int beta, int depthLevel)
This function tries to calculate the maximum possible value for a counter move.

public int findBestPlay(GameBoard currentGame, int depthLevel )
This function initializes the value of alpha and beta and then always find the best move for a player.

int minimum_value(GameBoard state,int alpha,int beta, int depthLevel)
It calculates the minimum value for a counter move.

public int utility(GameBoard currentGame)
It calculates the utility value.

public int evaluate(GameBoard currentGame)
It calculates the score.

get_probable_board(GameBoard state)
It calclates all the possible moves for a player.



=====>>>>Compilation Instructions:

javac GameBoard.java
javac AiPlayer.java
javac maxconnect4.java
javac minimax.java

======>>>>Execution Instruction:

Interactive mode: java maxconnect4 interactive input1.txt computer-next 2
One move mode: java maxconnect4 one-move input2.txt output.txt 2

To calculate runtime: time java maxconnect4 one-move input2.txt output.txt 2

Reffered links:

https://www.mkyong.com/java/how-to-read-input-from-console-java/

https://www.mkyong.com/java/how-to-loop-arraylist-in-java/