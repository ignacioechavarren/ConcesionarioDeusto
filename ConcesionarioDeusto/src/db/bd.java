package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Coche;
import domain.Marca;

public class bd {
	
	private Connection conn;
	
	public bd() throws DBException {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DBException("No se pudo cargar el driver de la base de datos", e);
		}
	}
	
	public void open() throws DBException {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:db/Concesionario.db");
		} catch (SQLException e) {
			throw new DBException("No se pudo conectar de la base de datos astronómica", e);
		}
	}
	
	
	public void close() throws DBException {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("No se pudo desconectar correctamente de la base de datos", e);
		}
	}
	
	public List<Coche> getCoches() throws DBException {
	    List<Coche> coches = new ArrayList<>();

	    try (Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery("SELECT precio, anyo, modelo, marca, matrícula FROM coches")) {
	        while (rs.next()) {
	            Coche coche = new Coche(
	            		rs.getDouble("precio"),
	                    rs.getInt("anio"),
	                    rs.getString("modelo"),
	                    (Marca) rs.getObject("marca"),
	                    rs.getString("matrícula")
	            );

	            coches.add(coche);
	        }
	        return coches;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DBException("No se pudo obtener el listado de coches", e);
	    }
	}
	
	public void actualizarCoche(Coche coche) throws DBException {
	    String sql = "UPDATE coches SET precio = ?, anyo = ?, modelo = ?, marca = ?, matricula = ? WHERE matricula = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setDouble(1, coche.getPrecio());
	        pstmt.setInt(2, coche.getAnyo());
	        pstmt.setString(3, coche.getModelo());
	        pstmt.setObject(4, coche.getMarca());
	        pstmt.setString(5, coche.getMatricula());
	        pstmt.setString(6, coche.getMatricula());

	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DBException("No se pudo actualizar la información del coche en la base de datos", e);
	    }
	}
	
	public void eliminarCochePorMatricula(String matricula) throws DBException {
	    String sql = "DELETE FROM coches WHERE matricula = ?";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, matricula);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DBException("No se pudo eliminar el coche de la base de datos", e);
	    }
	}
	
	public List<Coche> buscarCochesAvanzado(String marca, String modelo, Integer anyo, Double precioMin, Double precioMax) throws DBException {
	    List<Coche> coches = new ArrayList<>();
	    
	    StringBuilder sql = new StringBuilder("SELECT precio, anio, modelo, marca, matricula FROM coches WHERE 1 = 1");

	    if (marca != null && !marca.isEmpty()) {
	        sql.append(" AND marca = ?");
	    }

	    if (modelo != null && !modelo.isEmpty()) {
	        sql.append(" AND modelo = ?");
	    }

	    if (anyo != null) {
	        sql.append(" AND anio = ?");
	    }

	    if (precioMin != null) {
	        sql.append(" AND precio >= ?");
	    }

	    if (precioMax != null) {
	        sql.append(" AND precio <= ?");
	    }

	    try (PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
	        int parameterIndex = 1;

	        if (marca != null && !marca.isEmpty()) {
	            pstmt.setString(parameterIndex++, marca);
	        }

	        if (modelo != null && !modelo.isEmpty()) {
	            pstmt.setString(parameterIndex++, modelo);
	        }

	        if (anyo != null) {
	            pstmt.setInt(parameterIndex++, anyo);
	        }

	        if (precioMin != null) {
	            pstmt.setDouble(parameterIndex++, precioMin);
	        }

	        if (precioMax != null) {
	            pstmt.setDouble(parameterIndex++, precioMax);
	        }

	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                double precio = rs.getDouble("precio");
	                int anyoResultado = rs.getInt("anio");
	                String modeloResultado = rs.getString("modelo");
	                Marca marcaResultado = (Marca) rs.getObject("marca");
	                String matriculaResultado = rs.getString("matricula");

	                Coche coche = new Coche(precio, anyoResultado, modeloResultado, marcaResultado, matriculaResultado);
	                coches.add(coche);
	            }
	            return coches;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new DBException("Error en la búsqueda avanzada de coches", e);
	    }
	}

}

