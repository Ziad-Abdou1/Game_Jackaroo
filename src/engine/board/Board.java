package engine.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.management.RuntimeErrorException;

import engine.BoardListener;
import engine.Game;
import engine.GameListener;
import engine.GameManager;
import exception.*;
import model.Colour;
import model.player.Marble;
import model.player.Player;

public class Board implements BoardManager {
	private final List<BoardListener> listeners = new ArrayList<>();
	
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
    		if(c!=null && c.getMarble()==marble){
    			return i;
    		}
    	}
    	return -1;
    }
    private int getBasePosition(Colour colour){
    	for(int i=0;i<safeZones.size();i++){
    		if(safeZones.get(i)!=null && safeZones.get(i).getColour()==colour){
    			return 25*i;
    		}
    	}
    	return -1;
    }
    private int getEntryPosition(Colour colour){
    	for(int i=0;i<safeZones.size();i++){
    		if(safeZones.get(i)!=null && safeZones.get(i).getColour()==colour){
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
    	if(marble==null) return new ArrayList<Cell>(); //this is to handle if there is strange test cases
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

    	int entry=getEntryPosition(marble.getColour());
    	int distanceToEnd=((entry-cur)%100+100)%100 +4;
    	
    	//moving my own marble and steps is much higher than the end of the game
    	if(distanceToEnd<steps && clrCurrentPlayer==marble.getColour()) throw new IllegalMovementException("the rank of the card played is too high.");
    	
    	//moving an opponent
    	if(clrCurrentPlayer!=marble.getColour()){
    		for(int i=0;i<=steps;i++){
    			Cell cell=track.get((cur+i)%100);
    			ans.add(cell);
    			if(i!=0 && cell.getMarble()!=null && cell.getMarble().getColour()==clrCurrentPlayer){
    				throw new IllegalMovementException("can not bypass or land your own marbles");
    			}
    		}
    		return ans;
    	}
    	
    	
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
//    public static void main(String[] args) throws IllegalMovementException,IOException{
//    	ArrayList<Colour> colourOrder=new ArrayList(Arrays.asList(Colour.RED,Colour.GREEN,Colour.BLUE,Colour.YELLOW));
//    	GameManager g=new Game("Morkos");
//    	Marble marble=new Marble(Colour.RED);
//    	Marble m1=new Marble(Colour.RED);
//    	Cell c=new Cell(CellType.SAFE);
//    	c.setMarble(m1);
//    	ArrayList<Cell> fullPath=new ArrayList<Cell>();
//    	fullPath.add(c);
//    	Board b=new Board(colourOrder, g);
//    	b.validatePath(marble, fullPath, false);
//    }
    private void validatePath(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalMovementException{
    	int startIdx;
//    	if(marble==fullPath.get(0).getMarble()) startIdx=1;
//    	else startIdx=0;
    	
    	startIdx=1;
    	
    	Colour currClr=gameManager.getActivePlayerColour();
    	String s="";
    	int mn=fullPath.size();
    	if(destroy){
    		for(int i=startIdx;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getCellType()==CellType.BASE && c.getMarble()!=null && track.get(getBasePosition(c.getMarble().getColour()))==c){
//    				throw new IllegalMovementException("Base Cell Blockage");
    				if(i<mn) {
    					s="Base Cell Blockage";
    					mn=i;
    					break;
    				}
    			}
    		}
    		
    	}else{
    		for(int i=startIdx;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getMarble()!=null && c.getMarble().getColour()==currClr){
//    				throw new IllegalMovementException("Self Blocking");
    				if(i<mn){
    					s="Self Blocking";
    					mn=i;
    				}
					break;
    			}
    		}
    		int cntOtherMarbles=0;
    		for(int i=startIdx;i<fullPath.size()-1; i++){
    			Cell c=fullPath.get(i);
    			if(c.getMarble()!=null){
    				cntOtherMarbles++;
    				if(cntOtherMarbles>1){
//    					throw new IllegalMovementException("path Blockage");
    					if(i<mn){
    						s="path Blockage";
    						mn=i;
    					}
    					break;
    				}
    			}
    		}
    		for(int i=startIdx;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getCellType()==CellType.ENTRY && c.getMarble()!=null && i<fullPath.size()-1 && fullPath.get(i+1).getCellType()==CellType.SAFE){
//    				throw new IllegalMovementException("Safe Zone Entry: cannot enter safe zone if any marble is in entry");
    				if(i<mn){
    					s="Safe Zone Entry: cannot enter safe zone if any marble is in entry";
    					mn=i;
    				}
					break;
    			}
    		}
    		for(int i=startIdx;i<fullPath.size();i++){
    			Cell c=fullPath.get(i);
    			if(c.getCellType()==CellType.BASE && c.getMarble()!=null && track.get(getBasePosition(c.getMarble().getColour()))==c){
//    				throw new IllegalMovementException("Base Cell Blockage");
    				if(i<mn){
    					s="Base Cell Blockage";
    					mn=i;
    				}
					break;
    			}
    		}
    		
    	}
    	for(int i=1;i<fullPath.size();i++){
			Cell c=fullPath.get(i);
			if(c.getCellType()==CellType.SAFE && c.getMarble()!=null){
//				throw new IllegalMovementException("marbles in their Safe Zone are safe from any interference");
				if(i<mn){
					s="marbles in their Safe Zone are safe from any interference";
					mn=i;
				}
				break;
			}
		}
    	
    	if(mn<fullPath.size()){
    		throw new IllegalMovementException(s);
    	}
    }
    private void move(Marble marble, ArrayList<Cell> fullPath, boolean destroy) throws IllegalDestroyException{ 
    	int startIdx;
//    	boolean marbleIsAtFirstOfPath;
//    	if(marble==fullPath.get(0).getMarble()){
////    		startIdx=1;
//    		marbleIsAtFirstOfPath=true;
//    	}else{
////    		startIdx=0;
//    		marbleIsAtFirstOfPath=false;
//    	}
    	startIdx=1;
    	ArrayList<Marble> destroyed=new ArrayList<Marble>(); 
    	Cell last=fullPath.get(fullPath.size()-1);

//    	if(marbleIsAtFirstOfPath)
    		fullPath.get(0).setMarble(null);
    	if(destroy){
    		for(int i=startIdx;i<fullPath.size();i++){
    			Marble m=fullPath.get(i).getMarble();
    			if(m!=null){
    				destroyMarble(m);
    			}
    		}
    	}else{
    		Marble m= last.getMarble();
    		if(m!=null){ 
    			destroyMarble(m);
    		}
    	}
    	
    	
    	if(last.isTrap()){
    		notifyTrap(1);
    		last.setMarble(null); //no usage
    		
    		last.setMarble(marble);
//    		destroyed.add(marble);
    		
    		destroyMarble(marble);
    		last.setTrap(false);
    		assignTrapCell();
    	}else{
    		last.setMarble(marble);
    	}
    	
    	for(int i=0;i<destroyed.size();i++){ //has no usage
    		Marble m=destroyed.get(i);
    		gameManager.sendHome(m);
    	}
    }
    
    private void validateSwap(Marble marble_1, Marble marble_2) throws IllegalSwapException{
    	// I assumed that marble_1 is my marble , and marble_2 is my opponent marble
    	Colour ccc=gameManager.getActivePlayerColour();
    	if(marble_2.getColour()==ccc){
    		Marble tmp=marble_1;
    		marble_1=marble_2;
    		marble_2=tmp;
    	}
    	
    	int idx1=getPositionInPath(track, marble_1);
    	int idx2=getPositionInPath(track, marble_2);
    	if(idx1==-1 || idx2==-1){
    		throw new IllegalSwapException("One of the marbles is not in the track"); 
    	}
    	int base1=getBasePosition(marble_1.getColour());
    	int base2=getBasePosition(marble_2.getColour());
    	
    	if(base2==idx2){ //if my opponent marble in its base cell 
    		throw new IllegalSwapException("other player marble is on its base");
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
    		throw new IllegalDestroyException("marble isn't in the track");
    	}
    	Cell c=track.get(positionInPath);
    	Marble m=c.getMarble();
//    	if(m==null){          //He didn't mention this. I write it myself. because it has no meaning that I destroy an empty cell
//    		                   also it handles the next if condition, in case of m==null , null pointer exception
//    		throw new IllegalDestroyException(); 
//    	}
//    	if(m==null) throw new IllegalDestroyException("marble is not found in the track"); //I changed my mind. if m==null .
    	if(m==null) return;
    	//I think in real use of this function , if m=null , then postionInPath will =-1. So this condition is not important anymore(and not do what is expected)
    	if(getBasePosition(m.getColour())==positionInPath){
    		throw new IllegalDestroyException("marble is safe in its base cell");
    	}
    	
    }
    private void validateFielding(Cell occupiedBaseCell) throws CannotFieldException{
    	Marble m=occupiedBaseCell.getMarble();
    	if(m!=null){
    		int idxbase=getBasePosition(m.getColour());
    		if(m.getColour()==gameManager.getActivePlayerColour()) throw new CannotFieldException("a marble of same colour exist in your base cell");
//    		if(track.get(idxbase)==occupiedBaseCell) throw new CannotFieldException();
    	}
    }
    private void validateSaving(int positionInSafeZone, int positionOnTrack) throws InvalidMarbleException{
    	if(positionOnTrack==-1 || positionInSafeZone!=-1) throw new InvalidMarbleException("the selected marble is not in the track");
//    	if(positionInSafeZone!=-1) throw new InvalidMarbleException();
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
    	if(marble.getColour()!=gameManager.getActivePlayerColour()) //note that this will happen in the real logic of the game because if the same color , the only way to handle exception is king and kill my own marble in its base cell , and this will not happen because validateSteps or validatePath
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
    	ArrayList<Cell> sf=getSafeZone(marble.getColour());
    	validateSaving(getPositionInPath(sf, marble), getPositionInPath(track, marble)); // 0 is any integer , because validateSaving() doesn't depend on position in safe zone
    	Random r=new Random();
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
    	//the marbles on the track are returned , even if it is not my color , because I can use these marbles if I want to swap with them.
    	ArrayList<Marble> ans=new ArrayList<Marble>();
    	Colour clrOfCurrentPlayer= gameManager.getActivePlayerColour();
    	for(int i=0;i<track.size();i++){
    		Marble m=track.get(i).getMarble();
    		if(m!=null){ //  && m.getColour()==clrOfCurrentPlayer
    			ans.add(m);
    		}
    	}
    	ArrayList<Cell> s=getSafeZone(clrOfCurrentPlayer);
    	for(int i=0;i<s.size();i++){
    		Cell c=s.get(i);
    		Marble m=c.getMarble();
    		if(m!=null){
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
    
    
    
    public void addListener(BoardListener l) {
        listeners.add(l);
    }

    /** Call this whenever you detect trapped marbles. */
    private void notifyTrap(int trappedCount) {
        for (BoardListener l : listeners) {
            l.onTrap(trappedCount);
        }
    }
   

    
}
