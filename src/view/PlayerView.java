package view;

import engine.Game;
import model.Colour;
import model.player.Player;
import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import javafx.util.Duration;

public class PlayerView extends GridPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();

	private StackPane wrapper1;
	private StackPane wrapper;
	private StackPane wrapper2;

	private Game game;
	private Player player;
	private Circle fallbackCircle;
	private Circle coloredRing;
	private ImageView playerImageView;
	private Label name;
	private Label nextLabel;

	private Timeline pulseTimeline;
	private DropShadow glowEffect;

	public PlayerView(Player player, Game game) {
		this.game = game;
		this.player = player;
		fallbackCircle = new Circle();
		name = new Label(player.getName());
		nextLabel = new Label("Next");

		name.getStyleClass().add("player-name");
		name.setTextFill(Color.LIGHTBLUE);

		nextLabel = new Label("Next");

		nextLabel.getStyleClass().removeAll("next-label");
		nextLabel.setId(null);

		nextLabel.setTextFill(Color.LIGHTBLUE);
		nextLabel.setAlignment(Pos.CENTER);
		draw();
	}

	public boolean active() {
		return game.getActivePlayerColour() == player.getColour();
	}

	public boolean isNext() {
		Colour nextColour = game.getNextPlayerColour();
		return game.getPlayers().stream()
				.anyMatch(p -> p.getColour() == nextColour && p == player);
	}

	public void refresh() {
		if (active()) {
			startPulseEffect();
		} else {
			stopPulseEffect();
		}
		wrapper2.setOpacity(isNext() ? 1 : 0);
	}

	private void draw() {
		this.getChildren().clear();
		double radius = screenWidth / 40;

		try {
			Image image = null ; 
			if (player.getColour() == Colour.BLUE) {
				 image = new Image(getClass().getResource("/playerBlue.jpg")
						.toExternalForm());
			}
			if (player.getColour() == Colour.GREEN) {
				 image = new Image(getClass().getResource("/playerGreen.jpg")
						.toExternalForm());
			}
			if (player.getColour() == Colour.RED) {
				 image = new Image(getClass().getResource("/playerRed.jpg")
						.toExternalForm());
			}
			if (player.getColour() == Colour.YELLOW) {
				 image = new Image(getClass().getResource("/playeryellow.jpg")
						.toExternalForm());
			}
			
			playerImageView = new ImageView(image);
			playerImageView.setFitWidth(radius * 2);
			playerImageView.setFitHeight(radius * 2);
			playerImageView.setClip(new Circle(radius, radius, radius));
		} catch (Exception e) {
			playerImageView = null;
		}

		fallbackCircle.setRadius(radius);
		fallbackCircle.setFill(GameView.toFxColor(player.getColour()));

		coloredRing = new Circle(radius + 5);
		Color ringColor = GameView.toFxColor(player.getColour());
		coloredRing.setStroke(ringColor);
		coloredRing.setStrokeWidth(3);
		coloredRing.setFill(null);

		wrapper1 = new StackPane();
		wrapper1.getChildren().add(coloredRing);

		if (playerImageView != null) {
			wrapper1.getChildren().add(playerImageView);
		} else {
			wrapper1.getChildren().add(fallbackCircle);
		}
		wrapper1.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

		if (active()) {
			startPulseEffect();
		}

		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		addNode(wrapper1, 0, 0);

		wrapper = new StackPane(name);
		addNode(wrapper, 0, 1);

		wrapper2 = new StackPane(nextLabel);
		addNode(wrapper2, 0, 2);
		wrapper2.setOpacity(isNext() ? 1 : 0);
	}

	private void startPulseEffect() {
		if (pulseTimeline != null
				&& pulseTimeline.getStatus() == Animation.Status.RUNNING) {
			return;
		}

		glowEffect = new DropShadow();
		glowEffect.setColor(GameView.toFxColor(player.getColour()));
		glowEffect.setRadius(20);
		glowEffect.setSpread(0.5);
		glowEffect.setOffsetX(0);
		glowEffect.setOffsetY(0);

		coloredRing.setEffect(glowEffect);

		pulseTimeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(
				coloredRing.strokeWidthProperty(), 3, Interpolator.EASE_BOTH),
				new KeyValue(glowEffect.radiusProperty(), 10,
						Interpolator.EASE_BOTH), new KeyValue(
						glowEffect.spreadProperty(), 0.3,
						Interpolator.EASE_BOTH), new KeyValue(
						coloredRing.opacityProperty(), 0.7,
						Interpolator.EASE_BOTH)), new KeyFrame(
				Duration.seconds(1), new KeyValue(
						coloredRing.strokeWidthProperty(), 8,
						Interpolator.EASE_BOTH),
				new KeyValue(glowEffect.radiusProperty(), 25,
						Interpolator.EASE_BOTH), new KeyValue(
						glowEffect.spreadProperty(), 0.6,
						Interpolator.EASE_BOTH), new KeyValue(
						coloredRing.opacityProperty(), 1.0,
						Interpolator.EASE_BOTH)));
		pulseTimeline.setAutoReverse(true);
		pulseTimeline.setCycleCount(Animation.INDEFINITE);
		pulseTimeline.play();
	}

	private void stopPulseEffect() {
		if (pulseTimeline != null) {
			pulseTimeline.stop();
		}
		if (coloredRing != null) {
			coloredRing.setStrokeWidth(3);
			coloredRing.setOpacity(1.0);
			coloredRing.setEffect(null);
		}
	}

	public void addNode(Node node, int col, int row) {
		this.add(node, col, row);
	}
}
