import java.io.*;
/**@author James Spargo
 * This class controls the game play for the Max Connect-Four game.
 * To compile the program, use the following command from the maxConnectFour directory:
 * javac *.java
 *
 * the usage to run the program is as follows:
 * ( again, from the maxConnectFour directory )
 *
 *  -- for interactive mode:
 * java MaxConnectFour interactive [ input_file ] [ computer-next / human-next ] [ search depth]
 *
 * -- for one move mode
 * java maxConnectFour.MaxConnectFour one-move [ input_file ] [ output_file ] [ search depth]
 *
 * description of arguments:
 *  [ input_file ]
 *  -- the path and filename of the input file for the game
 *
 *  [ computer-next / human-next ]
 *  -- the entity to make the next move. either computer or human. can be abbreviated to either C or H. This is only used in interactive mode
 *
 *  [ output_file ]
 *  -- the path and filename of the output file for the game.  this is only used in one-move mode
 *
 *  [ search depth ]
 *  -- the depth of the minimax search algorithm
 *
 *
 */

public class maxconnect4
{
	public static void main(String[] args)
	{

		if( args.length != 4 ) // check for the correct number of arguments
		{
			System.out.println("Four command-line arguments are needed:\n"
					+ "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
					+ " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

			exit_function( 0 );
		}

		// parse the input arguments
		String game_mode = args[0].toString();				// the game mode
		String input = args[1].toString();					// the input game file
		int depthLevel = Integer.parseInt( args[3] );  		// the depth level of the  AI search

		// create and initialize the game board
		GameBoard currentGame = new GameBoard( input );

		// create the AI Player
		AiPlayer calculon = new AiPlayer(depthLevel);

		//  variables to keep up with the game
		int selected_col = 99;				               //  the players choice of column to play
		boolean playMade = false;			               //  set to true once a play has been made

		if( game_mode.equalsIgnoreCase( "interactive" ) )
		{
			System.out.print("\n***********MaxConnect-4 game*********\n");
			System.out.print("game state before move:\n");

			//print the current game board
			currentGame.printGameBoard();
			System.out.println( "Score ===>\n  Player 1 = " + currentGame.getScore( 1 ) +
					", Player2 = " + currentGame.getScore( 2 ) + "\n " );

			// print the current scores
			Boolean end_of_Play=false;
			String player2 =args[2].toString();
			while(end_of_Play!=true) {
				if (player2.equalsIgnoreCase("human-next")){
					if( currentGame.getPieceCount() < 42 ) {
						currentGame.setCurrentTurn(2);
						int player1 = currentGame.getCurrentTurn();
						// AI play - random play
						System.out.println("Enter next column number");
						BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)); //to write user console input
						try {
							selected_col=Integer.parseInt(buffer.readLine().toString())-1;
							while(currentGame.isValidPlay(selected_col)==false) {
								System.out.println("the entered value is invalid, try again!!");
								selected_col=Integer.parseInt(buffer.readLine().toString())-1;
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// play the piece
						currentGame.playPiece( selected_col );
						player2 = "computer-next";
						// display the current game board
						System.out.println("move " + currentGame.getPieceCount()
						+ ": Player " + player1
						+ ", column " + selected_col);
						System.out.print("game state after move:\n");
						currentGame.printGameBoard();

						// print the current scores
						System.out.println( "Score ===>\n  Player 1 = " + currentGame.getScore( 1 ) +
								", Player2 = " + currentGame.getScore( 2 ) + "\n " );

						currentGame.printGameBoardToFile( "human.txt" );

					}
					else {
						end_of_Play = true;
						System.out.println( "Score ===>\n  Player 1 = " + currentGame.getScore( 1 ) +
								", Player2 = " + currentGame.getScore( 2 ) + "\n " );

					}
				}
				else if (player2.equalsIgnoreCase("computer-next")){
					if( currentGame.getPieceCount() < 42 ) {
						currentGame.setCurrentTurn(1);
						int player1 = currentGame.getCurrentTurn();
						selected_col = calculon.findBestPlay( currentGame,depthLevel);

						// play the piece
						currentGame.playPiece( selected_col );
						player2 = "human-next";
						// display the current game board
						System.out.println("move " + currentGame.getPieceCount()
						+ ": Player " + player1
						+ ", column " + selected_col);
						System.out.print("game state after move:\n");
						currentGame.printGameBoard();

						// print the current scores
						System.out.println( "Score ===>\n Player 1 = " + currentGame.getScore( 1 ) +
								", Player2 = " + currentGame.getScore( 2 ) + "\n " );

						currentGame.printGameBoardToFile( "computer.txt" );

					}
					else {
						end_of_Play = true;
						System.out.println( "Score ===>\n Player 1 = " + currentGame.getScore( 1 ) +
								", Player2 = " + currentGame.getScore( 2 ) + "\n " );

					}
				} 
				else {
					System.out.println("You have entered wrong player name.");
					end_of_Play=true;
				}
			}
			//decide the winner based on scores
			if (currentGame.getScore(1)>currentGame.getScore(2)){
				System.out.println("******************************* Player 1 won the Game ******************************* ");

			}
			else if((currentGame.getScore(1)<currentGame.getScore(2))){
				System.out.println("******************************* Player 2 won the Game *******************************");
			}
			else {
				System.out.println("its a draw!!!! :(  ");
			}

			return;
		}
		else if( game_mode.equalsIgnoreCase( "one-move" ) ) {
			/////////////   one-move mode ///////////
			// get the output file name
			String output = args[2].toString();				// the output game file

			System.out.print("\nMaxConnect-4 game\n");
			System.out.print("game state before move:\n");

			//print the current game board
			currentGame.printGameBoard();
			// print the current scores
			System.out.println( "Score ===>\n Player 1 = " + currentGame.getScore( 1 ) +
					", Player2 = " + currentGame.getScore( 2 ) + "\n " );

			// ****************** this chunk of code makes the computer play
			int player1;
			if( currentGame.getPieceCount() < 42 )
			{
				player1 = currentGame.getCurrentTurn();
				if(player1 ==2) {
					System.out.println("Enter next column number: ");
					BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
					try {
						selected_col=Integer.parseInt(buffer.readLine().toString())-1;
						while(currentGame.isValidPlay(selected_col)==false) {
							System.out.println("The selected column is not valid, enter valid column number!");
							selected_col=Integer.parseInt(buffer.readLine().toString())-1;
						}
					}catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// play the piece
					currentGame.playPiece( selected_col );

					// Show updated game board
					System.out.println("move " + currentGame.getPieceCount()
					+ ": Player " + player1
					+ ", column " + selected_col);
					System.out.print("game state after move:\n");
					currentGame.printGameBoard();
					System.out.println( "Score ===>\n Player 1 = " + currentGame.getScore( 1 ) +
							", Player2 = " + currentGame.getScore( 2 ) + "\n " );

					currentGame.printGameBoardToFile( output );
				}
				else {
					
					player1 = currentGame.getCurrentTurn();
					// AI play - random play
					selected_col = calculon.findBestPlay( currentGame, depthLevel );
					
					// play the piece
					currentGame.playPiece( selected_col );

					// display the current game board
					System.out.println("move " + currentGame.getPieceCount()
					+ ": Player " + player1
					+ ", column " + selected_col);
					System.out.print("game state after move:\n");
					currentGame.printGameBoard();

					// print the current scores
					System.out.println( "Score ===>\n Player 1 = " + currentGame.getScore( 1 ) +
							", Player2 = " + currentGame.getScore( 2 ) + "\n " );

					currentGame.printGameBoardToFile( output );
				} }
			else
			{
				System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
			}

			//************************** end computer play *****************************************


			return;

		}

		else 
		{
			System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
			return;
		}
		


	} // end of main()

	/**
	 * This method is used when to exit the program prematurely.
	 * @param value an integer that is returned to the system when the program exits.
	 */
	private static void exit_function( int value )
	{
		System.out.println("exiting from MaxConnectFour.java!\n\n");
		System.exit( value );
	}
} // end of class connectFour
