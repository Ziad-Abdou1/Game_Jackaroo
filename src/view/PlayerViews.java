package view;

import java.util.ArrayList;

import model.player.Player;
import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class PlayerViews extends StackPane {
	Game game;
	ArrayList<PlayerView> playerViews;
	public PlayerViews(Game game){
		this.game = game;
		draw();
	}
	
	public void draw(){
		playerViews = new ArrayList<>();
		for (Player p : game.getPlayers()){
			PlayerView playerView = new PlayerView(p);
			playerViews.add(playerView);
		}
    	playerViews.get(1).setRotate(90);
    	playerViews.get(2).setRotate(180);
    	playerViews.get(3).setRotate(270);
//
        this.setAlignment(playerViews.get(2), Pos.TOP_CENTER);
        this.setAlignment(playerViews.get(1), Pos.CENTER_LEFT);
        this.setAlignment(playerViews.get(3), Pos.CENTER_RIGHT);
        this.setAlignment(playerViews.get(0), Pos.BOTTOM_CENTER);
        for (PlayerView pv : playerViews) this.getChildren().add(pv);
        

	}

	public ArrayList<PlayerView> getPlayerViews() {
		return playerViews;
	}

	public void setPlayerViews(ArrayList<PlayerView> playerViews) {
		this.playerViews = playerViews;
	}
	
}
