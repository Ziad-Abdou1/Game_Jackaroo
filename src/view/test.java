package view;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class test extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button button = new Button("Click me");

        button.setOnAction(event -> {
            System.out.println("Action 1: Button clicked");

            // Create a pause for 1 second
            PauseTransition pause = new PauseTransition(Duration.seconds(1));

            pause.setOnFinished(e -> {
                System.out.println("Action 2: 1 second later");
                // Put your next action here
            });

            pause.play();
        });

        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Delayed Action Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
