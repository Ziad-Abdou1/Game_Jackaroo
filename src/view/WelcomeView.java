package view;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WelcomeView extends StackPane {
    Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
    double ratioScreenWidth = screenWidth / 1920;
    double ratioScreenHeight = screenHeight / 1080;

    private String userName;
    private TextField nameField;
    private Button startButton;

    public WelcomeView() {
        // Background image (optional)
        Image bgImage = new Image("cardss/backgound.jpg");
        ImageView bgView = new ImageView(bgImage);
        bgView.setFitWidth(screenWidth);
        bgView.setFitHeight(screenHeight);

        // Marble animation layer
        Pane marbleLayer = new Pane();
        marbleLayer.setPrefSize(screenWidth, screenHeight);
        addBouncingMarbles(marbleLayer);

        // Prompt
        Text prompt = new Text("Enter your name:");
        prompt.setFont(Font.font("Verdana", 24));
        prompt.setFill(Color.WHITE);

        // Styled TextField
        nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setFont(Font.font("Verdana", 20));
        nameField.setMaxWidth(300);
        nameField.setStyle("-fx-background-color: rgba(255,255,255,0.1);"
                + "-fx-text-fill: white;"
                + "-fx-prompt-text-fill: gray;"
                + "-fx-background-radius: 15;"
                + "-fx-padding: 10;"
                + "-fx-border-color: white;"
                + "-fx-border-radius: 15;");

        // Error message
        Text errorText = new Text("Please enter a valid name");
        errorText.setFont(Font.font("Verdana", 16));
        errorText.setFill(Color.RED);
        errorText.setVisible(false);

        // Custom start button
        startButton = new Button("Start Game");
        startButton.getStyleClass().add("start-button");

        // Layout
        VBox nameBox = new VBox(10 * ratioScreenHeight, prompt, nameField, errorText, startButton);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setTranslateY(150 * ratioScreenHeight);

        // Add nodes
        this.getChildren().addAll(bgView, marbleLayer, nameBox);

        // Actions
        startButton.setOnAction(e -> handleNameInput(errorText));
        nameField.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                handleNameInput(errorText);
            }
        });

        // Apply CSS
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
    }

    private void handleNameInput(Text errorText) {
        String input = nameField.getText().trim();
        if (input.isEmpty()) {
            errorText.setVisible(true);
            nameField.setStyle("-fx-background-color: rgba(255,255,255,0.1);"
                    + "-fx-text-fill: white;"
                    + "-fx-prompt-text-fill: gray;"
                    + "-fx-background-radius: 15;"
                    + "-fx-padding: 10;"
                    + "-fx-border-color: red;"
                    + "-fx-border-radius: 15;");
        } else {
            errorText.setVisible(false);
            userName = input;
            nameField.setStyle("-fx-background-color: rgba(255,255,255,0.1);"
                    + "-fx-text-fill: white;"
                    + "-fx-prompt-text-fill: gray;"
                    + "-fx-background-radius: 15;"
                    + "-fx-padding: 10;"
                    + "-fx-border-color: white;"
                    + "-fx-border-radius: 15;");
        }
    }

    public TextField getNameField() {
        return nameField;
    }

    public Button getStartButton() {
        return startButton;
    }

    public String getUserName() {
        return userName;
    }

    // === Marbles ===
    private void addBouncingMarbles(Pane layer) {
        List<Marble> marbles = new ArrayList<>();
        Random rand = new Random();
        int maxMarbles = 15;  // fewer because of collision cost
        double radius = 40;

        Color[] baseColors = new Color[] {
            Color.RED,
            Color.BLUE,
            Color.YELLOW,
            Color.GREEN
        };

        AnimationTimer timer = new AnimationTimer() {
            long lastAddTime = 0;

            @Override
            public void handle(long now) {
                if (marbles.size() < maxMarbles && now - lastAddTime > 1_500_000_000) {
                    lastAddTime = now;
                    double x = radius + rand.nextDouble() * (screenWidth - 2 * radius);
                    double y = radius + rand.nextDouble() * (screenHeight - 2 * radius);
                    double dx = -3 + rand.nextDouble() * 6;
                    double dy = -3 + rand.nextDouble() * 6;

                    Color base = baseColors[rand.nextInt(baseColors.length)];
                    Circle circle = new Circle(radius);
                    circle.setCenterX(x);
                    circle.setCenterY(y);
                    circle.setOpacity(0);

                    circle.setFill(new RadialGradient(
                        0, 0,
                        0.4, 0.4,
                        1, true,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, Color.WHITE),
                        new Stop(0.4, base.brighter()),
                        new Stop(1, base.darker().darker())
                    ));

                    Marble marble = new Marble(circle, dx, dy);
                    marbles.add(marble);
                    layer.getChildren().add(circle);
                }

                // Update positions & handle wall collisions
                for (Marble m : marbles) {
                    Circle c = m.circle;
                    c.setCenterX(c.getCenterX() + m.dx);
                    c.setCenterY(c.getCenterY() + m.dy);

                    if (c.getCenterX() - radius <= 0 || c.getCenterX() + radius >= screenWidth) m.dx *= -1;
                    if (c.getCenterY() - radius <= 0 || c.getCenterY() + radius >= screenHeight) m.dy *= -1;

                    // Fade in/out
                    if (c.getOpacity() < 1 && !m.fadingOut) {
                        c.setOpacity(Math.min(1.0, c.getOpacity() + 0.02));
                    } else if (m.life-- < 0) {
                        m.fadingOut = true;
                        c.setOpacity(c.getOpacity() - 0.02);
                        if (c.getOpacity() <= 0) {
                            layer.getChildren().remove(c);
                        }
                    }
                }

               
                marbles.removeIf(m -> m.fadingOut && m.circle.getOpacity() <= 0);

                for (int i = 0; i < marbles.size(); i++) {
                    Marble m1 = marbles.get(i);
                    Circle c1 = m1.circle;
                    for (int j = i + 1; j < marbles.size(); j++) {
                        Marble m2 = marbles.get(j);
                        Circle c2 = m2.circle;

                        double dx = c2.getCenterX() - c1.getCenterX();
                        double dy = c2.getCenterY() - c1.getCenterY();
                        double dist = Math.sqrt(dx * dx + dy * dy);
                        if (dist < radius * 2 && dist > 0) {
                            // Calculate normal and tangent vectors
                            double nx = dx / dist;
                            double ny = dy / dist;

                            // Relative velocity
                            double vx = m1.dx - m2.dx;
                            double vy = m1.dy - m2.dy;

                            // Velocity along normal
                            double vn = vx * nx + vy * ny;

                            // If moving towards each other
                            if (vn < 0) {
                                // Elastic collision impulse
                                double impulse = -2 * vn / 2; // equal mass

                                // Update velocities
                                m1.dx += impulse * nx;
                                m1.dy += impulse * ny;
                                m2.dx -= impulse * nx;
                                m2.dy -= impulse * ny;

                                // Separate overlapping marbles
                                double overlap = radius * 2 - dist;
                                c1.setCenterX(c1.getCenterX() - nx * overlap / 2);
                                c1.setCenterY(c1.getCenterY() - ny * overlap / 2);
                                c2.setCenterX(c2.getCenterX() + nx * overlap / 2);
                                c2.setCenterY(c2.getCenterY() + ny * overlap / 2);
                            }
                        }
                    }
                }
            }
        };
        timer.start();
    }

    private static class Marble {
        Circle circle;
        double dx, dy;
        int life = 400; // frames before fade out
        boolean fadingOut = false;

        Marble(Circle circle, double dx, double dy) {
            this.circle = circle;
            this.dx = dx;
            this.dy = dy;
        }
    }
}
