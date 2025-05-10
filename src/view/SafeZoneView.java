package view;

import java.util.ArrayList;

import model.player.Marble;

public class SafeZoneView {
	private ArrayList<CellView> cellViews;
	private int firstX, firstY, dx, dy,cellSize;
	public SafeZoneView(int firstX, int firstY, int dx, int dy, int cellSize) {
		this.firstX = firstX;
		this.firstY = firstY;
		this.dx = dx;
		this.dy = dy;
		this.cellSize = cellSize;
		
		cellViews = new ArrayList<>();
		int curx = firstX, cury = firstY;
		for (int i= 0; i < 4; i++){
			cellViews.add(new CellView(curx,cury,cellSize));
			curx+=dx;
			cury+=dy;
		}
	}
	
	public ArrayList<CellView> getCellViews(){
		return cellViews;
	}
	
	public void updateCellView(int idx, Marble marble){
		cellViews.get(idx).updateColor(marble);
	}
	
}
