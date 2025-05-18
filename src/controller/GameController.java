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
import engine.Game;
import engine.board.Cell;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
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

	@Override
	public void start(Stage stage)throws IOException {

		launchGame(stage);
	}


	private void launchGame(Stage stage) throws IOException {
		Game game = new Game("adham");
		gameView = new GameView(game);
		Scene scene = new Scene(gameView, screenWidth, screenHeight);

		scene.setOnKeyPressed(evt -> {
			if (evt.getCode() == KeyCode.ENTER) {
				gameView.playAll();
			}
		});

		stage.setScene(scene);
		stage.setMaxHeight(screenHeight);
		stage.setWidth(screenWidth);
		stage.show();
	}
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
