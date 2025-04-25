package org.example.Model;

import lombok.*;
import org.example.Enum.Estado;

@Data
@NoArgsConstructor
public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private Estado estado = Estado.DISPONIBLE;

    public Libro(String ISBN, String titulo, String autor) {
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
    }
}
