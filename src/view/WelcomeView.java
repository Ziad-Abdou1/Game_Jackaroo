package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Pos;

public class WelcomeView extends StackPane {
    private String userName;
    private TextField nameField;
    StackPane startButton;

    public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public StackPane getStartButton() {
		return startButton;
	}

	public void setStartButton(StackPane startButton) {
		this.startButton = startButton;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public WelcomeView() {
        // Background image
        Image boardImage = new Image("cardss/Welcome.jpg");
        ImageView boardView = new ImageView(boardImage);
        boardView.setFitWidth(1200);
        boardView.setPreserveRatio(true);

        // Logo
        Image logo = new Image("cardss/logo.png");
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(800);
        logoView.setPreserveRatio(true);
        logoView.setTranslateY(300);

        // Name input prompt
        Text prompt = new Text("Enter your name:");
        prompt.setFont(Font.font("Verdana", 20));
        prompt.setFill(javafx.scene.paint.Color.WHITE);

        nameField = new TextField();
        nameField.setMaxWidth(300);
        nameField.setPromptText("Your name");

        VBox nameBox = new VBox(10, prompt, nameField);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setTranslateY(150);

        // Start Game button
        Image startBtnImg = new Image("cardss/bbb.png");
        ImageView startBtnView = new ImageView(startBtnImg);
        startBtnView.setFitWidth(500);
        startBtnView.setPreserveRatio(true);
        Text startLabel = new Text("Start Game");
        startLabel.setFont(Font.font("Verdana", 24));
        startLabel.setFill(javafx.scene.paint.Color.WHITE);
        startButton = new StackPane(startBtnView, startLabel);
        startButton.setAlignment(Pos.CENTER);
        startButton.setTranslateY(0);
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> storeUserName());
        startButton.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

//        // Settings button
//        Image settingsImg = new Image("cardss/bbb.png");
//        ImageView settingsView = new ImageView(settingsImg);
//        settingsView.setFitWidth(500);
//        settingsView.setPreserveRatio(true);
//        Text settingsLabel = new Text("Settings");
//        settingsLabel.setFont(Font.font("Verdana", 24));
//        settingsLabel.setFill(javafx.scene.paint.Color.WHITE);
//        StackPane settingsButton = new StackPane(settingsView, settingsLabel);
//        settingsButton.setAlignment(Pos.CENTER);
//        settingsButton.setTranslateY(100);
//
//        // Information button
//        Image infoImg = new Image("cardss/bbb.png");
//        ImageView infoView = new ImageView(infoImg);
//        infoView.setFitWidth(500);
//        infoView.setPreserveRatio(true);
//        Text infoLabel = new Text("Information");
//        infoLabel.setFont(Font.font("Verdana", 24));
//        infoLabel.setFill(javafx.scene.paint.Color.WHITE);
//        StackPane infoButton = new StackPane(infoView, infoLabel);
//        infoButton.setAlignment(Pos.CENTER);
//        infoButton.setTranslateY(200);

        // Assemble
        this.getChildren().addAll(boardView, logoView, nameBox, startButton);
        StackPane.setAlignment(logoView, Pos.TOP_CENTER);
    }

    private void storeUserName() {
        this.userName = nameField.getText();
    }

    /**
     * Returns the name entered by the user.
     * @return the user name, or null if not set
     */
    public String getUserName() {
        return userName;
    }
}
