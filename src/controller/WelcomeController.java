package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class WelcomeController extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root StackPane (everything layered here)
        StackPane root = new StackPane();

        // Background game board image (pixel art style)
        Image boardImage = new Image("cardss/Welcome.jpg"); // use your own file path
        ImageView boardView = new ImageView(boardImage);
        boardView.setFitWidth(1200);
        boardView.setPreserveRatio(true);
 // logo
        
        
        
        Image logo = new Image("cardss/logo.png"); // Add "file:" if loading from local
        ImageView logoview = new ImageView(logo);
        logoview.setFitWidth(800); // Optional sizing
        logoview.setPreserveRatio(true);
        //
     // UI overlay - e.g., Start Game button
        Image Stbutton = new Image("cardss/bbb.png"); // Add "file:" if loading from local
        ImageView StbuttonView = new ImageView(Stbutton);
        StbuttonView.setFitWidth(500); // Optional sizing
        StbuttonView.setPreserveRatio(true);

        Text label = new Text("Start Game");
        label.setFont(Font.font("Verdana", 24));
        label.setFill(javafx.scene.paint.Color.WHITE);
        //settings

        StackPane StartButton = new StackPane(StbuttonView, label); // Stack text over image
        StartButton.setAlignment(Pos.CENTER);

        Image settbut = new Image("cardss/bbb.png"); // Add "file:" if loading from local
        ImageView SettingbuttonView = new ImageView(settbut);
        SettingbuttonView.setFitWidth(500); // Optional sizing
        SettingbuttonView.setPreserveRatio(true);

        Text label2 = new Text("Settings");
        label2.setFont(Font.font("Verdana", 24));
        label2.setFill(javafx.scene.paint.Color.WHITE);

        StackPane SettingsButton = new StackPane(SettingbuttonView, label2); // Stack text over image
        SettingsButton.setAlignment(Pos.CENTER);

// Info
        Image Infobutton = new Image("cardss/bbb.png"); // Add "file:" if loading from local
        ImageView InfobuttonView = new ImageView(Infobutton);
        InfobuttonView.setFitWidth(500); // Optional sizing
        InfobuttonView.setPreserveRatio(true);

        Text label3 = new Text("Information");
        label3.setFont(Font.font("Verdana", 24));
        label3.setFill(javafx.scene.paint.Color.WHITE);

        StackPane InfoButton = new StackPane(InfobuttonView, label3); // Stack text over image
        StartButton.setAlignment(Pos.CENTER);

        // Title overlay
        StartButton.setTranslateY(0);
        SettingsButton.setTranslateY(100);
        InfoButton.setTranslateY(200);
        logoview.setTranslateY(350); // Move it up from center

        // Add everything to StackPane
        root.getChildren().addAll(boardView,logoview, StartButton,SettingsButton,InfoButton);
        StackPane.setAlignment(StbuttonView, Pos.CENTER);
        StackPane.setAlignment(logoview, Pos.TOP_CENTER);

        // Scene setup
        Scene scene = new Scene(root);
        primaryStage.setTitle("Jackaroo Game - StackPane UI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
