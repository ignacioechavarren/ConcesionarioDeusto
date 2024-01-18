package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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
//IAG: ChatGPT
//FILE: daniel.nocito-opendeusto
//ADAPTADO: La intención era entender un error con relacion a lo group layout en uso, pero ante la imposibilidad de solucionarlo le pedi 
//a la ia una solución mas simple. En conclusion no se usaron los layout group y se desecho la 
//idea y fuimos mas conservadores con una interface sencilla.
import java.util.logging.Logger;

public class VentanaCombinaciones extends JFrame {
	private static final Logger logger=Logger.getLogger(VentanaCombinaciones.class.getName());
    private JTable tabla;
    private JTextField presupuestoField;
    private JButton generarCombinacionesButton, volver;
    private Map<Integer, List<Coche>> combinaciones;

    public VentanaCombinaciones(List<Coche> listaDeCoches) {
        super("Combinaciones de Compra");
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("ID");
        modelo.addColumn("Dinero Restante");
        modelo.addColumn("Ver Detalles");

        tabla = new JTable(modelo);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabla.setDefaultRenderer(Object.class, centerRenderer);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 400);

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        panelSuperior.add(new JLabel("Presupuesto:"));
        presupuestoField = new JTextField(10);
        panelSuperior.add(presupuestoField);
        generarCombinacionesButton = new JButton("Generar Combinacion"); 
        volver = new JButton("Volver");
        generarCombinacionesButton.setBackground(new Color(75, 169, 255)); 
        generarCombinacionesButton.setForeground(Color.WHITE); 
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
            logger.warning("Por favor, ingrese un valor numérico para el presupuesto.");
            
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
