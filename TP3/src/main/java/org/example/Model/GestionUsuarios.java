package org.example.Model;

import lombok.Data;
import org.example.Exceptions.LibroNoDisponibleException;
import org.example.Exceptions.UsuarioNoEncontradoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class GestionUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();
    private SistemaPrestamos sistemaPrestamos;

    public GestionUsuarios(SistemaPrestamos sistemaPrestamos) {
        this.sistemaPrestamos = sistemaPrestamos;
    }

    public Usuario buscarPorNombre(String nombre) throws UsuarioNoEncontradoException{
        Optional<Usuario> resultado = usuarios.stream()
                .filter(l -> l.getNombre().equals(nombre))
                .findFirst();
        if (resultado.isEmpty()){
            throw new UsuarioNoEncontradoException("El usuario no fue encontrado");
        }
        return resultado.orElse(null);
    }

    public void registrarPrestamo(String usuario, String isbn) throws UsuarioNoEncontradoException, LibroNoDisponibleException {
        Usuario user = buscarPorNombre(usuario);
        Prestamo prestamo = sistemaPrestamos.prestarLibro(isbn);
        user.getHistorialPrestamos().add(prestamo);


    }

}
