
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import db.bd;
import domain.Cliente;
import domain.Coche;
import domain.Marca;
import domain.Concesionario;
import domain.Pedido;
import gui.VentanaCarrito;
import gui.VentanaLogin;
import main.Main;

public class VentanaCarritoTest {
	 private Cliente cliente;
	 private List<Coche> coches;

	   
	
	@Test
	public void testGuardarFactura() throws IOException, SQLException {
		  // Crear una instancia de VentanaCarrito para acceder al método facturaSave
		
		bd bdd=new bd();		
		bdd.cargarCochesBDDConcesionario();
		Concesionario.cargarClientesEnLista("Clientes.csv");
		Main.carrito = new ArrayList<>(Concesionario.getCoches());
		cliente=Concesionario.buscarCliente("ddd");
		coches=Main.carrito;
        VentanaCarrito ventanaCarrito = new VentanaCarrito(new Concesionario(), cliente, coches);
        ventanaCarrito.setVisible(false);
        

        // Llamar al método facturaSave
        ventanaCarrito.facturaSave(cliente, coches);


        // Verificar que se haya creado el archivo de factura
        String nombreArchivo = cliente.getDni() + "-Factura.txt";
        assertTrue("El archivo de factura debería existir", archivoExiste(nombreArchivo));

        // Leer el contenido del archivo de factura
        String contenidoFactura = leerContenidoArchivo(nombreArchivo);

        // Verificar algunos detalles del contenido de la factura
        assertTrue("La factura debería contener el nombre del cliente", contenidoFactura.contains("Cliente: " + cliente.getNombre()));
        assertTrue("La factura debería contener la fecha actual", contenidoFactura.contains("Fecha de Facturación: " ));
        assertTrue("La factura debería contener detalles del pedido", contenidoFactura.contains("Detalles del Pedido:"));
        assertTrue("La factura debería contener el precio total", contenidoFactura.contains("Precio Total:"));

        // Eliminar el archivo de factura después de la prueba
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
        } catch (IOException e) {
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


