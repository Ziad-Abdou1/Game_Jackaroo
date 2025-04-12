package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.card.Card;
import model.player.Marble;

public class Standard extends Card {
    private final int rank;
    private final Suit suit;

    public Standard(String name, String description, int rank, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
		if (!validateMarbleSize(marbles)) throw new InvalidMarbleException("Invalid marble count"); // we need to ask about this
		//if so , then we need to add also validateMarbleColours(marbles) , but I don't know which exception we should throw.
		if(!validateMarbleColours(marbles)) throw new InvalidMarbleException("Invalid marble colours");
		boardManager.moveBy(marbles.get(0), rank, false);
        
	}
   
  
}
