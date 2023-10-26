package gui;



import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import domain.Coche;



public class VentanaCarrito extends JFrame{

	private JPanel contentPane;
	
	private  ArrayList<Coche> carrito = new ArrayList<Coche>();
	
	public VentanaCarrito() {
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
		lblNewLabel.setForeground(new Color(192, 192, 192));
		lblNewLabel.setBounds(311, 11, 293, 40);
		contentPane.add(lblNewLabel);
		
		JTable tablaReservas = new JTable();
		contentPane.add(tablaReservas);
		tablaReservas.setFont(new Font("Arial", Font.PLAIN, 14));
		tablaReservas.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		tablaReservas.setBounds(137, 70, 546, 247);
		
		JButton btnEliminarReservas = new JButton("ELIMINAR RESERVA");
		btnEliminarReservas.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEliminarReservas.setBounds(60, 408, 178, 30);
		contentPane.add(btnEliminarReservas);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnVolver.setBounds(700, 412, 89, 22);
		contentPane.add(btnVolver);
		
		JButton btnCrearFactura = new JButton("Crear Factura");
		btnCrearFactura.setBounds(294, 344, 149, 40);
		contentPane.add(btnCrearFactura);
		
		
		btnVolver.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaConcesionario(null);
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