package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import java.util.ArrayList;

import engine.Game;
import model.card.Card;
import model.player.Player;

public class HandsView extends StackPane {
	Game game;
	ArrayList<HPlayerCardView> hands;
    public HandsView(Game game) {
    	this.game = game;
        
    	draw();
    }
    
    public void draw(){
    	hands = new ArrayList<>();
    	for (Player p : game.getPlayers()){
    		HPlayerCardView hand = new HPlayerCardView(p.getHand());
    		hands.add(hand);
    	}
    	hands.get(1).setRotate(270);
    	hands.get(2).setRotate(180);
    	hands.get(3).setRotate(90);
    	
        for (HPlayerCardView hand : hands) this.getChildren().addAll(hand);

        
        this.setAlignment(hands.get(2), Pos.TOP_CENTER);
        this.setAlignment(hands.get(1), Pos.CENTER_LEFT);
        this.setAlignment(hands.get(3), Pos.CENTER_RIGHT);
        this.setAlignment(hands.get(0), Pos.BOTTOM_CENTER);
        
        this.setMaxWidth(1300); 
    }

    private Pane wrap(HPlayerCardView view, Pos alignment) {
        StackPane wrapper = new StackPane(view);
        wrapper.setAlignment(alignment);
        return wrapper;
    }
}
