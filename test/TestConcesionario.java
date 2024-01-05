import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.junit.BeforeClass;
import org.junit.Test;

import db.bd;
import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import gui.VentanaCarrito;
import gui.VentanaInicio;
import gui.VentanaLogin;
import gui.VentanaProfile;
import gui.VentanaRegistro;
import main.Main;

public class TestConcesionario {
	private static final String nomfichClientes = "Clientes.csv";
	private static Concesionario concesionario;
	private static Cliente cliente; 
	private static Cliente cliente2; 
	private static List<Coche> coches;
	private static VentanaRegistro ventanaregis;
	private static VentanaInicio ventanainicio;
	private static VentanaProfile ventanaprofile;
	private static VentanaLogin ventanalogin;
	private static VentanaCarrito ventanaCarrito;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bd bdd=new bd();
		bdd.cargarCochesBDDConcesionario();
		Concesionario.cargarClientesEnLista("Clientes.csv");
		Main.carrito = new ArrayList<>(Concesionario.getCoches());
		coches=Main.carrito; 
		concesionario = new Concesionario();
		cliente2=Concesionario.buscarCliente("ddd");
		cliente= new Cliente("prueba","prueba","22-22-2222","prueba");
		SwingUtilities.invokeLater(() -> {
			ventanaprofile=new VentanaProfile(cliente, concesionario);
			ventanaprofile.setVisible(false);
			});
		SwingUtilities.invokeLater(() -> {
			ventanaregis=new VentanaRegistro();
			ventanaregis.setVisible(false);
			});
		SwingUtilities.invokeLater(() -> {
			ventanainicio=new VentanaInicio(concesionario);
			ventanainicio.setVisible(false);
		});
		SwingUtilities.invokeLater(() -> {
			ventanalogin=new VentanaLogin(concesionario);
			ventanalogin.setVisible(false);
		});
		ventanaCarrito=new VentanaCarrito(concesionario, cliente2, coches);
		ventanaCarrito.setVisible(false);
		
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
	
	@Test
	public void testBorrarClientePorID() {
		concesionario.cargarClientesEnLista(nomfichClientes);
		concesionario.borrarClientePorDNI(nomfichClientes, cliente.getDni());
		concesionario.cargarClientesEnLista(nomfichClientes);		
		assertFalse(concesionario.getClientes().contains(cliente));
	}
	
	
	@Test
	public void testCarritoFactura() {		
		ventanaCarrito.facturaSave(cliente2, coches);        
        String nombreArchivo = cliente2.getDni() + "-Factura.txt";
        assertTrue("El archivo de factura debería existir", archivoExiste(nombreArchivo));
        String contenidoFactura = leerContenidoArchivo(nombreArchivo);
        assertTrue("La factura debería contener el nombre del cliente", contenidoFactura.contains("Cliente: " + cliente2.getNombre()));
        assertTrue("La factura debería contener la fecha actual", contenidoFactura.contains("Fecha de Facturación: " ));
        assertTrue("La factura debería contener detalles del pedido", contenidoFactura.contains("Detalles del Pedido:"));
        assertTrue("La factura debería contener el precio total", contenidoFactura.contains("Precio Total:"));
        eliminarArchivo(nombreArchivo);
	}
	
	private boolean archivoExiste(String nombreArchivo) {
	        return new java.io.File(nombreArchivo).exists();
	    }

	private String leerContenidoArchivo(String nombreArchivo) {
		StringBuilder contenido = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				contenido.append(linea).append("\n");
			}
			}catch (IOException e) {
	            fail("Error al leer el contenido del archivo de factura");
	        }
	        return contenido.toString();
	    }

	private void eliminarArchivo(String nombreArchivo) {
		java.io.File archivo = new java.io.File(nombreArchivo);
		if (archivo.exists()) {
			archivo.delete();
			}
	    }
}
