import java.util.*;

/**@author James Spargo
 * This is the AiPlayer class.  It simulates a minimax player for the max
 * connect four game.
 * The constructor essentially does nothing.
 *
 *
 *
 */

public class AiPlayer
{

	/**
	 * The constructor essentially does nothing except instantiate an
	 * AiPlayer object.4
	 *
	 *
	 */
	public AiPlayer(int depthLevel)
	{
		// nothing to do here
	}
	/**
	 * @param currentGame The GameBoard object that is currently being used to
	 * play the game.
	 * @return an integer indicating which column the AiPlayer would like
	 * to play in.
	 */

	public int findBestPlay(GameBoard currentGame, int depth ) //Logic for alpha beta pruing
	{
		int alpha=Integer.MIN_VALUE; //initialise alpha with -infinity
		int beta=Integer.MAX_VALUE;  //initialise beta with +infinity
		int large_value = Integer.MIN_VALUE;//initialise large_value with +infinity
		int largest = -1;
		

		for (int i=0; i<7;i++) {
			if(currentGame.isValidPlay(i)) {
				GameBoard temp = new GameBoard(currentGame.getGameBoard());
				temp.playPiece(i);
				
				int nextV = minimax.findMin(temp,alpha,beta,depth-1);
				
				if( nextV > large_value){
					large_value = nextV;
					largest=i;
				}
			}

		}
		return largest;


	}
	
	
	public static ArrayList<GameBoard> getNextProbableBoard(GameBoard state) {
		ArrayList<GameBoard> childs = new ArrayList<GameBoard>();
		for (int i=0; i<7; i++) {

			if(state.isValidPlay(i)) {
				GameBoard temp = new GameBoard(state.getGameBoard());
				temp.playPiece(i);
				childs.add(temp);
			}
		}
		return childs;

	}

	/* start random play code
	Random randy = new Random();
	int playChoice = 99;

	playChoice = randy.nextInt( 7 );

	while(!currentGame.isValidPlay( playChoice ))
	    playChoice = randy.nextInt( 7 );

	 //end random play code*/

	//return playChoice;
}
