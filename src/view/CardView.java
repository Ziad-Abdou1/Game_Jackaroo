package view;

import java.util.ArrayList;

import engine.Game;
import model.card.Card;
import model.card.standard.*;
import model.card.wild.Burner;
import model.card.wild.Saver;
import model.card.wild.Wild;
import model.player.Marble;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class CardView extends ImageView{
	private  Card card ; 
	private Game game;
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
	private boolean orientation;
	public CardView(Game game, Card card ,boolean f){
		f=true;
		this.game = game;
		this.orientation = f;
		this.card  = card;
		if(f)
			drawCard();
		else
			drawCPUCard();
		action();
	}
	public void action(){
		this.setOnMouseEntered(e -> {
		    this.hover(true);
		});

		this.setOnMouseExited(e -> {
		    this.hover(false);
		});
		this.setOnMouseClicked(e -> {
			try{
				
				//redraw();
				game.deselectAll();
				game.selectCard(this.getCard());
				this.setStyle("-fx-border-color: lightblue; -fx-border-width: 50;");
				this.setScaleX(0.3);
				this.setScaleY(0.3);
				System.out.println("card is selected");
			}catch(Exception exc){
				System.out.println(exc.getMessage());
			}
		});
	}
	
	
	public void hover(boolean f){
		if (game.getPlayers().get(0).getHand().contains(card)){
			if (game.getPlayers().get(0).getSelectedCard()==this.card){
				return;
			}
			if (f){
				this.setScaleX(1.2);
				this.setScaleY(1.2);
			}
			else{
				this.setScaleX(1.0);
				this.setScaleY(1.0);
			}
		}

	}
	private void drawCard(){
		this.setImage(new Image(getPath()));
		this.setPreserveRatio(true);
	    this.setFitWidth(screenWidth * 0.04); 
	    this.setFitHeight(screenHeight*0.1);
	}
	private void drawCPUCard(){
		this.setImage(new Image("cardss/Card back.png"));
		this.setPreserveRatio(true);
	    this.setFitWidth(screenWidth * 0.04); 
	    this.setFitHeight(screenHeight*0.1);
	}
	public void setCard(Card card){
		this.card = card;
		refresh();
	}
	
	public void refresh(){
		if (orientation) drawCard();
		else drawCPUCard();
	}
	
	public String getPath(){
		if(card==null){
			return "cardss/baby.jpg";
		}
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
	public Card getCard() {
		return card;
	}


	
}
