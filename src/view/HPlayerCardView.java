package view;

import java.util.ArrayList;

import com.sun.prism.paint.Color;

import engine.Game;
import engine.board.Cell;
import model.card.Card;
import model.card.standard.Standard;
import model.player.Marble;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class HPlayerCardView extends HBox {
	Game game;
	ArrayList<Card> hand;
	ArrayList<CardView> handView;
	private boolean orientation;

	public HPlayerCardView(Game game, ArrayList<Card> hand, boolean f) {
		this.game = game;
		this.orientation = f;
		this.hand = hand;
		handView = new ArrayList<CardView>();
		draw();

		this.setSpacing(20);
		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	}

	public ArrayList<CardView> getCardViews() {
		return handView;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
		
		draw();
	}

	public void draw() {
		handView.clear();
		this.getChildren().clear();
		for (Card c : hand) {
			CardView cv = new CardView(game, c, orientation);
			handView.add(cv);
			this.getChildren().add(cv);
		}
		
	}
	
	public void addCardAnimated(Card card) {
		CardView cardView = new CardView(game, card, orientation); // Create card view
		hand.add(card);                  // Add to the logical hand
		handView.add(cardView);         // Track in visual hand view

		cardView.setScaleX(0);          // Start tiny
		cardView.setScaleY(0);
		this.getChildren().add(cardView); // Add to UI

		// Animate the scale-up (pop-in effect)
		ScaleTransition scale = new ScaleTransition(Duration.millis(700), cardView);
		scale.setToX(1);
		scale.setToY(1);
		scale.play();
	}
	public void canPlayEffect() {
	    for (CardView cv : handView) {
	        boolean playable = canPlayCard(cv.getCard());
	        cv.setPlayable(playable); // Triggers internal refresh
	    }
	}

	//Can Play Card
	public boolean canPlayCard(Card c){
		boolean f = false;
		if(c instanceof Standard){
			Standard card = (Standard)c;
			int rank =card.getRank();
			ArrayList<Marble> marbles = game.getBoard().getActionableMarbles();
			ArrayList<ArrayList<Cell>> fullPaths =new ArrayList<>();
			
			switch(rank){
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 8:
			case 9:
				for(Marble m : marbles){
					fullPaths.add(game.getBoard().createFullPath(m, rank));
				}
				f=card.canPlay(marbles, fullPaths,false,game.getBoard().getSafeZones(),game.getBoard().getTrack());
				break;
				
			case 1:
				for(Marble m : marbles){
					fullPaths.add(game.getBoard().createFullPath(m, rank));
				}
				f=card.canPlay(marbles, fullPaths,false,game.getBoard().getSafeZones(),game.getBoard().getTrack()) || game.canField();
				break;
				
			case 10:
			case 12:
				f =true;
				break;
				
			case 13:
				for(Marble m : marbles){
					fullPaths.add(game.getBoard().createFullPath(m, rank));
				}
				f=card.canPlay(marbles, fullPaths,true,game.getBoard().getSafeZones(),game.getBoard().getTrack()) || game.canField();
				break;
				
			case 11:
				canSwap();
				break;
				
			case 7:                                             // for one marble 
				ArrayList<Marble> mine = new ArrayList<>();
				for(Marble m : marbles){
					fullPaths.add(game.getBoard().createFullPath(m, rank));
					if(m.getColour().equals(game.getPlayers().get(0))){
						mine.add(m);
					}
				}
				f=card.canPlay(marbles, fullPaths,false,game.getBoard().getSafeZones(),game.getBoard().getTrack());
				                                                 //for two marbles 
				boolean a=false,b=false;
				if(mine.size()>1){
					for(int i =0;i<mine.size()-1;i++){
						for(int j =i+1;j<mine.size();j++){
							for(int z =1;z<7;z++){
								a =card.validatePlay(mine.get(i), game.getBoard().createFullPath(mine.get(i), z), false,game.getBoard().getSafeZones(),game.getBoard().getTrack());
								b =card.validatePlay(mine.get(i), game.getBoard().createFullPath(mine.get(i), 7-z), false,game.getBoard().getSafeZones(),game.getBoard().getTrack());
								f= f ||(a&&b);
							}
						}
					}
				}
				
				break;
			
			}
		}
		else{
			if(c.getName().equals("MarbleBurner")){
				
				ArrayList<Cell> track = game.getBoard().getTrack();
				for(int i =0;i<track.size();i++){
					Marble m = track.get(i).getMarble();
					if(m != null && m.getColour()!=game.getPlayers().get(0).getColour() && i != game.getBoard().getBasePosition(m.getColour()))
							f = true;
				}
			}
			else{
				ArrayList<Cell> track = game.getBoard().getTrack();
				for(int i =0;i<track.size();i++){
					Marble m = track.get(i).getMarble();
					if(m != null && m.getColour()==game.getPlayers().get(0).getColour())
							f = true;
				}
			}
		}
		
		
		return f;
	}
	// new method to check if i can swap or not
		public boolean canSwap(){
			boolean f = false;
			boolean ihave = false;
			boolean youhave = false;
			ArrayList<Cell> track = game.getBoard().getTrack();
			for(int i =0;i<track.size();i++){
				Marble m = track.get(i).getMarble();
				if(m != null){
					if(m.getColour()==game.getPlayers().get(0).getColour())
						ihave = true;
					else{
						if(i != game.getBoard().getBasePosition(m.getColour()))
							youhave = true;
					}
				}
			}
			f = ihave && youhave;
			return f ;
		}

}
