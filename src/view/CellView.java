package view;

import model.player.Marble;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Colour;
public class CellView {
	private int x, y, r;
	private Circle cellCircle;
	
	public CellView(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.r = r;
		cellCircle = new Circle();
		cellCircle.setCenterX(x);
		cellCircle.setCenterY(y);
		cellCircle.setRadius(r);
		cellCircle.setFill(Color.GREY);
		cellCircle.setStroke(Color.DARKBLUE); 
	}
	
	public Circle drawCell(){
		return cellCircle;
	}
	
	public void updateColor(Marble marble){
		if (marble==null){
			cellCircle.setFill(Color.GREY);
			cellCircle.setStroke(Color.DARKBLUE); 
		}
		else{
			if (marble.getColour()==Colour.RED){
				cellCircle.setFill(Color.RED);
			}
			else if (marble.getColour()==Colour.YELLOW){
				cellCircle.setFill(Color.YELLOW);
			} 
			else if (marble.getColour()==Colour.GREEN){
				cellCircle.setFill(Color.GREEN);
			}
			else cellCircle.setFill(Color.BLUE);
		}
	}
	public void upd(){
		cellCircle.setFill(Color.GREEN);
	}
}
