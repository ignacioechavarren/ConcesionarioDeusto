package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Coche;
import domain.Concesionario;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

public class VentanaCombinaciones extends JFrame {

    private JTable tabla;
    private JTextField presupuestoField;
    private JButton generarCombinacionesButton;
    private Map<Integer, List<Coche>> combinaciones;

    public VentanaCombinaciones(List<Coche> listaDeCoches) {
        super("Combinaciones de Compra");
        DefaultTableModel modelo = new DefaultTableModel(){
        	@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Dinero Restante");
        modelo.addColumn("Ver Detalles");
        tabla = new JTable(modelo);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Presupuesto:"));
        presupuestoField = new JTextField(10);
        panelSuperior.add(presupuestoField);
        generarCombinacionesButton = new JButton("Generar Combinaciones");
        panelSuperior.add(generarCombinacionesButton);

        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.add(new JScrollPane(tabla), BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(panelSuperior, BorderLayout.NORTH);
        add(panelTabla, BorderLayout.CENTER);

        generarCombinacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarCombinaciones();
            }
        });
      
        setVisible(true);
    }

    private void generarCombinaciones() {
        try {
            double presupuesto = Double.parseDouble(presupuestoField.getText());
            List<Coche> listaDeCoches = Concesionario.getCoches();
            combinaciones = Concesionario.generarCombinacionesDeCompra(presupuesto, listaDeCoches);
            actualizarTabla(combinaciones);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico para el presupuesto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarTabla(Map<Integer, List<Coche>> combinaciones){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (Map.Entry<Integer, List<Coche>> entry : combinaciones.entrySet()) {
            int id = entry.getKey();
            List<Coche> cochesComprados = entry.getValue();
            StringBuilder matriculas = new StringBuilder();
            for (Coche coche : cochesComprados) {
                matriculas.append(coche.getMatricula()).append(", ");
            }
            if (matriculas.length() > 0) {
                matriculas.setLength(matriculas.length() - 2);
            }
            Object[] fila = {id, calcularPrecioTotal(cochesComprados), matriculas.toString()};
            modelo.addRow(fila);
        }
        int numRows = modelo.getRowCount();
        if (numRows > 0) {
            modelo.removeRow(numRows - 1);
        }
    }

    private double calcularPrecioTotal(List<Coche> coches) {
        double precioTotal = 0;
        for (Coche coche : coches) {
            precioTotal += coche.getPrecio();
        }
        return precioTotal;
    }
    
    
}
