package Exceptions;

public class NoTagsException extends Exception {
    public NoTagsException() {
        super("Repository doesn't have tags.");
    }
}
