package view;

import java.util.ArrayList;

import model.card.Card;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class VPalyerCardView {
	 private GridPane cardGrid;
	    private ArrayList<CardView> cardButtons;
	    
	    public VPalyerCardView() {
	        cardGrid = new GridPane();
	        cardGrid.setPadding(new Insets(40));
	        cardGrid.setHgap(10);
	        cardGrid.setVgap(10);
	        cardButtons = new ArrayList<>();
	    }
	    public Button addCard(Card card) {

	    	CardView cardButton = new  CardView(GameView.WINDOW_HEIGHT/8, GameView.WINDOW_WIDTH/10, card,1);
	        
	        int index = cardButtons.size();
	        int row = index / 4;  
	        int col = index % 4;

	        cardGrid.add(cardButton.drawCard(), row, col);
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
	            cardGrid.add(cardButtons.get(i).drawCard(), row, col);
	        }
	    }
	    public GridPane getCardGrid() {
	        return cardGrid;
	    }

	    public ArrayList<CardView> getCardButtons() {
	        return cardButtons;
	    }
}
