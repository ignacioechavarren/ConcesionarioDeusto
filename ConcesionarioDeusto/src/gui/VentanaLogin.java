package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
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

public class VentanaLogin extends JFrame{
	private static final String nomfichClientes = "Clientes.csv";
	private JPanel pNorte,pCentro,pCentro2,pCentro3,pCentro4,pSur,pTodo;
	private JLabel inicio,dni,contrasena;
	private JTextField textfield;
	private JPasswordField password;
	private JButton botonI,botonS;	

	public VentanaLogin(Concesionario conc) {
		super();
		setBounds(300, 100, 600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pTodo=new JPanel(new GridLayout(0, 1));
		pNorte=new JPanel();
		pCentro = new JPanel();
		pCentro2 = new JPanel();
		pCentro3 = new JPanel();
		pCentro4 = new JPanel();
		pSur = new JPanel();
				
				
		inicio=new JLabel("INICIO DE SESION");
		dni=new JLabel("DNI: ");
		contrasena=new JLabel("CONTRASENA: ");
		
		textfield=new JTextField(20);
		

		password=new JPasswordField(20);
		
		botonI=new JButton("INICIAR");
		botonS=new JButton("VOLVER");
		
		
		
		pNorte.add(inicio);
		
		
		pCentro.add(dni);
		pCentro2.add(textfield);
		pCentro3.add(contrasena);
		pCentro4.add(password);
		
		pTodo.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		pSur.add(botonI);
		pSur.add(botonS);
		
		conc.cargarClientesEnLista(nomfichClientes);
		
		botonI.addActionListener((e)->{
			
			if(conc.buscarCliente(textfield.getText())!=null&&conc.getPassword(textfield.getText())==password.getText()) {
				//abrir siguiente ventana
				setVisible(false);
			}
			else{
				JOptionPane.showMessageDialog(null, "Los datos no son correctos","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			});
		botonS.addActionListener((e)->{
			setVisible(false);
			conc.guardarClientesEnFichero(nomfichClientes);
		});
		pTodo.add(pNorte);
		pTodo.add(pCentro);
		pTodo.add(pCentro2);
		pTodo.add(pCentro3);
		pTodo.add(pCentro4);
		pTodo.add(pSur);
		getContentPane().add(pTodo);
		
		setVisible(true);
	}

}
