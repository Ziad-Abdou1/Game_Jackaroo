package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import model.Colour;
import model.card.Card;
import model.player.Marble;
import model.player.Player;
import view.CardView;
import view.CellView;
import view.GameView;
import view.HPlayerCardView;
import view.MarbleView;
import view.WelcomeView;
import view.WinningView;
import engine.Game;
import engine.board.Cell;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class GameController extends Application {
	String name;
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	
	
//    Rectangle2D vb = Screen.getPrimary().getVisualBounds();
//    double realW = vb.getWidth();
//    double realH = vb.getHeight();
//    
//    private static final double DESIGN_W = 1920;
//    private static final double DESIGN_H = 1080;
    
    
	GameView gameView;
	WelcomeView welcomeView;
	WinningView winningView;
	Scene welcomeScene,gameScene,winningScene;
	Stage stage;	
	Player wonPlayer;
	boolean gameEnded = false;
	
	@Override
	public void start(Stage stage)throws IOException {
		this.stage = stage;
		launchGame();
		startGameAction();
		endGameAction();
		replayAction();
	}

	
	private void startGame() throws IOException {
		Game game = new Game(name);
		gameView = new GameView(game);
		gameScene = new Scene(gameView, screenWidth, screenHeight);
		
//        DoubleBinding scaleX = gameScene.widthProperty().divide(DESIGN_W);
//        DoubleBinding scaleY = gameScene.heightProperty().divide(DESIGN_H);
//        gameView.scaleXProperty().bind(scaleX);
//        gameView.scaleYProperty().bind(scaleY);
		
		//game actions
		gameScene.setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.ENTER) {
				gameView.playAll();
			}
			if (evt.getCode() == KeyCode.DIGIT1){
				try{
					game.fieldMarble();
//					game.endPlayerTurn();
					gameView.refresh();

				}catch (Exception e){
					gameView.showExceptionWindow(e.getMessage());
				}
			}
		});
		stage.setScene(gameScene);
		stage.setFullScreen(true);
	}
	
	private void win() {
		Colour winCol = gameView.getGame().checkWin();
		for (int i = 0; i < gameView.getGame().getPlayers().size(); i++){
			if (gameView.getGame().getPlayers().get(i).getColour()==winCol){
				wonPlayer = gameView.getGame().getPlayers().get(i);
				break;
			}
		}
		winningView = new WinningView(wonPlayer);
		winningScene = new Scene(winningView,screenWidth,screenHeight);
		stage.setScene(winningScene);
	}
	
	private void launchGame() throws IOException {
		welcomeView = new WelcomeView();
		welcomeScene = new Scene(welcomeView);
		stage.setScene(welcomeScene);
//		stage.setMaxHeight(screenHeight);
//		stage.setWidth(screenWidth);
		stage.show();
	}
	
	//handle actions
	public void startGameAction(){
		welcomeView.getStartButton().setOnMouseClicked(e ->{
			name = welcomeView.getNameField().getText();
			try{
				startGame();
			}catch (Exception ex){
				//
			}
		});
		welcomeView.getStartButton().setOnMouseEntered(e ->{
			welcomeView.getStartButton().setScaleX(1.2);
			welcomeView.getStartButton().setScaleY(1.2);
		});
		welcomeView.getStartButton().setOnMouseExited(e ->{
			welcomeView.getStartButton().setScaleX(1.0);
			welcomeView.getStartButton().setScaleY(1.0);
		});
		
	}

	public void endGameAction(){
		Timeline replay = new Timeline(
			    new KeyFrame(Duration.seconds(0.001), event -> {
			        if (gameView!=null && gameView.getGame() != null && gameView.getGame().checkWin()!=null){
			        	if (!gameEnded){
			        		win();
			        		gameEnded = true;
			        	}
			        	
			        }
			    })
			);
			replay.setCycleCount(Animation.INDEFINITE); 
			replay.play(); // Starts the timeline
	}
	
	public void replayAction(){
		Timeline replay = new Timeline(
			    new KeyFrame(Duration.seconds(0.001), event -> {
			        if (gameEnded){
			    		winningView.getContinueButton().setOnMouseClicked(e->{
			    			try{
			    				startGame();
			    				gameEnded = false;
			    			}catch(Exception ex){
			    				//
			    			}
			    		});
			        }
			    })
			);
			replay.setCycleCount(Animation.INDEFINITE); 
			replay.play(); // Starts the timeline

	}
	
	private Point2D scenePos(Node node) {
		Bounds b = node.localToScene(node.getBoundsInLocal());
		return new Point2D(b.getMinX(), b.getMinY());
	}

	public void redraw() {
		gameView.draw();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
