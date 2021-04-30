package api.exceptions;

public class NoFirefightersException extends Exception {
    public NoFirefightersException() {
        super("This fire station has not hired any firefighters!");
    }
}
