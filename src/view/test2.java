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
import javafx.scene.input.MouseEvent; 
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class test2 extends Application  {
	private HPlayerCardView player ; 
	private VPalyerCardView cpu1 ;
	private HPlayerCardView cpu2 ;
	private VPalyerCardView cpu3 ;
	public void start(Stage Stage) throws Exception {
//player
		player = new HPlayerCardView(); 
//        String path = getClass().getResource("/cardss/101.png").toExternalForm();
//        String p2 = getClass().getResource("/cardss/93.png").toExternalForm();
//        String p3 = getClass().getResource("/cardss/54.png").toExternalForm();
//        String p4 = getClass().getResource("/cardss/61.png").toExternalForm();
//        player.addCard(path);
//        player.addCard(p2);
//        player.addCard(p3);
//        player.addCard(p4);
////cpu1
//  		
//  		  
//          cpu1 = new VPalyerCardView();
//          String pa1 = getClass().getResource("/cardss/101.png").toExternalForm();
//          String pa2 = getClass().getResource("/cardss/93.png").toExternalForm();
//          String pa3 = getClass().getResource("/cardss/54.png").toExternalForm();
//          String pa4 = getClass().getResource("/cardss/61.png").toExternalForm();
//          cpu1.addCard(pa1);
//          cpu1.addCard(pa2);
//          cpu1.addCard(pa3);
//          cpu1.addCard(pa4);
////cpu2
//        
//            
//          cpu2 = new HPalyerCardView();
//          String pb1 = getClass().getResource("/cardss/101.png").toExternalForm();
//          String pb2 = getClass().getResource("/cardss/93.png").toExternalForm();
//          String pb3 = getClass().getResource("/cardss/54.png").toExternalForm();
//          String pb4 = getClass().getResource("/cardss/61.png").toExternalForm();
//          cpu2.addCard(pb1);
//          cpu2.addCard(pb2);
//          cpu2.addCard(pb3);
//          cpu2.addCard(pb4);
////cpu3
//          
//            
//          cpu3 = new VPalyerCardView();
//          String pc1 = getClass().getResource("/cardss/101.png").toExternalForm();
//          String pc2 = getClass().getResource("/cardss/93.png").toExternalForm();
//          String pc3 = getClass().getResource("/cardss/54.png").toExternalForm();
//          String pc4 = getClass().getResource("/cardss/61.png").toExternalForm();
//          cpu3.addCard(pc1);
//          cpu3.addCard(pc2);
//          cpu3.addCard(pc3);
//          cpu3.addCard(pc4);
//          
//          
////board
//          BoardView board = new BoardView(1200, 1200);
//          
//          
//          GameView game = new GameView(board,player, cpu1, cpu2, cpu3);
//          
//        Scene scene = new Scene(game.drawGameView(),1700, 900);
//        Stage.setScene(scene);
//        Stage.setTitle("CardView Test");
//        Stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}

