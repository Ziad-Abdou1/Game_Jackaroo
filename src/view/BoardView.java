package view;

import java.util.ArrayList;

import javafx.scene.layout.Pane;
import engine.board.Board;
import model.Colour;
import model.player.Marble;

public class BoardView {
	private final int BOARD_HEIGHT = 800, BOARD_WIDTH = 800;
	private Board board;
	private Pane grid;
	private ArrayList<CellView> trackView;
	private int spacing = BOARD_HEIGHT/40;
	private int cellSize = BOARD_HEIGHT/100;
	private int startX = (BOARD_WIDTH/2-spacing-3*cellSize), startY = (BOARD_HEIGHT/10);
	private ArrayList<SafeZoneView> safeZoneViews;	
	private ArrayList<HomeZoneView> homeZoneViews;
	public void initTrackView(){
		trackView = new ArrayList<>();
		int curX = startX, curY = startY;
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX += spacing*1.5;
		}
		curX -= spacing*1.5;
		curY += spacing*1.5;
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY += spacing*1.5;
		}
		curY -= 3*spacing;
		curX += 1.5*spacing;
		for (int i = 0; i < 2; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX += spacing*1.5;
			curY -= spacing*1.5;
		}
		curX -= spacing*1.5;
		curY += spacing*3;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY += spacing*1.5;
		}
		curX += spacing*1.5;
		curY -= spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX += spacing*1.5;
		}
		curX -= spacing*1.5;
		//curY -= spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX -= spacing*1.5;
			curY += spacing*1.5;
		}
		curX += spacing*3;
		curY -= spacing*1.5;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX += spacing*1.5;
		}
		////////////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY += spacing*1.5;
		}
		curY -= spacing*1.5;
		curX -= spacing*1.5;
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX -= spacing*1.5;
		}
		curX += 3*spacing;
		curY += 1.5*spacing;
		for (int i = 0; i < 2; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY += spacing*1.5;
			curX += spacing*1.5;
		}
		curY -= spacing*1.5;
		curX -= spacing*3;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX -= spacing*1.5;
		}
		curY += spacing*1.5;
		curX += spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY += spacing*1.5;
		}
		curY -= spacing*1.5;
		//curY -= spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY -= spacing*1.5;
			curX -= spacing*1.5;
		}
		curY += spacing*3;
		curX += spacing*1.5;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY += spacing*1.5;
		}
		////////////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX -= spacing*1.5;
		}
		curX += spacing*1.5;
		curY -= spacing*1.5;
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY -= spacing*1.5;
		}
		curY += 3*spacing;
		curX -= 1.5*spacing;
		for (int i = 0; i < 2; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX -= spacing*1.5;
			curY += spacing*1.5;
		}
		curX += spacing*1.5;
		curY -= spacing*3;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY -= spacing*1.5;
		}
		curX -= spacing*1.5;
		curY += spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX -= spacing*1.5;
		}
		curX += spacing*1.5;
		//curY -= spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX += spacing*1.5;
			curY -= spacing*1.5;
		}
		curX -= spacing*3;
		curY += spacing*1.5;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX -= spacing*1.5;
		}
		////////////////////////////////////////////////////////////////////////////////
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY -= spacing*1.5;
		}
		curY += spacing*1.5;
		curX += spacing*1.5;
		for (int i = 0; i < 5; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX += spacing*1.5;
		}
		curX -= 3*spacing;
		curY -= 1.5*spacing;
		for (int i = 0; i < 2; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY -= spacing*1.5;
			curX -= spacing*1.5;
		}
		curY += spacing*1.5;
		curX += spacing*3;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curX += spacing*1.5;
		}
		curY -= spacing*1.5;
		curX -= spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY -= spacing*1.5;
		}
		curY += spacing*1.5;
		//curY -= spacing*1.5;
		for (int i = 0; i < 3; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY += spacing*1.5;
			curX += spacing*1.5;
		}
		curY -= spacing*3;
		curX -= spacing*1.5;
		for (int i = 0; i < 4; i++){
			trackView.add(new CellView(curX,curY,cellSize));
			curY -= spacing*1.5;
		}
	}
	public void initSafeZoneViews(){
		safeZoneViews = new ArrayList<>();
		int modifiedspacing = (int)(1.5*spacing);
		safeZoneViews.add(new SafeZoneView(startX+2*modifiedspacing,startY+19*modifiedspacing,0,-modifiedspacing,cellSize));
		safeZoneViews.add(new SafeZoneView(startX+2*modifiedspacing,startY+modifiedspacing,0,modifiedspacing,cellSize));
		safeZoneViews.add(new SafeZoneView(startX+11*modifiedspacing,startY+10*modifiedspacing,-modifiedspacing,0,cellSize));
		safeZoneViews.add(new SafeZoneView(startX-7*modifiedspacing,startY+10*modifiedspacing,modifiedspacing,0,cellSize));
		
	}
	public void initHomeZoneView(){
		homeZoneViews = new ArrayList<>();
		int modifiedspacing = (int)(1.5*spacing);
		homeZoneViews.add(new HomeZoneView(startX-4*modifiedspacing,startY,modifiedspacing,modifiedspacing,cellSize,Colour.BLUE));
		homeZoneViews.add(new HomeZoneView(startX+11*modifiedspacing,startY+5*modifiedspacing,modifiedspacing,modifiedspacing,cellSize,Colour.GREEN));
		homeZoneViews.add(new HomeZoneView(startX+6*modifiedspacing,startY+20*modifiedspacing,modifiedspacing,modifiedspacing,cellSize,Colour.RED));
		homeZoneViews.add(new HomeZoneView(startX-9*modifiedspacing,startY+15*modifiedspacing,modifiedspacing,modifiedspacing,cellSize,Colour.YELLOW));
	}
	public BoardView(){
		grid = new Pane();
		grid.setPrefSize(BOARD_HEIGHT,BOARD_WIDTH);
		
		initTrackView();
		initSafeZoneViews();
		initHomeZoneView();
		
		
		//draw:
		for (CellView cv : trackView){
			grid.getChildren().add(cv.drawCell());
		}
		for (SafeZoneView sf : safeZoneViews){
			
			for (CellView cv : sf.getCellViews())
				grid.getChildren().add(cv.drawCell());
		}
		for (HomeZoneView hz : homeZoneViews){
			
			for (CellView cv : hz.getCellViews())
				grid.getChildren().add(cv.drawCell());
		}
	}
	public Pane view(){
		return grid;
	}
}
