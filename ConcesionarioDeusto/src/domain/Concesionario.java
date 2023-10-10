package domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Concesionario {
	
	/**
	 * 
	 */
	private static Set<Coche> coches = new TreeSet<>();
	private static List<Cliente> clientes = new ArrayList<>();
	private static Map<Cliente, List<Coche>> reservas = new TreeMap<>();//new HashMap<>();
	
	
	/**
	 * 
	 * @param metodo añadircoche
	 */
	public static void aniadirCoche(Coche co) {
		coches.add(co);
	}
	
	
	/**
	 * 
	 */
	public static void imprimirCoches() {
		for(Coche co: coches) {
			System.out.println(co);
		}
	}
	/**
	 * 
	 * @param meotodo añadir cliente
	 */
	public static void aniadirCliente(Cliente c) {
		clientes.add(c);
	}
	/**
	 * 
	 */
	public static void imprimirClientes() {
		for(Cliente c: clientes) {
			System.out.println(c);
		}
	}
	/**
	 * 
	 */
	public static void ordenarListaClientes() {
		Comparator<Cliente> c = new Comparator<Cliente>() {
			@Override
			public int compare(Cliente o1, Cliente o2) {
				return o1.getDni().compareTo(o2.getDni());
			}
		}; 
		
		Collections.sort(clientes, c);
	}
	
	/**
	 * 
	 * @param c
	 * @param co
	 */
	public static void aniadirReserva(Cliente c, Coche co) {
	/*	if(!reservas.containsKey(c)) { //Si el cliente no está en el mapa
			reservas.put(c, new ArrayList<>()); //Guardamos el cliente y le creamos la lista vacía
			reservas.get(c).add(a); //Accedemos a la lista del cliente c y le añadimos el coche a
		}else {
			reservas.get(c).add(a); //Accedemos a la lista del cliente c y le añadimos el coche a
		}
	*/
		
		if(!reservas.containsKey(c)) { //Si el cliente no está en el mapa
			reservas.put(c, new ArrayList<>()); //Guardamos el cliente y le creamos la lista vacía
		}
		reservas.get(c).add(co); //Accedemos a la lista del cliente c y le añadimos el artículo a
	}
	
	//Método que imprime por consola las compras de todos los clientes
	/**
	 * Método que imprime por consola las reservas de todos los clientes
	 */
	public static void imprimirReservas() {
		//Recorremos las claves del mapa
		for(Cliente c: reservas.keySet()) {
			System.out.println(c);
			//Por cada cliente, obtenemos su lista de Articulos comprados
			List<Coche> l = reservas.get(c);
			//Recorremos los artículos comprados
			for(Coche co: l) {
				System.out.println(co);
			}
			System.out.println("************************************************************************");
		}
	}
	
	/**
	 * 
	 * @param c Cliente del que se van a imprimir las reservas
	 * Método que imprime por consola las reservas del cliente recibido por parámetro
	 */
	public static void imprimirReservasCliente(Cliente c) {
		List<Coche> l = reservas.get(c);
		for(Coche co: l) {
			System.out.println(co);
		}
	}
	
	public static void cargarClientesEnLista(String nomfich) {
		//linea = dni;nom;fNac;con
		try {
			Scanner sc = new Scanner(new FileReader(nomfich));
			String linea;
			while(sc.hasNext()) {
				linea = sc.nextLine();
				String [] partes = linea.split(";");
				String dni = partes[0];
				String nom = partes[1];
				String fNac = partes[2];
				String con = partes[3];
				Cliente c = new Cliente(dni, nom, fNac, con);
				if(buscarCliente(dni)==null)
					clientes.add(c);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
		}
		
	}
	
	public static Cliente buscarCliente(String dni) {
		boolean enc = false;
		int pos = 0;
		Cliente c = null;
		while(!enc && pos<clientes.size()) {
			c = clientes.get(pos);
			if(c.getDni().equals(dni)) {
				enc = true;
			}else {
				pos++;
			}
		}
		if(enc) {
			return c;
		}else{
			return null;
		}
	}
	
	public static void guardarClientesEnFichero(String nomfich) {
		try {
			PrintWriter pw = new PrintWriter(nomfich);
			for(Cliente c : clientes) {
				pw.println(c.getDni()+";"+c.getNombre()+";"+c.getfNacStr()+";"+c.getContrasenia());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
