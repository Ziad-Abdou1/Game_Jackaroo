package view;

import model.card.Card;
import model.card.standard.*;
import model.card.wild.Burner;
import model.card.wild.Saver;
import model.card.wild.Wild;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardView extends ImageView{
	private  Card card ; 
	
	public CardView(Card card ){
		this.card  = card;	
		drawCard();
	}
	private void drawCard(){
		this.setImage(new Image(getPath()));
		
	}
	


	public String getPath(){
		String path ="cardss/";
		if ( this.card instanceof Standard ){
			Standard c = (Standard) this.card ; 
			if (c.getRank()>=2 && c.getRank()<=10){
				path += ""+c.getRank();
			}
			if(c.getRank() == 1 )path+="A";
			if(c.getRank() == 11 )path+="J";
			if(c.getRank() == 12 )path+="Q";
			if(c.getRank() == 13 )path+="K";
			
			if(c.getSuit() ==  Suit.CLUB)path+="1";
			if(c.getSuit() ==  Suit.SPADE)path+="2";
			if(c.getSuit() ==  Suit.HEART)path+="3";
			if(c.getSuit() ==  Suit.DIAMOND)path+="4";
		}else{
			 if ( this.card instanceof Burner)path+="A1";
			 if ( this.card instanceof Saver)path+="A1";
		}
		path += ".png";
		return path;
	}


	
}
