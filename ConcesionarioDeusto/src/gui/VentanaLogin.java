package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import java.awt.Color;
import java.awt.Font;

public class VentanaLogin extends JFrame{
	private static final String nomfichClientes = "Clientes.csv";
	private JPanel pNorte;
	private JPanel pCentro;
	private JPanel pCentro2;
	private JPanel pCentro3;
	private JPanel pCentro4;
	private JPanel pSur;
	private JPanel pTodo;
	private JLabel inicio,dni,contraseña;
	private JTextField textfield;
	private JPasswordField password;
	private JButton botonI,botonS;	
	private JFrame ventana;
	
	
	private static Cliente cliente; //c almacenará la información del cliente que ha iniciado sesión
	private static List<Coche> carrito; //almacenará los artículo que ha añadido al carrito el cliente que ha iniciado sesión
	
	
	public static Cliente getCliente() {
		return cliente;
	}


	public static List<Coche> getCarrito() {
		return carrito;
	}


	public VentanaLogin(Concesionario conc) {
		super();
		setBounds(300, 100, 600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		pTodo=new JPanel(new GridLayout(0, 1));
		pNorte=new JPanel();
		pNorte.setBackground(new Color(40, 40, 40));
		pCentro = new JPanel();
		pCentro.setBackground(new Color(40, 40, 40));
		pCentro2 = new JPanel();
		pCentro2.setBackground(new Color(40, 40, 40));
		pCentro3 = new JPanel();
		pCentro3.setBackground(new Color(40, 40, 40));
		pCentro4 = new JPanel();
		pCentro4.setBackground(new Color(40, 40, 40));
		pSur = new JPanel();
		pSur.setBackground(new Color(40, 40, 40));
				
				
		inicio=new JLabel("INICIO DE SESION");
		inicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inicio.setForeground(new Color(255, 255, 255));
		contraseña=new JLabel("CONTRASENA: ");
		contraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contraseña.setForeground(new Color(255, 255, 255));
		
		botonI=new JButton("INICIAR");
		botonI.setFont(new Font("Tahoma", Font.BOLD, 15));
		botonS=new JButton("VOLVER");
		botonS.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		
		pNorte.add(inicio);
		pCentro3.add(contraseña);
		
		pTodo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pSur.add(botonI);
		pSur.add(botonS);
		
		conc.cargarClientesEnLista(nomfichClientes);
		
		botonI.addActionListener((e)->{
			
			String dni = textfield.getText();
			String con = password.getText();
			Cliente c = Concesionario.buscarCliente(dni);
			if(c == null) {
				JOptionPane.showMessageDialog(null, "Para poder iniciar sesión tienes que estar registrado","ERROR",JOptionPane.ERROR_MESSAGE);
			}else {
				if(c.getContrasenia().equals(con)) {
					JOptionPane.showMessageDialog(null, "Bienvenido!","SESIÓN INICIADA",JOptionPane.INFORMATION_MESSAGE);
					cliente = c; //Guardo en cliente los datos del cliente que ha iniciado sesión
					carrito = new ArrayList<>(); //Instancio (creo) un carrito vacío
					
					
					//new VentanaProductos(conc);
					new VentanaConcesionario(conc, cliente);
					dispose();
					
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta","ERROR",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
			
		botonS.addActionListener((e)->{
			setVisible(false);
			conc.guardarClientesEnFichero(nomfichClientes);
			VentanaInicio vi= new VentanaInicio(conc);
			dispose();
		});
		pTodo.add(pNorte);
		pTodo.add(pCentro);
		pTodo.add(pCentro2);
		dni=new JLabel("DNI: ");
		dni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pCentro2.add(dni);
		dni.setForeground(new Color(255, 255, 255));
		
		textfield=new JTextField(20);
		pCentro2.add(textfield);
		pTodo.add(pCentro3);
		

		password=new JPasswordField(20);
		pCentro3.add(password);
		pTodo.add(pCentro4);
		pTodo.add(pSur);
		getContentPane().add(pTodo);
		
		setVisible(true);
	}

}