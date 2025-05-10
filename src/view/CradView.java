package view;

import model.card.Card;
import model.card.standard.*;
import model.card.wild.Burner;
import model.card.wild.Saver;
import model.card.wild.Wild;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CradView {
	private  Card card ; 
	private int x ,  y ; 
	private Button cardButton ; 
	
	public CradView( int x ,int u ,Card card ){
		this.card  = card;
		this.x = x;
		this.y = y;		
	}
	
	//  just for testing 
	public CradView(int width, int height, String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        cardButton = new Button();
        cardButton.setGraphic(imageView);
    }
	public Button drawCard(int x , int y){
		String path ="/cardss/";
		if ( this.card instanceof Standard ){
			Standard c = (Standard) this.card ; 
			if(c.getRank() == 1 )path+="1";
			if(c.getRank() == 2 )path+="2";
			if(c.getRank() == 3 )path+="3";
			if(c.getRank() == 4 )path+="4";
			if(c.getRank() == 5 )path+="5";
			if(c.getRank() == 6 )path+="6";
			if(c.getRank() == 7 )path+="7";
			if(c.getRank() == 8 )path+="8";
			if(c.getRank() == 9 )path+="9";
			if(c.getRank() == 10 )path+="10";
			if(c.getRank() == 11 )path+="11";
			if(c.getRank() == 12 )path+="12";
			if(c.getRank() == 13 )path+="13";
			
			if(c.getSuit() ==  Suit.CLUB)path+="1";
			if(c.getSuit() ==  Suit.SPADE)path+="2";
			if(c.getSuit() ==  Suit.HEART)path+="3";
			if(c.getSuit() ==  Suit.DIAMOND)path+="4";
		}else{
			 if ( this.card instanceof Burner)path+="";
			 if ( this.card instanceof Saver)path+="";
		}
		
		Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(x);
        imageView.setFitHeight(y);
        imageView.setPreserveRatio(true);

        cardButton = new Button();
        cardButton.setGraphic(imageView);
		
		
		
		return cardButton;
		

	
		
	}
    public Button drawCard() {
        return cardButton;
    }
	
}
