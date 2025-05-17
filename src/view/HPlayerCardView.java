package view;

import java.util.ArrayList;

import com.sun.prism.paint.Color;

import engine.Game;
import model.card.Card;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class HPlayerCardView extends HBox{
		Game game;
	    ArrayList<Card> hand;
	    ArrayList<CardView> handView;
	    private boolean orientation;
	    public HPlayerCardView(Game game, ArrayList<Card> hand,boolean f) {
	    	this.game = game;
	    	this.orientation = f;
	    	this.hand=hand;
	    	handView=new ArrayList<CardView>();
	    	draw();

	    	this.setSpacing(20);
	    	this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//	    	this.setStyle("-fx-background-color: red");
	    }
	    public ArrayList<CardView> getCardViews(){return handView;}
	    public void setHand(ArrayList<Card> hand){
	    	this.hand = hand;
	    	draw();
	    }
	   
	    public void draw(){
	    	handView.clear();
	    	this.getChildren().clear();
	    	for(Card c:hand){
	    		CardView cv=new CardView(game,c,orientation);
	    		handView.add(cv);
	    		this.getChildren().add(cv);
	    	}
	    }

//	    public void removeCard(Card card){
//	    	int idx=-1;
//	    	for(int i=0;i<hand.size();i++){
//	    		if(hand.get(i)==card){
//	    			idx=i;
//	    			break;
//	    		}
//	    	}
//	    	if(idx==-1) return; //no usage
//	    	hand.remove(idx);
//	    	handView.clear();
//	    	this.getChildren().clear();
//	    	draw();
//	    }

}
