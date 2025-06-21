package br.com.alura.fipesearch.models;

public class InvalidOptionException extends RuntimeException {
    private final String msg;

    public InvalidOptionException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
