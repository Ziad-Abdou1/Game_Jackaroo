package model.player;

import java.util.ArrayList;

import exception.ActionException;
import exception.GameException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import model.Colour;
import model.card.Card;
import engine.GameManager;


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
    //  Adds a specified marble to the player’s collection of marbles 
    public void regainMarble(Marble marble){
    	if (marble.getColour() != this.colour) {
            throw new IllegalArgumentException("Marble color must match player's color");
        }
        marbles.add(marble);
    }
    
    //  Returns the first marble without removing it form marbles ArrayList
    public Marble getOneMarble() {
        return marbles.isEmpty() ? null : marbles.get(0);
    }
    
    //  Checks if the given card is available in the player’s hand and sets it to the selectedCard
    public void selectCard(Card card) throws InvalidCardException {
        if (!hand.contains(card)) {
            throw new InvalidCardException("Card not in player's hand");
        }
        this.selectedCard = card;
    }
    // Selects a marble to be used in the game by adding it to the selectedMarbles.
    public void selectMarble(Marble marble) throws InvalidMarbleException {
        if (selectedMarbles.size() >= 2) {
            throw new InvalidMarbleException("Cannot select more than two marbles");
        }
        // he don't say to add it , i don't sure if it ok 
        Colour marbleColour = marble.getColour();
        if (colour!= marbleColour) {
            throw new InvalidMarbleException("Marble does not belong to player");
        }
        selectedMarbles.add(marble);
    }
    
    // Clears all selections 
    public void deselectAll() {
        selectedCard = null;
        selectedMarbles.clear();
    }
    
    public void play() throws GameException {
    	if (selectedCard == null) {
            throw new InvalidCardException("No card selected");
        }
        if (!selectedCard.validateMarbleSize(selectedMarbles)) {
            throw new InvalidMarbleException("Invalid number of marbles for this card");
        }
        if (!selectedCard.validateMarbleColours(selectedMarbles)) {
            throw new InvalidMarbleException("Invalid marble colors for this card");
        }
        
        ///   not sure this is right 
        try {
            selectedCard.act(selectedMarbles);
        } catch (ActionException | InvalidMarbleException e) {
            deselectAll(); // Clean up on failure
            throw e; // Re-throw for game to handle
        }

        if (selectedCard instanceof model.card.standard.Ace || selectedCard instanceof model.card.standard.King) {
            if (selectedMarbles.isEmpty()) {
                if (!marbles.isEmpty()) {
                    marbles.remove(0);
                }
            }
        }
        deselectAll();
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
