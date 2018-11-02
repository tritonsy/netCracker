package building.exceptions;

public class InvalidRoomsCountException extends IllegalArgumentException {
    public InvalidRoomsCountException(String s) {
        super(s);
    }
}
