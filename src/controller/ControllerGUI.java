//package controller;
//
//import java.util.ArrayList;
//
//import model.card.Card;
//import model.player.Marble;
//import engine.Game;
//import engine.board.Cell;
//import view.BoardView;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//import view.*;
//
//public class ControllerGUI extends Application {
//	public void start(Stage stage) throws Exception{
//		Game game = new Game("sa3d nabiha");
//		ArrayList<Cell> track=game.getBoard().getTrack();
//		ArrayList<ArrayList<Marble>> homes=new ArrayList<>();
//		for(int i=0;i<4;i++){
//			homes.add(game.getPlayers().get(i).getMarbles());
//		}
//		BoardView bv = new BoardView(400,400,track,homes);
//		HPlayerCardView cv1 = new HPlayerCardView();
//		for (Card c : game.getPlayers().get(0).getHand()){
//			//System.out.println(c.getName());
//			cv1.addCard(c);
//		}
//		VPalyerCardView cv2 = new VPalyerCardView();
//		for (Card c : game.getPlayers().get(1).getHand()){
//			cv2.addCard(c);
//		}
//		HPlayerCardView cv3 = new HPlayerCardView();
//		for (Card c : game.getPlayers().get(2).getHand()){
//			cv3.addCard(c);
//		}
//		VPalyerCardView cv4 = new VPalyerCardView();
//		for (Card c : game.getPlayers().get(3).getHand()){
//			cv4.addCard(c);
//		}
//		GameView gv = new GameView(bv,cv1,cv2,cv3,cv4);
//		BorderPane grid = gv.drawGameView();
//		
//		bv.refresh();
//		
//		
//		
//		
//		
//		Scene scene = new Scene(grid);
//		stage.setScene(scene);
//		stage.show();
//	}
//	public static void main(String[]args){
//		launch(args);
//	}
//}
