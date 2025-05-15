package view;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Colour;
import model.player.Marble;

public class MarbleView extends StackPane {
	private Marble marble;
	private final double radius = 10;
	private Circle circle;
	public MarbleView(Marble marble){
		circle = new Circle();
		this.marble = marble;
		draw();
	}
	
	public void draw(){
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
		this.getChildren().addAll(circle);
	}
}