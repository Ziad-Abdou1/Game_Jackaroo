package model.card;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

import java.util.*;

import model.Colour;
import model.player.Marble;

public abstract class Card {
	private final String name;
    private final String description;
    protected BoardManager boardManager;
    protected GameManager gameManager;

    public Card(String name, String description, BoardManager boardManager, GameManager gameManager) {
        this.name = name;
        this.description = description;
        this.boardManager = boardManager;
        this.gameManager = gameManager;
    }
    
    // M2  it's not completed 
    
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
    	// i think i handle Jack in wrong way 
        if (this instanceof model.card.standard.Jack || this instanceof model.card.standard.Seven) {
            return (marbles.size() == 1 || marbles.size() == 2); 
        } 
        else if (this instanceof model.card.standard.King || this instanceof model.card.standard.Ten || this instanceof model.card.standard.Queen || this instanceof model.card.standard.Ace) {
            return (marbles.size() == 0 || marbles.size() == 1); 
        } 
        else {
            return marbles.size() == 1; 
        }
    }

    public boolean validateMarbleColours(ArrayList<Marble> marbles) {	
    	if (marbles == null || marbles.isEmpty()) {
            return true; 
        }
        
    	Colour playerColour = gameManager.getActivePlayerColour();
        for (Marble marble : marbles) {
            if (marble.getColour() != playerColour) {
                return false; 
            }
        }
        return true;	
    }

    public abstract void act(ArrayList<Marble> marbles) throws ActionException,InvalidMarbleException;
   
    //

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    
}
