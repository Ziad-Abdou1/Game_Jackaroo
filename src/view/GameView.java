package view;

import model.Colour;
import model.player.Marble;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameView extends Application{
	private BoardView boardView;
	public void start(Stage Stage) throws Exception {

		Group root=new Group();
//		boardView =new BoardView(800,800);
//		root.getChildren().add(boardView.grid);
        Scene scene = new Scene(root, 800, 800);
//        root.getChildren().add(boardView.draw());
        
        Marble m=new Marble(Colour.BLUE);
        MarbleView marbleView=new MarbleView(m);
        Button btn=marbleView.drawMarble(500,500,30);   //dksjldl
 
        root.getChildren().add(btn);
        
        
        Stage.setScene(scene);
        Stage.setTitle("CardView Test");
        Stage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}
