package view;

import java.security.acl.Group;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
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
		boardView = new BoardView(game.getBoard(), game);
//		boardView.setMinSize(300, 300);
//		boardView.setMaxSize(300, 300);
		handsView = new HandsView(game);
		handsView.setMaxSize(1400, 1000);
		homesView = new HomesView(game.getPlayers(), game);
		homesView.setMaxSize(700, 700);
		playerViews = new PlayerViews(game);
		playerViews.setMaxSize(1500, 900);
//		playerViews.setStyle("-fx-background-color: green;");
		
		this.getChildren().addAll(boardView,homesView,playerViews,handsView);
		StackPane.setAlignment(homesView, Pos.CENTER);
		StackPane.setAlignment(handsView, Pos.CENTER);
		StackPane.setAlignment(boardView, Pos.CENTER);
		StackPane.setAlignment(playerViews, Pos.CENTER);
//		this.setPadding(new Insets(10));
	}
	public HandsView getHandView(){return this.handsView;}
	public Rectangle2D getScreenBounds() {
		return screenBounds;
	}
	public void setScreenBounds(Rectangle2D screenBounds) {
		this.screenBounds = screenBounds;
	}
	public double getScreenWidth() {
		return screenWidth;
	}
	public void setScreenWidth(double screenWidth) {
		this.screenWidth = screenWidth;
	}
	public double getScreenHeight() {
		return screenHeight;
	}
	public void setScreenHeight(double screenHeight) {
		this.screenHeight = screenHeight;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public BoardView getBoardView() {
		return boardView;
	}
	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}
	public HandsView getHandsView() {
		return handsView;
	}
	public void setHandsView(HandsView handsView) {
		this.handsView = handsView;
	}
	public HomesView getHomesView() {
		return homesView;
	}
	public void setHomesView(HomesView homesView) {
		this.homesView = homesView;
	}
	public PlayerViews getPlayerViews() {
		return playerViews;
	}
	public void setPlayerViews(PlayerViews playerViews) {
		this.playerViews = playerViews;
	}
	
	
}