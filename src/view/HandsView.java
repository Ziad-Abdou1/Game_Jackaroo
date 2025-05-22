package view;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.ArrayList;

import engine.Game;
import model.card.Card;
import model.player.Player;

public class HandsView extends StackPane {
	Game game;
	ArrayList<HPlayerCardView> hands;

	public HandsView(Game game) {
		this.game = game;
		hands = new ArrayList<>();
		draw();
	}

	public void draw() {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			// Create handView but with an *empty* list (we’ll animate cards in)
			HPlayerCardView hand = new HPlayerCardView(game, new ArrayList<>(),
					i == 0);
			hands.add(hand);
		}

		hands.get(1).setRotate(270);
		hands.get(2).setRotate(180);
		hands.get(3).setRotate(90);

		for (HPlayerCardView hand : hands) {
			this.getChildren().add(hand);
		}

		this.setAlignment(hands.get(2), Pos.TOP_CENTER);
		this.setAlignment(hands.get(1), Pos.CENTER_LEFT);
		this.setAlignment(hands.get(3), Pos.CENTER_RIGHT);
		this.setAlignment(hands.get(0), Pos.BOTTOM_CENTER);

		for (int i = 0; i < game.getPlayers().size(); i++) {
			ArrayList<Card> cardsToDraw = new ArrayList<>(game.getPlayers()
					.get(i).getHand());
			hands.get(i).setHand(new ArrayList<>()); // Start empty (clear
														// visual)
			animateDraw(i, cardsToDraw); // Animate adding them
		}
	}

	public ArrayList<HPlayerCardView> getHands() {
		return hands;
	}

	public void refresh() {
		if( game.refresh ){
			for (int i = 0; i < game.getPlayers().size(); i++) {
				ArrayList<Card> cardsToDraw = new ArrayList<>(game.getPlayers()
						.get(i).getHand());
				hands.get(i).setHand(new ArrayList<>()); // Start empty (clear
															// visual)
				animateDraw(i, cardsToDraw); // Animate adding them
			}
			game.refresh = false; 
		}else{
			for (int i = 0; i < game.getPlayers().size(); i++) {
				hands.get(i).setHand(game.getPlayers().get(i).getHand());
			}
		}
		

	}

	public void animateDraw(int playerIndex, ArrayList<Card> newCards) {
		if (playerIndex < 0 || playerIndex >= hands.size())
			return;

		HPlayerCardView handView = hands.get(playerIndex);

		SequentialTransition sequence = new SequentialTransition();

		for (Card card : newCards) {
			PauseTransition delay = new PauseTransition(Duration.millis(500));
			delay.setOnFinished(ev -> {
				handView.addCardAnimated(card);
			});
			sequence.getChildren().add(delay);
		}

		sequence.play();
	}


}
