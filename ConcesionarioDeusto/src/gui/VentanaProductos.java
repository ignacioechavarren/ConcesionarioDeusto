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
import javax.swing.JButton;
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

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;


public class VentanaProductos extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<Coche> coches;
	private JTable tablaCoches;
	private DefaultTableModel modeloDatosCoches;
	private JPanel pSur;
	private JButton btnVolver;
	
	public VentanaProductos(Concesionario conc, Cliente cliente) {
		getContentPane().setBackground(new Color(0, 128, 255));
		
		this.coches = new ArrayList<Coche>(conc.getCoches());
		this.iniciarTabla();
		this.cargarCoches();
		
		JScrollPane panelCoches = new JScrollPane(this.tablaCoches);
		panelCoches.setBorder(new TitledBorder("Coches disponibles"));
		
		
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(panelCoches);
		
		pSur = new JPanel();
		pSur.setBackground(new Color(0, 128, 255));
		btnVolver = new JButton("VOLVER");
		getContentPane().add(pSur, BorderLayout.SOUTH);
		pSur.add(btnVolver);
		
		this.setTitle("Ventana principal de Coches");		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);	
		
		
		btnVolver.addActionListener((e)->{
			dispose();
			new VentanaConcesionario(conc,cliente);
			
		});
		
	}

	private void iniciarTabla() {

		Vector<String> cabeceraCoches = new Vector<String>(Arrays.asList( "Precio", "Año", "Marca", "Modelo", "Matrícula"));
		this.modeloDatosCoches = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraCoches);
		this.tablaCoches = new JTable(this.modeloDatosCoches);
		this.tablaCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel resultado = new JLabel(value.toString());
			
			if(value instanceof Marca) {
				Marca m = (Marca) value;
				
				resultado.setText("");		
				resultado.setToolTipText(m.toString());
				resultado.setHorizontalAlignment(JLabel.CENTER);
				
				switch (m) { 
					case SEA:
						resultado.setIcon(new ImageIcon("imagenes/SEAT_logo_from_2017.png"));
						break;
					case BMW:
						resultado.setIcon(new ImageIcon("imagenes/bmw_logo_PNG19712.png"));
						break;
					case OPE:
						resultado.setIcon(new ImageIcon("imagenes/Opel-Logo_2017.png"));
						break;
					case TOY:
						resultado.setIcon(new ImageIcon("imagenes/toyota.png"));
						break;
					case FOR:
						resultado.setIcon(new ImageIcon("imagenes/Ford-Motor-Company-Logo.png"));
						break;
					case HON:
						resultado.setIcon(new ImageIcon("imagenes/Logo_Honda_F1.png"));
						break;
					case VOL:
						resultado.setIcon(new ImageIcon("imagenes/Volkswagen_Logo_till_1995.svg.png"));
						break;
					default:
			}
				
			}
			resultado.setOpaque(true);
			return resultado;
		};
		this.tablaCoches.setDefaultRenderer(Object.class, cellRenderer);
	}
	
	private void cargarCoches() {
		
		this.modeloDatosCoches.setRowCount(0);
		this.coches.forEach(e -> this.modeloDatosCoches.addRow(
				new Object[] {e.getPrecio(), e.getAnyo(), e.getModelo(), e.getMarca(), e.getMatricula()} )
		);
		
	}
	
	
	
}