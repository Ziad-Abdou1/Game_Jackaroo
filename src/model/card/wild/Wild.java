package model.card.wild;

import java.util.ArrayList;

import engine.GameManager;
import engine.board.BoardManager;
import exception.ActionException;
import exception.InvalidMarbleException;
import model.card.Card;
import model.player.Marble;

public abstract class Wild extends Card {

    public Wild(String name, String description, BoardManager boardManager, GameManager gameManager) {
        super(name, description, boardManager, gameManager);
    }
   

}
