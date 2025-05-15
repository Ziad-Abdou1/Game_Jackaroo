package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import model.player.Player;

public class HomesView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

    
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
        this.setAlignment(homes.get(0), Pos.BOTTOM_LEFT);
        this.setAlignment(homes.get(1), Pos.BOTTOM_RIGHT);
        this.setAlignment(homes.get(3), Pos.TOP_RIGHT);
        this.setAlignment(homes.get(2), Pos.TOP_LEFT);
        
        this.setMaxWidth(300);
        this.setMaxHeight(600);
	}
    
	
	
	private Pane wrap(HomeZoneView view, Pos alignment) {
        StackPane wrapper = new StackPane(view);
        wrapper.setAlignment(alignment);
        return wrapper;
    }
}
