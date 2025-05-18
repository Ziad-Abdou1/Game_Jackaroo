package view;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.player.Player;

/**
 * A view that shows when a player wins the game.
 * Displays the player's name and colour with built-in styling and animations.
 */
public class WinningView extends StackPane {
    private static final double WIDTH = 600;
    private static final double HEIGHT = 400;

	private final Player winner;
    private Button continueButton;

    public WinningView(Player winner) {
        this.winner = winner;
        this.continueButton = new Button("Replay");

        // Configure this StackPane
        setPrefSize(WIDTH, HEIGHT);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: radial-gradient(radius 100%, rgba(0,0,0,0.8) 0%, rgba(0,0,0,0.95) 100%);");

        // Background circle
        Circle bg = new Circle(1000);
        bg.setFill(getColorValue(winner.getColour()));
        // bg.setStroke(Color.WHITE);
        bg.setStrokeWidth(4);

        // Headline text
        Text headline = new Text("Congratulations!");
        headline.setFill(Color.WHITE);
        headline.setFont(Font.font(36));
        headline.setOpacity(0);
        headline.setTranslateY(-40);

        // Winner name text
        Text nameText = new Text(winner.getName() + " wins!");
        // nameText.setFill(getColorValue(winner.getColour()));
        nameText.setFill(Color.WHITE);
        nameText.setFont(Font.font(28));
        nameText.setOpacity(0);
        nameText.setTranslateY(10);

        // Continue button styling
        continueButton.setFont(Font.font(16));
        continueButton.setTranslateY(60);
        continueButton.setStyle(
            "-fx-background-radius: 6;" +
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0.3, 0, 2);");
        continueButton.setOnMouseEntered(e -> continueButton.setStyle(
            "-fx-background-radius: 6;" +
            "-fx-background-color: #f0f0f0;" +
            "-fx-text-fill: black;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0.3, 0, 2);")
        );
        continueButton.setOnMouseExited(e -> continueButton.setStyle(
            "-fx-background-radius: 6;" +
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0.3, 0, 2);")
        );

        // Add nodes to pane
        getChildren().addAll(bg, headline, nameText, continueButton);

        // Play entrance animation with slower timing
        playEntranceAnimation(bg, headline, nameText);
    }

    // Map player colour to JavaFX Color
    private Color getColorValue(model.Colour colour) {
        switch (colour) {
            case GREEN:  return Color.web("#4CAF50");
            case RED:    return Color.web("#F44336");
            case YELLOW: return Color.web("#FFEB3B");
            case BLUE:   return Color.web("#2196F3");
            default:     return Color.WHITE;
        }
    }

    // Animate background and texts with extended durations
    private void playEntranceAnimation(Circle bg, Text headline, Text nameText) {
        ScaleTransition scale = new ScaleTransition(Duration.seconds(3), bg);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(10);
        scale.setToY(10);

        FadeTransition fadeHead = new FadeTransition(Duration.seconds(2), headline);
        fadeHead.setFromValue(0);
        fadeHead.setToValue(1);

        FadeTransition fadeName = new FadeTransition(Duration.seconds(2), nameText);
        fadeName.setFromValue(0);
        fadeName.setToValue(1);

        SequentialTransition seq = new SequentialTransition(scale, fadeHead, fadeName);
        seq.play();
    }

    /**
     * Returns the Continue button so external handlers can be attached.
     */
    public Button getContinueButton() {
        return continueButton;
    }
}
