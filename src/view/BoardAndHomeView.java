package view;

import java.util.ArrayList;

import model.player.Player;
import engine.Game;
import engine.board.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;

public class BoardAndHomeView extends StackPane {
	BoardView boardView;
	Game game;
	ArrayList<Player> players;
	ArrayList<HomeZoneView> homes;
	
	public BoardAndHomeView (ArrayList<Player> players , Board board, Game game){
		boardView = new BoardView(game.getBoard(), game);

		this.game = game;
		this.players = players;
		draw();

	}

	private void draw() {
		this.getChildren().addAll(boardView);
		homes = new ArrayList<>();
		for (int i = 0; i < players.size(); i++) {
			homes.add(new HomeZoneView(players.get(i), game));
			this.getChildren().addAll(homes.get(i));
		}
		// Insets(top, right, bottom, left)

		this.setAlignment(homes.get(0), Pos.BOTTOM_LEFT);
		this.setMargin(homes.get(0), new Insets(50,50 , 50 , 50 ));

		this.setAlignment(homes.get(1), Pos.TOP_LEFT);
		this.setMargin(homes.get(1), new Insets(50,50 , 50 , 50 ));

		this.setAlignment(homes.get(2), Pos.TOP_RIGHT);
		this.setMargin(homes.get(2), new Insets(50,50 , 50 , 50 ));

		this.setAlignment(homes.get(3), Pos.BOTTOM_RIGHT);
		this.setMargin(homes.get(3), new Insets(50,50 , 50 , 50 ));

		this.setAlignment(boardView , Pos.CENTER);
		
		
	}
	public void refresh() {
		boardView.refresh();
		for (int i = 0; i < players.size(); i++) {
			homes.get(i).refresh();
		}
	}

}
