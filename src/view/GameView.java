package view;

import java.security.acl.Group;

import model.card.Card;
import model.player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import engine.Game;
import engine.board.Board;

public class GameView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
	ImageView PlayButton;
	private Game game;
	private BoardView boardView;
	private HandsView handsView;
	private HomesView homesView;
	private PlayerViews playerViews;
	public GameView(Game game){
		initPlayButton();
		this.game = game;
		draw();
	}
	
	public void initPlayButton(){
		Image img = new Image("playButton.png");
		PlayButton = new ImageView(img);
		PlayButton.setScaleX(0.4);
		PlayButton.setScaleY(0.4);
		PlayButton.setOnMouseClicked(e -> {
			try{
				if (game.canPlayTurn()){
					game.playPlayerTurn();
					game.endPlayerTurn();
					draw();
				}

			}catch (Exception ex){
				System.out.println(ex.getMessage());
			}
		});
		PlayButton.setOnMouseEntered(e -> {
			PlayButton.setScaleX(0.5);
			PlayButton.setScaleY(0.5);
		});
		PlayButton.setOnMouseExited(e -> {
			PlayButton.setScaleX(0.4);
			PlayButton.setScaleY(0.4);
		});
	}

	public void draw(){
		this.getChildren().clear();
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
//		for (Player p : game.getPlayers()){
//			System.out.println(p.getName()+": ");
//			for (Card card : p.getHand()){
//				System.out.println(card.getName());
//			}
//		}
		this.getChildren().addAll(boardView,homesView,playerViews,handsView,PlayButton);
		StackPane.setAlignment(homesView, Pos.CENTER);
		StackPane.setAlignment(handsView, Pos.CENTER);
		StackPane.setAlignment(boardView, Pos.CENTER);
		StackPane.setAlignment(playerViews, Pos.CENTER);
		StackPane.setAlignment(PlayButton, Pos.BOTTOM_RIGHT);
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