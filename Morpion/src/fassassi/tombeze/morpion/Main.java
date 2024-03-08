package fassassi.tombeze.morpion;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// Chargement du fichier FXML
            Parent root = FXMLLoader.load(getClass().getResource("ViewGame.fxml"));

         // Création de la scène avec la racine chargée depuis le fichier FXML
            Scene scene = new Scene(root);
            
            // Configuration de la scène et affichage de la fenêtre
            primaryStage.setScene(scene);
            primaryStage.setTitle("Jeu TicTacToe");
            primaryStage.show();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
