package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.player.Player;

public class HomesView extends StackPane {
	private ArrayList<Player> players;
	private ArrayList<HomeZoneView> homes;
	public HomesView(ArrayList<Player> players){
		this.players = players;
		draw();
	}
	public void draw(){
		homes = new ArrayList<>();
		for (int i = 0; i < players.size(); i++){
			homes.add(new HomeZoneView(players.get(i)));
			this.getChildren().addAll(homes.get(i));
		}
        this.setAlignment(homes.get(0), Pos.TOP_CENTER);
        this.setAlignment(homes.get(1), Pos.CENTER_LEFT);
        this.setAlignment(homes.get(3), Pos.CENTER_RIGHT);
        this.setAlignment(homes.get(2), Pos.BOTTOM_CENTER);
        
        this.setMaxWidth(1000);
        this.setMaxHeight(2000);
	}
    
	
	
	private Pane wrap(HomeZoneView view, Pos alignment) {
        StackPane wrapper = new StackPane(view);
        wrapper.setAlignment(alignment);
        return wrapper;
    }
}
