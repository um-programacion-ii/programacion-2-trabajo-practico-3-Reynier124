package org.example.Model;

import lombok.Data;
import org.example.Enum.Estado;
import org.example.Exceptions.LibroNoDisponibleException;

import java.util.ArrayList;
import java.util.List;

@Data
public class SistemaPrestamos {
    private Catalogo catalogo;
    private List<Prestamo> prestamos = new ArrayList<>();

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Libro buscarLibro(String isbn) {
        try {
            return catalogo.buscarPorIsbn(isbn);
        } catch (LibroNoDisponibleException e) {
            return null;
        }
    }

    public Prestamo prestarLibro(String isbn) throws LibroNoDisponibleException {
        Libro libro = buscarLibro(isbn);
        if (libro == null) {
            throw new LibroNoDisponibleException("Libro no encontrado");
        }
        if (libro.getEstado() == Estado.DISPONIBLE){
            Prestamo prestamo = new Prestamo(libro);
            prestamos.add(prestamo);
            libro.setEstado(Estado.PRESTADO);
            return prestamo;
        }else {
            throw new LibroNoDisponibleException("El libro ya está prestado");
        }

    }

    public void devolverLibro(String isbn) throws LibroNoDisponibleException {
        Libro libro = buscarLibro(isbn);
        if (libro == null) {
            throw new LibroNoDisponibleException("Libro no encontrado");
        }
        if (libro.getEstado() == Estado.PRESTADO){
            prestamos.remove(libro);
            libro.setEstado(Estado.DISPONIBLE);
        }else {
            throw new LibroNoDisponibleException("El libro no está prestado");
        }
    }
}
