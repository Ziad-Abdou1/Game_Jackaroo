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
//	public void animateFielding(MarbleView marbleView, CellView targetCell) {
//	    // 1) figure out start & end positions
//	    Point2D start = scenePos(marbleView);
//	    Point2D end   = scenePos(targetCell);
//
//	    // 2) detach marbleView from its parent and re-parent it to the GameView root
//	    //    so that we can move it freely over the board.
//	    Pane overlay = gameView.getOverlayPane(); 
//	    // (you can add a transparent Pane on top of everything in GameView)
//	    Parent parent = marbleView.getParent();
//	    ((Pane) parent).getChildren().remove(marbleView);
//	    overlay.getChildren().add(marbleView);
//
//	    // 3) position it exactly at the start
//	    marbleView.setTranslateX(start.getX());
//	    marbleView.setTranslateY(start.getY());
//
//	    // 4) build the transition
//	    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.6), marbleView);
//	    tt.setFromX(start.getX());
//	    tt.setFromY(start.getY());
//	    tt.setToX(end.getX());
//	    tt.setToY(end.getY());
//
//	    // 5) on finish: snap it into the cellView and clean up
//	    tt.setOnFinished(evt -> {
//	        overlay.getChildren().remove(marbleView);
//	        targetCell.getChildren().add(marbleView);
//	        marbleView.setTranslateX(0);
//	        marbleView.setTranslateY(0);
//	    });
//
//	    tt.play();
//	}

	public void redraw(){
		gameView.draw();
	}
	public static void main(String[]args){
		launch(args);
	}
}
