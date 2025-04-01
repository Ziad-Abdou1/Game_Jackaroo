package model.player;

import java.util.ArrayList;

import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.Card;


public class Player {
    private final String name;
    private final Colour colour;
    // representing the marbles each player has in their Home Zone.
    private ArrayList<Card> hand;
    private final ArrayList<Marble> marbles;
    // representing the card to be played.
    private Card selectedCard;
    //representing the marbles to be played.
	private final ArrayList<Marble> selectedMarbles;

    public Player(String name, Colour colour) {
        this.name = name;
        this.colour = colour;
        this.hand = new ArrayList<>();
        this.selectedMarbles = new ArrayList<>();
        this.marbles = new ArrayList<>();
        
        for (int i = 0; i < 4; i++) {
            this.marbles.add(new Marble(colour));
        }
        
        //default value
        this.selectedCard = null;
    }
    
    
    
    // M2
    public void regainMarble(Marble marble){
    	marbles.add(marble);
    }
    public Marble getOneMarble(){
    	if(marbles.isEmpty()){
    		return null ;
    	}else{
    		return marbles.get(0);
    	}
    }
    public void selectCard(Card card) throws InvalidCardException{
    	if (!hand.contains(card)) {
            throw new InvalidCardException("Card not found in player's hand");
        }
        
        this.selectedCard = card;
    }
    public void selectMarble(Marble marble) throws InvalidMarbleException {
        if (selectedMarbles.size() >= 2) {
            throw new InvalidMarbleException("Cannot select more than two marbles");
        }
        // he don't say to add it , i don't sure if it ok 
        if (!marbles.contains(marble)) {
            throw new InvalidMarbleException("Marble does not belong to player");
        }
        selectedMarbles.add(marble);
    }
    public void deselectAll() {
        selectedCard = null;
        selectedMarbles.clear();
    }
    // not completed 
    public void play() throws GameException {
        if (selectedCard == null) {
            throw new InvalidCardException("No card selected");
        }

       
    }
    
  

    //

    public String getName() {
        return name;
    }

    public Colour getColour() {
        return colour;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    
    public ArrayList<Marble> getMarbles() {
		return marbles;
	}
    
    public Card getSelectedCard() {
        return selectedCard;
    }

}
