package Exceptions;

public class RepositoryNotFoundException extends Exception {
    public RepositoryNotFoundException() {
        super("Repository doesn't exist.");
    }
}
