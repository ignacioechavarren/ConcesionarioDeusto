import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.Cliente;
import domain.Concesionario;

public class TestRegistro {
	
	private static Concesionario concesionario;
	private static final String nomfichClientes = "Clientes.csv";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		concesionario = new Concesionario();
	}

	@Test
	public void testGuardarClientes() {
		
		Cliente cliente = new Cliente("16102892V", "Jon", "14-10-1999", "A");
		concesionario.aniadirCliente(cliente);
		concesionario.guardarClientesEnFichero(nomfichClientes);
		
		assertEquals(concesionario.getClientes().get(0).getNombre(), "Jon");
	}

}
