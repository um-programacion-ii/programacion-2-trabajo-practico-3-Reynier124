package org.example.Model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Prestamo {
    private LocalDate fechaPrestamo;
    private Libro libro;
}
