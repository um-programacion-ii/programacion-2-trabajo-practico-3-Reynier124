package org.example.Model;

import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public Libro buscarPorIsbn(String isbn) {
        Optional<Libro> resultado = libros.stream()
                .filter(l -> l.getISBN().equals(isbn))
                .findFirst();
        return resultado.orElse(null);
    }

    public List<Libro> todosLosLibros() {
        return libros;
    }


}
