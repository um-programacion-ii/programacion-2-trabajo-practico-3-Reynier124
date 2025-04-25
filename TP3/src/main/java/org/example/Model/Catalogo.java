package org.example.Model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.Exceptions.LibroNoDisponibleException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@ToString
public class Catalogo {
    private List<Libro> libros = new ArrayList<>();

    public void agregarLibro(Libro libro) {
        this.libros.add(libro);
    }

    public Libro buscarPorIsbn(String isbn) throws LibroNoDisponibleException {
        Optional<Libro> resultado = libros.stream()
                .filter(l -> l.getISBN().equals(isbn))
                .findFirst();
        if (resultado.isEmpty()){
            throw new LibroNoDisponibleException("El libro no fue encontrado");
        }
        return resultado.orElse(null);
    }

    public List<Libro> todosLosLibros() {
        return libros;
    }


}
