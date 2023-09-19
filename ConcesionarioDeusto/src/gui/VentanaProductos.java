package gui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;


public class VentanaProductos extends JFrame{

			private static final long serialVersionUID = 1L;
			private JPanel contentPane;
			
			private JTable ProductosJTable;
			private JTextField textFieldNombre;
			private JTextField textFieldTipo;
			private JTextField textFieldPrecio;
			private JTextField textFieldId;
			private JButton btnAnyadirArticulo;
			private JTextField textField;
		
			
			public VentanaProductos()  {
				setResizable(false);
				getContentPane().add(new JScrollPane(ProductosJTable), BorderLayout.CENTER);
				

				

				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(200, 200, 1012, 513);
				contentPane = new JPanel();
				contentPane.setBackground(new Color(255, 0, 0));
				contentPane.setForeground(new Color(0, 0, 128));
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
					
				ProductosJTable = new JTable();
				
				ProductosJTable.setBounds(88, 62, 595, 223);	
				
		        
				contentPane.add(ProductosJTable);
				
			
				JComponent lblCantidadProductos = null;
				lblCantidadProductos.setFont(new Font("Tahoma", Font.BOLD, 10));
				lblCantidadProductos.setForeground(new Color(255, 255, 255));
				lblCantidadProductos.setBounds(369, 285, 332, 40);
				contentPane.add(lblCantidadProductos);
				
				JLabel lblProductos = new JLabel("PRODUCTOS");
				lblProductos.setFont(new Font("Tahoma", Font.BOLD, 40));
				lblProductos.setForeground(new Color(192, 192, 192));
				lblProductos.setBounds(265, 11, 332, 40);
				contentPane.add(lblProductos);
						
				JButton btnRealizarPedidos = new JButton("REALIZAR PEDIDO");
				btnRealizarPedidos.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnRealizarPedidos.setBounds(300, 385, 178, 30);
				contentPane.add(btnRealizarPedidos);
				
				JButton btnAtras = new JButton("ATRAS");
				btnAtras.setFont(new Font("Tahoma", Font.BOLD, 10));
				btnAtras.setBounds(884, 430, 89, 22);
				contentPane.add(btnAtras);

				JLabel labelTipo = new JLabel("Tipo:");
				labelTipo.setForeground(new Color(255, 255, 255));
				labelTipo.setBounds(233, 296, 46, 18);
				contentPane.add(labelTipo);
				
				JComboBox<String> comboBoxTipo = new JComboBox<String>();
				comboBoxTipo.setBounds(265, 296, 94, 22);
				contentPane.add(comboBoxTipo);
				comboBoxTipo.addItem("Todos");
				
				textFieldNombre = new JTextField();
				textFieldNombre.setBounds(777, 110, 147, 20);
				contentPane.add(textFieldNombre);
				textFieldNombre.setColumns(10);
				
				textFieldTipo = new JTextField();
				textFieldTipo.setBounds(777, 154, 147, 20);
				contentPane.add(textFieldTipo);
				textFieldTipo.setColumns(10);
				
				textFieldPrecio = new JTextField();
				textFieldPrecio.setBounds(777, 201, 147, 20);
				contentPane.add(textFieldPrecio);
				textFieldPrecio.setColumns(10);
				
				textFieldId = new JTextField();
				textFieldId.setBounds(777, 248, 147, 20);
				contentPane.add(textFieldId);
				textFieldId.setColumns(10);
				
				btnAnyadirArticulo = new JButton("Comprar articulo");
				btnAnyadirArticulo.setBounds(793, 342, 119, 23);
				contentPane.add(btnAnyadirArticulo);
				
				JLabel lblId = new JLabel("Marca:");
				lblId.setBounds(733, 250, 46, 14);
				lblId.setForeground(new Color(192, 192, 192));
				lblId.setFont(new Font("Tahoma", Font.BOLD, 10));
				contentPane.add(lblId);
				
				JLabel lblPrecio = new JLabel("Modelo:");
				lblPrecio.setBounds(733, 204, 58, 14);
				lblPrecio.setForeground(new Color(192, 192, 192));
				lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 10));
				contentPane.add(lblPrecio);
				
				JLabel lblNombre = new JLabel("Precio");
				lblNombre.setBounds(733, 113, 46, 14);
				lblNombre.setForeground(new Color(192, 192, 192));
				lblNombre.setFont(new Font("Tahoma", Font.BOLD, 10));
				contentPane.add(lblNombre);
				
				JLabel lblTipo = new JLabel("Año:");
				lblTipo.setBounds(744, 157, 46, 14);
				lblTipo.setForeground(new Color(192, 192, 192));
				lblTipo.setFont(new Font("Tahoma", Font.BOLD, 10));
				contentPane.add(lblTipo);
				
				JLabel lblAnyadir = new JLabel("Añadir Nuevo Articulo");
				lblAnyadir.setBounds(724, 45, 244, 40);
				lblAnyadir.setForeground(new Color(192, 192, 192));
				lblAnyadir.setFont(new Font("Tahoma", Font.BOLD, 20));
				contentPane.add(lblAnyadir);
				
				textField = new JTextField();
				textField.setBounds(777, 286, 147, 22);
				contentPane.add(textField);
				textField.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("Matricula:");
				lblNewLabel.setForeground(new Color(192, 192, 192));
				lblNewLabel.setBounds(725, 278, 66, 30);
				contentPane.add(lblNewLabel);
				
				
				btnAnyadirArticulo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
					
					
				

				
				
				
				btnRealizarPedidos.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                     
                    }
                    
				});
				
				comboBoxTipo.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
					
					}
				});
			
				
		
			}
			
			
			
			  public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
				  VentanaProductos vI = new VentanaProductos();      // creamos una ventana
			       vI.setVisible(true);            		 // hacemos visible la ventana creada
			    }
		}