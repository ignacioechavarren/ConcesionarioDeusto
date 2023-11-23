import static org.junit.Assert.*;

import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.Cliente;
import domain.Concesionario;
import gui.VentanaInicio;

public class TestInicio {

	private static VentanaInicio ventanainicio;
	private static Concesionario concesionario;
	private static final String nomfichClientes = "Clientes.csv";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		concesionario = new Concesionario();
		ventanainicio=new VentanaInicio(concesionario);
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
        
		assertNotNull(ventanainicio);
		
        //assertEquals("CONCESIONARIO DEUSTO", ventanainicio.getTitle());
        assertTrue(ventanainicio.isResizable());
        //assertEquals(600, ventanainicio.getWidth());
        //assertEquals(400, ventanainicio.getHeight());
    }

}
