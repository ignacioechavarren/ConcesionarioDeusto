package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import db.bd;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Pedido;
import main.Main;

public class VentanaCarrito extends JFrame {
	private JFrame frame=new JFrame("CONCESIONARIO DEUSTO");
    private List<Cliente> clientes;
    private JPanel contentPane;
    private JPanel panelNorte;
    private JPanel panelCentro;
    private JPanel panelSur, panelInferior, panelFactura;
    private JButton btnRealizarReserva, btnVolver, btnCrearFactura, btnEliminarPedidos;
    private JLabel lblCarrito, lblTotal;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private Coche selectedCoche;
    private JTextField txtTotal;
    private double totalFactura;
    private static final int ANCHO_MAXIMO = 100;

    public VentanaCarrito(Concesionario conc, Cliente cliente, List<Coche> reservas) throws IOException {
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    	frame.setResizable(false);

        contentPane = new JPanel(new BorderLayout());
        panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNorte.setBackground(new Color(40, 40, 40));
        panelNorte.setBorder(new EmptyBorder(20, 0, 20, 0));
        panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(40, 40, 40));
        modeloTabla = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {                    
                    return ImageIcon.class;
                } else {                    
                    return String.class;
                }
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabla.addColumn("Imagen");
        modeloTabla.addColumn("Modelo del Coche");
        modeloTabla.addColumn("Precio (€)");
        
        for (Coche coche : Main.carrito) {
        	Image imagenOriginal = ImageIO.read(new File ("imagenes/"+coche.getModelo()+".png"));
        	Image imagenEscalada = imagenOriginal.getScaledInstance(ANCHO_MAXIMO, -1, Image.SCALE_SMOOTH);
            modeloTabla.addRow(new Object[]{new ImageIcon(imagenEscalada), coche.getModelo(), coche.getPrecio()});
        }
        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setRowHeight(100);
        tablaProductos.getColumnModel().getColumn(0).setPreferredWidth(100);
        panelCentro.add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(panelCentro);
        splitPane.setBottomComponent(crearPanelInferior(conc, cliente, reservas));
        splitPane.setDividerLocation(600);        

        panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSur.setBackground(new Color(40, 40, 40));

        lblCarrito = new JLabel("TU CARRITO");
        lblCarrito.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCarrito.setForeground(new Color(255, 255, 255));
        lblCarrito.setBackground(new Color(40,40,40));
        panelNorte.add(lblCarrito);

        btnVolver = new JButton("VOLVER");
        btnEliminarPedidos = new JButton("ELIMINAR PRODUCTO");
        btnRealizarReserva = new JButton("REALIZAR RESERVA");
        btnCrearFactura = new JButton("CREAR FACTURA");

        JButton[] buttons = { btnVolver, btnEliminarPedidos, btnRealizarReserva, btnCrearFactura };
        for (JButton button : buttons) {
            button.setFont(new Font("Tahoma", Font.BOLD, 15));
            panelSur.add(button);
        }

        btnEliminarPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar este producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    panelFactura.removeAll();
                    Main.carrito.remove(selectedCoche);
                    modeloTabla.removeRow(tablaProductos.getSelectedRow());
                    totalFactura = calcularTotal();
                    txtTotal = new JTextField(String.valueOf(totalFactura));
                    lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
                    panelFactura.add(lblTotal);
                    txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
                    panelFactura.add(txtTotal);
                    panelFactura.revalidate();
                    panelFactura.repaint();
                    panelFactura.setBackground(Color.GRAY);
                }
            }
        });

        btnVolver.addActionListener(e -> {
            VentanaConcesionario ventanaConcesionario = new VentanaConcesionario(conc, cliente);
            frame.dispose();
        });

        btnRealizarReserva.addActionListener((e) -> {
            bd bdtemporal= new bd();
            bdtemporal.crearBBDD();
            double precio= 0;
            for (Coche c : Main.carrito) {
				precio=+c.getPrecio();
			}
            LocalDateTime ldt=LocalDateTime.now();
            Pedido pactual=new Pedido(cliente, Main.carrito, ldt, precio);
            try {
				bdtemporal.insertarPedidoConDetalles(pactual);
				JOptionPane.showMessageDialog(null, "Enhorabuena, ha realizado la"+
				" reserva correctamente en la base de datos",
	            "RESERVA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
            
        });

        btnCrearFactura.addActionListener(e -> {
        	facturaSave(cliente, Main.carrito);
        });

        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = tablaProductos.rowAtPoint(evt.getPoint());
                int col = tablaProductos.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {                    
                    selectedCoche = Main.carrito.get(row);
                }
            }
        });
        
        tablaProductos.getColumnModel().getColumn(0).setMinWidth(520);
        tablaProductos.getColumnModel().getColumn(0).setMaxWidth(520);

        contentPane.add(panelNorte, BorderLayout.NORTH);
        contentPane.add(splitPane, BorderLayout.CENTER); 
        contentPane.add(panelSur, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }



    private JPanel crearPanelInferior(Concesionario conc, Cliente cliente, List<Coche> reservas) {
        panelInferior = new JPanel(new BorderLayout());
        
        panelFactura = new JPanel(new GridLayout(0, 2));
        panelFactura.setBorder(new EmptyBorder(10, 10, 10, 10));

        totalFactura = calcularTotal();
        
        lblTotal = new JLabel("Total: ");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTotal.setBackground(new Color(40,40,40));
        lblTotal.setForeground(new Color(255,255,255));
        panelFactura.add(lblTotal);

        txtTotal = new JTextField(String.valueOf(totalFactura));
        txtTotal.setEditable(false);
        txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        panelFactura.add(txtTotal);
        panelFactura.setBackground(Color.GRAY);
        panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBackground(new Color(40,40,40));
        panelInferior.add(panelFactura, BorderLayout.CENTER);
        
        return panelInferior;
    }

    private double calcularTotal() {
        double total = 0.0;
        for (Coche coche : Main.carrito) {
            total += coche.getPrecio();
        }
        return total;
    }
    public void facturaSave(Cliente cliente, List<Coche> pedidoFactura) {
        if (Main.carrito.size() == 0) {
            JOptionPane.showMessageDialog(null, "No hay coches seleccionados y por lo" +
                    " tanto no se puede crear la factura", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String nombreArchivo = cliente.getDni()+"-Factura.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {                
                writer.write("========CONCESIONARIO DEUSTO========");
                writer.newLine();
                writer.newLine();

                writer.write(String.format("Cliente: %s", cliente.getNombre()));
                writer.newLine();
                writer.write(String.format("DNI: %s", cliente.getDni()));
                writer.newLine();
                writer.write(String.format("Fecha de Nacimiento: %s", cliente.getfNacStr()));
                writer.newLine();
                writer.newLine();

                writer.write(String.format("Fecha de Facturación: %s",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
                writer.newLine();
                writer.newLine();

                writer.write("Detalles del Pedido:");
                writer.newLine();
                double precioTotal = 0.0;

                for (Coche coche : pedidoFactura) {
                    writer.write(String.format("Modelo: %s", coche.getModelo()));
                    writer.newLine();
                    writer.write(String.format("Marca: %s", coche.getMarca().getMarca()));
                    writer.newLine();
                    writer.write(String.format("Matrícula: %s", coche.getMatricula()));
                    writer.newLine();
                    writer.write(String.format("Precio: %s", coche.getPrecio()));
                    writer.newLine();
                    writer.newLine();
                    precioTotal += coche.getPrecio();
                }

                writer.newLine();
                writer.write(String.format("Precio Total: %s", precioTotal));
                writer.newLine();
                writer.newLine();

                writer.write("---Información de contacto---");
                writer.newLine();
                writer.write("Teléfono: +1234567890");
                writer.newLine();
                writer.write("Email: info@concesionariodeusto.com");
                writer.newLine();
                writer.write("Sede: Ibaiondo Kalea 13");

                JOptionPane.showMessageDialog(null, "La factura se ha guardado exitosamente en " + nombreArchivo + "." +
                        " Para más información, consulte la factura en detalle.", "Notificación", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}