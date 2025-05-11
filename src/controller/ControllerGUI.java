package controller;

import model.card.Card;
import engine.Game;
import view.BoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.*;

public class ControllerGUI extends Application {
	public void start(Stage stage) throws Exception{
		Game game = new Game("sa3d nabiha");
		BoardView bv = new BoardView(800,800);
		HPalyerCardView cv1 = new HPalyerCardView();
		for (Card c : game.getPlayers().get(0).getHand()){
			//System.out.println(c.getName());
			cv1.addCard(c);
		}
		VPalyerCardView cv2 = new VPalyerCardView();
		for (Card c : game.getPlayers().get(1).getHand()){
			cv2.addCard(c);
		}
		HPalyerCardView cv3 = new HPalyerCardView();
		for (Card c : game.getPlayers().get(2).getHand()){
			cv3.addCard(c);
		}
		VPalyerCardView cv4 = new VPalyerCardView();
		for (Card c : game.getPlayers().get(3).getHand()){
			cv4.addCard(c);
		}
		GameView gv = new GameView(bv,cv1,cv2,cv3,cv4);
		BorderPane grid = gv.drawGameView();
		
//		while (game.checkWin()==null){
//			
//			//grid = gv.drawGameView();
//		}
		
		Scene scene = new Scene(grid);
		stage.setScene(scene);
		stage.show();
	}
	public static void main(String[]args){
		launch(args);
	}
}
