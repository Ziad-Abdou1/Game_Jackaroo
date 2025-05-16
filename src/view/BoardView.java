package view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.Node;

import java.util.*;

import model.Colour;
import model.player.Marble;
import engine.Game;
import engine.board.Board;
import engine.board.Cell;
import engine.board.SafeZone;
public class BoardView extends GridPane {
    private Board board;
    private Game game;

    public BoardView(Board board, Game game) {
    	this.game=game;
    	safeZoneView = new ArrayList[4];
    	for (int i=0; i < 4; i++) safeZoneView[i] = new ArrayList<>();
    	trackView = new ArrayList<>();
    	this.board=board;
    	refresh();
    }
    public CellView getBaseCellView(Colour clr){
    	int idx=getBasePosition(clr);
    	return trackView.get(idx);
    }
    private int getBasePosition(Colour colour) {
    	ArrayList<SafeZone> safeZones=board.getSafeZones();
        for(int i = 0; i < safeZones.size(); i++) {
            if(safeZones.get(i).getColour() == colour)
                return i * 25;
        }
        
        return -1;
    }
    //track------------------------------------------
    private ArrayList<CellView> trackView;

    //private Hashmap<CellView,Cell> trackManager;
    //up right down left upLeft upRight downLeft downRight
    int[] dy = {0,1,0,-1,-1,1,-1,1};
    int[] dx = {-1,0,1,0,-1,-1,1,1};
    //{6,5,5,6,3}
    int[] segment=new int[]  {7,7,7,4};
    int[][] directions = {
    	    {0,4,3,0},
    	    {1,5,0,1},
    	    {2,7,1,2},
    	    {3,6,2,3}
    	};

    int initI=61;
    int initJ=61;
    private void initTrack(){
    	trackView.clear();
    	int curIdx = 0;
    	int curi = initI, curj = initJ;
    	for(int i=0;i<directions.length;i++){
    		for(int j=0;j<directions[i].length;j++){
    			for (int k = 0; k < segment[j]; k++){
    				Cell cell=board.getTrack().get(curIdx);

    				CellView cv = new CellView(cell,game);
    				trackView.add(cv);
    				this.addNode(cv,curj,curi);
    				curi += dx[directions[i][j]];
    				curj += dy[directions[i][j]];
    				curIdx++;
//    				System.out.println(curi+" "+curj);
//    				System.out.println(curIdx);
    			}
    		}
    	}
    }
    ////----------------------------------------------------------------------------
    
    
    //SafeZones---------------------------------------------------------------------
    ArrayList<CellView> [] safeZoneView;
    
    private void initSafeZone(){


    	int curIdx=98;
    	int[] direction={0,1,2,3};
    	for (int i = 0; i < 4; i++){
    		safeZoneView[i].clear();
        	int curi=this.getRowIndex(trackView.get(curIdx));
        	int curj=this.getColumnIndex(trackView.get(curIdx));
    		for (int j = 0; j < 4; j++){
				curi += dx[direction[i]];
				curj += dy[direction[i]];
				CellView cv=new CellView(board.getSafeZones().get(i).getCells().get(j), game);
//				Colour clr = board.getSafeZones().get(i).getColour();
//				if (clr == Colour.BLUE){
//					cv.setStyle("-fx-ba");
//				}
				safeZoneView[i].add(cv);
				this.addNode(cv,curj,curi);
    		}
    		curIdx=(curIdx+25)%100;
    		//System.out.println(curIdx);
    	}
    	
    }
    ////----------------------------------------------------------------------------
    



    public void refresh(){
    	
    	
    	//just making background image------------------------------------------------------------------------------------
    	Image backgroundImage = new Image("/wood2.png");  // or use getClass().getResource("/path/to/image.png")

    	BackgroundSize backgroundSize = new BackgroundSize(
    		    100, 100,      // width and height as percentages
    		    true, true,    // width and height are percentages
    		    true, true    // preserveRatio = true, cover = false
    		);


    	BackgroundImage background = new BackgroundImage(
    	    backgroundImage,
    	    BackgroundRepeat.NO_REPEAT,
    	    BackgroundRepeat.NO_REPEAT,
    	    BackgroundPosition.CENTER,
    	    backgroundSize);

    	this.setBackground(new Background(background));
    	//---------------------------------------------------------------------------------------------------------------
    	
    	initTrack();
    	initSafeZone();
    	this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }



    public void addNode(Node node, int col, int row) {
        this.add(node, col, row);
    }
}
//package view;
//
//import java.util.ArrayList;
//
//import model.player.Marble;
//import engine.board.Cell;
//import javafx.geometry.Insets;
//import javafx.scene.layout.Background;
//import javafx.scene.layout.BackgroundFill;
//import javafx.scene.layout.CornerRadii;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Circle;
//
//public class BoardView {
//	private int HEIGHT, WIDTH;
//	private int cols, rows;
//	private Pane grid;
//	private final int div = 25;
//	private ArrayList<CellView> trackView;
//	private ArrayList<Cell> track;
//	private ArrayList<ArrayList<Marble>> homes;
//	private int initTrackI, initTrackJ;
//	ArrayList<SafeZoneView> safeZoneViews;
//	ArrayList<HomeView> homeViews;
//	public void initTrackView(){
//		trackView = new ArrayList<>();
//		int curi = initTrackI,curj = initTrackJ;
//		for (int i = 0; i < 5; i++){
//			trackView.add(new CellView(curi,curj));
//			curj++;
//		}
//		curj--;
//		curi--;
//		for (int i = 0; i <5; i++){
//			trackView.add(new CellView(curi,curj));
//			curi--;
//		}
//		curi+=2;
//		curj++;
//		for (int i =0; i < 2; i++){
//			trackView.add(new CellView(curi,curj));
//			curi++;curj++;
//		}
//		curi-=2;
//		curj--;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curi--;
//		}
//		curj++;
//		curi++;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curj++;
//		}
//		curi--;
//		curj-=2;
//		for (int i =0; i <2; i++){
//			trackView.add(new CellView(curi,curj));
//			curj--;
//			curi--;
//		}
//		curj+=2;
//		curi++;
//		for (int i = 0; i < 4; i++){
//			trackView.add(new CellView(curi,curj));
//			curj++;
//		}
//		/////////////////////////////////////////////
//		for (int i = 0; i < 5; i++){
//			trackView.add(new CellView(curi,curj));
//			curi--;
//		}
//		curj--;
//		curi++;
//		for (int i = 0; i <5; i++){
//			trackView.add(new CellView(curi,curj));
//			curj--;
//		}
//		curi--;
//		curj+=2;
//		for (int i =0; i < 2; i++){
//			trackView.add(new CellView(curi,curj));
//			curi--;curj++;
//		}
//		curi++;
//		curj-=2;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curj--;
//		}
//		curj++;
//		curi--;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curi--;
//		}
//		curi+=2;
//		curj--;
//		for (int i =0; i <2; i++){
//			trackView.add(new CellView(curi,curj));
//			curj--;
//			curi++;
//		}
//		curj++;
//		curi-=2;
//		for (int i = 0; i < 4; i++){
//			trackView.add(new CellView(curi,curj));
//			curi--;
//		}
//		
//		/////////////////////////////////////////////
//		for (int i = 0; i < 5; i++){
//			trackView.add(new CellView(curi,curj));
//			curj--;
//		}
//		curj++;
//		curi++;
//		for (int i = 0; i <5; i++){
//			trackView.add(new CellView(curi,curj));
//			curi++;
//		}
//		curi-=2;
//		curj--;
//		for (int i =0; i < 2; i++){
//			trackView.add(new CellView(curi,curj));
//			curi--;curj--;
//		}
//		curi+=2;
//		curj++;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curi++;
//		}
//		curj--;
//		curi--;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curj--;
//		}
//		curi++;
//		curj+=2;
//		for (int i =0; i <2; i++){
//			trackView.add(new CellView(curi,curj));
//			curj++;
//			curi++;
//		}
//		curj-=2;
//		curi--;
//		for (int i = 0; i < 4; i++){
//			trackView.add(new CellView(curi,curj));
//			curj--;
//		}
//		/////////////////////////////////////////////
//		for (int i = 0; i < 5; i++){
//			trackView.add(new CellView(curi,curj));
//			curi++;
//		}
//		curj++;
//		curi--;
//		for (int i = 0; i <5; i++){
//			trackView.add(new CellView(curi,curj));
//			curj++;
//		}
//		curi++;
//		curj-=2;
//		for (int i =0; i < 2; i++){
//			trackView.add(new CellView(curi,curj));
//			curi++;curj--;
//		}
//		curi--;
//		curj+=2;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curj++;
//		}
//		curj--;
//		curi++;
//		for (int i =0; i < 3; i++){
//			trackView.add(new CellView(curi,curj));
//			curi++;
//		}
//		curi-=2;
//		curj++;
//		for (int i =0; i <2; i++){
//			trackView.add(new CellView(curi,curj));
//			curj++;
//			curi--;
//		}
//		curj--;
//		curi+=2;
//		for (int i = 0; i < 4; i++){
//			trackView.add(new CellView(curi,curj));
//			curi++;
//		}
//		
//	}
//	public void initSafeZoneView(){
//		safeZoneViews = new ArrayList<>();
//		safeZoneViews.add(new SafeZoneView(initTrackI-1,initTrackJ+2,-1,0));
//		safeZoneViews.add(new SafeZoneView(initTrackI-10,initTrackJ+11,0,-1));
//		safeZoneViews.add(new SafeZoneView(initTrackI-19,initTrackJ+2,1,0));
//		safeZoneViews.add(new SafeZoneView(initTrackI-10,initTrackJ-7,0,1));
//	}
//	public void initHomeView(){
//		homeViews = new ArrayList<>();
//		homeViews.add(new HomeView(initTrackI-1,initTrackJ+7));
//		homeViews.add(new HomeView(initTrackI-16,initTrackJ+12));
//		homeViews.add(new HomeView(initTrackI-21,initTrackJ-3));
//		homeViews.add(new HomeView(initTrackI-6,initTrackJ-8));
//	}
//	
//	public BoardView(int height,int width, ArrayList<Cell> track, ArrayList<ArrayList<Marble>> homes){
//		this.homes=homes;
//		this.track=track;
//		
//		HEIGHT = height; WIDTH = width;
//		grid = new Pane();
//		grid.setBackground(new Background(new BackgroundFill(Color.BROWN, CornerRadii.EMPTY, Insets.EMPTY)));
//		grid.setPrefHeight(HEIGHT);
//		grid.setPrefWidth(WIDTH);
//		cols=HEIGHT/div;rows=WIDTH/div;
//		initTrackI = rows/2+10;
//		initTrackJ = cols/2-2;
//		
//		initTrackView();
//		initSafeZoneView();
//		initHomeView();
//	}
//	
//	public Pane draw(){
////		for (int i =0; i < rows; i++){
////		for (int j =0; j < cols; j++){
////			Circle circ = new Circle();
////			circ.setFill(Color.WHITE);
////			circ.setStroke(Color.BLACK);
////			circ.setCenterX(transfromX(i));
////			circ.setCenterY(transformY(j));
////			circ.setRadius(div/3);
////			grid.getChildren().add(circ);
////		}
////		}
//		for (CellView cv : trackView){
//			if (cv.getMarbleView()==null) grid.getChildren().add(cv.draw(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
//			else grid.getChildren().add(cv.getMarbleView().drawMarble(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
//		}
//		for (SafeZoneView sfzv : safeZoneViews){
//			for (CellView cv : sfzv.getCellViews()){
//				if (cv.getMarbleView()==null) grid.getChildren().add(cv.draw(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
//				else grid.getChildren().add(cv.getMarbleView().drawMarble(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
//			}
//		}
//		for (HomeView hv : homeViews){
//			for (CellView cv : hv.getCellViews()){
//				if (cv.getMarbleView()==null) grid.getChildren().add(cv.draw(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
//				else grid.getChildren().add(cv.getMarbleView().drawMarble(transfromX(cv.getJ()),transformY(cv.getI()),div/3));
//			}
//		}
//		return grid;
//	}
//	public void refresh(){
//		
////		for(int i=0;i<track.size();i++){
////			Cell c=track.get(i);
////			if(c.getMarble()!=null){
////				trackView.get(i).setMarbleView(new MarbleView(c.getMarble()));
////			}
////		}
//		for(int i =0; i< 4; i++){
//			for (int j = 0; j < 4; j++){
//				if (j < homes.get(i).size()){
//					homeViews.get(i).getCellViews().get(j).setMarbleView(new MarbleView(homes.get(i).get(j)));
//				}
//				else homeViews.get(i).getCellViews().get(j).setMarbleView(null);
//			}
//			
//		}
//		draw();
//	}
//	void print(ArrayList<Marble> arr)
//	{
//		for(int i=0;i<arr.size();i++){
//			System.out.println(arr.get(i));
//		}
//	}
//	public int transfromX(int x){return x*(WIDTH/cols)+div/2;}
//	public int transformY(int y){return y*(HEIGHT/rows)+div/2;}
//	
//}
