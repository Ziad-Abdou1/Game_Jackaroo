package engine.board;

import java.util.ArrayList;

import model.Colour;

public class SafeZone {
    private final Colour colour;
    private final ArrayList<Cell> cells;

    public SafeZone(Colour colour) {
        this.colour = colour;
        this.cells = new ArrayList<>();
        for (int i = 0; i < 4; i++) 
            this.cells.add(new Cell(CellType.SAFE));
    }

    //milestone 2
    public boolean isFull(){
    	assert(cells.size()==4);      // will not happen (cells.size()==4 always)
    	for(int i=0;i<cells.size();i++){
    		Cell x=cells.get(i);
    		if(x==null) return false; //will not happen for sure
    		if(x.getMarble()==null){
    			return false;
    		}
    	}
    	return true;
    }
    
    //____________________
    
    public Colour getColour() {
        return this.colour;
    }

    public ArrayList<Cell> getCells() {
        return this.cells;
    }

}
