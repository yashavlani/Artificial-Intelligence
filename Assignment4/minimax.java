import java.util.ArrayList;

public class minimax {
	
	//calculate the utility function
		public static int utility(GameBoard currentGame){
			if(currentGame.getScore(1) > currentGame.getScore(2)) {
	 		return Integer.MAX_VALUE;
			}
			else if(currentGame.getScore(2) > currentGame.getScore(1)) {
				return Integer.MIN_VALUE;
			}
			else {
				return 0;
			}
		}
		
		//calculation of evaluation function
		public static int evaluate(GameBoard currentGame) {
			return (currentGame.getScore(1) - currentGame.getScore(2));


			// TODO Auto-generated method stub

		}
		
	public static int findMax(GameBoard state,int alpha,int beta, int depth) //Return maximum of player move
	{
		if(state.getPieceCount()==42)
			return utility(state);
		if( depth == 0 )
			return evaluate(state);
		else {
			ArrayList<GameBoard> childs = new ArrayList<GameBoard>(); //generate arraylist
			childs=	AiPlayer.getNextProbableBoard(state);

			int v = Integer.MIN_VALUE;
			for (int i = 0; i < childs.size(); i++) {
				v = Math.max(v, findMax( childs.get(i), alpha, beta, depth-1));
				if(v>=beta) {
					return v;
				}
				alpha = Math.max(alpha, v);
			}
			return v;
		}


	}
	
	public static int findMin(GameBoard state,int alpha,int beta, int depth) //Return minimum of player move
	{
		if(state.getPieceCount()==42) //It returns utility values when reaches terminal
			return utility(state);
		if( depth == 0 )
			return evaluate(state);
		else {
			ArrayList<GameBoard> childs = new ArrayList<GameBoard>(); //get array list for child nodes
			childs=	AiPlayer.getNextProbableBoard(state);

			int temp = Integer.MAX_VALUE;
			for (int i = 0; i < childs.size(); i++) {
				temp = Math.min(temp, findMax(childs.get(i), alpha, beta,depth-1));
				if(temp<=alpha) {
					return temp;
				}
				beta = Math.min(beta, temp);
			}
			return temp;

		}
	}
}
