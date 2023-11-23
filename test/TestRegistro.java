import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.Cliente;
import domain.Concesionario;
import gui.VentanaInicio;
import gui.VentanaRegistro;

public class TestRegistro {
	private static VentanaRegistro ventanaregis;
	private static Concesionario concesionario;
	private static final String nomfichClientes = "Clientes.csv";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		concesionario = new Concesionario();
		ventanaregis=new VentanaRegistro();
	}
	
	@Test
	public void testCargarClientes() {
		concesionario.cargarClientesEnLista(nomfichClientes);
		
		assertEquals(concesionario.getClientes().get(0).getNombre(), "Jon");
	}

	@Test
	public void testGuardarClientes() {
		
		Cliente cliente = new Cliente("16102892V", "Jon", "14-10-1999", "A");
		concesionario.aniadirCliente(cliente);
		concesionario.guardarClientesEnFichero(nomfichClientes);
		
		assertEquals(concesionario.getClientes().get(0).getNombre(), "Jon");
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(ventanaregis);
		//assertEquals("CONCESIONARIO DEUSTO", ventanaregis.getTitle());
		assertTrue(ventanaregis.isResizable());
		//assertEquals(600, ventanaregis.getWidth());
		//assertEquals(400, ventanaregis.getHeight());
    }
}
