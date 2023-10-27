package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;


public class VentanaConcesionario extends JFrame{
	private JPanel pSur;
	private JPanel pOeste;
	private JPanel pCentro;
	private JPanel pNorte;
	private JButton btnVolver;
	private JButton btnAniadirCocheAlaReserva;
	private JButton btnVerReservas;
	private JButton btnFinalizarReservas;
	private JButton btnVerTodosLosCoches;
	
	
	private JTextArea areaCarrito;
	
	private DefaultListModel<Coche> modeloListaCoches; //El modelo guarda la información, los artículos
	private JList<Coche> listaCoches; //La JList presenta/visualiza esos artículos
	private JScrollPane scrollListaCoches;
	
	private JLabel lblBusqueda;
	private JTextField txtBusqueda;
	
	
	
	public VentanaConcesionario(Concesionario conc, Cliente cliente) {
		super();
		getContentPane().setBackground(new Color(0, 128, 255));
		
		setBounds(300, 200, 800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		pNorte = new JPanel();
		pNorte.setBackground(new Color(0, 128, 255));
		lblBusqueda = new JLabel("Introduce el número mínimo de unidades: ");
		lblBusqueda.setForeground(new Color(255, 255, 255));
		txtBusqueda = new JTextField(20);
		pNorte.add(lblBusqueda);
		pNorte.add(txtBusqueda);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		
		pSur = new JPanel();
		pSur.setBackground(new Color(0, 128, 255));
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		btnAniadirCocheAlaReserva = new JButton("AÑADIR COCHE A LA RESERVA");
		btnAniadirCocheAlaReserva.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnAniadirCocheAlaReserva);
		btnVerReservas = new JButton("VER CARRITO");
		btnVerReservas.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnVerReservas);
		btnFinalizarReservas = new JButton("FINALIZAR RESERVA");
		btnFinalizarReservas.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnFinalizarReservas);
		btnVerTodosLosCoches = new JButton("VER TODOS LOS COCHES");
		btnVerTodosLosCoches.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnVerTodosLosCoches);
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnVolver);
		
		pOeste = new JPanel();
		pOeste.setBackground(new Color(0, 128, 255));
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		
		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(265, 296, 94, 22);
		pOeste.add(comboBoxTipo);
		
		comboBoxTipo.addItem("Todos");
		comboBoxTipo.addItem("Honda");
		comboBoxTipo.addItem("BMW");
		comboBoxTipo.addItem("Opel");
		comboBoxTipo.addItem("Toyota");
		comboBoxTipo.addItem("Ford");
		comboBoxTipo.addItem("Toyota");
		comboBoxTipo.addItem("Volkswagen");
		comboBoxTipo.addItem("Seat");
		
		
		
		
		
		
		ArrayList<Coche> cocheTipo = new ArrayList<Coche>();
		List<Coche>reservas=new ArrayList<Coche>();
		
		
		List<Coche>coches1=new ArrayList<>(conc.getCoches());
		modeloListaCoches = new DefaultListModel<>();
		for (Coche c : coches1) {
			modeloListaCoches.addElement(c);
		}
		listaCoches = new JList<>(modeloListaCoches);
		scrollListaCoches = new JScrollPane(listaCoches);
		scrollListaCoches.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollListaCoches.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		pOeste.add(scrollListaCoches);
		
		
		comboBoxTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				   String marcaSeleccionada = comboBoxTipo.getSelectedItem().toString();
			        modeloListaCoches.clear(); // Limpia el modelo de la lista de coches

			        if (marcaSeleccionada.equals("Todos")) {
			            for (Coche c : coches1) {
			                modeloListaCoches.addElement(c);
			            }
			        } else {
			            for (Coche c : coches1) {			            	
			                if (c.getMarca().equals(Marca.getPorID(marcaSeleccionada))) {
			                    modeloListaCoches.addElement(c);
			                }
			            }
			        }
			    }
				
		});
	
		
		
		btnVolver.addActionListener((e)->{
			dispose();
			new VentanaInicio(conc);
			
		});
		
		btnAniadirCocheAlaReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Coche cocheRes=listaCoches.getSelectedValue();
				reservas.add(cocheRes);
				
				
				
			}
		});
		
		btnVerReservas.addActionListener((e)->{
			new VentanaCarrito(conc,cliente, reservas);
			dispose();
		});
		
		btnFinalizarReservas.addActionListener((e)->{
			for (Coche c : reservas) {
				Concesionario.aniadirReserva(cliente, c);
			}
					
		});
		
		btnVerTodosLosCoches.addActionListener((e)->{
		new VentanaProductos(conc,cliente);
		dispose();
	
		
		});
		
	
	
	
	}
}
