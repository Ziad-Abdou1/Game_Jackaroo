package view;

import java.security.acl.Group;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import engine.Game;
import engine.board.Board;

public class GameView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
	
	
	private Game game;
	private BoardView boardView;
	private HandsView handsView;
	private HomesView homesView;
	private PlayerViews playerViews;
	public GameView(Game game){
		this.game = game;
		draw();
	}
	
	public void draw(){
		boardView = new BoardView(game.getBoard());
//		boardView.setMinSize(300, 300);
//		boardView.setMaxSize(300, 300);
		handsView = new HandsView(game);
		handsView.setMaxSize(1400, 1000);
		homesView = new HomesView(game.getPlayers());
		homesView.setMaxSize(700, 700);
		playerViews = new PlayerViews(game);
		playerViews.setMaxSize(1500, 900);
//		playerViews.setStyle("-fx-background-color: green;");
		
		this.getChildren().addAll(boardView,handsView,homesView,playerViews);
		StackPane.setAlignment(homesView, Pos.CENTER);
		StackPane.setAlignment(handsView, Pos.CENTER);
		StackPane.setAlignment(boardView, Pos.CENTER);
		StackPane.setAlignment(playerViews, Pos.CENTER);
//		this.setPadding(new Insets(10));
	}
	public HandsView getHandView(){return this.handsView;}
}