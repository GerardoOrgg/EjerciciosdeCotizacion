package mx.edu.utez.proyecto1C.customException;

public class BadRequestsException extends RuntimeException {
    public BadRequestsException(String message) {
        super(message);
    }
}
