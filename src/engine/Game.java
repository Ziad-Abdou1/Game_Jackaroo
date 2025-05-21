package engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import engine.board.Board;
import engine.board.SafeZone;
import exception.CannotDiscardException;
import exception.CannotFieldException;
import exception.GameException;
import exception.IllegalDestroyException;
import exception.InvalidCardException;
import exception.InvalidMarbleException;
import exception.SplitOutOfRangeException;
import model.Colour;
import model.card.Card;
import model.card.Deck;
import model.player.*;


public class Game implements GameManager {
	private final List<GameListener> listeners = new ArrayList<>();
	
	 
    private final Board board;
    private final ArrayList<Player> players;
    // representing which of the players should play next.
	private int currentPlayerIndex;
	//  representing the cards that have been played / discarded in the game.
    private final ArrayList<Card> firePit;
    
    private int turn;

    public Game(String playerName) throws IOException {
        turn = 0;
        currentPlayerIndex = 0;
        firePit = new ArrayList<>();

        ArrayList<Colour> colourOrder = new ArrayList<>();
        
        colourOrder.addAll(Arrays.asList(Colour.values()));
        
        Collections.shuffle(colourOrder);
        
        // how he send "this" 
        this.board = new Board(colourOrder, this);
        
        // Loads the card pool from the deck to ensure all cards are ready for gameplay
        Deck.loadCardPool(this.board, (GameManager)this);
        
        this.players = new ArrayList<>();
        this.players.add(new Player(playerName, colourOrder.get(0)));
        
        for (int i = 1; i < 4; i++) 
            this.players.add(new CPU("CPU " + i, colourOrder.get(i), this.board));
        
        for (int i = 0; i < 4; i++) 
            this.players.get(i).setHand(Deck.drawCards());
        
    }
    
  //------Milestone 2------
    public void selectCard(Card card) throws InvalidCardException{ // selects a card to the current player
    	players.get(currentPlayerIndex).selectCard(card); 
    }
    public void selectMarble(Marble marble) throws InvalidMarbleException{ // selects a marble to the current player
    	players.get(currentPlayerIndex).selectMarble(marble);
    }
    public void deselectAll(){
    	players.get(currentPlayerIndex).deselectAll();
    }
    public void editSplitDistance(int splitDistance) throws SplitOutOfRangeException{
    	if(splitDistance > 6 || splitDistance <1 ){
    		throw new SplitOutOfRangeException("The splitDistance is out of Range");
    	}
    	
    	board.setSplitDistance(splitDistance); //The splitDistance represents how far the first marble moves, with the remainder applied to the second marble (7 - splitDistance).
    	
    }
    public boolean canPlayTurn(){ // checks if the player still can play in this turn or not.
    	if(players.get(currentPlayerIndex).getHand().size()+turn != 4){
    		return false;
    	}
    	return true;
    }
    public void playPlayerTurn() throws GameException{ // starts the player's turn.
    	players.get(currentPlayerIndex).play();
    }
    public void endPlayerTurn(){ // ends the player's turn.
    	Card c=players.get(currentPlayerIndex).getSelectedCard();
//    	if(c!=null){
    		firePit.add(c);
    		players.get(currentPlayerIndex).getHand().remove(c);
//    	}
//    	System.out.println(firePit.size());
    	players.get(currentPlayerIndex).deselectAll();
    	currentPlayerIndex=(currentPlayerIndex+1)%4; 
    	if(currentPlayerIndex==0)
    		turn++;
    	if(turn == 4){
    		for(int i =0;i<4;i++){
    			if(Deck.getPoolSize()<4){
    				Deck.refillPool(firePit);
    				firePit.clear();
    			}
    			players.get(i).setHand(Deck.drawCards());
    		}
    		turn = 0;
    	}
    	
    	//suppose for simplicity that a trap will happen here. what should I write here as a code?
//    	notifyTrap(1);
    	
    }
    public Colour checkWin(){ //checks if anyone has won.
//    	return players.get(2).getColour();
    	for(SafeZone s:board.getSafeZones()){
    		if(s.isFull())
    			return s.getColour();
    	}
    	
    	return null;
    }
    public void sendHome(Marble marble){ // finds who's marble and send it to his home.
    	for(Player p :players){
    		if(marble.getColour()==(p.getColour())){
    			p.regainMarble(marble);
    			break;
    		}
    	}
    }
    public void fieldMarble() throws CannotFieldException, IllegalDestroyException{ // tries to take a marble from the home zone and put it in the field(Base cell).
    	Player p=players.get(currentPlayerIndex);
    	Marble m=p.getOneMarble();
    	if(m == null)
    		throw new CannotFieldException("No Marbles left in the Home!");
    	board.sendToBase(m);
    	p.getMarbles().remove(m);
    }
    public void discardCard(Colour colour) throws CannotDiscardException{ // removes a random card from the player with the given colour.
    	for(Player p : players){
    		if(p.getColour()==(colour)){
    			ArrayList<Card> temp = p.getHand();
    			if(temp.size()==0)
    				throw new CannotDiscardException("No Cards to be discarded!");
    			int randomindex = (int)(Math.random()*temp.size());
    			Card c=temp.remove(randomindex);
    			firePit.add(c);
    			break;
    		}
    		
    	}
    }
    public void discardCard() throws CannotDiscardException{ //Discards a random card from the hand of a random player colour other than the current.
    	int randomindex = currentPlayerIndex;
    	while(randomindex==currentPlayerIndex){
    		randomindex= (int)(Math.random()*4);
    	}
    	discardCard(players.get(randomindex).getColour());
    }
    public Colour getActivePlayerColour(){ // Returns the colour of the current player.
    	return players.get(currentPlayerIndex).getColour();
    }
    public Colour getNextPlayerColour(){ // Returns the colour of the next player.
    	return players.get((currentPlayerIndex+1)%4).getColour();
    }

    //--------------------------------------------------------------------------
	public ArrayList<Card> getFirePit() {
		return firePit;
	}

	public Board getBoard() {
		return board;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}
	

    
	
    public void addListener(GameListener l) {
        listeners.add(l);
    }

//    /** Call this whenever you detect trapped marbles. */
//    private void notifyTrap(int trappedCount) {
//        for (GameListener l : listeners) {
//            l.onTrap(trappedCount);
//        }
//    }


}
