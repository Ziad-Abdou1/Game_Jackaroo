package view;

import java.util.ArrayList;

import com.sun.prism.paint.Color;

import engine.Game;
import model.card.Card;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class HPlayerCardView extends HBox {
	Game game;
	ArrayList<Card> hand;
	ArrayList<CardView> handView;
	private boolean orientation;

	public HPlayerCardView(Game game, ArrayList<Card> hand, boolean f) {
		this.game = game;
		this.orientation = f;
		this.hand = hand;
		handView = new ArrayList<CardView>();
		draw();

		this.setSpacing(20);
		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	}

	public ArrayList<CardView> getCardViews() {
		return handView;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
		draw();
	}

	public void draw() {
		handView.clear();
		this.getChildren().clear();
		for (Card c : hand) {
			CardView cv = new CardView(game, c, orientation);
			handView.add(cv);
			this.getChildren().add(cv);
		}
	}
	
	public void addCardAnimated(Card card) {
		CardView cardView = new CardView(game, card, orientation); // Create card view
		hand.add(card);                  // Add to the logical hand
		handView.add(cardView);         // Track in visual hand view

		cardView.setScaleX(0);          // Start tiny
		cardView.setScaleY(0);
		this.getChildren().add(cardView); // Add to UI

		// Animate the scale-up (pop-in effect)
		ScaleTransition scale = new ScaleTransition(Duration.millis(700), cardView);
		scale.setToX(1);
		scale.setToY(1);
		scale.play();
	}

}
