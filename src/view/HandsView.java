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
		hands = new ArrayList<>();
		draw();
	}

	public void draw() {

		for (int i = 0; i < game.getPlayers().size(); i++) {
			HPlayerCardView hand = new HPlayerCardView(game, game.getPlayers()
					.get(i).getHand(), i == 0);
			hands.add(hand);
		}
		hands.get(1).setRotate(270);
		hands.get(2).setRotate(180);
		hands.get(3).setRotate(90);

		for (HPlayerCardView hand : hands)
			this.getChildren().addAll(hand);

		this.setAlignment(hands.get(2), Pos.TOP_CENTER);
		this.setAlignment(hands.get(1), Pos.CENTER_LEFT);
		this.setAlignment(hands.get(3), Pos.CENTER_RIGHT);
		this.setAlignment(hands.get(0), Pos.BOTTOM_CENTER);

	}

	public void refresh() {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			hands.get(i).setHand(game.getPlayers().get(i).getHand());
		}
	}

	public ArrayList<HPlayerCardView> getHands() {
		return hands;
	}

	private Pane wrap(HPlayerCardView view, Pos alignment) {
		StackPane wrapper = new StackPane(view);
		wrapper.setAlignment(alignment);
		return wrapper;
	}

}
