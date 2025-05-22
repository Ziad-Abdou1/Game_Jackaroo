package model.card.standard;

import java.util.ArrayList;

import engine.Game;
import engine.GameManager;
import engine.board.Board;
import engine.board.BoardManager;
import engine.board.Cell;
import engine.board.CellType;
import engine.board.SafeZone;
import exception.ActionException;
import exception.IllegalMovementException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.Card;
import model.player.Marble;

public class Standard extends Card {
    private final int rank;
    private final Suit suit;
    
    public Standard(String name, String description, int rank, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException{
        this.boardManager.moveBy(marbles.get(0), rank, false);
    }
    
    
    //showing if i can play this card edit------------------------------
    
    public boolean canPlay(ArrayList<Marble> a,ArrayList<ArrayList<Cell>> listOfPaths,boolean destroy,ArrayList<SafeZone> safeZones, ArrayList<Cell> track){
    	boolean can = false;
    	ArrayList<Marble> z = new ArrayList<Marble>();
    	for(int i =0;i<a.size();i++){
    		if(!listOfPaths.get(i).isEmpty()&& validatePlay(a.get(i),listOfPaths.get(i), destroy, safeZones, track)){
    			can = true;
    			z.add(a.get(i));
    		}
    	}
    	
    	
    	return can;
    }
    
    //for one Marble
    private boolean validatePlay(Marble marble, ArrayList<Cell> fullPath,boolean destroy,ArrayList<SafeZone> safeZones, ArrayList<Cell> track){
		int startIdx;
		if(validateMarbleColour(marble)){
			boolean f = true;
			// if(marble==fullPath.get(0).getMarble()) startIdx=1;
			// else startIdx=0;
	
			startIdx = 1;
	
			Colour currClr = gameManager.getActivePlayerColour();
			int mn = fullPath.size();
			if (destroy) {
				for (int i = startIdx; i < fullPath.size(); i++) {
					Cell c = fullPath.get(i);
					if (c.getCellType() == CellType.BASE && c.getMarble() != null && track.get(getBasePosition(c.getMarble().getColour(),safeZones)) == c) {
						
						// Base Cell Blockage---------------->
						if (i < mn) {
							f = false;
							mn = i;
							break;
						}
					}
				}
	
			} else {
				for (int i = startIdx; i < fullPath.size(); i++) {
					Cell c = fullPath.get(i);
					if (c.getMarble() != null && c.getMarble().getColour() == currClr) {
						
						
						// Self Blocking------------------------->
						if (i < mn) {
							f =false;
							mn = i;
						}
						break;
					}
				}
				int cntOtherMarbles = 0;
				for (int i = startIdx; i < fullPath.size() - 1; i++) {
					Cell c = fullPath.get(i);
					if (c.getMarble() != null) {
						cntOtherMarbles++;
						if (cntOtherMarbles > 1) {
							
							// path Blockage-------------------------->
							if (i < mn) {
								f=false;
								mn = i;
							}
							break;
						}
					}
				}
				for (int i = startIdx; i < fullPath.size(); i++) {
					Cell c = fullPath.get(i);
					if (c.getCellType() == CellType.ENTRY && c.getMarble() != null && i < fullPath.size() - 1 && fullPath.get(i + 1).getCellType() == CellType.SAFE) {
					
						// Safe Zone Entry: cannot enter safe zone if any marble is in entry-------->
						if (i < mn) {
							f = false;
							mn = i;
						}
						break;
					}
				}
				for (int i = startIdx; i < fullPath.size(); i++) {
					Cell c = fullPath.get(i);
					if (c.getCellType() == CellType.BASE
							&& c.getMarble() != null
							&& track.get(getBasePosition(c.getMarble().getColour(),safeZones)) == c) {
						
						//Base Cell Blockage ----------------------------------------->
						if (i < mn) {
							f = false;
							mn = i;
						}
						break;
					}
				}
	
			}
			for (int i = 1; i < fullPath.size(); i++) {
				Cell c = fullPath.get(i);
				if (c.getCellType() == CellType.SAFE && c.getMarble() != null) {
					// marbles in their Safe Zone are safe from any interference----->
					if (i < mn) {
						f = false;
						mn = i;
					}
					break;
				}
			}
	
			if (mn < fullPath.size()) {
				f = false;
			}
			return f ;
		}
		else
			return false;
	}
    
    
    
    //helper methods----------------
    private int getBasePosition(Colour colour, ArrayList<SafeZone> safeZones) {
		for (int i = 0; i < safeZones.size(); i++) {
			if (safeZones.get(i) != null
					&& safeZones.get(i).getColour() == colour) {
				return 25 * i;
			}
		}
		return -1;
	}

}
