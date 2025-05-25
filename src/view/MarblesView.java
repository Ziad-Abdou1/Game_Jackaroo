package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import engine.Game;
import engine.board.Board;
import engine.board.Cell;
import engine.board.SafeZone;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import model.player.Marble;
import model.player.Player;

public class MarblesView extends Pane {
	public static ArrayList<Cell> trackCells;
	public static ArrayList<SafeZone> safeZones;
	public static ArrayList<ArrayList<Marble>> homeZones;
	public static Map<Marble, Pair> previousMarblePositions = new HashMap<>();
	Game game;
	boardView2 boardView2;

	public MarblesView(Game game, boardView2 boardView2) {
		this.game = game;
		this.boardView2 = boardView2;
	}

	public void update() {
		this.getChildren().clear();

		trackCells = game.getBoard().getTrack();
		safeZones = game.getBoard().getSafeZones();
		homeZones = new ArrayList<>();
		ArrayList<Player> players = game.getPlayers();

		for (int i = 0; i < players.size(); i++) {
			homeZones.add(players.get(i).getMarbles());
		}

		for (int i = 0; i < homeZones.size(); i++) {
			for (int j = 0; j < homeZones.get(i).size(); j++) {
				Marble m = homeZones.get(i).get(j);
				if (m == null)
					continue;
				Pair position = boardView2.homeindexToPoint.get(i).get(j);
				updateMarblePosition(m, position);
			}
		}

		for (int i = 0; i < trackCells.size(); i++) {
			Marble m = trackCells.get(i).getMarble();
			if (m == null)
				continue;

			Pair position = boardView2.indexToPoint.get(i);
			updateMarblePosition(m, position);
		}

		for (int i = 0; i < safeZones.size(); i++) {
			for (int j = 0; j < safeZones.get(i).getCells().size(); j++) {
				Marble m = safeZones.get(i).getCells().get(j).getMarble();
				if (m == null)
					continue;

				Pair position = boardView2.safeZoneIndexToPoint.get(i).get(j);
				updateMarblePosition(m, position);
			}
		}
	}

	private void updateMarblePosition(Marble marble, Pair newPos) {
		Circle circle = marble.circle;
		Pair oldPos = previousMarblePositions.get(marble);

		if (oldPos != null && (oldPos.x != newPos.x || oldPos.y != newPos.y)) {
			double dx = newPos.x - oldPos.x;
			double dy = newPos.y - oldPos.y;
			double distance = Math.sqrt(dx * dx + dy * dy);
			double speed = 0.5;
			double duration = distance / speed;

			TranslateTransition transition = new TranslateTransition(
					Duration.millis(duration), circle);

			transition.setFromX(0);
			transition.setFromY(0);
			transition.setToX(dx);
			transition.setToY(dy);
			transition.setOnFinished(e -> {
				circle.setTranslateX(0);
				circle.setTranslateY(0);
				circle.setCenterX(newPos.x);
				circle.setCenterY(newPos.y);
			});
			transition.play();
		} else {
			circle.setCenterX(newPos.x);
			circle.setCenterY(newPos.y);
		}

		previousMarblePositions.put(marble, new Pair(newPos.x, newPos.y));
		this.getChildren().add(circle);
	}
}