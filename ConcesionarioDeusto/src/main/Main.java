package main;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import gui.VentanaInicio;
import gui.VentanaLogin;
import gui.VentanaProductos;
import gui.VentanaRegistro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) {
		Concesionario conc=new Concesionario();
		Coche c1 = new Coche(500, 1996, "Seat", "Ibiza","8828CUP" );
		Coche c2 = new Coche(800, 2000, "BMW", "E46","4777CHD" );
		Coche c3 = new Coche(650, 1996, "Opel", "Corsa", "8408KLP");
	    Coche c4 = new Coche(1300, 2005, "Toyota", "Corolla", "1234ABC");
	    Coche c5 = new Coche(1900, 2010, "Ford", "Focus", "5678XYZ");
	    Coche c6 = new Coche(2800, 2015, "Honda", "Civic", "9876PQR");
	    Coche c7 = new Coche(2500, 2020, "Volkswagen", "Golf", "5432LMN");
		
	    
		Cliente cli1 = new Cliente("16102892V", "Jon", "14-10-1999", "A");
		Cliente cli2 = new Cliente("16122842V", "Alex", "04-10-2009", "B");
		
		
		
		Concesionario.aniadirCoche(c1);
		Concesionario.aniadirCoche(c2);
		Concesionario.imprimirCoches();
		
		Concesionario.aniadirCliente(cli1);
		Concesionario.aniadirCliente(cli2);
		System.out.println("Lista de clientes antes de ordenarla");
		Concesionario.imprimirClientes();
		Concesionario.ordenarListaClientes();
		System.out.println("Lista de clientes después de ordenarla");
		Concesionario.imprimirClientes();
		
		Concesionario.aniadirReserva(cli1, c1);
		Concesionario.aniadirReserva(cli2, c2);
		Concesionario.aniadirReserva(cli1, c2);
		System.out.println("TODAS LAS RESERVAS");
		Concesionario.imprimirReservas();
		System.out.println("Reservas del cliente: "+cli1.getDni());
		Concesionario.imprimirReservasCliente(cli1);
		
		List<Coche> coches = new ArrayList<>(Arrays.asList(c1, c2, c3, c4, c5, c6, c7)); 
	
		//SwingUtilities.invokeLater(() -> new VentanaProductos(coches));

		VentanaInicio vi=new VentanaInicio(conc);
		
		
		
	}


	}


