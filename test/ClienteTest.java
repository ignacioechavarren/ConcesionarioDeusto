

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import domain.Cliente;

public class ClienteTest {
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	
	private Cliente cliente;
	private String dni = "dni";
	private String nombre = "nombre";
	private Date fNac;
	private String contrasenia = "contrasenia";
	

	@Before
	public void setUp() throws Exception{
		cliente = new Cliente();
	}
	
	@After
	public void tearDown() throws Exception {
		cliente = null;
	}
	
	@Test
	public void testUsuario() {
		assertNotNull(cliente);
		assertEquals(dni, cliente.getDni());
		assertEquals(nombre, cliente.getNombre());
		assertEquals(fNac, cliente.getfNacStr());
		assertEquals(contrasenia, cliente.getContrasenia());
	}
	
	
	
	
	@Test
	public void testGetDni() {
		assertEquals(dni, Cliente.getDni());
	}
	
	@Test
	public void testSetDni() {
		String newDni = " New dni";
		cliente.setDni(newDni);
		
		assertEquals(newDni, cliente.getDni());
	}
	@Test
	public void testGetNombre() {
		assertEquals(nombre, cliente.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		String newNombre = " New nombre";
		cliente.setNombre(newNombre);
		
		assertEquals(newNombre, cliente.getNombre());
	}
	
	@Test
	public void testGetfNac() {
		assertEquals(fNac, cliente.getfNac());
	}
	
	@Test
	public void testSetfNac() {
		try {
			this.fNac = sdf.parse(fNac);
		} catch (ParseException e) {
			this.fNac = new Date(0);
		}
		cliente.setfNac(fNac);
		
		assertEquals(fNac, cliente.getfNacStr());
	}
	@Test
	public void testGetContrasena() {
		assertEquals(contrasenia, cliente.getContrasenia());
	}
	
	@Test
	public void testSetContrasena() {
		String newContrasena = " New contrasena";
		cliente.setContrasenia(newContrasena);
		
		assertEquals(newContrasena, cliente.getContrasenia());
	}
	
	
}


