package fassassi.tombeze.morpion;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.NumberExpression;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.*;

public class TicTacToeModel {
	/**
	 * Taille du plateau de jeu (pour être extensible).
	 */
	private final static int BOARD_WIDTH = 3;
	private final static int BOARD_HEIGHT = 3;
	/**
	* Nombre de pièces alignés pour gagner (idem).
	*/
	private final static int WINNING_COUNT = 3;
	/**
	* Joueur courant.
	*/
	private final ObjectProperty<Owner> turn = new SimpleObjectProperty<>(Owner.FIRST);
	/**
	* Vainqueur du jeu, NONE si pas de vainqueur.
	*/
	private final ObjectProperty<Owner> winner = new SimpleObjectProperty<>(Owner.NONE);
	/**
	* Plateau de jeu.
	*/
	private final ObjectProperty<Owner>[][] board;
	/**
	* Positions gagnantes.
	*/
	private final BooleanProperty[][] winningBoard;
	
	/**Cases occupées par X*/
	private final SimpleIntegerProperty squaresForX;
	
	/**Cases occupées par O**/
    private final SimpleIntegerProperty squaresForO;
    
    /**Cases vides**/
    private final SimpleIntegerProperty emptySquares;

    /**Message à la fin dujeu */
    private final StringProperty endOfGameMessage;

    /**Nombre de case occupées**/
    private int round;
	
	/**
	* Constructeur privé.
	*/
	private TicTacToeModel() {
		this.board=new ObjectProperty[BOARD_WIDTH][BOARD_HEIGHT];
		this.winningBoard = new BooleanProperty[BOARD_WIDTH][BOARD_HEIGHT];
		for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                board[i][j] = new SimpleObjectProperty<>(Owner.NONE);
                winningBoard[i][j] = new SimpleBooleanProperty(false);
            }
        }
		
		this.squaresForO = new SimpleIntegerProperty(0);
		this.squaresForX = new SimpleIntegerProperty(0);
		this.emptySquares = new SimpleIntegerProperty(BOARD_WIDTH * BOARD_HEIGHT);
		this.endOfGameMessage = new SimpleStringProperty("");
		
		this.round = 0;
	}
	
	/**
	* @return la seule instance possible du jeu.
	*/
	public static TicTacToeModel getInstance() {
		return TicTacToeModelHolder.INSTANCE;
		}
		
		/**
		* Classe interne selon le pattern singleton.
		*/
		private static class TicTacToeModelHolder {
			private static final TicTacToeModel INSTANCE = new TicTacToeModel();
		}
		
		public void restart() {
			winner.set(Owner.NONE);
	        for (int i = 0; i < BOARD_HEIGHT; i++) {
	            for (int j = 0; j < BOARD_WIDTH; j++) {
	                board[i][j].set(Owner.NONE);
	                winningBoard[i][j].set(false);
	            }
	        }
	        turn.set(Owner.FIRST);
	        squaresForX.set(0);
	        squaresForO.set(0);
	        
		}
		
		//Joueur courant
		public final ObjectProperty<Owner> turnProperty() { 
			 return turn;
		}
		
		//Propriété de la case à la position [row][column]
		public final ObjectProperty<Owner> getSquare(int row, int column){
			return board[row][column];
		}
		
		// Indique si si la case à la position [row][column] fait partie de la combinaison gagnante
		public final BooleanProperty getWinningSquare(int row, int column){
			 return winningBoard[row][column];
		}
		
		/**
		* Cette fonction ne doit donner le bon résultat que si le jeu
		* est terminé. L’affichage peut être caché avant la fin du jeu.
		*
		* @return résultat du jeu sous forme de texte
		*/
		public final StringProperty getEndOfGameMessage() {
			return endOfGameMessage;
		}
		
		public void setWinner(Owner winner) {
			this.winner.set(winner);
		}
		
		public boolean validSquare(int row, int column) {
			return row >= 0 && row < BOARD_WIDTH && column >= 0 &&  column < BOARD_HEIGHT;
		}
		
		public void nextPlayer() {
			turn.set(turn.get() == Owner.FIRST ? Owner.SECOND : Owner.FIRST);
			//turn.set(turn.get().opposite());
		}
		
		/**
		* Jouer dans la case (row, column) quand c’est possible.
		*/
		public void play(int row, int column) {
			if (validSquare(row, column)) {
				board[row][column].set(turn.get());
				round++;
				
				
			}
		}
		
		/**
		 * 
		 */
		
		
		/**
		* @return true s’il est possible de jouer dans la case
		* c’est-à-dire la case est libre et le jeu n’est pas terminé
		*/
		public BooleanBinding legalMove(int row, int column) {
			return board[row][column].isEqualTo(Owner.NONE).and(gameOver().not());
		}
		
		public NumberExpression getScore(Owner owner) {
			
		}
		
		/**
		* @return true si le jeu est terminé
		* (soit un joueur a gagné, soit il n’y a plus de cases à jouer)
		*/
		public BooleanBinding gameOver() {
			return squaresForO.greaterThanOrEqualTo(3).or(squaresForX.greaterThanOrEqualTo(3)).or(emptySquares.isEqualTo(0));
		}
		
		


}
