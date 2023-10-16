package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import domain.Coche;


public class VentanaProductos extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<Coche> coches;
	private JTable tablaCoches;
	private DefaultTableModel modeloDatosCoches;
	
	public VentanaProductos(List<Coche> coches) {
		
		this.coches = coches;
		this.iniciarTabla();
		this.cargarCoches();
		
		JScrollPane panelCoches = new JScrollPane(this.tablaCoches);
		panelCoches.setBorder(new TitledBorder("Coches disponibles"));
		
		
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(panelCoches);
		
		this.setTitle("Ventana principal de Coches");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
		
	}

	private void iniciarTabla() {

		Vector<String> cabeceraCoches = new Vector<String>(Arrays.asList( "Precio", "Año", "Modelo", "Marca", "Matrícula"));
		this.modeloDatosCoches = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraCoches);
		this.tablaCoches = new JTable(this.modeloDatosCoches);
		this.tablaCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
	}
	
	private void cargarCoches() {
		
		this.modeloDatosCoches.setRowCount(0);
		this.coches.forEach(e -> this.modeloDatosCoches.addRow(
				new Object[] {e.getPrecio(), e.getAnyo(), e.getModelo(), e.getMarca(), e.getMatricula()} )
		);
		
	}
	
}