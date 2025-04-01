package exception;

public abstract class ActionException extends GameException {
	
	public ActionException() {
        super();
    }
	//test
    public ActionException(String message) {
        super(message);
    }

}
