package view;

import java.util.ArrayList;

import model.player.Player;
import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;

public class PlayerViews extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
    
	Game game;
	ArrayList<PlayerView> playerViews;
	public PlayerViews(Game game){
		this.game = game;
		draw();
	}
	
	public void draw(){
		playerViews = new ArrayList<>();
		for (Player p : game.getPlayers()){
			PlayerView playerView = new PlayerView(p,game);
			playerViews.add(playerView);
		}
//    	playerViews.get(1).setRotate(90);
//    	playerViews.get(2).setRotate(180);
//    	playerViews.get(3).setRotate(270);
//
    	
    	StackPane.setMargin(playerViews.get(0), new Insets(0, screenWidth*0.3, 0, 0));
    	StackPane.setMargin(playerViews.get(1), new Insets(0, 0, screenWidth*0.3, 0));
    	StackPane.setMargin(playerViews.get(2), new Insets(0, 0, 0, screenWidth*0.3));
    	StackPane.setMargin(playerViews.get(3), new Insets(screenWidth*0.3, 0, 0, 0));
        this.setAlignment(playerViews.get(2), Pos.TOP_CENTER);
        this.setAlignment(playerViews.get(1), Pos.CENTER_LEFT);
        this.setAlignment(playerViews.get(3), Pos.CENTER_RIGHT);
        this.setAlignment(playerViews.get(0), Pos.BOTTOM_CENTER);
        for (PlayerView pv : playerViews) this.getChildren().add(pv);
        

	}

	public void refresh(){
		for (int i = 0; i < game.getPlayers().size(); i++){
			playerViews.get(i).refresh();
		}
	}
	
	public ArrayList<PlayerView> getPlayerViews() {
		return playerViews;
	}

	public void setPlayerViews(ArrayList<PlayerView> playerViews) {
		this.playerViews = playerViews;
	}
	
}
