package org.example.Model;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Usuario {
    private String nombre;
    private List<Prestamo> historialPrestamos = new ArrayList<>();

    public Usuario(String nombre) {
        this.nombre = nombre;
    }
}
