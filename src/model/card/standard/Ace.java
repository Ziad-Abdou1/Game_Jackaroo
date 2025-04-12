package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import engine.board.CellType;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Ace extends Standard {

    public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 1, suit, boardManager, gameManager);
    }
    // M2
    
    @Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
		if (!validateMarbleSize(marbles)) throw new InvalidMarbleException();
		if (marbles.size()==0) {
			gameManager.fieldMarble();
		}
		else if (marbles.size()==1) {
			super.act(marbles);
		}
	}

    //

}
