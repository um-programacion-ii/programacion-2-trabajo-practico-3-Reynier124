import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Enum.Estado;
import org.example.Model.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibroTest {
    Libro libro;

    @BeforeEach
    void prepararLibro() {
        this.libro = new Libro("978-3-16-148410-0", "Clean Code", "Robert C. Martin");
    }

    @Test
    void testCrearLibroValido(){
        assertEquals("978-3-16-148410-0", libro.getISBN());
        assertEquals("Clean Code", libro.getTitulo());
        assertEquals("Robert C. Martin", libro.getAutor());
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
    }

    @Test
    void testCambioEstado(){
        assertEquals(Estado.DISPONIBLE, libro.getEstado());
        libro.setEstado(Estado.PRESTADO);
        assertEquals(Estado.PRESTADO, libro.getEstado());
    }
}
