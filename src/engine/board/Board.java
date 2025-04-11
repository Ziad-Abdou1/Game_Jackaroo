package engine.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import engine.GameManager;
import exception.*;
import model.Colour;
import model.player.Marble;
import model.player.Player;

public class Board implements BoardManager {
    private final ArrayList<Cell> track;
    private final ArrayList<SafeZone> safeZones;
	private final GameManager gameManager;
    private int splitDistance;

    public Board(ArrayList<Colour> colourOrder, GameManager gameManager) {
        this.track = new ArrayList<>();
        this.safeZones = new ArrayList<>();
        this.gameManager = gameManager;
        
        for (int i = 0; i < 100; i++) {
            this.track.add(new Cell(CellType.NORMAL));
            
            if (i % 25 == 0) 
                this.track.get(i).setCellType(CellType.BASE);
            
            else if ((i+2) % 25 == 0) 
                this.track.get(i).setCellType(CellType.ENTRY);
        }

        for(int i = 0; i < 8; i++)
            this.assignTrapCell();

        for (int i = 0; i < 4; i++)
            this.safeZones.add(new SafeZone(colourOrder.get(i)));

        splitDistance = 3;
    }
    
    private void assignTrapCell() {
        int randIndex = -1;
        
        do
            randIndex = (int)(Math.random() * 100); 
        while(this.track.get(randIndex).getCellType() != CellType.NORMAL || this.track.get(randIndex).isTrap());
        
        this.track.get(randIndex).setTrap(true);
    }
    
    //milestone 2---------------------------------------------------------------------------------------------------------------------------
    private ArrayList<Cell> getSafeZone(Colour colour){
    	for(SafeZone s:safeZones){
    		if(s.getColour()==colour){
    			return s.getCells();
    		}
    	}
    	return null;
    }
    private int getPositionInPath(ArrayList<Cell> path, Marble marble){
    	if(path==null) return -1;            //I added this because validateSteps function
    	for(int i=0;i<path.size();i++){
    		Cell c=path.get(i);
    		if(c.getMarble()==marble){
    			return i;
    		}
    	}
    	return -1;
    }
    private int getBasePosition(Colour colour){
    	for(int i=0;i<safeZones.size();i++){
    		if(safeZones.get(i).getColour()==colour){
    			return 25*i;
    		}
    	}
    	return -1;
    }
    private int getEntryPosition(Colour colour){
    	for(int i=0;i<safeZones.size();i++){
    		if(safeZones.get(i).getColour()==colour){
    			return ((25*i-2)+100)%100;
    		}
    	}
    	return -1;
    }
//    private ArrayList<Cell> validateSteps(Marble marble, int steps) throws IllegalMovementException{
//    	//question: if card is 4 , will he give me steps as 4 or as -4 ?
//    	//I considered it 4
//    	// if steps can be -ve ,  you as a programmer should set the boolean stepsCanBeNegative to be true
//    	boolean stepsCanBeNegative=false;
//    	if(stepsCanBeNegative){
//    		return validateSteps2(marble, steps);
//    	}
//    	ArrayList<Cell> ans=new ArrayList<Cell>();
//    	int cur=getPositionInPath(track, marble);
//    	if(cur==-1){
//    		ArrayList<Cell> sf=getSafeZone(marble.getColour()); //should I handle if it is null?
//    		cur=getPositionInPath(sf, marble);
//    		if(cur==-1) throw new IllegalMovementException("the marble cannot be moved.");
//    		// marble now is in safe zone
//    		if(steps==4) throw new IllegalMovementException("marble cannot move backwards in Safe Zone");
//    		if(cur+steps>=4) throw new IllegalMovementException("the rank of the card played is too high."); //steps==5 is included here automatically
//    		for(int i=0;i<=steps;i++){ 
//    			ans.add(sf.get(cur+i));
//    		}
//    		return ans;
//    	}
//    	//marble now is on track
//    	if(steps==4){
//    		for(int i=0;i<=steps;i++){
//    			ans.add( track.get( ((cur-i)%100+100)%100 ));
//    		}
//    		return ans;
//    	}
//    	Colour clrCurrentPlayer=gameManager.getActivePlayerColour();
//    	if(steps==5){
//    		for(int i=0;i<=steps;i++){
//    			Cell cell=track.get((cur+i)%100);
//    			ans.add(cell);
//    			if(cell.getMarble()!=null && cell.getMarble().getColour()==clrCurrentPlayer){
//    				throw new IllegalMovementException();
//    			}
//    		}
//    		return ans;
//    	}
//    	int entry=getEntryPosition(marble.getColour());
//    	int distanceToEnd=((entry-cur)%100+100)%100 +4;
//    	if(distanceToEnd<steps) throw new IllegalMovementException("the rank of the card played is too high.");
//    	int distanceToEntry=distanceToEnd-4;
//    	
//    	
//    	int initialDistance=Math.min(distanceToEntry,steps);
//    	for(int i=0;i<=initialDistance;i++){
//    		ans.add(track.get((cur+i)%100)); 
//    	}
//    	if(distanceToEntry<steps){
//    		int remainingSteps=steps-distanceToEntry;
//    		ArrayList<Cell> sf=getSafeZone(marble.getColour()); //should I handle if it is null?
//    		for(int i=0;i<remainingSteps;i++){
//    			ans.add(sf.get(i));
//    		}
//    	}
//    	return ans;
//    }
    private ArrayList<Cell> validateSteps(Marble marble, int steps) throws IllegalMovementException{
    	//I handled here if steps <0
//    	if(steps<0 && steps!=-4) throw new IllegalMovementException(); // not mentioned anywhere
    	ArrayList<Cell> ans=new ArrayList<Cell>();
    	int cur=getPositionInPath(track, marble);
    	if(cur==-1){
    		ArrayList<Cell> sf=getSafeZone(marble.getColour()); //should I handle if it is null?
    		cur=getPositionInPath(sf, marble);
    		if(cur==-1) throw new IllegalMovementException("the marble cannot be moved.");
    		// marble now is in safe zone
    		if(steps<0) throw new IllegalMovementException("marble cannot move backwards in Safe Zone");
    		if(cur+steps>=4) throw new IllegalMovementException("the rank of the card played is too high."); //steps==5 is included here automatically
    		for(int i=0;i<=steps;i++){ 
    			ans.add(sf.get(cur+i));
    		}
    		return ans;
    	}
    	//marble now is on track
    	if(steps<0){
    		steps=-steps;
    		for(int i=0;i<=steps;i++){
    			ans.add( track.get( ((cur-i)%100+100)%100 ));
    		}
    		return ans;
    	}
    	Colour clrCurrentPlayer=gameManager.getActivePlayerColour();
    	if(steps==5){
    		for(int i=0;i<=steps;i++){
    			Cell cell=track.get((cur+i)%100);
    			ans.add(cell);
    			if(cell.getMarble()!=null && cell.getMarble().getColour()==clrCurrentPlayer){
    				throw new IllegalMovementException();
    			}
    		}
    		return ans;
    	}
    	int entry=getEntryPosition(marble.getColour());
    	int distanceToEnd=((entry-cur)%100+100)%100 +4;
    	if(distanceToEnd<steps) throw new IllegalMovementException("the rank of the card played is too high.");
    	int distanceToEntry=distanceToEnd-4;
    	
    	
    	int initialDistance=Math.min(distanceToEntry,steps);
    	for(int i=0;i<=initialDistance;i++){
    		ans.add(track.get((cur+i)%100)); 
    	}
    	if(distanceToEntry<steps){
    		int remainingSteps=steps-distanceToEntry;
    		ArrayList<Cell> sf=getSafeZone(marble.getColour()); //should I handle if it is null?
    		for(int i=0;i<remainingSteps;i++){
    			ans.add(sf.get(i));
    		}
    	}
    	return ans;
    }
    
    private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException{
    	if(destroy){
    		for(int i=1;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getCellType()==CellType.BASE && c.getMarble()!=null && track.get(getBasePosition(c.getMarble().getColour()))==c){
    				throw new IllegalMovementException("Base Cell Blockage");
    			}
    		}
    		
    	}else{
    		for(int i=1;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getMarble()!=null && c.getMarble().getColour()==fullPath.get(0).getMarble().getColour()){
    				throw new IllegalMovementException("Self Blocking");
    			}
    		}
    		int cntOtherMarbles=0;
    		for(int i=1;i<fullPath.size()-1; i++){
    			Cell c=fullPath.get(i);
    			if(c.getMarble()!=null && c.getMarble().getColour()!=fullPath.get(0).getMarble().getColour()){
    				cntOtherMarbles++;
    				if(cntOtherMarbles>1){
    					throw new IllegalMovementException("path Blockage");
    				}
    			}
    		}
    		for(int i=1;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getCellType()==CellType.ENTRY && c.getMarble()!=null && i<fullPath.size()-1 && fullPath.get(i+1).getCellType()==CellType.SAFE){
    				throw new IllegalMovementException("Safe Zone Entry: cannot enter safe zone if any marble is in entry");
    			}
    		}
    		for(int i=1;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getCellType()==CellType.BASE && c.getMarble()!=null && track.get(getBasePosition(c.getMarble().getColour()))==c){
    				throw new IllegalMovementException("Base Cell Blockage");
    			}
    		}
    	}
    	for(int i=1;i<fullPath.size();i++){
			Cell c=fullPath.get(i);
			if(c.getCellType()==CellType.SAFE && c.getMarble()!=null){
				throw new IllegalMovementException("marbles in their Safe Zone are safe from any interference");
			}
		}
    }
    private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalDestroyException{ 
    	ArrayList<Marble> destroyed=new ArrayList<Marble>(); 
    	Cell last=fullPath.get(fullPath.size()-1);

    	fullPath.get(0).setMarble(null);
    	if(destroy){
    		for(int i=0;i<fullPath.size();i++){
    			Marble m=fullPath.get(i).getMarble();
    			if(m!=null){
    				if(i!=0) destroyed.add(m);
    				fullPath.get(i).setMarble(null);
    			}
    		}
    	}else{
    		Marble m= last.getMarble();
    		if(m!=null){ 
    			destroyed.add(m);
    			last.setMarble(null);
    		}
    	}
    	
    	
    	if(last.isTrap()){
    		last.setMarble(null); //no usage
    		last.setTrap(false);
    		destroyed.add(marble);
    		assignTrapCell();
    	}else{
    		last.setMarble(marble);
    	}
    	
    	for(int i=0;i<destroyed.size();i++){
    		Marble m=destroyed.get(i);
    		gameManager.sendHome(m);
    	}
    }
    
    private void validateSwap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
    	// I assumed that marble_1 is my marble , and marble_2 is my opponent marble
    	int idx1=getPositionInPath(track, marble_1);
    	int idx2=getPositionInPath(track, marble_2);
    	if(idx1==-1 || idx2==-1){
    		throw new IllegalSwapException(); 
    	}
    	int base1=getBasePosition(marble_1.getColour());
    	int base2=getBasePosition(marble_2.getColour());
    	
    	if(base2==idx2){ //if my opponent marble in its base cell 
    		throw new IllegalSwapException();
    	}
    	
//    	//this is not mentioned in the milestone , but rather in the game description
//    	//it says that the 2 marbles should not be to the same player
//    	if(marble_1.getColour()==marble_2.getColour()){
//    		throw new IllegalSwapException();
//    	}                                      
    	//this will be handled in the  boolean validateMarbleColours(ArrayList<Marble> marbles);
    }
    
    private void validateDestroy(int positionInPath) throws IllegalDestroyException{
    	if(positionInPath==-1){
    		throw new IllegalDestroyException();
    	}
    	Cell c=track.get(positionInPath);
    	Marble m=c.getMarble();
//    	if(m==null){          //He didn't mention this. I write it myself. because it has no meaning that I destroy an empty cell
//    		                   also it handles the next if condition, in case of m==null , null pointer exception
//    		throw new IllegalDestroyException(); 
//    	}
    	if(m==null) return; //I changed my mind. if m==null .
    	//I think in real use of this function , if m=null , then postionInPath will =-1. So this condition is not important anymore(and not do what is expected)
    	if(getBasePosition(m.getColour())==positionInPath){
    		throw new IllegalDestroyException();
    	}
    	
    }
    private void validateFielding(Cell occupiedBaseCell) throws CannotFieldException{
    	Marble m=occupiedBaseCell.getMarble();
    	if(m!=null){
    		int idxbase=getBasePosition(m.getColour());
    		if(track.get(idxbase)==occupiedBaseCell) throw new CannotFieldException();
    	}
    }
    private void validateSaving(int positionInSafeZone, int positionOnTrack) throws InvalidMarbleException{
    	if(positionOnTrack==-1) throw new InvalidMarbleException();
    }
    
    public void moveBy(Marble marble, int steps, boolean destroy) throws IllegalMovementException, IllegalDestroyException{
    	//PLEASE: don't use this method inside Game.sendHome(Marble marble) function
    	ArrayList<Cell> fullPath=validateSteps(marble, steps);
    	validatePath(marble, fullPath, destroy);
    	move(marble, fullPath, destroy);
    }
    public void swap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
    	Colour ccc=gameManager.getActivePlayerColour();
    	if(marble_2.getColour()==ccc){
    		Marble tmp=marble_1;
    		marble_1=marble_2;
    		marble_2=tmp;
    	}
    	validateSwap(marble_1, marble_2);
    	int idx1=getPositionInPath(track, marble_1);
    	int idx2=getPositionInPath(track, marble_2);
    	
    	Cell c1=track.get(idx1);
    	Cell c2=track.get(idx2);
    	
    	Marble tmp=c1.getMarble();
    	c1.setMarble(c2.getMarble());
    	c2.setMarble(tmp);
    }
    public void destroyMarble(Marble marble) throws IllegalDestroyException{
    	//PLEASE: check if marble is null or not before calling this function
    	//PLEASE: don't use this function inside Game.sendHome(Marble marble) function

    	int idx=getPositionInPath(track, marble);
    	validateDestroy(idx);
    	track.get(idx).setMarble(null);
    	gameManager.sendHome(marble);
    }
    public void sendToBase(Marble marble) throws CannotFieldException,IllegalDestroyException{
    	int baseidx=getBasePosition(marble.getColour());
    	validateFielding(track.get(baseidx));
    	Marble m=track.get(baseidx).getMarble();
    	if(m!=null){
    		destroyMarble(m);
    	}
    	track.get(baseidx).setMarble(marble);
    	
    }
    public void sendToSafe(Marble marble) throws InvalidMarbleException{
    	validateSaving(0, getPositionInPath(track, marble)); // 0 is any integer , because validateSaving() doesn't depend on position in safe zone
    	Random r=new Random();
    	ArrayList<Cell> sf=getSafeZone(marble.getColour());
    	ArrayList<Cell> sfEmpty=new ArrayList<Cell>();
    	for(int i=0;i<sf.size();i++){
    		if(sf.get(i).getMarble()==null){
    			sfEmpty.add(sf.get(i));
    		}
    	}
    	Collections.shuffle(sfEmpty);
    	if(sf.size()>0){//has no usage, because if it is =0 , then validateSaving() will throw exception , because all safe cells are full , so the marble is in safe zone   
    		Cell target=sf.get(0);
    		int idx=getPositionInPath(track, marble);
    		track.get(idx).setMarble(null);
    		target.setMarble(marble);
    	}
    }
    public ArrayList<Marble> getActionableMarbles(){
    	ArrayList<Marble> ans=new ArrayList<Marble>();
    	Colour clrOfCurrentPlayer= gameManager.getActivePlayerColour();
    	for(int i=0;i<track.size();i++){
    		Marble m=track.get(i).getMarble();
    		if(m!=null && m.getColour()==clrOfCurrentPlayer){
    			ans.add(m);
    		}
    	}
    	return ans;
    }
    
    //----------------------------------------------------------------------------------------------------------------------------------------

    public ArrayList<Cell> getTrack() {
        return this.track;
    }
 // ziad test 
    public ArrayList<SafeZone> getSafeZones() {
        return this.safeZones;
    }
    
    @Override
    public int getSplitDistance() {
        return this.splitDistance;
    }

    public void setSplitDistance(int splitDistance) {
        this.splitDistance = splitDistance;
    }
   

    
}
