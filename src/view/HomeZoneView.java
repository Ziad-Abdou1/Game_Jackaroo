package view;

import engine.board.Cell;
import engine.board.CellType;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import model.Colour;
import model.player.Marble;
import model.player.Player;

public class HomeZoneView extends GridPane {
	
	private Player player;
	public HomeZoneView(Player player){
		this.player = player;
		draw();
		this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
	}
	private void draw(){
		int num = player.getMarbles().size();
		Colour clr = player.getColour();
		int curi = 1, curj = 1;
		int[] dx = {-1,0,1,0};
		int[] dy ={0,-1,0,1};
		for (int i =0; i < 4; i++){
			if (i < num){
				Cell c = new Cell(CellType.BASE);
				c.setMarble(new Marble(clr));
				this.addNode(new CellView(c),curj,curi);
			}
			else{
				Cell c = new Cell(CellType.BASE);
				this.addNode(new CellView(c),curj,curi);
			}
			curi += dy[i];
			curj += dx[i];
		}
		this.setRotate(45);
	}
    public void addNode(Node node, int col, int row) {
        this.add(node, col, row);
    }
}
