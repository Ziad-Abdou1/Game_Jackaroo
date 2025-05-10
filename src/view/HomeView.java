package views;

import java.util.ArrayList;

public class HomeView {
	private ArrayList<CellView> cellViews;
	int initI, initJ;
	public HomeView(int initI, int initJ) {
		super();
		this.initI = initI;
		this.initJ = initJ;
		cellViews = new ArrayList<>();
		cellViews.add(new CellView(initI,initJ));
		initI++;initJ++;
		cellViews.add(new CellView(initI,initJ));
		initI++;initJ--;
		cellViews.add(new CellView(initI,initJ));
		initI--;initJ--;
		cellViews.add(new CellView(initI,initJ));
		initI--;initJ++;
	}
	public ArrayList<CellView> getCellViews() {
		return cellViews;
	}
//	public void setCellViews(ArrayList<CellView> cellViews) {
//		this.cellViews = cellViews;
//	}
}
