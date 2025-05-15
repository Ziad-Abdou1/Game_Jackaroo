package controller;

import view.GameView;
import engine.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameController extends Application {
	String name;
	public void start(Stage stage) throws Exception{
		Game game = new Game(name);
		GameView gameView = new GameView(game);
		Scene scene = new Scene(gameView);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[]args){
		launch(args);
	}
}
