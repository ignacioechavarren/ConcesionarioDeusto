package main;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;
import gui.VentanaInicio;
import gui.VentanaLogin;
import gui.VentanaRegistro;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingUtilities;

import db.bd;



public class Main {
	
  public static ArrayList<Coche> carrito = new ArrayList<Coche>();

	public static void main(String[] args) {
		Concesionario conc=new Concesionario();
		bd bdd=new bd();
		bdd.crearBBDD();
		try {
			bdd.cargarCochesBDDConcesionario();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Coche c1 = new Coche(500, 1996, "Ibiza",Marca.SEA,"8828CUP" );
		Coche c2 = new Coche(800, 2000, "E46",Marca.BMW,"4777CHD" );
				
		Cliente cli1 = new Cliente("16102892V", "Jon", "14-10-1999", "A");
		Cliente cli2 = new Cliente("16122842V", "Alex", "04-10-2009", "B");
				
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


