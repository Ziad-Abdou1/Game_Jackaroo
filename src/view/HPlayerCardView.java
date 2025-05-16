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
	    ArrayList<Card> hand;
	    ArrayList<CardView> handView;
	    private boolean orientation;
	    public HPlayerCardView(ArrayList<Card> hand,boolean f) {
	    	this.orientation = f;
	    	this.hand=hand;
	    	handView=new ArrayList<CardView>();
	    	draw();
	    		
	    		
	    	
	    	this.setSpacing(20);
	    	this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
//	    	this.setStyle("-fx-background-color: red");
	    }
	    public ArrayList<CardView> getCardViews(){return handView;}
	    public void setHand(ArrayList<Card> hand){this.hand = hand;}
	    public void draw(){
	    	for(Card c:hand){
	    		CardView cv=new CardView(c,orientation);
	    		handView.add(cv);
	    		this.getChildren().add(cv);
	    	}
	    }
	    public void refresh(){
	    	this.getChildren().clear();
	    	handView.clear();
	    	draw();
	    }
}
