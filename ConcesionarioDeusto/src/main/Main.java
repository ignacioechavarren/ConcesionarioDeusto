package main;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;

public class Main {

	public static void main(String[] args) {
		/*No vamos a instanciar (crear objetos) la clase Tienda porque sus atributos y métodos van a ser static*/
		Coche c1 = new Coche(500, 1996, "Seat", "Ibiza","8828CU" );
		Coche c2 = new Coche(800, 2000, "BMW", "E46","4777CHD" );
		
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
		
		
	}


	}


