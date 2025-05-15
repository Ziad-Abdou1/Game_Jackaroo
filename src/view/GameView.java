package view;

import java.security.acl.Group;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import engine.Game;
import engine.board.Board;

public class GameView extends StackPane {
	private Game game;
	private BoardView boardView;
	private HandsView handsView;
	private HomesView homesView;
	public GameView(Game game){
		this.game = game;
		draw();
	}
	
	public void draw(){
		boardView = new BoardView(game.getBoard());
		handsView = new HandsView(game);
		homesView = new HomesView(game.getPlayers());
		handsView.setMaxSize(1200, 1200);
		handsView.setStyle("-fx-background-color: green;");
		this.getChildren().addAll(boardView,handsView,homesView);
	}
}