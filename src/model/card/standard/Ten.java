package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;

public class Ten extends Standard {

    public Ten(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 10, suit, boardManager, gameManager);
    }
    
    //M2
    
    
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
//		if (!validateMarbleSize(marbles)) throw new InvalidMarbleException("Invalid marble count");
//		if (!validateMarbleColours(marbles)) throw new InvalidMarbleException("Invalid marble colours");
		if (marbles.size()==0) {
			gameManager.discardCard(gameManager.getNextPlayerColour());
		}
		else if (marbles.size()==1) {
			super.act(marbles);
		}
        
	}

	@Override
	public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return (marbles.size() == 0 || marbles.size() == 1); 
	}
		
	//

}
