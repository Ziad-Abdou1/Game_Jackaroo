package view;

import model.Colour;
import engine.board.Cell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CellView extends StackPane {
	private Cell cell;
	private Circle circle;
	private final double radius = 10;
	private MarbleView marbleView;
	public CellView(Cell c){
		circle = new Circle();
		this.cell = c;
		circle.setRadius(radius);
		draw();
	}
	public void draw(){
		
		if (cell.getMarble()!=null){
			marbleView = new MarbleView(cell.getMarble());
			this.getChildren().addAll(marbleView);
		}
		else{
			this.getChildren().removeAll();
			marbleView = null;
			circle.setFill(Color.GREY);
			this.getChildren().addAll(circle);
			
		}
	}
	
}
