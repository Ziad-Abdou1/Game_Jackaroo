package view;

import java.lang.reflect.Array;
import java.util.ArrayList;

import model.Colour;
import engine.Game;
import engine.board.Cell;
import engine.board.CellType;
import engine.board.SafeZone;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;

public class CellView extends StackPane {
	Rectangle2D screenBounds = Screen.getPrimary().getBounds();
	double screenWidth = screenBounds.getWidth();
	double screenHeight = screenBounds.getHeight();
	Game game;
	private Cell cell;
	private Circle circle;
	private final double radius = 14 * screenWidth / 3500;
	private MarbleView marbleView;

	public CellView(Cell c, Game game) {
		this.game = game;
		marbleView = new MarbleView(c.getMarble(), game);
		circle = new Circle();
		this.cell = c;
		circle.setRadius(radius);
		draw();
	}
    private Color toJavaFX(Colour col) {
        switch (col) {
            case RED:    return Color.CRIMSON;
            case BLUE:   return Color.DARKBLUE;
            case GREEN:  return Color.FORESTGREEN;
            case YELLOW: return Color.GOLD;
            // add the rest...
            default:     return Color.GRAY;
        }
    }
    private Colour getColourOfCell(Cell cell){
    	int idx=-1;
    	ArrayList<Cell> track=game.getBoard().getTrack();
    	for(int i=0;i<track.size();i++){
    		if(cell==track.get(i)){
    			idx=i;
    			break;
    		}
    	}
    	if(idx!=-1){
    		idx/=25;
    		if(idx==0) return game.getPlayers().get(0).getColour();
    		if(idx==1) return game.getPlayers().get(1).getColour();
    		if(idx==2) return game.getPlayers().get(2).getColour();
    		if(idx==3) return game.getPlayers().get(3).getColour();
    	}
    	// else , it is in safe zone
    	ArrayList<SafeZone> safeZones=game.getBoard().getSafeZones();
    	for(int i=0;i<safeZones.size();i++){
    		SafeZone sf=safeZones.get(i);
    		ArrayList<Cell> cells=sf.getCells();
    		if(cells.contains(cell)) return game.getPlayers().get(i).getColour();
    	}
    	return Colour.RED; //will not reach here.
    }
	public void draw() {

		if (cell.getMarble() != null) {
			marbleView = new MarbleView(cell.getMarble(), game);
		} else {
			this.getChildren().removeAll();
			circle.setFill(Color.DARKGRAY);

		}
		if (cell.isTrap()) {
			circle.setFill(Color.BLACK);
		}
		this.getChildren().addAll(circle);
		if (marbleView.getMarble() != null)
			this.getChildren().addAll(marbleView);
		
		if(cell.getCellType()==CellType.BASE){
            Colour owner = getColourOfCell(cell);  // assuming cell knows its owner colour
            circle.setStroke(toJavaFX(owner));
            circle.setStrokeWidth(4);

		}else if(cell.getCellType()==CellType.SAFE){
			Colour owner = getColourOfCell(cell);  // assuming cell knows its owner colour
	        circle.setStroke(toJavaFX(owner));
            circle.setStrokeWidth(2);
            // subtle glow
            DropShadow glow = new DropShadow(8, toJavaFX(owner));
            glow.setSpread(0.3);
            circle.setEffect(glow);
		}
		
	}

	public void refresh() {

		circle.setFill(Color.DARKGRAY);
		
		if (cell.isTrap()) {
			circle.setFill(Color.BLACK);
		} else {
			circle.setFill(Color.DARKGRAY);
		}
	}

	public void setCell(Cell cell) {
		this.cell = cell;
		circle.setFill(Color.DARKGRAY);
		this.getChildren().remove(marbleView);
		marbleView.setMarble(cell.getMarble());
		if (marbleView.getMarble() != null)
			this.getChildren().addAll(marbleView);
		refresh();
	}

	public MarbleView getMarbleView() {
		return this.marbleView;
	}

}
