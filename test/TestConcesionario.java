import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.SwingUtilities;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import db.bd;
import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;
import domain.Pedido;
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
	private static bd bdd;
	private static VentanaRegistro ventanaregis;
	private static VentanaInicio ventanainicio;
	private static VentanaProfile ventanaprofile;
	private static VentanaLogin ventanalogin;
	private static VentanaCarrito ventanaCarrito;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bdd=new bd();
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
	
	 @After
	    public void tearDown() throws Exception {
	        
	    }
	 
	@Test
	   public void testCrearBBDD() {
	       bdd.crearBBDD();
	       assertTrue(tableExists("Cliente"));
	    }
	
	 

	    @Test
	    public void testInsertarCoche() throws SQLException {
	        Coche coche = new Coche(20000.0, 2022, "Civic", Marca.getPorID("Honda"), "123ABC");
	        Concesionario.aniadirCoche(coche);
	        for (Coche c : Concesionario.getCoches()) {
				bdd.insertarCoche(c);
			}
	        bdd.insertarCoche(coche);
	        bdd.cargarCochesBDDConcesionario();	        
	        assertTrue(Concesionario.getCoches().contains(coche));
	    }

	    @Test
	    public void testCargarCochesBDDConcesionario() throws SQLException {	        
	        bdd.cargarCochesBDDConcesionario();
	        assertFalse(Concesionario.getCoches().isEmpty());
	    }

	    @Test
	    public void testInsertarPedidoConDetalles() throws SQLException {
	    Cliente cliente = new Cliente("testDNI", "TestNombre", new Date(), "testPassword");
	     Coche coche = new Coche(20000.0, 2022, "Civic", Marca.getPorID("Honda"), "123ABC");
	        Pedido pedido = new Pedido(cliente, List.of(coche), LocalDateTime.now(), 20000.0);
	        bdd.insertarPedidoConDetalles(pedido);
	        List<Pedido>pedidos=bdd.obtenerTodosLosPedidosConCoches();
	         boolean res=false;
	        for (Pedido p : pedidos) {
	        	if(p.getCliente().getDni().equals("testDNI")&&p.getPrecioTotal()==20000.0){
					int tam=p.getCoche().size();
					for (Coche c : p.getCoche()) {
						if(c.getMatricula().equals("123ABC")){
							tam--;
							}if(tam==0){
							res=true;
								}
						}
					}
				}
	        assertEquals(true, res);
	    }

	    @Test
	    public void testInsertarCliente() throws SQLException {
	        Cliente cliente = new Cliente("testDNI", "TestNombre", new Date(), "testPassword");	        
	        bdd.insertarCliente(cliente);
	        boolean res=false;
	        for (Cliente c : bdd.obtenerClientes()) {
				if(c.getDni().equals("testDNI")){
					res=true;
				}
			}

	        assertEquals(true,res);
	    }

	    
	   
	    private boolean tableExists(String tableName) {
	        try (Connection connection = DriverManager.getConnection(bd.CONNECTION_STRING);
	             ResultSet tables = connection.getMetaData().getTables(null, null, tableName, null)) {
	            return tables.next();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
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
