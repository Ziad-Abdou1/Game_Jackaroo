package view;

import java.util.ArrayList;

import model.card.Card;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class HPalyerCardView {
	 private GridPane cardGrid;
	    private ArrayList<CardView> cardButtons;
	    
	    public HPalyerCardView() {
	        cardGrid = new GridPane();
	        cardGrid.setPadding(new Insets(10));
	        cardGrid.setHgap(10);
	        cardGrid.setVgap(10);
	        cardButtons = new ArrayList<>();
	    }
	    public Button addCard(String path ) {

	    	CardView cardButton = new  CardView(100, 150, path,0);
	        
	        int index = cardButtons.size();
	        int row = index / 4;  
	        int col = index % 4;

	        cardGrid.add(cardButton.drawCard(), col, row);
	        cardButtons.add(cardButton);

	        return cardButton.drawCard();
	    }

	    public void removeCard(CardView cardButton) {
	        cardGrid.getChildren().remove(cardButton); 
	        cardButtons.remove(cardButton);            
	        refreshGrid();        
	    }
	    private void refreshGrid() {
	        cardGrid.getChildren().clear();
	        for (int i = 0; i < cardButtons.size(); i++) {
	            int row = i / 4;
	            int col = i % 4;
	            cardGrid.add(cardButtons.get(i).drawCard(), col, row);
	        }
	    }
	    public GridPane getCardGrid() {
	        return cardGrid;
	    }

	    public ArrayList<CardView> getCardButtons() {
	        return cardButtons;
	    }
}
