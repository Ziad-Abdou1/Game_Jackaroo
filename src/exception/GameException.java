package exception;

// These exceptions arise from any invalid action that is performed.
public abstract class GameException extends Exception {

	public GameException() {
        super();
    }
	
    public GameException(String message) {
        super(message);
    }
}
