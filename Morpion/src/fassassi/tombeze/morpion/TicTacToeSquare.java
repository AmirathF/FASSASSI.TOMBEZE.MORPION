package fassassi.tombeze.morpion;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.awt.*;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.text.FontWeight;


public class TicTacToeSquare extends TextField {
	
	private static final Color GREEN_BACKGROUND = Color.rgb(173, 255, 47);
    private static final Color RED_BACKGROUND = Color.RED;
    private static final Color WINNER_COLOR = Color.rgb(30, 144, 255);
	
	private static TicTacToeModel model = TicTacToeModel.getInstance();
	
	private final int row;
    private final int column;
	
	private ObjectProperty<Owner> ownerProperty =	new SimpleObjectProperty<>(Owner.NONE);
    private ObjectProperty<Owner> owner = new SimpleObjectProperty<>(Owner.NONE); // Déclarer owner ici
	private BooleanProperty winnerProperty = new SimpleBooleanProperty(false);
	
	public ObjectProperty<Owner> ownerProperty() {
		return ownerProperty;
	}
	
	public BooleanProperty winnerProperty() {
		return winnerProperty;
		
	}
	public TicTacToeSquare(int row, int column) { 
		this.row = row;
        this.column = column;
        setPrefSize(70, 70);
        setAlignment(javafx.geometry.Pos.CENTER);
        setFont(Font.font(30));
        setEditable(false);
        setPadding(new Insets(-10, 0, -10, 0));
        setFont(Font.font("Georgia", 30));
        setOnMouseClicked(event -> handleMouseClicked(event));
        setOnMouseEntered(event -> handleMouseEntered(event));
        setOnMouseExited(event -> handleMouseExited(event));
        
        owner.bind(model.turnProperty());
        model.getWinningSquare(row, column).addListener((observable, oldValue, newValue) -> {
            if (Boolean.TRUE.equals(newValue)) {
                this.setFont(Font.font("Arial", 50));
            }
        });
		//return;
	}
	
	 private void handleMouseClicked(MouseEvent event) {
		 if (model.legalMove(row, column).get()) {
	            setText(model.turnProperty().get() == Owner.FIRST ? "X" : "O");
	            model.play(row, column);
	        }
	    }

	    private void handleMouseEntered(MouseEvent event) {
	    	if (!model.gameOver().get() && model.legalMove(row, column).get()) {
	            setCursor(Cursor.HAND);
	            setStyle("-fx-background-color: lightgreen;");
	        } else {
	            setStyle("-fx-background-color: lightcoral;");
	        }
	    }

	    private void handleMouseExited(MouseEvent event) {
	        setStyle("");
	    }

	
	 


}
