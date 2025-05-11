package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CellView {
	private int i, j;
	private MarbleView marbleView;
	public CellView(int i, int j){
		this.i =i;
		this.j =j;
		this.marbleView =null;
	}
	public Circle draw(double d, double e, int r){
		Circle circ = new Circle();
		circ.setCenterX(d);
		circ.setCenterY(e);
		circ.setRadius(r);
		circ.setFill(Color.WHITE);
		circ.setStroke(Color.BLACK);
		return circ;
	}
	public void setMarbleView(MarbleView marbleView){
		this.marbleView =marbleView;
	}
	public MarbleView getMarbleView(){
		return this.marbleView;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
}
