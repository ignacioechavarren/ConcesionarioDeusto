import static org.junit.Assert.*;

import org.junit.Test;

import domain.Cliente;
import domain.Concesionario;
import gui.VentanaInicio;
import gui.VentanaLogin;

public class TestLogin {
	private static VentanaLogin ventanalogin;
	private static Concesionario concesionario;
	private static final String nomfichClientes = "Clientes.csv";
	
	public static void setUpBeforeClass() throws Exception {
		concesionario = new Concesionario();
		ventanalogin=new VentanaLogin(concesionario);
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
		assertNotNull(ventanalogin);
		//assertEquals("CONCESIONARIO DEUSTO", ventanalogin.getTitle());
		assertTrue(ventanalogin.isResizable());
		//assertEquals(600, ventanalogin.getWidth());
		//assertEquals(400, ventanalogin.getHeight());
    }
}
