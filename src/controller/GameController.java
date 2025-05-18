package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import model.card.Card;
import model.player.Player;
import view.CardView;
import view.CellView;
import view.GameView;
import view.HPlayerCardView;
import view.MarbleView;
import view.WelcomeView;
import engine.Game;
import engine.board.Cell;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
	GameView gameView;
	WelcomeView welcomeView;
	Stage mainStage;
	Scene gameScene;
	Scene welcomeScene;
	String playerName;
	//Scene scene1 = new Scene(WinningView, screenWidth, screenHeight);
	@Override
	public void start(Stage stage)throws IOException {
		mainStage = stage;
		launchGame();
		startGameAction();
	}
	
	private void startGame() throws IOException {
		Game game = new Game(playerName);
		gameView = new GameView(game);
		gameScene = new Scene(gameView, screenWidth, screenHeight);
		
		
		gameScene.setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.ENTER) {
				gameView.playAll();
			}
		});
	}
	
	private void launchGame() throws IOException {
		welcomeView = new WelcomeView();
		welcomeScene = new Scene(welcomeView);
		mainStage.setScene(welcomeScene);
//		stage.setMaxHeight(screenHeight);
//		stage.setWidth(screenWidth);
		mainStage.show();
	}
	
	
	///all actions---------------------------------------------------------------------------------------------------------------------
	
	public void startGameAction() throws IOException {
//		welcomeView.getStartButton().setOnMouseClicked(e -> {
//			mainStage.setScene(gameScene);
//			playerName = welcomeView.getNameField().getText();
//			startGame();
//			//mainStage.show();
//		});
		welcomeView.getStartButton().setOnMouseClicked(new EventHandler<Event>() {
            @Override
            public void handle(Event event)  {
    			playerName = welcomeView.getNameField().getText();
    			try{
    				startGame();
    			}catch (Exception e){
    				System.out.println(e.getMessage());
    			}
            	mainStage.setScene(gameScene);
    			//mainStage.show();
            }
        });
	}
	
	///--------------------------------------------------------------------------------------------------------------------------------
	
	public void switchScene(Stage stage, Scene scene){
		stage.setScene(scene);
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
