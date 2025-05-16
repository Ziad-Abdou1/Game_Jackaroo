package controller;

import view.CardView;
import view.GameView;
import view.HPlayerCardView;
import engine.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameController extends Application {
	String name;
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
	public void start(Stage stage) throws Exception{
		name = "Adham";
		Game game = new Game(name);
		GameView gameView = new GameView(game);
		Scene scene = new Scene(gameView,screenWidth,screenHeight);
	
		stage.setScene(scene);
		stage.setMaxHeight(screenHeight);
		stage.setWidth(screenWidth);
		stage.show();
		
//		scene.setOnKeyPressed(event -> {
//		    switch(event.getCode()) {
//		        case ESCAPE:
//		            stage.close();
//		            break;
//		        // handle other keys
//		    }
//		});
		

		for (HPlayerCardView hp : gameView.getHandView().getHand()){
			for (CardView c : hp.getCardViews()){
				c.setOnMouseEntered(e -> {
				    c.hover(true);
				});

				c.setOnMouseExited(e -> {
				    c.hover(false);
				});
			}
		}
		
	    AnimationTimer gameLoop = new AnimationTimer() {
	        @Override
	        public void handle(long now) {
	        	
	        	
	        	gameView.draw();

	            if (game.checkWin() != null) {
	                stop();
	                System.out.println("We have a winner!");
	            }
	        }
	    };
	    gameLoop.start();
	}
	
	public static void main(String[]args){
		launch(args);
	}
}
