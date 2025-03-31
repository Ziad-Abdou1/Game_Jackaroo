package engine;
import java.util.ArrayList;

import model.Colour;
import model.player.Marble;
import exception.*;
public interface GameManager {
	
	//milestone 2-----------------------------------------------------------------------------------------------------
	void sendHome(Marble marble);
	
	void fieldMarble() throws CannotFieldException, IllegalDestroyException;
	
	void discardCard(Colour colour) throws CannotDiscardException;
	
	void discardCard() throws CannotDiscardException;
	
	Colour getActivePlayerColour();
	
	Colour getNextPlayerColour();
	
	//-----------------------------------------------------------------------------------------------------------------
}
