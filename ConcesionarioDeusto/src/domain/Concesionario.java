package domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
	 * Atributos
	 */
	private static List<Coche> coches = new ArrayList<>();
	private static List<Cliente> clientes = new ArrayList<>();
	private static Map<Cliente, List<Coche>> reservas = new TreeMap<>();//new HashMap<>();
	/**
	 * Método para acceder a la contraseña de cada cliente de la lista
	 * @param Recibe String dni de cada cliente de la lista
	 * @return Devuelve String contrasenia del cliente
	 */
	public static String getPassword(String dni) {
		String result = null;
		for (Cliente c : clientes) {
			if(c.getDni().equals(dni)) {
				result=c.getContrasenia();
			}
		}
		return result;
	}
	/**
	 * Getters y setters de las colecciones
	 */
	public static List<Cliente> getClientes(){
		return clientes;
	}
	public static List<Coche> getCoches(){
		return coches;
	}
	public static Map<Cliente, List<Coche>> getReservas(){
		return reservas;
	}
	
	/**
	 * Metodo para añadir un coche al TreeSet de coches
	 * @param coche co
	 */
	public static void aniadirCoche(Coche co) {
		coches.add(co);
	}
	/**
	 * Método para añadir cliente a la lista clientes
	 * @param cliente
	 */
	public static void aniadirCliente(Cliente c) {
		clientes.add(c);
	}
	/**
	 * Metodo que muestra por consola los clientes del ArrayList clientes
	 */
	public static void imprimirClientes() {
		for(Cliente c: clientes) {
			System.out.println(c);
		}
	}
	/**
	 * Método que muestra por consola los coches del TreeSet coches
	 */
	public static void imprimirCoches() {
		for(Coche co: coches) {
			System.out.println(co);
		}
	}
	/**
	 * Metodo que ordena la lista clientes por sus dni
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
	 * Método para añadir una reserva de un cliente y un coche
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
	 * Método que imprime por consola las reservas del cliente recibido por parámetro
	 * @param c Cliente del que se van a imprimir las reservas
	 */
	public static void imprimirReservasCliente(Cliente c) {
		List<Coche> l = reservas.get(c);
		for(Coche co: l) {
			System.out.println(co);
		}
	}
	/**
	 * Metodo que carga los clientes guardados en fichero
	 * @param String nomfich , el nombre del fichero fuente
	 */
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
	

    public static void borrarClientePorDNI(String nomfich,String dni) {        
        clientes.removeIf(cliente -> cliente.getDni().equals(dni));        
        guardarClientesEnFichero(nomfich);
    }
	/**
	 * Método para buscar un cliente en lista clientes dado un String dni
	 * @param String dni
	 * @return Cliente c si existe, null si no existe en la lista clientes
	 */
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
	/**
	 * Método para guardar clientes en fichero
	 * @param nomfich
	 */
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
