public class NotValidSetFoundException extends Exception {
    public NotValidSetFoundException() {
        super("Valid set not found");
    }
    public NotValidSetFoundException(String message) {
        super(message);
    }
}
