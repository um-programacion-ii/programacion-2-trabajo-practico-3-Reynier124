package org.example.Model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Prestamo {
    private LocalDate fechaPrestamo;
    private Libro libro;

    public Prestamo(Libro libro) {
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
    }
}
