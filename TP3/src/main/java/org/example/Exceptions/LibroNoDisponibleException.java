package org.example.Exceptions;

public class LibroNoDisponibleException extends Exception {
    public LibroNoDisponibleException() {}
    public LibroNoDisponibleException(String message) {
        super(message);
    }
}
