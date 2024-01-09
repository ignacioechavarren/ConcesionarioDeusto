package main;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;
import gui.VentanaCombinaciones;
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
			e.printStackTrace();
		}

		VentanaInicio vi=new VentanaInicio(conc);			
		
	}
}


