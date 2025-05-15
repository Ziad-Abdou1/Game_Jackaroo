//package view;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//public class GameView {
//	public static final int WINDOW_HEIGHT= 800, WINDOW_WIDTH = 1400;
//	private BorderPane root = new BorderPane();
//	BoardView board;
//	HPalyerCardView playerview;
//	VPalyerCardView cpu1;
//	HPalyerCardView cpu2;
//	VPalyerCardView cpu3;
//	public GameView(BoardView board,HPalyerCardView playerview, VPalyerCardView cpu1,HPalyerCardView cpu2, VPalyerCardView cpu3) {
//		this.board = board;
//		this.playerview = playerview;
//		this.cpu1 =  cpu1;
//		this.cpu2 = cpu2;
//		this.cpu3 = cpu3;
//		
////		root.setPrefHeight(WINDOW_HEIGHT);
////		root.setPrefHeight(WINDOW_WIDTH);
//		root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
//		// Top (centered horizontally)
//	       
//        HBox topBox = new HBox(cpu2.getCardGrid());
//        topBox.setAlignment(Pos.CENTER); // This centers it horizontally
//        root.setTop(topBox);
//
//     // Bottom (centered horizontally)
//        HBox bottomBox = new HBox(playerview.getCardGrid());
//        bottomBox.setAlignment(Pos.CENTER);
//        root.setBottom(bottomBox);
//        
//     // Center
////      board.draw().prefWidthProperty().bind(root.widthProperty().subtract(WINDOW_WIDTH/4));
////      board.draw().prefHeightProperty().bind(root.heightProperty().subtract(WINDOW_HEIGHT/4));
//       root.setCenter(board.draw());
//
//        
//        
//     // Left (centered vertically)
//        
//        VBox leftBox = new VBox(cpu3.getCardGrid());
//        leftBox.setAlignment(Pos.CENTER);
//        root.setLeft(leftBox);
//        
//     // Right (centered vertically)
//        
//        VBox rightBox = new VBox(cpu1.getCardGrid());
//        rightBox.setAlignment(Pos.CENTER);
//        root.setRight(rightBox);
//        
//
//	}
//	public BorderPane drawGameView(){
//		return root;
//	}
//}
