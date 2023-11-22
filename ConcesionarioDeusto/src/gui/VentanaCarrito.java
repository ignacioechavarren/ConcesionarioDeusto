package gui;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;


import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import main.Main;




public class VentanaCarrito extends JFrame{
	private JFrame frame = new JFrame ("CONCESIONARIO DEUSTO");
	private JPanel contentPane;
	private JPanel pNorte;		
	private JPanel pCentro;
	private JPanel pSur;
	private JButton btnEliminarReservas, btnVolver, btnCrearFactura, btnEliminarpedidos;
	private JLabel lblNewLabel,lblCarrito;
	private JLabel inicio;
	private JList productos;
	private DefaultListModel modelo;
	private Coche selectedCoche;
	private JScrollPane scroll;
	private DefaultListModel<Coche>dlm;
	private  ArrayList<Coche> carrito = new ArrayList<Coche>();
	
	public VentanaCarrito(Concesionario conc, Cliente cliente, List<Coche> reservas) {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		
		contentPane=new JPanel(new GridLayout(0, 1));
		pNorte=new JPanel(new GridLayout(2, 1));
		pNorte.setBackground(new Color(40, 40, 40));
		pNorte.setBorder(new EmptyBorder(90,0,0,0));
		pNorte.setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER));
		pCentro = new JPanel();
		pCentro.setBackground(new Color(40, 40, 40));
		pCentro.setLayout(new BorderLayout());
		
		dlm=new DefaultListModel<Coche>();
		for (Coche coche : Main.carrito) {
			dlm.addElement(coche);
		}
		productos = new JList(dlm);		
		scroll = new JScrollPane(productos);
		scroll.setSize(new Dimension(20,25));
		pCentro.add(scroll);
		
		pSur = new JPanel();
		pSur.setBackground(new Color(40, 40, 40));
		

		btnEliminarpedidos = new JButton("ELIMINAR PEDIDO");
		btnEliminarpedidos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEliminarpedidos.setBounds(60, 408, 178, 30);
		pSur.add(btnEliminarpedidos);
		
		
		lblCarrito = new JLabel("TU CARRITO" );
		lblCarrito.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCarrito.setForeground(new Color(255, 255, 255));
		lblCarrito.setBounds(369, 285, 332, 40);
		pNorte.add(lblCarrito);
				
		btnVolver=new JButton("VOLVER");
		pSur.add(btnVolver);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		productos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {                
                int index = productos.locationToIndex(e.getPoint());                
                Coche selectedValue = dlm.getElementAt(index);                
                 selectedCoche=selectedValue;
                }
		});
		btnEliminarpedidos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//productos.removeAll();				
				//scroll.removeAll();				
				System.out.println("Eliminado:"+selectedCoche);
				Main.carrito.remove(selectedCoche);					
				new VentanaCarrito(conc, cliente, Main.carrito);
				frame.dispose();
				//dlm=new DefaultListModel<Coche>();
				//for (Coche coche : Main.carrito) {
					//dlm.addElement(coche);
				//}
				//productos = new JList(dlm);
				//scroll = new JScrollPane(productos);
				//productos.revalidate();
				//productos.repaint();
				//scroll.revalidate();
				//scroll.repaint();	
				//pCentro.revalidate();
				//pCentro.repaint();
				//contentPane.revalidate();
				//contentPane.repaint();
				//frame.revalidate();
				//frame.repaint();
			}
		});	
		
		
		btnVolver.addActionListener(e -> {
			VentanaConcesionario b= new VentanaConcesionario(conc, cliente);
			frame.dispose();
		});
		
		btnEliminarReservas =new JButton("ELIMINAR RESERVAS");
		pSur.add(btnEliminarReservas);
		btnEliminarReservas.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnEliminarReservas.addActionListener(e -> {
			for (Coche coche : Main.carrito) {
				Main.carrito.remove(coche);				
			}
			scroll.revalidate();
			scroll.repaint();
			frame.dispose();
		});
		
		btnCrearFactura =new JButton("CREAR FACTURA");
		pSur.add(btnCrearFactura);
		btnCrearFactura.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		btnCrearFactura.addActionListener(e -> {
			
			frame.dispose();
		});		
			
		contentPane.add(pNorte);
		contentPane.add(pCentro);
		contentPane.add(pSur);
		frame.getContentPane().add(contentPane);
			
		frame.setVisible(true);
	}
		
	
	 
}