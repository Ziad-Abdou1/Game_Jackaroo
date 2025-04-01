package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Ace extends Standard {

    public Ace(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 1, suit, boardManager, gameManager);
    }
    // M2
    
    

    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
    	
    	if (marbles == null || marbles.isEmpty()) {
            return true; 
        }
        
        Colour activeColour = gameManager.getActivePlayerColour();
        for (Marble marble : marbles) {
            if (marble.getColour() != activeColour) {
                return false; 
            }
        }
        return true;
    }


    //

}
