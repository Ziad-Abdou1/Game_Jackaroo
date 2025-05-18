package view;

import java.util.ArrayList;

import model.card.Card;
import model.card.Deck;
import engine.Game;
import javafx.scene.layout.StackPane;

public class DeckView extends StackPane {
    private static int CARD_COUNT = 7;    // How many cards to show
    private static final double X_OFFSET = 2.5;  // Horizontal offset per card
    private static final double Y_OFFSET = 3.0;  // Vertical offset per card

    private final Game game;

    public DeckView(Game game) {
        this.game = game;
        this.getStyleClass().add("deck-view");
        draw();
    }

    public void draw() {
        ArrayList<Card> deck = Deck.cardsPool;
        int cardsToShow = Math.min(CARD_COUNT, deck.size());

        // Clear any previous children if redraw is called
        this.getChildren().clear();

        // Stack them so the “top” card is really on top
        for (int i = cardsToShow - 1; i >= 0; i--) {
            Card card = deck.get(i);
            CardView cv = new CardView(game, card, false);

            // Shift each card a little for a fanned stack
            cv.setTranslateX(i * X_OFFSET);
            cv.setTranslateY(-i * Y_OFFSET);

            cv.getStyleClass().add("deck-card");
            this.getChildren().add(cv);
        }
    }

}