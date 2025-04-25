import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.example.Enum.Estado;
import org.example.Model.Catalogo;
import org.example.Model.SistemaPrestamos;
import org.example.Model.Prestamo;
import org.example.Model.Libro;
import org.example.Exceptions.LibroNoDisponibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class PrestamosTest {
    Libro libro;
    @Mock
    private Catalogo catalogo;

    @InjectMocks
    private SistemaPrestamos sistemaPrestamos;

    @BeforeEach
    public void setUp() throws LibroNoDisponibleException {
        MockitoAnnotations.initMocks(this);
        libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);
    }

    @DisplayName("Prestar libro")
    @Test
    void testPrestarLibro() throws LibroNoDisponibleException {
        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");

        assertNotNull(prestamo);
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @DisplayName("Devolver libro")
    @Test
    void testDevolverLibro() throws LibroNoDisponibleException {
        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");
        assertNotNull(prestamo);
        assertEquals(Estado.PRESTADO, libro.getEstado());

        sistemaPrestamos.devolverLibro("978-3-16-148410-0");

        assertEquals(Estado.DISPONIBLE, libro.getEstado());
        verify(catalogo, times(2)).buscarPorIsbn("978-3-16-148410-0");

    }

    @DisplayName("Prestar libro no encontrado")
    @Test
    void testPrestarLibroNoEncontrado() throws LibroNoDisponibleException {
        when(catalogo.buscarPorIsbn("978-3-16-148410-1")).thenThrow(new LibroNoDisponibleException("Libro no encontrado"));

        assertThrows(LibroNoDisponibleException.class, () -> {
            sistemaPrestamos.prestarLibro("978-3-16-148410-1");
        });

        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @DisplayName("Devolver libro no encontrado")
    @Test
    void testDevolverLibroNoEncontrado() throws LibroNoDisponibleException {
        when(catalogo.buscarPorIsbn("978-3-16-148410-1")).thenThrow(new LibroNoDisponibleException("Libro no encontrado"));

        assertThrows(LibroNoDisponibleException.class, () -> {
            sistemaPrestamos.devolverLibro("978-3-16-148410-1");
        });

        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @DisplayName("Prestar libro ya prestado")
    @Test
    void testPrestarLibroYaPrestado() throws LibroNoDisponibleException {
        libro.setEstado(Estado.PRESTADO);

        assertThrows(LibroNoDisponibleException.class, () -> {
            Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");
        });

    }

    @DisplayName("Devolver libro ya devuelto")
    @Test
    void testDevolverLibroYaDevuelto() throws LibroNoDisponibleException {
        libro.setEstado(Estado.DISPONIBLE);

        assertThrows(LibroNoDisponibleException.class, () -> {
            sistemaPrestamos.devolverLibro("978-3-16-148410-0");
        });
    }
}

