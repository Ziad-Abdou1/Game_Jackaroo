package view;



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
		Group  view = new Group();  
        view.getChildren().add(player.getCardGrid());
        String path = getClass().getResource("/cardss/101.png").toExternalForm();
//
//        player.addCard(path);
//        player.addCard("file:/D:/GUC/CSEN401 Game/Game project/JackarooM1/src/cardss/101.png");
//        player.addCard("file:/D:/GUC/CSEN401 Game/Game project/JackarooM1/src/cardss/101.png");
//        player.addCard("file:/D:/GUC/CSEN401 Game/Game project/JackarooM1/src/cardss/101.png");
        Scene scene = new Scene(view, 550, 200);
        Stage.setScene(scene);
        Stage.setTitle("CardView Test");
        Stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}
