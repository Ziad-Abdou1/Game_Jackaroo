package view;



import java.util.ArrayList;

import model.card.Card;
import engine.Game;
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
import javafx.scene.input.MouseEvent; 
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class test extends Application{

	@Override
	public void start(Stage stage) throws Exception {
//		BoardView boardView=new BoardView();
		Game game=new Game("Adham");
		BoardView boardView=new BoardView(game.getBoard());
		boardView.setMaxSize(200, 200);
		ArrayList<Card> a1=game.getPlayers().get(0).getHand();
		ArrayList<Card> a2=game.getPlayers().get(1).getHand();
		ArrayList<Card> a3=game.getPlayers().get(2).getHand();
		ArrayList<Card> a4=game.getPlayers().get(3).getHand();
		HandsView h=new HandsView(a3, a2, a1, a4);
		StackPane st=new StackPane();
		st.setMaxSize(1400, 900);
		st.getChildren().addAll(h,boardView);
		StackPane.setAlignment(boardView, Pos.CENTER);
		st.setPadding(new Insets(20));
		Scene scene=new Scene(st,1400,900);
		System.out.println(scene.getHeight());
		stage.setScene(scene);
		stage.show();

	}
	public static void main(String[] args) {
		launch();
	}
}