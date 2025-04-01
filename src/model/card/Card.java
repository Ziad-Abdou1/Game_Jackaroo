package model.card;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

import java.util.*;

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
    public  boolean validateMarbleSize(ArrayList<Marble> marbles){
    	return false ;
    }
    public  boolean validateMarbleColours(ArrayList<Marble> marbles){
		return false;
    	
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
