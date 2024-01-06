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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;
import domain.Pedido;

public class bd {
	
	public static String DRIVER_NAME;
	public static String DATABASE_FILE;
	public static String CONNECTION_STRING;
	
	public bd() {		
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.load(new FileReader("resources/parametros.properties"));
			
			DRIVER_NAME = connectionProperties.getProperty("DRIVER_NAME");
			DATABASE_FILE = connectionProperties.getProperty("DATABASE_FILE");
			CONNECTION_STRING = connectionProperties.getProperty("CONNECTION_STRING") + DATABASE_FILE;
						
			Class.forName(DRIVER_NAME);
		} catch (Exception ex) {
			System.err.format("\n* Error al cargar el driver de BBDD: %s", ex.getMessage());
			ex.printStackTrace();
		}
	}
		
	public void crearBBDD() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING)) {			
	        String sql = "CREATE TABLE IF NOT EXISTS Cliente (\n"
	                   + " DNI TEXT PRIMARY KEY AUTOINCREMENT,\n"
	                   + " NOMBRE TEXT NOT NULL,\n"
	                   + " FNAC TEXT NOT NULL,\n"
	                   + " PASSWORD TEXT NOT NULL\n"
	                   + ");";
	        
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        
	        if (!pstmt.execute()) {
	        	System.out.println("\n- Se ha creado las tablas con exito");
	        }
	        else if(pstmt.execute()) {
	        	System.out.println("Se han creado las tablas manualmente");
	        }
	        pstmt.close();		
		} catch (Exception ex) {
			System.err.format("\n* Error al crear la BBDD: %s", ex.getMessage());
			ex.printStackTrace();			
		}
	}
	
	public void insertarCliente(Cliente cliente) throws SQLException {
	    Connection conn = DriverManager.getConnection(CONNECTION_STRING);
	    String sql = "INSERT OR IGNORE INTO Cliente (DNI, NOMBRE, FNAC, PASSWORD) VALUES (?, ?, ?, ?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, cliente.getDni());
	        pstmt.setString(2, cliente.getNombre());
	        pstmt.setString(3, cliente.getfNacStr());
	        pstmt.setString(4, cliente.getContrasenia());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void insertarCoche(Coche coche) throws SQLException {
	    Connection con = DriverManager.getConnection(CONNECTION_STRING);
	    String sql = "INSERT OR IGNORE INTO Coche (precio, anyo, modelo, marca, matricula) VALUES (?, ?, ?, ?, ?)";

	    try (PreparedStatement statement = con.prepareStatement(sql)) {
	        statement.setDouble(1, coche.getPrecio());
	        statement.setInt(2, coche.getAnyo());
	        statement.setString(3, coche.getModelo());
	        statement.setString(4, coche.getMarca().getMarca());
	        statement.setString(5, coche.getMatricula());
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
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
	
	public void insertarPedidoConDetalles(Pedido pedido) throws SQLException {
	    Connection con = DriverManager.getConnection(CONNECTION_STRING);
	    String sqlPedido = "INSERT OR IGNORE INTO Pedido (dni, fecha_compra, precio_total) VALUES (?, ?, ?)";
	    String sqlDetallePedido = "INSERT OR IGNORE INTO DetallePedido (id_pedido, matricula) VALUES (?, ?)";

	    try (PreparedStatement statementPedido = con.prepareStatement(sqlPedido, PreparedStatement.RETURN_GENERATED_KEYS);
	         PreparedStatement statementDetallePedido = con.prepareStatement(sqlDetallePedido)) {
	        con.setAutoCommit(false);

	        statementPedido.setString(1, pedido.getCliente().getDni());
	        statementPedido.setString(2, pedido.getFechaCompra().toString());
	        statementPedido.setDouble(3, pedido.getPrecioTotal());
	        statementPedido.executeUpdate();

	        try (ResultSet generatedKeys = statementPedido.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int idPedidoGenerado = generatedKeys.getInt(1);

	                for (Coche coche : pedido.getCoche()) {
	                    statementDetallePedido.setInt(1, idPedidoGenerado);
	                    statementDetallePedido.setString(2, coche.getMatricula());
	                    statementDetallePedido.executeUpdate();
	                }
	                con.commit();
	            } else {
	                con.rollback();
	                throw new SQLException("Error al obtener el ID del pedido generado.");
	            }
	        }
	    } catch (SQLException e) {
	        con.rollback();
	        throw e;
	    } finally {
	        con.setAutoCommit(true);
	    }
	    System.out.println("Inserción de pedido exitosa");
	}
	
	public List<Pedido> obtenerTodosLosPedidosConCoches() throws SQLException {
        List<Pedido> listaPedidos = new ArrayList<>();
        Connection con = DriverManager.getConnection(CONNECTION_STRING);        
        String sqlPedidos = "SELECT * FROM Pedido";

        try (PreparedStatement statementPedidos = con.prepareStatement(sqlPedidos)) {
            try (ResultSet resultSetPedidos = statementPedidos.executeQuery()) {                
                while (resultSetPedidos.next()) {
                    int idPedido = resultSetPedidos.getInt("id_pedido");
                    String dniCliente = resultSetPedidos.getString("dni");
                    LocalDateTime fechaCompra = LocalDateTime.parse(resultSetPedidos.getString("fecha_compra"));
                    double precioTotal = resultSetPedidos.getDouble("precio_total");                    
                    List<Coche> coches = obtenerCochesPorPedido(idPedido); 
                    List<Cliente> clientes=obtenerClientes();  
                    Cliente cliente=null;
                    for (Cliente c : clientes) {
                    	if(c.getDni().equals(dniCliente)){
                    		cliente=c;
                    	}						
					}  
                    Pedido pedido = new Pedido(cliente, coches, fechaCompra, precioTotal);
                    listaPedidos.add(pedido);
                }
            }
        }

        return listaPedidos;
    }    
    private List<Coche> obtenerCochesPorPedido(int idPedido) throws SQLException {
        List<Coche> coches = new ArrayList<>();
        Connection con = DriverManager.getConnection(CONNECTION_STRING);        
        String sqlCoches = "SELECT Coche.* FROM Coche " +
                           "JOIN DetallePedido ON Coche.matricula = DetallePedido.matricula " +
                           "WHERE DetallePedido.id_pedido = ?";

        try (PreparedStatement statementCoches = con.prepareStatement(sqlCoches)) {
            statementCoches.setInt(1, idPedido);
            try (ResultSet resultSetCoches = statementCoches.executeQuery()) {                
                while (resultSetCoches.next()) {
                    double precio = resultSetCoches.getDouble("precio");
                    int anyo = resultSetCoches.getInt("anyo");
                    String modelo = resultSetCoches.getString("modelo");                    
                    String marcaStr = resultSetCoches.getString("marca");
                    Marca marca = Marca.getPorID(marcaStr);
                    String matricula = resultSetCoches.getString("matricula");                    
                    Coche coche = new Coche(precio, anyo, modelo, marca, matricula);
                    coches.add(coche);
                }
            }
        }

        return coches;
    }    
   

    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT DNI, NOMBRE, FNAC, PASSWORD FROM Cliente";

        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

            while (rs.next()) {
                String dni = rs.getString("DNI");
                String nombre = rs.getString("NOMBRE");
                String fNacStr = rs.getString("FNAC");
                Date fechaNacimiento = null;
                try {
                    fechaNacimiento = formatoFecha.parse(fNacStr);
                } catch (ParseException e) {                   
                    fechaNacimiento = new Date();
                    e.printStackTrace();
                }

                String contrasenia = rs.getString("PASSWORD");
                Cliente cliente = new Cliente(dni, nombre, fechaNacimiento, contrasenia);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }


    public void borrarCliente(String dniCliente) {
        String sql = "DELETE FROM Cliente WHERE DNI = ?";
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dniCliente);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente con DNI " + dniCliente + " eliminado exitosamente.");
            } else {
                System.out.println("No se encontró ningún cliente con DNI " + dniCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();            
        }
    }
}

