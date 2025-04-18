package model.card.standard;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Seven extends Standard {

    public Seven(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 7, suit, boardManager, gameManager);
    }


    
    // M2 
	@Override
	public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return (marbles.size() == 1 || marbles.size() == 2); 
	}
	@Override
	public void act(ArrayList<Marble> marbles) throws ActionException,
			InvalidMarbleException {
//		if (!validateMarbleSize(marbles)) throw new InvalidMarbleException("Invalid marble count");
//		if (!validateMarbleColours(marbles)) throw new InvalidMarbleException("Invalid marble colours");
		if(marbles.size()==1){
			boardManager.moveBy(marbles.get(0), 7, false);
		}else{
			int splitdistance=boardManager.getSplitDistance();
			boardManager.moveBy(marbles.get(0), splitdistance, false);
			boardManager.moveBy(marbles.get(1), 7-splitdistance, false);
		}
	}




    
    
    //
  
}
