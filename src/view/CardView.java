package view;

import com.sun.prism.Image;
import java.util.Random;
import model.card.Card;
import model.card.standard.*;
import javafx.scene.control.Button;

public class CardView {
	Button btn;
	int width,height;
	public CardView(int width, int height){
		this.width = width;
		this.height = height;
		btn = new Button();
		btn.setPrefWidth(width);
		btn.setPrefHeight(height);
	}
	public Button addCard(Card card){
		String imFile = "";
		if (card instanceof Ace){
			imFile = "A";
		}
		else if (card instanceof Jack){
			imFile = "J";
		}
		else if (card instanceof Queen){
			imFile = "Q";
		}
		else if (card instanceof King){
			imFile = "K";
		}
		else if (card instanceof Standard){
			imFile = ""+((Standard)card).getRank();
		}
		Random random = new Random();
        int randomNumber = random.nextInt(4) + 1;
        imFile += random;
//		Image crdImage = new Image(imFile);
		return btn;
	}
}
