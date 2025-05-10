package view;

import java.util.ArrayList;

import model.Colour;
import model.player.Marble;

public class HomeZoneView {
	private ArrayList<CellView> cellViews;
	private int firstX, firstY, dx, dy,cellSize;
	private Colour colour;
	int curIdx = 0;
	public HomeZoneView(int firstX, int firstY, int dx, int dy, int cellSize, Colour colour) {
		this.firstX = firstX;
		this.firstY = firstY;
		this.dx = dx;
		this.dy = dy;
		this.cellSize = cellSize;
		this.colour = colour;
		curIdx = 3; //current marble to be removed
		cellViews = new ArrayList<>();
		int curx = firstX, cury = firstY;
		cellViews.add(new CellView(curx,cury,cellSize));
		curx += dx;
		cury -= dy;
		cellViews.add(new CellView(curx,cury,cellSize));
		curx += dx;
		cury += dy;
		cellViews.add(new CellView(curx,cury,cellSize));
		curx -= dx;
		cury += dy;
		cellViews.add(new CellView(curx,cury,cellSize));
		curx -= dx;
		cury -= dy;
		cellViews.add(new CellView(curx,cury,cellSize));
		for (CellView cv : cellViews){
			cv.updateColor(new Marble(colour));
		}
	}
	
	public ArrayList<CellView> getCellViews(){
		return cellViews;
	}
	public void removeMarble(){
		cellViews.get(curIdx--).updateColor(null);
	}
	public void addMarble(){
		cellViews.get(curIdx++).updateColor(new Marble(colour));
	}
	public void updateCellView(int idx, Marble marble){
		cellViews.get(idx).updateColor(marble);
	}
}
