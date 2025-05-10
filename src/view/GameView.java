package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameView {
	
	private BorderPane root = new BorderPane();
	public GameView(BoardView board,HPalyerCardView playerview, VPalyerCardView cpu1,HPalyerCardView cpu2, VPalyerCardView cpu3) {
		
		// Top (centered horizontally)
	       
        HBox topBox = new HBox(cpu2.getCardGrid());
        topBox.setAlignment(Pos.CENTER); // This centers it horizontally
        root.setTop(topBox);

     // Bottom (centered horizontally)
        HBox bottomBox = new HBox(playerview.getCardGrid());
        bottomBox.setAlignment(Pos.CENTER);
        root.setBottom(bottomBox);
        
     // Center
        
        root.setCenter(board.draw());
        
     // Left (centered vertically)
        
        VBox leftBox = new VBox(cpu3.getCardGrid());
        leftBox.setAlignment(Pos.CENTER);
        root.setLeft(leftBox);
        
     // Right (centered vertically)
        
        VBox rightBox = new VBox(cpu1.getCardGrid());
        rightBox.setAlignment(Pos.CENTER);
        root.setRight(rightBox);
        

	}
	public BorderPane drawGameView(){
		return root;
	}
}
