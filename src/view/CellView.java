package view;

import model.Colour;
import engine.board.Cell;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;

public class CellView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
    double screenWidth = screenBounds.getWidth();
    double screenHeight = screenBounds.getHeight();

	private Cell cell;
	private Circle circle;
	//private final double radius = 12*screenWidth/1920;
	private final double radius = 12*screenWidth/1700;
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
			circle.setFill(Color.DARKGRAY);
			this.getChildren().addAll(circle);
			
		}
	}
	public void setCell(Cell cell) {this.cell = cell;}
	public void refresh(){
		this.getChildren().clear();
		draw();
	}
	
}
