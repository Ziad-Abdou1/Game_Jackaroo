package controller;

import model.card.Card;
import model.player.Player;
import view.CardView;
import view.CellView;
import view.GameView;
import view.HPlayerCardView;
import view.MarbleView;
import engine.Game;
import engine.board.Cell;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;


public class GameController extends Application {
	String name;
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
    GameView gameView;
	public void start(Stage stage) throws Exception{
		name = "Adham";
		Game game = new Game(name);
		gameView = new GameView(game);
		Scene scene = new Scene(gameView,screenWidth,screenHeight);
		System.out.println("Board state:");
        for (Cell cell : game.getBoard().getTrack()) {
            System.out.print(cell.isTrap()?"1":"0");
        }
        System.out.println();
    
		for (int i = 0; i < 4; i++){
			System.out.println(game.getPlayers().get(i).getColour());
		}
		
		scene.setOnKeyPressed(evt -> {
		    if (evt.getCode() == KeyCode.ENTER) {
		        gameView.playAll();
		    }
		});
		
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
		

//		for (HPlayerCardView hp : gameView.getHandView().getHands()){
//			for (CardView c : hp.getCardViews()){
//				
//		}

	}
	private Point2D scenePos(Node node) {
	    Bounds b = node.localToScene(node.getBoundsInLocal());
	    return new Point2D(b.getMinX(), b.getMinY());
	}


	public void redraw(){
		gameView.draw();
	}
	public static void main(String[]args){
		launch(args);
	}
}
