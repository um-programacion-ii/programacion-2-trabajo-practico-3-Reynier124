import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.example.Exceptions.LibroNoDisponibleException;
import org.example.Exceptions.UsuarioNoEncontradoException;
import org.example.Model.Catalogo;
import org.example.Model.SistemaPrestamos;
import org.example.Model.SistemaUsuarios;
import org.example.Model.Usuario;
import org.example.Model.Prestamo;
import org.example.Model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class UsuariosTest {
    private Usuario usuario;
    private Libro libro;
    @Mock
    private Catalogo catalogo;
    @Mock
    private SistemaPrestamos sistemaPrestamos;
    @InjectMocks
    private SistemaUsuarios sistemaUsuarios;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        usuario = new Usuario("usuario1");
        libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);
        when(sistemaPrestamos.prestarLibro("978-3-16-148410-0"))
                .thenReturn(new Prestamo(libro));

    }

    @DisplayName("RegistrarPrestamo")
    @Test
    void testRegistrarPrestamo() {
        gestionUsuarios.registrarPrestamo("usuario1", "978-3-16-148410-0");

        verify(sistemaPrestamos).prestarLibro("978-3-16-148410-0");
        assertEquals(1, usuario.getHistorialPrestamos().size());
    }

    @DisplayName("Excepcion Usuario")
    @Test
    void testRegistrarPrestamoUsuarioNoEncontrado() {
        when(sistemaUsuarios.buscarUsuario("usuarioInexistente")).thenThrow(new UsuarioNoEncontradoException("Usuario no encontrado"));

        assertThrows(UsuarioNoEncontradoException.class, () -> {
            sistemaUsuarios.registrarPrestamo("usuarioInexistente", "978-3-16-148410-0");
        });

        verify(sistemaPrestamos, never()).prestarLibro(anyString());
    }

    @DisplayName("Excepcion Libro")
    @Test
    void testRegistrarPrestamoLibroNoDisponible() {
        when(sistemaPrestamos.prestarLibro("978-3-16-148410-0")).thenThrow(new LibroNoDisponibleException("Libro no disponible"));

        assertThrows(LibroNoDisponibleException.class, () -> {
            sistemaUsuarios.registrarPrestamo("usuario1", "978-3-16-148410-0");
        });

        assertEquals(0, usuario.getHistorialPrestamos().size());
    }
}
