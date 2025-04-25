package org.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Enum.Estado;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    private String ISBN;
    private String titulo;
    private String autor;
    private Estado estado;

}
