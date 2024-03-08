package fassassi.tombeze.morpion;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {
    
    // Déclaration des éléments de l'IHM annotés avec @FXML pour les lier au fichier FXML
    
    @FXML
    private GridPane gameGrid;
    
    @FXML
    private Button restartButton;
    
    @FXML
    private Label xCountLabel;
    
    @FXML
    private Label oCountLabel;
    
    @FXML
    private Label emptyCountLabel;
    
    @FXML
    private Label winnerLabel;
    
    // Référence au modèle TicTacToeModel
    private TicTacToeModel model;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialisation du modèle
        model = TicTacToeModel.getInstance();
        
        // Mise à jour de la grille de jeu
        updateGrid();
        
        // Mise à jour des compteurs de cases occupées
        updateCounts();
        
        // Mise à jour du label du gagnant
        updateWinner();
        
        // Ajout d'un gestionnaire d'événements pour le bouton de redémarrage du jeu
        restartButton.setOnAction(event -> restartGame());
    }
    
    // Méthode pour mettre à jour la grille de jeu
    private void updateGrid() {
        for (int row = 0; row < TicTacToeModel.getBHeigth(); row++) {
            for (int column = 0; column < TicTacToeModel.getBWidth(); column++) {
                // Création d'une nouvelle case TicTacToeSquare pour chaque emplacement dans la grille
                TicTacToeSquare square = new TicTacToeSquare(row, column);
                gameGrid.add(square, column, row); // Ajout de la case à la grille
            }
        }
    }
    
    // Méthode pour mettre à jour les compteurs de cases occupées
    private void updateCounts() {
        xCountLabel.setText(model.getSquaresForX().get()+ "cases occupé par X");
        oCountLabel.setText(model.getSquaresForO().get() + "cases occupé par O");
        emptyCountLabel.setText(model.getEmptySquares().get()+ "cases vides");
    }
    
    // Méthode pour mettre à jour le label du gagnant j
    private void updateWinner() {
        winnerLabel.setText("Winner: " + model.getWinner());
    }
    
    // Méthode pour redémarrer le jeu
    private void restartGame() {
        model.restart(); // Redémarrage du modèle
        updateGrid(); // Mise à jour de la grille de jeu
        updateCounts(); // Mise à jour des compteurs de cases occupées
        updateWinner(); // Mise à jour du label du gagnant
    }
}
