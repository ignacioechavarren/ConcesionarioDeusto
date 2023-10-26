package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
	
	public VentanaConcesionario(Concesionario conc) {
		super();
		
		setBounds(300, 200, 800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		pNorte = new JPanel();
		lblBusqueda = new JLabel("Introduce el número mínimo de unidades: ");
		txtBusqueda = new JTextField(20);
		pNorte.add(lblBusqueda);
		pNorte.add(txtBusqueda);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		
		pSur = new JPanel();
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		btnAniadirCocheAlaReserva = new JButton("AÑADIR COCHE A LA RESERVA");
		pSur.add(btnAniadirCocheAlaReserva);
		btnVerReservas = new JButton("VER RESERVA");
		pSur.add(btnVerReservas);
		btnFinalizarReservas = new JButton("FINALIZAR COMPRA");
		pSur.add(btnFinalizarReservas);
		btnVerTodosLosCoches = new JButton("VER TODOS LOS COCHES");
		pSur.add(btnVerTodosLosCoches);
		btnVolver = new JButton("VOLVER");
		pSur.add(btnVolver);
		
		pOeste = new JPanel();
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		pCentro = new JPanel();
		areaCarrito = new JTextArea(20, 30);
		pCentro.add(areaCarrito);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		/*CREACIÓN DE LA JLIST*/
		modeloListaCoches = new DefaultListModel<>();
		listaCoches = new JList<>(modeloListaCoches);
		scrollListaCoches = new JScrollPane(listaCoches);
		scrollListaCoches.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollListaCoches.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		pOeste.add(scrollListaCoches);
		
		/*EVENTOS*/
		btnVolver.addActionListener((e)->{
			dispose();
			new VentanaInicio(conc);
			
		});
		
		btnAniadirCocheAlaReserva.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnVerReservas.addActionListener((e)->{
			
		});
		
		btnFinalizarReservas.addActionListener((e)->{
			
			
		});
		
		btnVerTodosLosCoches.addActionListener((e)->{
		new VentanaProductos(null);
		
	
		
		});
		
	
	
	
	}
}


