package db;

import java.util.List;

import domain.*;
public class TestDB {

	public static void main(String[] args) {
		bd gestorBD = new bd();		
		gestorBD.crearBBDD();
		gestorBD.borrarBBDD();
	}
}