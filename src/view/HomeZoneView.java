package view;

import java.util.ArrayList;

import engine.Game;
import engine.board.Cell;
import engine.board.CellType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.Colour;
import model.player.Marble;
import model.player.Player;

public class HomeZoneView extends GridPane {
	
	Game game;
	private Player player;
	ArrayList<Cell> cells;
	ArrayList<CellView> cellViews;
	public HomeZoneView(Player player,Game game){
		this.game=game;
		this.player = player;
		cells=new ArrayList<Cell>();
		cellViews=new ArrayList<CellView>();
		draw();
		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
		this.setAlignment(Pos.CENTER);
	
>>>>>>> Stashed changes
=======
		this.setAlignment(Pos.CENTER);
>>>>>>> tester
	}
	private void draw(){
		int num = player.getMarbles().size();
		Colour clr = player.getColour();
		int curi = 1, curj = 1;
		int[] dx = {-1,0,1,0};
		int[] dy ={0,-1,0,1};
		ArrayList<Marble> marbles=player.getMarbles();
		for (int i =0; i < 4; i++){
			Cell c = new Cell(CellType.NORMAL);
			CellView cv;
			if (i < num){
				c.setMarble(marbles.get(i));
				cv=new CellView(c, game);
				this.addNode(cv,curj,curi);
			}
			else{
				cv=new CellView(c, game);
				this.addNode(cv,curj,curi);
			}
			cells.add(c);
			cellViews.add(cv);
			curi += dy[i];
			curj += dx[i];
		}
		this.setRotate(45);
		this.setStyle(
			    "-fx-background-color: lightblue;" +
			    "-fx-border-color: #4682B4;" +  
			    "-fx-border-width: 3;" +
			    "-fx-border-radius: 5;" +
			    "-fx-background-radius: 5;"
			);
	}
	
	public void refresh(){
		int num = player.getMarbles().size();
		Colour clr = player.getColour();
		int curi = 1, curj = 1;
		int[] dx = {-1,0,1,0};
		int[] dy ={0,-1,0,1};
		ArrayList<Marble> marbles=player.getMarbles();
		for (int i =0; i < 4; i++){
			Cell c = cells.get(i);
			CellView cv = cellViews.get(i);
			if (i < num){
				c.setMarble(marbles.get(i));
				cv.setCell(c);
			}
			else{
				c.setMarble(null);
				cv.setCell(c);
			}
			curi += dy[i];
			curj += dx[i];
		}
	}
    public void addNode(Node node, int col, int row) {
        this.add(node, col, row);
    }
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public ArrayList<Cell> getCells() {
		return cells;
	}
	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}
	public ArrayList<CellView> getCellViews() {
		return cellViews;
	}
	public void setCellViews(ArrayList<CellView> cellViews) {
		this.cellViews = cellViews;
	}
    
}

