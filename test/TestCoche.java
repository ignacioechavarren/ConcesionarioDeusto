import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import domain.Coche;
import domain.Marca;

public class TestCoche {

    private Coche coche1;
    private Coche coche2;

    @Before
    public void setUp() throws Exception {
        // Initialize test Coche objects
        coche1 = new Coche(20000.0, 2022, Marca.TOY, "Corolla", "ABC123");
        coche2 = new Coche(25000.0, 2021, Marca.HON, "Civic", "XYZ789");
    }

    @Test
    public void testGetPrecio() {
        // Test the getPrecio method
        assertEquals(20000.0, coche1.getPrecio(), 0.01);
        assertEquals(25000.0, coche2.getPrecio(), 0.01);
    }

    @Test
    public void testGetAnyo() {
        // Test the getAnyo method
        assertEquals(2022, coche1.getAnyo());
        assertEquals(2021, coche2.getAnyo());
    }

    @Test
    public void testGetModelo() {
        // Test the getModelo method
        assertEquals("Corolla", coche1.getModelo());
        assertEquals("Civic", coche2.getModelo());
    }

    @Test
    public void testGetMarca() {
        // Test the getMarca method
        assertEquals(Marca.TOY, coche1.getMarca());
        assertEquals(Marca.HON, coche2.getMarca());
    }

    @Test
    public void testGetMatricula() {
        // Test the getMatricula method
        assertEquals("ABC123", coche1.getMatricula());
        assertEquals("XYZ789", coche2.getMatricula());
    }

    
    
 

   
}
