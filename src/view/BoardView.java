package view;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BoardView {
	private int HEIGHT, WIDTH;
	private int cols, rows;
	private Pane grid;
	private final int div = 25;
	private ArrayList<CellView> trackView;
	private int initTrackI, initTrackJ;
	ArrayList<SafeZoneView> safeZoneViews;
	ArrayList<HomeView> homeViews;
	public void initTrackView(){
		trackView = new ArrayList<>();
		int curi = initTrackI,curj = initTrackJ;
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curi,curj));
			curj++;
		}
		curj--;
		curi--;
		for (int i = 0; i <5; i++){
			trackView.add(new CellView(curi,curj));
			curi--;
		}
		curi+=2;
		curj++;
		for (int i =0; i < 2; i++){
			trackView.add(new CellView(curi,curj));
			curi++;curj++;
		}
		curi-=2;
		curj--;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curi--;
		}
		curj++;
		curi++;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curj++;
		}
		curi--;
		curj-=2;
		for (int i =0; i <2; i++){
			trackView.add(new CellView(curi,curj));
			curj--;
			curi--;
		}
		curj+=2;
		curi++;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curi,curj));
			curj++;
		}
		/////////////////////////////////////////////
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curi,curj));
			curi--;
		}
		curj--;
		curi++;
		for (int i = 0; i <5; i++){
			trackView.add(new CellView(curi,curj));
			curj--;
		}
		curi--;
		curj+=2;
		for (int i =0; i < 2; i++){
			trackView.add(new CellView(curi,curj));
			curi--;curj++;
		}
		curi++;
		curj-=2;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curj--;
		}
		curj++;
		curi--;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curi--;
		}
		curi+=2;
		curj--;
		for (int i =0; i <2; i++){
			trackView.add(new CellView(curi,curj));
			curj--;
			curi++;
		}
		curj++;
		curi-=2;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curi,curj));
			curi--;
		}
		
		/////////////////////////////////////////////
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curi,curj));
			curj--;
		}
		curj++;
		curi++;
		for (int i = 0; i <5; i++){
			trackView.add(new CellView(curi,curj));
			curi++;
		}
		curi-=2;
		curj--;
		for (int i =0; i < 2; i++){
			trackView.add(new CellView(curi,curj));
			curi--;curj--;
		}
		curi+=2;
		curj++;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curi++;
		}
		curj--;
		curi--;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curj--;
		}
		curi++;
		curj+=2;
		for (int i =0; i <2; i++){
			trackView.add(new CellView(curi,curj));
			curj++;
			curi++;
		}
		curj-=2;
		curi--;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curi,curj));
			curj--;
		}
		/////////////////////////////////////////////
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curi,curj));
			curi++;
		}
		curj++;
		curi--;
		for (int i = 0; i <5; i++){
			trackView.add(new CellView(curi,curj));
			curj++;
		}
		curi++;
		curj-=2;
		for (int i =0; i < 2; i++){
			trackView.add(new CellView(curi,curj));
			curi++;curj--;
		}
		curi--;
		curj+=2;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curj++;
		}
		curj--;
		curi++;
		for (int i =0; i < 3; i++){
			trackView.add(new CellView(curi,curj));
			curi++;
		}
		curi-=2;
		curj++;
		for (int i =0; i <2; i++){
			trackView.add(new CellView(curi,curj));
			curj++;
			curi--;
		}
		curj--;
		curi+=2;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curi,curj));
			curi++;
		}
		
	}
	public void initSafeZoneView(){
		safeZoneViews = new ArrayList<>();
		safeZoneViews.add(new SafeZoneView(initTrackI-1,initTrackJ+2,-1,0));
		safeZoneViews.add(new SafeZoneView(initTrackI-10,initTrackJ+11,0,-1));
		safeZoneViews.add(new SafeZoneView(initTrackI-19,initTrackJ+2,1,0));
		safeZoneViews.add(new SafeZoneView(initTrackI-10,initTrackJ-7,0,1));
	}
	public void initHomeView(){
		homeViews = new ArrayList<>();
		homeViews.add(new HomeView(initTrackI-1,initTrackJ+7));
		homeViews.add(new HomeView(initTrackI-16,initTrackJ+12));
		homeViews.add(new HomeView(initTrackI-21,initTrackJ-3));
		homeViews.add(new HomeView(initTrackI-6,initTrackJ-8));
	}
	
	public BoardView(int height,int width){
		
		HEIGHT = height; WIDTH = width;
		grid = new Pane();
		grid.setPrefHeight(HEIGHT);
		grid.setPrefWidth(WIDTH);
		cols=HEIGHT/div;rows=WIDTH/div;
		initTrackI = rows/2+10;
		initTrackJ = cols/2-2;
		
		initTrackView();
		initSafeZoneView();
		initHomeView();
	}
	
	public Pane draw(){
//		for (int i =0; i < rows; i++){
//		for (int j =0; j < cols; j++){
//			Circle circ = new Circle();
//			circ.setFill(Color.WHITE);
//			circ.setStroke(Color.BLACK);
//			circ.setCenterX(transfromX(i));
//			circ.setCenterY(transformY(j));
//			circ.setRadius(div/3);
//			grid.getChildren().add(circ);
//		}
//		}
		for (CellView cv : trackView){
			if (cv.getMarbleView()==null) grid.getChildren().add(cv.draw(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
			else grid.getChildren().add(cv.getMarbleView().drawMarble(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
		}
		for (SafeZoneView sfzv : safeZoneViews){
			for (CellView cv : sfzv.getCellViews()){
				if (cv.getMarbleView()==null) grid.getChildren().add(cv.draw(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
				else grid.getChildren().add(cv.getMarbleView().drawMarble(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
			}
		}
		for (HomeView hv : homeViews){
			for (CellView cv : hv.getCellViews()){
				if (cv.getMarbleView()==null) grid.getChildren().add(cv.draw(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
				else grid.getChildren().add(cv.getMarbleView().drawMarble(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
			}
		}
		return grid;
	}
	public int transfromX(int x){return x*(WIDTH/cols)+div/2;}
	public int transformY(int y){return y*(HEIGHT/rows)+div/2;}
}
