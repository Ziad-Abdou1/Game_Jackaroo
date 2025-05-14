//package view;
//
//
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.Border;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.BorderStroke;
//import javafx.scene.layout.BorderStrokeStyle;
//import javafx.scene.layout.BorderWidths;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//import javafx.scene.input.MouseEvent; 
//import javafx.application.Application;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//import javafx.stage.Stage;
//
//
//public class test extends Application{
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		BoardView boardView=new BoardView(800, 800);
//		
//		
//		Scene scene=new Scene(boardView.draw());
//		System.out.println(scene.getHeight());
//		stage.setScene(scene);
//		stage.show();
//
//	}
//	public static void main(String[] args) {
//		launch();
//	}
//}