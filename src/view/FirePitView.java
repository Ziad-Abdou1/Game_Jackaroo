package view;

import java.util.ArrayList;

import engine.Game;
import exception.ActionException;
import exception.InvalidMarbleException;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import model.card.Card;
import model.player.Marble;

public class FirePitView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
    
    
	private final Game game;
	private final Rectangle pitBg;
	CardView topCardView;

	private static final double WIDTH_RATIO = 0.05; // 20% of screen width
	private static final double HEIGHT_RATIO = 0.05; // 15% of screen height

	public FirePitView(Game game) {
		this.game = game;
		topCardView = null;
		// background rectangle
		pitBg = new Rectangle();
		pitBg.setFill(Color.rgb(50, 50, 50, 0.3)); // translucent gray
		pitBg.setStroke(Color.DARKGRAY);
		pitBg.setStrokeWidth(2);

		// make sure the rectangle resizes with the pane
		pitBg.widthProperty().bind(this.widthProperty());
		pitBg.heightProperty().bind(this.heightProperty());

		// add only the rect for now
		this.getChildren().add(pitBg);
		StackPane.setAlignment(pitBg, Pos.CENTER);

		// set a preferred size relative to your GameView’s dimensions
		// you can adjust these ratios as you like
		 this.setPrefHeight(screenWidth * WIDTH_RATIO);
		 this.setPrefWidth(screenHeight *HEIGHT_RATIO);
		 this.setMaxSize(screenHeight *HEIGHT_RATIO,screenWidth * WIDTH_RATIO);
		//this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
		draw();
	}

	/**
	 * Call this whenever the fire-pit changes (after a turn). It will remove
	 * the old top card view (if any) and show the newest one.
	 */
	public void setTopCardView(CardView topCardView){
		this.topCardView=topCardView;
	}
	
	public Card getTopCard(){
		Card ret = null;
		for (Card c : game.getFirePit()){
			if (c != null) ret = c;
		}
		return ret;
	}
	
	public void draw() {
		// remove old top card if present
		if (topCardView != null) {
			this.getChildren().remove(topCardView);
			topCardView = null;
		}
		// get the latest card
		int size = game.getFirePit().size();
		Card topCard = getTopCard();
		if (topCard != null) {
			topCardView = new CardView(game, topCard, true);

			// scale it down so it fits nicely inside the pit
			topCardView.setScaleX(0.8);
			topCardView.setScaleY(0.8);

			this.getChildren().add(topCardView);
			StackPane.setAlignment(topCardView, Pos.CENTER);
		}	
	}
	public void refresh() {
	    Card topCard = getTopCard();
	    if (topCard != null) {
	        if (topCardView == null) {
	            topCardView = new CardView(game, topCard, true);
	            topCardView.setScaleX(0.8);
	            topCardView.setScaleY(0.8);
	            this.getChildren().add(topCardView);
	        } else {
	            topCardView.setCard(topCard);
	        }
	    } else {
	        this.getChildren().clear();
	        topCardView = null;
	    }
	}
//	public void refresh(){
//		Card topCard = getTopCard();
//		if (topCard!=null) {
//			topCardView.setCard(topCard);
//			if (this.getChildren().size()==0) this.getChildren().add(topCardView);
//		}
//		else{
//			this.getChildren().clear();
//		}
//	}
}
