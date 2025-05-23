package view;

import engine.Game;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
<<<<<<< Updated upstream
=======
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
>>>>>>> Stashed changes
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;
import model.Colour;
import model.player.Marble;

public class MarbleView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	boolean selected = false;
	Game game;

	private Marble marble;
	private final double radius = 14 * screenWidth / 4000;
	private Circle circle;

	public MarbleView(Marble marble, Game game) {
		this.game = game;
		this.marble = marble;
		circle = new Circle();
		circle.setRadius(radius);
		this.getChildren().add(circle);
		refresh();
		handle();
	}

	private Color getFXColor(Colour clr) {
		switch (clr) {
		case BLUE:
			return Color.DODGERBLUE;
		case RED:
			return Color.CRIMSON;
		case GREEN:
			return Color.FORESTGREEN;
		case YELLOW:
			return Color.GOLD;
		default:
			return Color.GRAY;
		}
	}

	public void refresh() {
		handle();
		circle.setRadius(radius);
		if (marble == null) {
			circle.setOpacity(0);
		} else {
			circle.setOpacity(1);
			Colour clr = marble.getColour();
			Color baseColor = getFXColor(clr);

			// Simulate 3D look using RadialGradient
			circle.setFill(new RadialGradient(0, 0, 0.3, 0.3, 1, true,
					CycleMethod.NO_CYCLE, new Stop(0, baseColor.brighter()
							.brighter()), new Stop(1, baseColor.darker())));
			if (selected)
				selectEffect();
			else
				deselectEffect();
		}
	}

	public void selectEffect() {
		circle.setStroke(Color.WHITE);
		circle.setStrokeWidth(radius * 0.15);
	}

	public void deselectEffect() {
		circle.setStroke(null);
		circle.setStrokeWidth(0);
	}

	public void resetSelect() {
		this.selected = false;
	}

	public Marble getMarble() {
		return this.marble;
	}

	public void setMarble(Marble marble) {
		this.marble = marble;
		refresh();
	}

	public void handle() {
		if (marble != null) {
			circle.setOnMouseClicked(e -> {
				if (game.getActivePlayerColour() == game.getPlayers().get(0)
						.getColour()) {
					try {
						System.out.println("marble is selected");
						selected = !selected;
						if (selected) {
							circle.setEffect(null);
							selectEffect();
						} else
							deselectEffect();
					} catch (Exception exc) {
						System.out.println(exc.getMessage());
						((GameView) this.getScene().getRoot())
								.showExceptionWindow(exc.getMessage());
					}
				}
			});

			DropShadow shadow = new DropShadow(15,
					getFXColor(marble.getColour()));
			shadow.setOffsetX(2);
			shadow.setOffsetY(2);
			shadow.setSpread(0.2);
			circle.setEffect(shadow);

			circle.setOnMouseEntered(e -> {
				if (!selected
						&& game.getActivePlayerColour() == game.getPlayers()
								.get(0).getColour()) {
					this.setScaleX(1.3);
					this.setScaleY(1.3);
				}
			});

			circle.setOnMouseExited(e -> {
				if (!selected
						&& game.getActivePlayerColour() == game.getPlayers()
								.get(0).getColour()) {
					this.setScaleX(1);
					this.setScaleY(1);
				}
			});
		}
	}
<<<<<<< Updated upstream
=======
	// added effect to playable Marbles
	public void showGoldenRing() {
	    DropShadow glow = new DropShadow();
	    glow.setRadius(10);
	    glow.setColor(Color.GOLD);
	    glow.setSpread(0.5);

	    ScaleTransition scale = new ScaleTransition(Duration.millis(200), this);
	    scale.setToX(1.1);
	    scale.setToY(1.1);
	    scale.play();

	    this.setEffect(glow);
	}

	public void clearEffect() {
	    this.setEffect(null);
	    this.setScaleX(1.0);
	    this.setScaleY(1.0);
	}


>>>>>>> Stashed changes
}