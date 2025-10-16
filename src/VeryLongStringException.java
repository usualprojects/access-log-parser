public class VeryLongStringException extends RuntimeException {

    public VeryLongStringException() {
        super();
    }

    public VeryLongStringException(String message) {
        super(message);
    }

    public VeryLongStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public VeryLongStringException(Throwable cause) {
        super(cause);
    }
}
