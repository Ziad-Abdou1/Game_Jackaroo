package model.card.standard;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;

public class Four  extends Standard {

    public Four(String name, String description, Suit suit, BoardManager boardManager, GameManager gameManager) {
        super(name, description, 4, suit, boardManager, gameManager);
    }
  
}
