package view;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.Colour;
import model.player.Marble;

public class MarbleView {
	private Marble marble;
	double x, y;
	int r;
	private Button bt;
	public MarbleView(Marble marble, int x, int y, int r) {
		this.marble = marble;
		bt = new Button();
		this.x = x;
		this.y = y;
		this.r = r;
	}
	public MarbleView(Marble marble) {
		this.marble = marble;
	}
	public Button drawMarble(double d, double e, int r){
		this.x = d;
	    this.y = e;
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
	    bt.setShape(circ);
	    bt.setMinSize(2 * r, 2 * r);
	    bt.setMaxSize(2 * r, 2 * r);

	    bt.setLayoutX(d - r); 
	    bt.setLayoutY(e - r);

	    return bt;
	}
	public Button drawMarble(){
		return drawMarble(x,y,r);
	}
}