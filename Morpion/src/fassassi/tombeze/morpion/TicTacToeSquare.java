package fassassi.tombeze.morpion;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.BooleanProperty;


public class TicTacToeSquare {
	
	private static TicTacToeModel model = TicTacToeModel.getInstance();
	
	private ObjectProperty<Owner> ownerProperty =	new SimpleObjectProperty<>(Owner.NONE);
	
	private BooleanProperty winnerProperty = new SimpleBooleanProperty(false);
	
	public ObjectProperty<Owner> ownerProperty() {
		return null;
	}
	
	public BooleanProperty winnerProperty() {
		return null;
		
	}
	public TicTacToeSquare(final int row, final int column) { 
		return;
	}


}
