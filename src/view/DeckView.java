package view;

import java.util.ArrayList;

import model.card.Card;
import model.card.Deck;
import engine.Game;
import javafx.scene.layout.StackPane;

public class DeckView extends StackPane {
    private static int CARD_COUNT = 7;  
    private static final double X_OFFSET = 2.5;  
    private static final double Y_OFFSET = 3.0; 

    private final Game game;

    public DeckView(Game game) {
        this.game = game;
        this.getStyleClass().add("deck-view");
        draw();
    }

    public void draw() {
        ArrayList<Card> deck = Deck.cardsPool;
        int cardsToShow = Math.min(CARD_COUNT, deck.size());

        this.getChildren().clear();

        for (int i = cardsToShow - 1; i >= 0; i--) {
            Card card = deck.get(i);
            CardView cv = new CardView(game, card, false);

            cv.setTranslateX(i * X_OFFSET);
            cv.setTranslateY(-i * Y_OFFSET);

            cv.getStyleClass().add("deck-card");
            this.getChildren().add(cv);
        }
    }

}