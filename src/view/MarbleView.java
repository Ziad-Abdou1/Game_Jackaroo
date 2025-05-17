package view;

import engine.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;
import model.Colour;
import model.player.Marble;

public class MarbleView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();
    
    Game game;
    
	private Marble marble;
	//private final double radius = 12*screenWidth/1920;
	private final double radius = 12*screenWidth/4000;
	private Circle circle;
	public MarbleView(Marble marble,Game game){
		this.game=game;
		circle = new Circle();
		this.marble = marble;
		refresh();
		this.getChildren().add(circle);
		
		this.setOnMouseClicked(e ->{
			try{
				System.out.println("marble is selected");
				game.selectMarble(this.marble);
			}catch(Exception exc){
				System.out.println(exc.getMessage()); //to be edited
			}
		});
		DropShadow shadow = new DropShadow();
		
		shadow.setColor(getFXColor (marble.getColour()));
		shadow.setRadius(20); 

		this.setOnMouseEntered(e -> {
		    circle.setEffect(shadow);
		    this.setScaleX(1.3);
			this.setScaleY(1.3);
			
		});

		this.setOnMouseExited(e -> {
		    circle.setEffect(null);
		    this.setScaleX(1);
			this.setScaleY(1);
		});
		
	}
	private Color getFXColor(Colour clr) {
	    switch (clr) {
	        case BLUE: return Color.BLUE;
	        case RED: return Color.RED;
	        case GREEN: return Color.GREEN;
	        case YELLOW: return Color.YELLOW;
	        default: return Color.GRAY;
	    }
	}
	
	public void refresh(){
		circle.setRadius(radius);
		if (marble==null){
			//circle.setFill(Color.GREY);
			circle.setOpacity(0);
		}
		else{
			circle.setOpacity(1);
			Colour clr = marble.getColour();
			if (clr == Colour.BLUE) circle.setFill(Color.BLUE);
			if (clr == Colour.YELLOW) circle.setFill(Color.YELLOW);
			if (clr == Colour.GREEN) circle.setFill(Color.GREEN);
			if (clr == Colour.RED) circle.setFill(Color.RED);
		}
	}
	public void setMarble(Marble marble){
		this.marble=marble;
		refresh();
	}
	
}