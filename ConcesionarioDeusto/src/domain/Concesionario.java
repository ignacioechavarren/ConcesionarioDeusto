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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Concesionario {
	
	private static List<Coche> coches = new ArrayList<>();
	private static List<Cliente> clientes = new ArrayList<>();
	private static Map<Cliente, List<Coche>> reservas = new TreeMap<>();//new HashMap<>();

	public static String getPassword(String dni) {
		String result = null;
		for (Cliente c : clientes) {
			if(c.getDni().equals(dni)) {
				result=c.getContrasenia();
			}
		}
		return result;
	}

	public static List<Cliente> getClientes(){
		return clientes;
	}
	public static List<Coche> getCoches(){
		return coches;
	}
	public static Map<Cliente, List<Coche>> getReservas(){
		return reservas;
	}
	
	public static void aniadirCoche(Coche co) {
		coches.add(co);
	}

	public static void aniadirCliente(Cliente c) {
		clientes.add(c);
	}

	public static void imprimirClientes() {
		for(Cliente c: clientes) {
			System.out.println(c);
		}
	}

	public static void imprimirCoches() {
		for(Coche co: coches) {
			System.out.println(co);
		}
	}

	public static void ordenarListaClientes() {
		Comparator<Cliente> c = new Comparator<Cliente>() {
			@Override
			public int compare(Cliente o1, Cliente o2) {
				return o1.getDni().compareTo(o2.getDni());
			}
		}; 
		
		Collections.sort(clientes, c);
	}
	
	public static void aniadirReserva(Cliente c, Coche co) {
		if(!reservas.containsKey(c)) { 
			reservas.put(c, new ArrayList<>()); 
		}
		reservas.get(c).add(co); 
	}
	
	public static void imprimirReservas() {
		for(Cliente c: reservas.keySet()) {
			System.out.println(c);
			List<Coche> l = reservas.get(c);
			for(Coche co: l) {
				System.out.println(co);
			}
			System.out.println("************************************************************************");
		}
	}

	public static void imprimirReservasCliente(Cliente c) {
		List<Coche> l = reservas.get(c);
		for(Coche co: l) {
			System.out.println(co);
		}
	}

	public static void cargarClientesEnLista(String nomfich) {
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
	
	public static void ordenarCoches(List<Coche> coches) {
        ordenarCochesRecursivo(coches, coches.size());
    }
	
    private static void ordenarCochesRecursivo(List<Coche> coches, int n) {       
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            if (coches.get(i).compareTo(coches.get(i + 1)) > 0) {
                Coche temp = coches.get(i);
                coches.set(i, coches.get(i + 1));
                coches.set(i + 1, temp);
            }
        }
        ordenarCochesRecursivo(coches, n - 1);
    }
	
    
    public static Map<Integer, List<Coche>> generarCombinacionesDeCompra(double presupuesto, List<Coche> coches) {
        Map<Integer, List<Coche>> combinaciones = new HashMap<>();
        generarCombinacionesRecursivo(presupuesto, coches, 0, new ArrayList<>(), combinaciones);
        return combinaciones;
    }

    private static void generarCombinacionesRecursivo(double presupuesto, List<Coche> coches, int indice,
                                                      List<Coche> combinacion, Map<Integer, List<Coche>> combinaciones) {
        if (indice == coches.size()) {
            double precioTotal = calcularPrecioTotal(combinacion);
            if (precioTotal <= presupuesto) {
                combinaciones.put(combinaciones.size() + 1, new ArrayList<>(combinacion));
            }
            return;
        }
        combinacion.add(coches.get(indice));
        generarCombinacionesRecursivo(presupuesto, coches, indice + 1, combinacion, combinaciones);
        combinacion.remove(combinacion.size() - 1);
        generarCombinacionesRecursivo(presupuesto, coches, indice + 1, combinacion, combinaciones);
    }
    
    private static double calcularPrecioTotal(List<Coche> coches) {
        double precioTotal = 0;
        for (Coche coche : coches) {
            precioTotal += coche.getPrecio();
        }
        return precioTotal;
    }

    public static void borrarClientePorDNI(String nomfich,String dni) {        
        clientes.removeIf(cliente -> cliente.getDni().equals(dni));        
        guardarClientesEnFichero(nomfich);
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
