package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.player.Marble;


public class Five extends Standard {

    public Five(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 5, suit, boardManager, gameManager);
    }
    
    // M2
    public boolean validateMarbleSize(ArrayList<Marble> marbles) {
        return marbles != null && marbles.size() == 1;
    }
    @Override
    public boolean validateMarbleColours(ArrayList<Marble> marbles) {
        return true ; 
    }

    @Override
    public void act(ArrayList<Marble> marbles) throws ActionException, InvalidMarbleException {
        if (marbles == null || marbles.size() != 1) {
            throw new InvalidMarbleException("Five card requires exactly 1 marble");
        }
        boardManager.moveBy(marbles.get(0), 5, false);
    }
    //

}
