package view;

import model.card.Card;
import model.card.standard.*;
import model.card.wild.Burner;
import model.card.wild.Saver;
import model.card.wild.Wild;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardView {
	private  Card card ; 
	private int x ,  y ; 
	private Button cardButton ; 
	
	public CardView( int x ,int u ,Card card ){
		this.card  = card;
		this.x = x;
		this.y = y;		
	}
	
	//  just for testing 
	public CardView(int width, int height, Card card,int rotate) {
		this.card = card;
		
	    Image image = new Image(getPath());
	    ImageView imageView = new ImageView(image);
	    imageView.setFitWidth(width);
	    imageView.setFitHeight(height);

	    imageView.setPreserveRatio(true); // or true, depending on your need

	    
	    imageView.setRotate(90*rotate); // Rotate 90 degrees clockwise

	    cardButton = new Button();
	    cardButton.setPrefWidth(width);
	    cardButton.setPrefHeight(height);
	    cardButton.setGraphic(imageView);
	    //cardButton.setPrefSize(width, height); // optional
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
			 if ( this.card instanceof Burner)path+="";
			 if ( this.card instanceof Saver)path+="";
		}
		path += ".png";
		return path;
	}

	public Button drawCard(int x , int y){
		String path = getPath();
		
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
