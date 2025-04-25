import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.Enum.Estado;
import org.example.Model.Catalogo;
import org.example.Model.SistemaPrestamos;
import org.example.Model.Prestamo;
import org.example.Model.Libro;
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
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        when(catalogo.buscarPorIsbn("978-3-16-148410-0")).thenReturn(libro);
    }

    @DisplayName("Prestar libro")
    @Test
    void testPrestarLibro() {
        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");

        assertNotNull(prestamo);
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }

    @DisplayName("Devolver libro")
    @Test
    void testDevolverLibro() {
        Prestamo prestamo = sistemaPrestamos.prestarLibro("978-3-16-148410-0");
        assertNotNull(prestamo);
        assertEquals(Estado.PRESTADO, libro.getEstado());

        sistemaPrestamos.devolverLibro("978-3-16-148410-0");

        assertEquals(Estado.DISPONIBLE, libro.getEstado());
        verify(catalogo).buscarPorIsbn("978-3-16-148410-0");

    }

}
