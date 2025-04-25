import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Model.Catalogo;
import org.example.Model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


public class CatalogoTest {
    Catalogo catalogo;
    Libro libro1;
    Libro libro2;

    @BeforeEach
    void setUp() {
        catalogo = new Catalogo();
        libro1 = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
        libro2 = new Libro("978-0-13-235088-4", "Clean Architecture", "Robert C. Martin");
        catalogo.agregarLibro(libro1);
        catalogo.agregarLibro(libro2);
    }

    @DisplayName("Búsqueda exitosa")
    @Test
    void testBuscarPorIsbn() {
        Libro libro = catalogo.buscarPorIsbn("978-3-16-148410-0");
        assertNotNull(libro);
        assertEquals("Clean Code", libro.getTitulo());
    }

    @DisplayName("Búsqueda fallida")
    @Test
    void testBusquedaFallida(){
        Libro libro = catalogo.buscarPorIsbn("978-3-16-148410-8");
        assertNull(libro);
    }

    @DisplayName("Devolver lista")
    @Test
    void testMostrarLibros() {
        List<Libro> prueba = catalogo.todosLosLibros();
        assertEquals(2, prueba.size());
    }
}
