package db;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Coche;
import domain.Concesionario;
import domain.Marca;

public class bd {
	
	protected static String DRIVER_NAME;
	protected static String DATABASE_FILE;
	protected static String CONNECTION_STRING;
	
	public bd() {		
		try {
			//Se crea el Properties y se actualizan los 3 parámetros
			Properties connectionProperties = new Properties();
			connectionProperties.load(new FileReader("resources/parametros.properties"));
			
			DRIVER_NAME = connectionProperties.getProperty("DRIVER_NAME");
			DATABASE_FILE = connectionProperties.getProperty("DATABASE_FILE");
			CONNECTION_STRING = connectionProperties.getProperty("CONNECTION_STRING") + DATABASE_FILE;
			
			//Cargar el diver SQLite
			Class.forName(DRIVER_NAME);
		} catch (Exception ex) {
			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}
		
	public void crearBBDD() {
		//Se abre la conexión y se obtiene el Statement
		//Al abrir la conexión, si no existía el fichero, se crea la base de datos
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {			
	        String sql = "CREATE TABLE IF NOT EXISTS Cliente (\n"
	                   + " ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NAME TEXT NOT NULL,\n"
	                   + " EMAIL TEXT NOT NULL,\n"
	                   + " PASSWORD TEXT NOT NULL\n"
	                   + ");";
	        
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        
	        if (!pstmt.execute()) {
	        	System.out.println("\n- Se ha creado las tablas con exito");
	        }
	        else if(pstmt.execute()) {
	        	System.out.println("Se han creado las tablas manualmente");
	        }
	        //Es necesario cerrar el PreparedStatement
	        pstmt.close();		
		} catch (Exception ex) {
			System.err.format("\n* Error al crear la BBDD: %s", ex.getMessage());
			ex.printStackTrace();			
		}
	}
	
	public void borrarBBDD() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {			
	        
			String sql = "DROP TABLE IF EXISTS CLIENTE";
			
	        PreparedStatement pstmt = con.prepareStatement(sql);
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!pstmt.execute()) {
	        	System.out.println("\n- Se ha borrado la tabla Cliente");
	        }

	        //Es necesario cerrar el PreparedStatement
	        pstmt.close();		
		} catch (Exception ex) {
			System.err.format("\n* Error al borrar la BBDD: %s", ex.getMessage());
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE));
			System.out.println("\n- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.format("\n* Error al borrar el archivo de la BBDD: %s", ex.getMessage());
			ex.printStackTrace();						
		}
	}
	
	public void insertarCoche(Coche coche) throws SQLException {
		Connection con = DriverManager.getConnection(CONNECTION_STRING);        
        String sql = "INSERT INTO coches (precio, anyo, modelo, marca, matricula) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = con.prepareStatement(sql)) {           
            statement.setDouble(1, coche.getPrecio());
            statement.setInt(2, coche.getAnyo());
            statement.setString(3, coche.getModelo());
            statement.setString(4, coche.getMarca().getMarca());
            statement.setString(5, coche.getMatricula());            
            statement.executeUpdate();
        } catch (SQLException e) {            
            if (e.getSQLState().equals("23505")) {                
                System.out.println("Error: La matrícula ya existe en la base de datos.");
            } else {                
                e.printStackTrace();
            }
        }
    }
	
	public void cargarCochesBDDConcesionario() throws SQLException {        
        Connection con = DriverManager.getConnection(CONNECTION_STRING);        
        String sql = "SELECT * FROM Coche";
        try (PreparedStatement statement = con.prepareStatement(sql)) {            
            try (ResultSet resultSet = statement.executeQuery()) {                
                while (resultSet.next()){
                    double precio = resultSet.getDouble("precio");
                    int anyo = resultSet.getInt("anyo");
                    String modelo = resultSet.getString("modelo");                    
                    String marcaStr = resultSet.getString("marca");
                    Marca marca = Marca.getPorID(marcaStr);
                    String matricula = resultSet.getString("matricula");                   
                    Coche coche = new Coche(precio, anyo, modelo, marca, matricula);
                    Concesionario.aniadirCoche(coche);
                }
            }
        }        
    }
}

