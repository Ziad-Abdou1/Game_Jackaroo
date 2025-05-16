package view;

import engine.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
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
		
	}
	
	public void refresh(){
		circle.setRadius(radius);
		if (marble==null){
			circle.setFill(Color.GREY);
		}
		else{
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