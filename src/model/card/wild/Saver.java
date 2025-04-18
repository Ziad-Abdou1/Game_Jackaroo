package model.card.wild;

import java.util.ArrayList;

import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Saver extends Wild {

    public Saver(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }

	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
//		if (!validateMarbleSize(marbles)) throw new InvalidMarbleException("Invalid marble count");
//		if (!validateMarbleColours(marbles)) throw new InvalidMarbleException("Invalid marble colours");
		boardManager.sendToSafe(marbles.get(0));
	}
    
}
