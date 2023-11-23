package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Pedido;
import main.Main;

public class VentanaCarrito extends JFrame {

    private List<Cliente> clientes;
    private JPanel contentPane;
    private JPanel panelNorte;
    private JPanel panelCentro;
    private JPanel panelSur, panelInferior, panelFactura;
    private JButton btnRealizarReserva, btnVolver, btnCrearFactura, btnEliminarPedidos;
    private JLabel lblCarrito, lblTotal;
    private JList<Coche> listaProductos;
    private DefaultListModel<Coche> modeloLista;
    private JScrollPane scrollPane;
    private Coche selectedCoche;
    private JTextField txtTotal;
    private double totalFactura;

    public VentanaCarrito(Concesionario conc, Cliente cliente, List<Coche> reservas) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);

        contentPane = new JPanel(new BorderLayout());
        panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelNorte.setBackground(new Color(40, 40, 40));
        panelNorte.setBorder(new EmptyBorder(20, 0, 20, 0));

        panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(new Color(40, 40, 40));

        modeloLista = new DefaultListModel<>();
        for (Coche coche : Main.carrito) {
            modeloLista.addElement(coche);
        }
        listaProductos = new JList<>(modeloLista);
        scrollPane = new JScrollPane(listaProductos);
        panelCentro.add(scrollPane, BorderLayout.CENTER);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(panelCentro);
        splitPane.setBottomComponent(crearPanelInferior(conc, cliente, reservas));
        splitPane.setDividerLocation(200); 

        panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSur.setBackground(new Color(40, 40, 40));

        lblCarrito = new JLabel("TU CARRITO");
        lblCarrito.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblCarrito.setForeground(new Color(255, 255, 255));
        panelNorte.add(lblCarrito);

        btnVolver = new JButton("VOLVER");
        btnEliminarPedidos = new JButton("ELIMINAR PEDIDO");
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
                panelFactura.removeAll();
                Main.carrito.remove(selectedCoche);
                modeloLista.removeElement(selectedCoche);
                totalFactura = calcularTotal();
                txtTotal = new JTextField(String.valueOf(totalFactura));
                lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
                panelFactura.add(lblTotal);
                txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
                panelFactura.add(txtTotal);
                panelFactura.revalidate();
                panelFactura.repaint();
            }
        });

        btnVolver.addActionListener(e -> {
            VentanaConcesionario ventanaConcesionario = new VentanaConcesionario(conc, cliente);
            dispose();
        });

        btnRealizarReserva.addActionListener((e) -> {
            for (Coche c : reservas) {
                Concesionario.aniadirReserva(cliente, c);
            }
            JOptionPane.showMessageDialog(null, "Enhorabuena, ha realizado la reserva correctamente",
                    "RESERVA FINALIZADA", JOptionPane.INFORMATION_MESSAGE);
        });

        btnCrearFactura.addActionListener(e -> {
            dispose();
        });

        listaProductos.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int index = listaProductos.locationToIndex(e.getPoint());
                selectedCoche = modeloLista.getElementAt(index);
            }
        });

        contentPane.add(panelNorte, BorderLayout.NORTH);
        contentPane.add(splitPane, BorderLayout.CENTER); 
        contentPane.add(panelSur, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setVisible(true);
    }



    private JPanel crearPanelInferior(Concesionario conc, Cliente cliente, List<Coche> reservas) {
        panelInferior = new JPanel(new BorderLayout());
        
        panelFactura = new JPanel(new GridLayout(0, 2));
        panelFactura.setBorder(new EmptyBorder(10, 10, 10, 10));

        totalFactura = calcularTotal();
        
        lblTotal = new JLabel("Total: ");
        lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
        panelFactura.add(lblTotal);

        txtTotal = new JTextField(String.valueOf(totalFactura));
        txtTotal.setEditable(false);
        txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panelFactura.add(txtTotal);
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
}