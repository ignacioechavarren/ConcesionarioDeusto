package gui;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;



public class VentanaCarrito extends JFrame{

	private JPanel contentPane;
	
	private  ArrayList<Coche> carrito = new ArrayList<Coche>();
	
	public VentanaCarrito(Concesionario conc, Cliente cliente, List<Coche> reservas) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 850, 500);
		setVisible(true); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setForeground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CARRITO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(315, 20, 186, 40);
		contentPane.add(lblNewLabel);
		
		JTable tablaReservas = new JTable();
		contentPane.add(tablaReservas);
		tablaReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		tablaReservas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaReservas.setBounds(147, 99, 546, 247);
		
		JButton btnEliminarReservas = new JButton("ELIMINAR RESERVA");
		btnEliminarReservas.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEliminarReservas.setBounds(60, 408, 178, 30);
		contentPane.add(btnEliminarReservas);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolver.setBounds(584, 408, 154, 30);
		contentPane.add(btnVolver);
		
		JButton btnCrearFactura = new JButton("CREAR FACTURA");
		btnCrearFactura.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnCrearFactura.setBounds(332, 408, 154, 30);
		contentPane.add(btnCrearFactura);
		
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaConcesionario(conc , cliente);
				dispose();
			}
		});
		

			btnCrearFactura.addActionListener((ActionListener) new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			}
		
	
	 
}