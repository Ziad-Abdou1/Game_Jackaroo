package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Jack extends Standard {

    public Jack(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 11, suit, boardManager, gameManager);
    }
    
    // M2 
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {	
    	Colour playerColour = gameManager.getActivePlayerColour();
    	if( marbles.size() == 1){
    		Marble m=marbles.get(0);
    		return m.getColour() == playerColour ; 
    		
    	}else if (marbles.size() == 2 ){
    		Marble m1 = marbles.get(0); Marble m2 = marbles.get(1);
			// i considered both situations, first is mine second is not and first is not mine and second is
			return (m1.getColour() == (playerColour) &&
					!(m2.getColour() == (playerColour)) )  || (!(m1.getColour() == (playerColour)) &&
					m2.getColour().equals(playerColour));
    		
    	}else{
    		return false ; 
    	}
      
    }

	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
		if (!validateMarbleColours(marbles)) throw new InvalidMarbleException("Invalid marble colours");
		if (!validateMarbleSize(marbles)) throw new InvalidMarbleException("invalid marble count");
		if (marbles.size()==1) {
			super.act(marbles);
		}
		else if (marbles.size()==2) {
			boardManager.swap(marbles.get(0),marbles.get(1));
		}
	}
    //

}
