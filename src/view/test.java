package view;



import model.Colour;
import model.card.Card;
import model.player.Marble;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class test extends Application  {
	private PalyerCardView player ; 
	public void start(Stage Stage) throws Exception {
		
		
		player = new PalyerCardView();
		CradView card1 =  player.addCard("file:/D:/GUC/CSEN401 Game/Game project/JackarooM1/src/cardss/A4.png");
		CradView card2 =  player.addCard("file:/D:/GUC/CSEN401 Game/Game project/JackarooM1/src/cardss/72.png");   
		CradView card3 =  player.addCard("file:/D:/GUC/CSEN401 Game/Game project/JackarooM1/src/cardss/21.png");   
		CradView card4 =  player.addCard("file:/D:/GUC/CSEN401 Game/Game project/JackarooM1/src/cardss/32.png");   

		card1.drawCard().setOnAction(e -> {
		    System.out.println("Card 1 clicked");
		    player.removeCard(card1);
		});

		card2.drawCard().setOnAction(e -> {
		    System.out.println("Card 2 clicked");
		    player.removeCard(card2);
		});

		card3.drawCard().setOnAction(e -> {
		    System.out.println("Card 3 clicked");
		    player.removeCard(card3);
		});

		card4.drawCard().setOnAction(e -> {
		    System.out.println("Card 4 clicked");
		    player.removeCard(card4);
		});
	
		Group root = new Group();
		root.getChildren().add(player.getCardGrid());
		Scene scene = new Scene(root, 550, 200);

		Stage.setTitle("ziad");
		Stage.setScene(scene);
		Stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}
