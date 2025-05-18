package Scenes;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.player.Player;

/**
 * A scene that shows when a player wins the game.
 * Displays the player's name and colour with built-in inline styling and animations.
 */
public class WinningScene {
    private static final double WIDTH = 600;
    private static final double HEIGHT = 400;
    private final Player winner;
    private final Stage stage;

    public WinningScene(Stage stage, Player winner) {
        this.stage = stage;
        this.winner = winner;
    }

    public void show() {
        // Root layout
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        // Inline background style (radial gradient)
        root.setStyle("-fx-background-color: radial-gradient(radius 100%, rgba(0,0,0,0.8) 0%, rgba(0,0,0,0.95) 100%);");

        // Background circle in player's colour
        Circle bg = new Circle(0);
        bg.setFill(getColorValue(winner.getColour()));
        bg.setStyle("-fx-stroke: white; -fx-stroke-width: 4;");

        // Congratulatory text
        Text headline = new Text("Congratulations!");
        headline.setFill(Color.WHITE);
        headline.setFont(Font.font(36));
        headline.setOpacity(0);
        headline.setTranslateY(-40);

        // Player name text
        Text nameText = new Text(winner.getName() + " wins!");
        nameText.setFill(getColorValue(winner.getColour()));
        nameText.setFont(Font.font(28));
        nameText.setOpacity(0);
        nameText.setTranslateY(10);

        // Continue button
        Button btn = new Button("Continue");
        btn.setFont(Font.font(16));
        btn.setTranslateY(60);
        btn.setStyle(
            "-fx-background-radius: 6;" +
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0.3, 0, 2);");
        btn.setOnMouseEntered(e -> btn.setStyle(
            "-fx-background-radius: 6;" +
            "-fx-background-color: #f0f0f0;" +
            "-fx-text-fill: black;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0.3, 0, 2);")
        );
        btn.setOnMouseExited(e -> btn.setStyle(
            "-fx-background-radius: 6;" +
            "-fx-background-color: white;" +
            "-fx-text-fill: black;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 6, 0.3, 0, 2);")
        );
        btn.setOnAction(e -> stage.close());

        root.getChildren().addAll(bg, headline, nameText, btn);

        // Build and show scene
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();

        // Play animations
        playEntranceAnimation(bg, headline, nameText);
    }

    // Converts enum Colour to JavaFX Color
    private Color getColorValue(model.Colour colour) {
        switch (colour) {
            case GREEN: return Color.web("#4CAF50");
            case RED: return Color.web("#F44336");
            case YELLOW: return Color.web("#FFEB3B");
            case BLUE: return Color.web("#2196F3");
            default: return Color.WHITE;
        }
    }

    private void playEntranceAnimation(Circle bg, Text headline, Text nameText) {
        // Circle grows
        ScaleTransition scale = new ScaleTransition(Duration.seconds(1), bg);
        scale.setFromX(0);
        scale.setFromY(0);
        scale.setToX(10);
        scale.setToY(10);

        // Fade in headline
        FadeTransition fadeHead = new FadeTransition(Duration.seconds(1), headline);
        fadeHead.setFromValue(0);
        fadeHead.setToValue(1);

        // Fade in name text
        FadeTransition fadeName = new FadeTransition(Duration.seconds(1), nameText);
        fadeName.setFromValue(0);
        fadeName.setToValue(1);

        SequentialTransition seq = new SequentialTransition(
            scale,
            fadeHead,
            fadeName
        );
        seq.play();
    }
    
}

// Usage example:
// new WinningScene(primaryStage, winningPlayer).show();
