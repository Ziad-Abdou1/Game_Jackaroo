package view;

import javafx.animation.*;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.player.Player;
import model.Colour;

import java.util.Random;

public class WinningView extends StackPane {
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;

    public WinningView(Player winner) {
        setPrefSize(WIDTH, HEIGHT);
        setAlignment(Pos.CENTER);

        // Dark radial background
        setStyle("-fx-background-color: radial-gradient(radius 80%, rgba(0,0,20,0.9) 0%, rgba(0,0,0,0.95) 100%);");

        // Confetti layer with capped nodes
        Pane confettiLayer = new Pane();
        confettiLayer.setPrefSize(WIDTH, HEIGHT);
        getChildren().add(confettiLayer);
        startConfettiAnimation(confettiLayer);

        // Celebration circle colored to winner
        Color baseColor = getColorValue(winner.getColour());
        RadialGradient gradient = new RadialGradient(
            0, 0,
            0.5, 0.5,
            0.5, true,
            CycleMethod.NO_CYCLE,
            new Stop(0, baseColor.brighter()),
            new Stop(1, baseColor.darker())
        );
        Circle circle = new Circle(150);
        circle.setFill(gradient);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(6);
        circle.setScaleX(0);
        circle.setScaleY(0);
        applyPulse(circle, 1.05, 1.05);

        // Texts with gentle pulsing
        Text title = new Text("Congratulations!");
        title.setFill(Color.WHITE);
        title.setFont(Font.font("Verdana", 48));
        title.setTranslateY(-400);
        applyPulse(title, 1.05, 1.5);

        Text nameText = new Text(winner.getName() + " wins!");
        nameText.setFill(Color.WHITE);
        nameText.setFont(Font.font("Verdana", 36));
        nameText.setTranslateY(-320);
        applyPulse(nameText, 1.05, 1.5);

        getChildren().addAll(circle, title, nameText);

        // Entrance animation for circle
        ScaleTransition st = new ScaleTransition(Duration.seconds(1.2), circle);
        st.setFromX(0); st.setFromY(0);
        st.setToX(1); st.setToY(1);
        st.setInterpolator(Interpolator.EASE_OUT);
        st.play();
    }

    private void startConfettiAnimation(Pane layer) {
        Random rand = new Random();
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            if (layer.getChildren().size() > 200) layer.getChildren().clear();
            Polygon confetto = createConfetto(rand);
            layer.getChildren().add(confetto);

            TranslateTransition fall = new TranslateTransition(Duration.seconds(2), confetto);
            fall.setFromX(rand.nextDouble() * WIDTH);
            fall.setFromY(-10);
            fall.setToX(fall.getFromX() + (rand.nextDouble() - 0.5) * 60);
            fall.setToY(HEIGHT + 10);

            RotateTransition rotate = new RotateTransition(Duration.seconds(2), confetto);
            rotate.setByAngle(360);

            FadeTransition fade = new FadeTransition(Duration.seconds(2), confetto);
            fade.setFromValue(1); fade.setToValue(0);

            ParallelTransition pt = new ParallelTransition(confetto, fall, rotate, fade);
            pt.setOnFinished(ev -> layer.getChildren().remove(confetto));
            pt.play();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private Polygon createConfetto(Random rand) {
        Polygon p = new Polygon(
            0.0, 0.0,
            10.0, 5.0,
            5.0, 10.0
        );
        p.setFill(Color.hsb(rand.nextDouble() * 360, 0.8, 0.9));
        return p;
    }

    private void applyPulse(Text text, double scaleTo, double duration) {
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(duration), text);
        pulse.setFromX(1); pulse.setFromY(1);
        pulse.setToX(scaleTo); pulse.setToY(scaleTo);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();
    }
    private void applyPulse(Circle text, double scaleTo, double duration) {
        ScaleTransition pulse = new ScaleTransition(Duration.seconds(duration), text);
        pulse.setFromX(1); pulse.setFromY(1);
        pulse.setToX(scaleTo); pulse.setToY(scaleTo);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();
    }

    private Color getColorValue(Colour colour) {
        switch (colour) {
            case GREEN:  return Color.web("#4CAF50");
            case RED:    return Color.web("#F44336");
            case YELLOW: return Color.web("#FFEB3B");
            case BLUE:   return Color.web("#2196F3");
            default:     return Color.WHITE;
        }
    }
}