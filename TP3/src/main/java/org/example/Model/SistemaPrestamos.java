package org.example.Model;

import lombok.Data;
import org.example.Enum.Estado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class SistemaPrestamos {
    private Catalogo catalogo;
    private List<Prestamo> prestamos = new ArrayList<>();

    public SistemaPrestamos(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Prestamo prestarLibro(String isbn){
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro.getEstado() == Estado.DISPONIBLE){
            Prestamo prestamo = new Prestamo(libro);
            prestamos.add(prestamo);
            libro.setEstado(Estado.PRESTADO);
            return prestamo;
        }else {
            System.out.println("El libro ya está prestado");
            return null;
        }

    }

    public void devolverLibro(String isbn){
        Libro libro = catalogo.buscarPorIsbn(isbn);
        if (libro.getEstado() == Estado.PRESTADO){
            prestamos.remove(libro);
            libro.setEstado(Estado.DISPONIBLE);
        }else {
            System.out.println("El libro no está prestado");
        }
    }
}
