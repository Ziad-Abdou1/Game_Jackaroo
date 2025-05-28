package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import engine.Game;
import engine.board.Board;
import engine.board.Cell;
import engine.board.SafeZone;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.util.Duration;
import model.player.Marble;
import model.player.Player;

public class MarblesView extends Pane {
	ArrayList<Cell> trackCells;
	ArrayList<SafeZone> safeZones;
	ArrayList<ArrayList<Marble>> homeZones;
	Map<Marble, Pair> previousMarblePositions = new HashMap<>();
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
			// Calculate control point for the quadratic curve (creates the arc)
			double controlX = (oldPos.x + newPos.x) / 2;
			double controlY = Math.min(oldPos.y, newPos.y)
					- Math.abs(newPos.x - oldPos.x) / 2;

			// Create path for arc movement
			Path path = new Path();
			path.getElements().add(new MoveTo(oldPos.x, oldPos.y));
			path.getElements().add(
					new QuadCurveTo(controlX, controlY, newPos.x, newPos.y));

			// Create path transition
			PathTransition pathTransition = new PathTransition();
			pathTransition.setDuration(Duration.millis(750));
			pathTransition.setPath(path);
			pathTransition.setNode(circle);
			pathTransition.setOrientation(PathTransition.OrientationType.NONE);

			// Add rotation to make it more realistic
			RotateTransition rotateTransition = new RotateTransition(
					Duration.millis(400), circle);
			rotateTransition.setByAngle(360 * (newPos.x > oldPos.x ? 1 : -1)); // rotate
																				// direction
																				// based
																				// on
																				// movement

			// Combine animations
			ParallelTransition parallelTransition = new ParallelTransition(
					pathTransition, rotateTransition);

			// Update final position immediately (to prevent position issues
			// after animation)
			circle.setCenterX(newPos.x);
			circle.setCenterY(newPos.y);

			parallelTransition.play();
		} else {
			circle.setCenterX(newPos.x);
			circle.setCenterY(newPos.y);
		}

		previousMarblePositions.put(marble, new Pair(newPos.x, newPos.y));
		this.getChildren().add(circle);
	}
//	private void updateMarblePosition(Marble marble, Pair newPos) {
//	    Circle circle = marble.circle;
//	    Pair oldPos = previousMarblePositions.get(marble);
//
//	    if (oldPos != null && (oldPos.x != newPos.x || oldPos.y != newPos.y)) {
//	        // Clear any existing animations
//	        circle.getProperties().remove("currentAnimation");
//	        Object oldAnim = circle.getProperties().get("currentAnimation");
//	        if (oldAnim instanceof Transition) {
//	            ((Transition)oldAnim).stop();
//	        }
//
//	        // 1. Arc movement parameters
//	        double distance = Math.hypot(newPos.x-oldPos.x, newPos.y-oldPos.y);
//	        double controlX = (oldPos.x + newPos.x) / 2;
//	        double controlY = Math.min(oldPos.y, newPos.y) - distance/2;
//
//	        // 2. Create path for arc movement
//	        Path path = new Path();
//	        path.getElements().add(new MoveTo(oldPos.x, oldPos.y));
//	        path.getElements().add(new QuadCurveTo(controlX, controlY, newPos.x, newPos.y));
//
//	        // 3. Create path transition (slowed to 750ms)
//	        PathTransition pathTransition = new PathTransition();
//	        pathTransition.setDuration(Duration.millis(750));
//	        pathTransition.setPath(path);
//	        pathTransition.setNode(circle);
//	        pathTransition.setOrientation(PathTransition.OrientationType.NONE);
//
//	        // 4. Rotation with easing
//	        RotateTransition rotateTransition = new RotateTransition(Duration.millis(750), circle);
//	        rotateTransition.setByAngle(180 * (distance/50 + 1) * (newPos.x > oldPos.x ? 1 : -1));
//	        rotateTransition.setInterpolator(Interpolator.EASE_BOTH);
//
//	        // 5. Squash and stretch effect
//	        Timeline squashTimeline = new Timeline(
//	            new KeyFrame(Duration.ZERO,
//	                new KeyValue(circle.scaleXProperty(), 1.0),
//	                new KeyValue(circle.scaleYProperty(), 1.0)
//	            ),
//	            new KeyFrame(Duration.millis(375), // Midpoint of animation
//	                new KeyValue(circle.scaleXProperty(), 1.2),
//	                new KeyValue(circle.scaleYProperty(), 0.8)
//	            ),
//	            new KeyFrame(Duration.millis(750),
//	                new KeyValue(circle.scaleXProperty(), 1.0),
//	                new KeyValue(circle.scaleYProperty(), 1.0)
//	            )
//	        );
//
//	        // 6. Glow effect that pulses during movement
//	        Glow glow = new Glow(0.7);
//	        circle.setEffect(glow);
//	        
//	        Timeline glowTimeline = new Timeline(
//	            new KeyFrame(Duration.ZERO,
//	                new KeyValue(glow.levelProperty(), 0.3)
//	            ),
//	            new KeyFrame(Duration.millis(375),
//	                new KeyValue(glow.levelProperty(), 0.7)
//	            ),
//	            new KeyFrame(Duration.millis(750),
//	                new KeyValue(glow.levelProperty(), 0.0)
//	            )
//	        );
//
//	        // 7. Combine all animations
//	        ParallelTransition parallelTransition = new ParallelTransition(
//	            pathTransition,
//	            rotateTransition,
//	            squashTimeline,
//	            glowTimeline
//	        );
//
//	        // Store animation for possible cancellation
//	        circle.getProperties().put("currentAnimation", parallelTransition);
//
//	        // Clean up after animation
//	        parallelTransition.setOnFinished(e -> {
//	            circle.setEffect(null);
//	            circle.setScaleX(1.0);
//	            circle.setScaleY(1.0);
//	            circle.setRotate(0);
//	        });
//
//	        // Update final position precisely
//	        circle.setCenterX(newPos.x);
//	        circle.setCenterY(newPos.y);
//
//	        parallelTransition.play();
//	    } else {
//	        circle.setCenterX(newPos.x);
//	        circle.setCenterY(newPos.y);
//	    }
//
//	    previousMarblePositions.put(marble, new Pair(newPos.x, newPos.y));
//	    this.getChildren().add(circle);
//	}


}