package controller;

import view.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller extends Application {
	private BoardView boardView;
	public void start(Stage stage) throws Exception{
		
		boardView = new BoardView();
		Pane view = boardView.view();
		Scene scene = new Scene(view);
		
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[]args){
		launch(args);
		//System.out.println(boardView.trackView.size());
	}
}
