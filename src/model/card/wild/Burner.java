package model.card.wild;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Burner extends Wild {

    public Burner(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager); 
    }

    
    // M2
    


	@Override
	 public boolean validateMarbleColours(ArrayList<Marble> marbles) {	
    	if (marbles == null || marbles.isEmpty()) {//Morkos: I think this if condition will not be entered, because this is checked already in validateMarbleSize() function
            return false; // i donot know what should i return 
        }
    	Colour playerColour = gameManager.getActivePlayerColour();
        for (Marble marble : marbles) {
            if (marble.getColour() == playerColour) {
                return false; 
            }
        }
        return true;	
    }



	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
//		if (!validateMarbleSize(marbles)) throw new InvalidMarbleException("Invalid marble count");
//		if (!validateMarbleColours(marbles)) throw new InvalidMarbleException("Invalid marble colours");
		boardManager.destroyMarble(marbles.get(0));
	}
	// 

    
    
}
