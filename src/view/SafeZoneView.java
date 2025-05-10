package views;

import java.util.ArrayList;

public class SafeZoneView {
	private ArrayList<CellView> cellViews;
	int initI, initJ, di, dj;
	public SafeZoneView(int initI, int initJ, int di, int dj) {
		super();
		this.initI = initI;
		this.initJ = initJ;
		this.di = di;
		this.dj = dj;
		cellViews = new ArrayList<>();
		for (int i =0; i < 4; i++){
			cellViews.add(new CellView(initI,initJ));
			initI+=di;initJ+=dj;
		}
	}
	public ArrayList<CellView> getCellViews() {
		return cellViews;
	}
//	public void setCellViews(ArrayList<CellView> cellViews) {
//		this.cellViews = cellViews;
//	}
	
}
