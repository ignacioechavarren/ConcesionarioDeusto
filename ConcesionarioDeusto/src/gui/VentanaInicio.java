package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaInicio extends JFrame{

		
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		
		
		public VentanaInicio()  {
			final JFrame ventana = this;
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(200, 200, 850, 500);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 0, 0));
			contentPane.setForeground(new Color(0, 0, 160));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("CONCESIONARIO DEUSTO");
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
			lblNewLabel.setBounds(259, 11, 554, 40);
			contentPane.add(lblNewLabel);
			
			JButton btnVenderProductos = new JButton("VENDER ARTICULOS");
			btnVenderProductos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JButton btnVerpedidos = new JButton("VER CARRITO");
			
			
			JButton btnSalir = new JButton("SALIR");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnSalir.setFont(new Font("Tahoma", Font.BOLD, 10));
			btnSalir.setBounds(542, 406, 166, 30);
			contentPane.add(btnSalir);
			
			
			JButton btnNewButton = new JButton("Ver Articulos");
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnNewButton.setBounds(311, 111, 209, 63);
			contentPane.add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Ver Carrito"+ "");
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnNewButton_1.setForeground(new Color(0, 0, 0));
			btnNewButton_1.setBounds(311, 214, 209, 63);
			contentPane.add(btnNewButton_1);
			
			btnNewButton_1.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
					
				 
				});					
			   
				}
			
				
		  public static void main(String[] args) {
		        VentanaInicio vl = new VentanaInicio();      // creamos una ventana
		       vl.setVisible(true);             			// hacemos visible la ventana creada
		    }
} 