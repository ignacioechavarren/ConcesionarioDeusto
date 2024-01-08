package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.bd;
import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import java.awt.Color;
import java.awt.Font;

public class VentanaRegistro extends JFrame{
	Concesionario conc = new Concesionario();
	private JPanel pTotal,pNorte,pCentro,pSur,pCentro2,pCentro3,pCentro4,pCentro5,pCentro6,pCentro7,pCentro8;
	private JLabel lblDniR,lblNomR,lblFNacR,lblConR, lblTitulo;
	private JTextField txtDniR, txtNomR,txtFNacR;
	private JPasswordField txtConR= new JPasswordField(20);
	private JButton btnRegistro, btnVolver;
	private static final String nomfichClientes = "Clientes.csv";
	private JFrame frame= new JFrame("CONCESIONARIO DEUSTO");	
	private static Cliente cliente = new Cliente();
	private static List<Coche> carrito = new ArrayList<>();
	
	public static Cliente getCliente() {
		return cliente;
	}


	public static List<Coche> getCarrito() {
		return carrito;
	}


	public VentanaRegistro() {
		super();		 
		frame.setBounds(300, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(MAXIMIZED_BOTH);
		
		pTotal=new JPanel(new GridLayout(0,1));
		pNorte = new JPanel();
		pNorte.setBackground(new Color(40, 40, 40));
		pCentro = new JPanel();
		pCentro.setBackground(new Color(40, 40, 40));
		pSur = new JPanel();
		pSur.setBackground(new Color(40, 40, 40));
		pCentro2 = new JPanel();
		pCentro2.setBackground(new Color(40, 40, 40));
		pCentro3 = new JPanel();
		pCentro3.setBackground(new Color(40, 40, 40));
		pCentro4 = new JPanel();
		pCentro4.setBackground(new Color(40, 40, 40));
		pCentro5 = new JPanel();
		pCentro5.setBackground(new Color(40, 40, 40));
		pCentro6 = new JPanel();
		pCentro6.setBackground(new Color(40, 40, 40));
		pCentro7 = new JPanel();
		pCentro7.setBackground(new Color(40, 40, 40));
		pCentro8 = new JPanel();
		pCentro8.setBackground(new Color(40, 40, 40));
						
		lblTitulo=new JLabel("REGISTRO DE USUARIO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setForeground(new Color(255, 165, 0));
		
		btnRegistro = new JButton("REGISTRO");
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 15));
		pSur.add(btnRegistro);
		pSur.add(btnVolver);
				
		pNorte.add(lblTitulo);
		
		pTotal.setAlignmentX(Component.CENTER_ALIGNMENT);	
		
		
		
		conc.cargarClientesEnLista(nomfichClientes);
		
		txtConR.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {		            
		            btnRegistro.doClick();
		        }
		    }
		});		
		btnRegistro.addActionListener((e)->{
			String dni = txtDniR.getText();
			String nom = txtNomR.getText();
			String fNac = txtFNacR.getText();
			String con = txtConR.getText();
			Cliente c = new Cliente(dni, nom, fNac, con);
			if(dni.isEmpty()||nom.isEmpty()||con.isEmpty()){
				JOptionPane.showMessageDialog(null, "Dni, nombre o password estan vacios","ERROR",JOptionPane.ERROR_MESSAGE);
			}else {
			if(Concesionario.buscarCliente(dni)!=null) {
				JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese dni","ERROR",JOptionPane.ERROR_MESSAGE);
			}else {
				Concesionario.aniadirCliente(c);
				bd bdd=new bd();
				bdd.crearBBDD();
				try {
					bdd.insertarCliente(c);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Cliente registrado con éxito","REGISTRADO",JOptionPane.INFORMATION_MESSAGE);
				conc.guardarClientesEnFichero(nomfichClientes);
				
			}}
		});	
		
		btnVolver.addActionListener((e)->{
			conc.guardarClientesEnFichero(nomfichClientes);		
			frame.dispose();
			VentanaInicio vi = new VentanaInicio(conc);
		});
		
		pTotal.add(pNorte);
		pTotal.add(pCentro);
		pTotal.add(pCentro2);
		pTotal.add(pCentro3);
		lblDniR = new JLabel("DNI: ");
		pCentro3.add(lblDniR);
		lblDniR.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDniR.setForeground(new Color(255, 165, 0));		
		
		txtDniR = new JTextField(20);
		pCentro3.add(txtDniR);
		pTotal.add(pCentro4);
		lblNomR = new JLabel("NOMBRE: ");
		pCentro4.add(lblNomR);
		lblNomR.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomR.setForeground(new Color(255, 165, 0));
		txtNomR = new JTextField(20);
		pCentro4.add(txtNomR);
		pTotal.add(pCentro5);
		lblFNacR = new JLabel("FECHA DE NACIMIENTO(dd-MM-yyyy): ");
		pCentro5.add(lblFNacR);
		lblFNacR.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFNacR.setForeground(new Color(255, 165, 0));
		txtFNacR = new JTextField(20);
		pCentro5.add(txtFNacR);
		pTotal.add(pCentro6);
		lblConR = new JLabel("CONTRASEÑA: ");
		pCentro6.add(lblConR);
		lblConR.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConR.setForeground(new Color(255, 165, 0));
		pCentro6.add(txtConR);
		pTotal.add(pCentro7);
		pTotal.add(pCentro8);
		pTotal.add(pSur);
		frame.getContentPane().add(pTotal);		
		frame.setVisible(true);
	}
}