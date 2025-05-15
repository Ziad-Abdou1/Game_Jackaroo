package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import java.util.ArrayList;

import model.card.Card;

public class HandsView extends StackPane {
    public HandsView(ArrayList<Card> topHand, ArrayList<Card> leftHand, ArrayList<Card> bottomHand, ArrayList<Card> rightHand) {

        // Create the individual hand views
        HPlayerCardView top = new HPlayerCardView(topHand);
        HPlayerCardView left = new HPlayerCardView(leftHand);
        HPlayerCardView bottom = new HPlayerCardView(bottomHand);
        HPlayerCardView right = new HPlayerCardView(rightHand);

        // Rotate cards to reflect their positions
        top.setRotate(180);   // upside-down
        left.setRotate(270);  // rotated to vertical
        right.setRotate(90);  // rotated to vertical


        this.getChildren().addAll(left,top,right,bottom);

        
        this.setAlignment(top, Pos.TOP_CENTER);
        this.setAlignment(left, Pos.CENTER_LEFT);
        this.setAlignment(right, Pos.CENTER_RIGHT);
        this.setAlignment(bottom, Pos.BOTTOM_CENTER);
        
        this.setMaxWidth(1200);

        
    }

    private Pane wrap(HPlayerCardView view, Pos alignment) {
        StackPane wrapper = new StackPane(view);
        wrapper.setAlignment(alignment);
        return wrapper;
    }
}
