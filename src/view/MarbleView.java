package view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Colour;
import model.player.Marble;

public class MarbleView {
	private Marble marble;
	int x, y, r;
	public MarbleView(Marble marble, int x, int y, int r) {
		this.marble = marble;
		this.x = x;
		this.y = y;
		this.r = r;
	}
	public MarbleView(Marble marble) {
		this.marble = marble;
	}
	public Button drawMarble(int x, int y, int r){
		this.x = x;
	    this.y = y;
	    this.r = r;

	    Circle circ = new Circle();
	    circ.setRadius(r);
	    if (marble.getColour()==Colour.RED){
			circ.setFill(Color.RED);
		}
		else if (marble.getColour()==Colour.GREEN){
			circ.setFill(Color.GREEN);
		}
		else if (marble.getColour()==Colour.YELLOW){
			circ.setFill(Color.YELLOW);
		}
		else if (marble.getColour()==Colour.BLUE){
			circ.setFill(Color.BLUE);
		}
	    Button bt = new Button();
	    bt.setShape(circ);
	    bt.setMinSize(2 * r, 2 * r);
	    bt.setMaxSize(2 * r, 2 * r);

	    bt.setLayoutX(x - r); 
	    bt.setLayoutY(y - r);

	    return bt;
	}
	public Button drawMarble(){
		return drawMarble(x,y,r);
	}
}