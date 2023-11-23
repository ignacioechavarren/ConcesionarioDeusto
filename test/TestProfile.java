import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.Cliente;
import domain.Concesionario;
import gui.VentanaInicio;
import gui.VentanaProfile;

public class TestProfile {
	private static VentanaProfile ventanaprofile;
	private static Concesionario concesionario;
	private static final String nomfichClientes = "Clientes.csv";
	private static Cliente cliente; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		concesionario = new Concesionario();
		cliente= new Cliente("prueba","prueba","22-22-2222","prueba");
		ventanaprofile=new VentanaProfile(cliente, concesionario);
	}
	
	@Test
	public void testBorrarClientePorID() {
		concesionario.cargarClientesEnLista(nomfichClientes);
		concesionario.borrarClientePorDNI(nomfichClientes, cliente.getDni());
		concesionario.cargarClientesEnLista(nomfichClientes);		
		assertFalse(concesionario.getClientes().contains(cliente));
	}
	
	@Test
	public void testCargarClientes() {
		concesionario.cargarClientesEnLista(nomfichClientes);
		concesionario.aniadirCliente(cliente);
		concesionario.guardarClientesEnFichero(nomfichClientes);
		concesionario.cargarClientesEnLista(nomfichClientes);		
		assertTrue(concesionario.getClientes().contains(cliente));
	}
	
	@Test
	public void testGuardarClientes() {		
		concesionario.aniadirCliente(cliente);
		concesionario.guardarClientesEnFichero(nomfichClientes);		
		assertTrue(concesionario.getClientes().contains(cliente));
	}
	
	

}
