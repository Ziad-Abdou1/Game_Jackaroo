package view;

import java.util.ArrayList;

import engine.Game;
import model.card.Card;
import model.card.standard.*;
import model.card.wild.Burner;
import model.card.wild.Saver;
import model.card.wild.Wild;
import model.player.Marble;
import javafx.animation.ScaleTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.util.Duration;

public class CardView extends ImageView {
	private Card card;
	private Game game;

	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	private boolean orientation;

	public CardView(Game game, Card card, boolean f) {
//		f=true;
		this.game = game;
		this.orientation = f;
		this.card = card;
		refresh();
		action();
	}

	public void action() {
		refresh();



		this.setOnMouseClicked(e -> {
			try {

				if (game.getPlayers().get(0).getSelectedCard()==this.card) game.deselectAll();
				else {
					game.deselectAll();
					game.selectCard(this.getCard());
				}
				refresh();
				System.out.println("card is selected");
			} catch (Exception exc) {
				System.out.println(exc.getMessage());
				((GameView)this.getScene().getRoot()).showExceptionWindow(exc.getMessage());
			}
		    ((GameView)this.getScene().getRoot()).refresh();
		});
	}

	public void hover(boolean f) {
		if (game.getPlayers().get(0).getHand().contains(card)) {
			if (game.getPlayers().get(0).getSelectedCard() == this.card) {
				return;
			}
			if (f) {
				this.setScaleX(1.2);
				this.setScaleY(1.2);
			} else {
				this.setScaleX(1.0);
				this.setScaleY(1.0);
			}
		}

	}

	private void drawCard() {
		this.setImage(new Image(getPath()));
		this.setPreserveRatio(true);
		this.setFitWidth(screenWidth * 0.04);
		this.setFitHeight(screenHeight * 0.1);
	}

	private void drawCPUCard() {
		this.setImage(new Image("cardss/backCard2.jpeg"));
		this.setPreserveRatio(true);
		this.setFitWidth(screenWidth * 0.04);
		this.setFitHeight(screenHeight * 0.1);
	}

	public void setCard(Card card) {
		this.card = card;
		refresh();
	}

	public void formatSelected() {
	    // 1) Add a colored glow around the card
	    DropShadow glow = new DropShadow();
	    glow.setColor(Color.web("#4A90E2", 0.7)); // adjust to your theme
	    glow.setRadius(15);
	    glow.setSpread(0.5);
	    this.setEffect(glow);

	    // 2) Scale up with a smooth animation
	    ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
	    st.setToX(1.2);
	    st.setToY(1.2);
	    st.play();

	    // 3) Optional: tint a colored border
	    this.setStyle(
	      "-fx-border-color: #4A90E2;" +
	      "-fx-border-width: 4;" +
	      "-fx-border-radius: 8;"
	    );
	}

	public void formatNotSelected() {
	    // 1) Remove glow
	    this.setEffect(null);

	    // 2) Scale back smoothly
	    ScaleTransition st = new ScaleTransition(Duration.millis(200), this);
	    st.setToX(1.0);
	    st.setToY(1.0);
	    st.play();

	    // 3) Clear border
	    this.setStyle("");
	}

	public void refresh() {
		if (game.getPlayers().get(0).getSelectedCard() != this.card)
			formatNotSelected();
		else
			formatSelected();
		if (orientation)
			drawCard();
		else
			drawCPUCard();
	}

	public String getPath() {
		if (card == null) {
			return "cardss/baby.jpg";
		}
		String path = "cardss/";
		if (this.card instanceof Standard) {
			Standard c = (Standard) this.card;
			if (c.getRank() >= 2 && c.getRank() <= 10) {
				path += "" + c.getRank();
			}
			if (c.getRank() == 1)
				path += "A";
			if (c.getRank() == 11)
				path += "J";
			if (c.getRank() == 12)
				path += "Q";
			if (c.getRank() == 13)
				path += "K";

			if (c.getSuit() == Suit.CLUB)
				path += "1";
			if (c.getSuit() == Suit.SPADE)
				path += "2";
			if (c.getSuit() == Suit.HEART)
				path += "3";
			if (c.getSuit() == Suit.DIAMOND)
				path += "4";
			path += ".png";
		} else {
			if (this.card instanceof Burner)
				path += "Burner.jpeg";
			if (this.card instanceof Saver)
				path += "Saver.jpeg";
		}

		return path;
	}

	public Card getCard() {
		return card;
	}

}
