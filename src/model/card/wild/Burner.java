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
    	if (marbles == null || marbles.isEmpty()) {
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
		// TODO Auto-generated method stub
		
	}
	// 

    
    
}
