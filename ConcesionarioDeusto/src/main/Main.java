package main;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;
import gui.VentanaInicio;
import gui.VentanaLogin;
import gui.VentanaRegistro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;



public class Main {
	
  public static ArrayList<Coche> carrito = new ArrayList<Coche>();

	public static void main(String[] args) {
		Concesionario conc=new Concesionario();

		Coche c1 = new Coche(500, 1996, Marca.SEA, "Ibiza","8828CUP" );
		Coche c2 = new Coche(800, 2000, Marca.BMW, "E46","4777CHD" );
		Coche c3 = new Coche(650, 1996, Marca.OPE, "Corsa", "8408KLP");
	    Coche c4 = new Coche(1300, 2005, Marca.TOY, "Corolla", "1234ABC");
	    Coche c5 = new Coche(1900, 2010, Marca.FOR, "Focus", "5678XYZ");
	    Coche c6 = new Coche(2800, 2015, Marca.HON, "Civic", "9876PQR");
	    Coche c7 = new Coche(2500, 2020, Marca.VOL, "Golf", "5432LMN");

		
		Cliente cli1 = new Cliente("16102892V", "Jon", "14-10-1999", "A");
		Cliente cli2 = new Cliente("16122842V", "Alex", "04-10-2009", "B");
		
		
		
		Concesionario.aniadirCoche(c1);
		Concesionario.aniadirCoche(c2);
		Concesionario.aniadirCoche(c3);
		Concesionario.aniadirCoche(c4);
		Concesionario.aniadirCoche(c5);
		Concesionario.aniadirCoche(c6);
		Concesionario.aniadirCoche(c7);
		Concesionario.imprimirCoches();
		
		//Concesionario.aniadirCliente(cli1);
		//Concesionario.aniadirCliente(cli2);
		Concesionario.cargarClientesEnLista("Clientes.csv");
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
		System.out.println("************************************************************************");
		
		
	
		//SwingUtilities.invokeLater(() -> new VentanaProductos(conc));

		VentanaInicio vi=new VentanaInicio(conc);
		
		
		
	}


	}


