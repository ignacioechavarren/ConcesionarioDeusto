package gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import domain.Cliente;
import domain.Concesionario;
import java.awt.Color;
import java.awt.Font;

public class VentanaProfile extends JFrame{
	private static final String nomfichClientes = "Clientes.csv";
	private JFrame frame= new JFrame("CONCESIONARIO DEUSTO");
	private JPanel pTodo=new JPanel(new GridLayout(1,3));
	private JPanel pTodoLeft=new JPanel(new GridLayout(0,1));
	private JPanel pTodoMid=new JPanel(new GridLayout(0,1));
	private JPanel pTodoRight=new JPanel(new GridLayout(0,1));
	private JPanel pIcon=new JPanel();
	private JPanel pIcon2=new JPanel();
	private JPanel pContent=new JPanel(new GridLayout(0,1));
	private JPanel pContent1,pContent2,pContent3,pContent4,pContent5,pContent6,pContent7,pContent8;
	private JPanel pBtn,pBtn2,pBtn3=new JPanel();
	private JPanel pBotonAtras=new JPanel();
	private JPanel pNorte;
	
	private JLabel userIcon;
	private JLabel userNameLabel= new JLabel("Nombre:");
	private JTextArea userName;
	private JLabel userDniLabel= new JLabel("Dni:");
	private String userDniSeguro;
	private JTextArea userDni;
	private JLabel userFechLabel= new JLabel("Fecha de Nacimiento");
	private JTextArea userFech;
	private JLabel userPsswLabel= new JLabel("Contraseña");
	private JTextArea userPssw;
	private JButton btnAtras;
	private JButton btnConfirmarCambios;
	private Cliente clienteMod;
	public VentanaProfile(Cliente cliente, Concesionario conc ) {
		super();
		frame.setBounds(300, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		this.clienteMod=cliente;
		userDniSeguro=cliente.getDni();
		pContent1=new JPanel();
		pContent1.setBackground(new Color(40, 40, 40));
		pContent2=new JPanel();
		pContent2.setBackground(new Color(40, 40, 40));
		pContent3=new JPanel();
		pContent3.setBackground(new Color(40, 40, 40));
		pContent4=new JPanel();
		pContent4.setBackground(new Color(40, 40, 40));
		pContent5=new JPanel();
		pContent5.setBackground(new Color(40, 40, 40));
		pContent6=new JPanel();
		pContent6.setBackground(new Color(40, 40, 40));
		pContent7=new JPanel();
		pContent7.setBackground(new Color(40, 40, 40));
		pContent8=new JPanel();
		pContent8.setBackground(new Color(40, 40, 40));
		
		ImageIcon icon=new ImageIcon("imagenes/perfil.png");	
		Image imagen=icon.getImage();
		Image im2=imagen.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		userIcon=new JLabel(new ImageIcon(im2));
		userIcon.setBorder(new EmptyBorder(50,0,0,50));
		userName=new JTextArea(cliente.getNombre());
		userDni=new JTextArea(cliente.getDni());
		userFech=new JTextArea(cliente.getfNacStr());
		userPssw=new JTextArea(cliente.getContrasenia());
		pIcon.setBackground(new Color(40, 40, 40));
		
		
		


		pIcon.add(userIcon);
		userNameLabel.setForeground(new Color(255, 255, 255));
		pContent1.add(userNameLabel);
		pContent1.add(userName);
		userDniLabel.setForeground(new Color(255, 255, 255));
		pContent2.add(userDniLabel);
		pContent2.add(userDni);
		userFechLabel.setForeground(new Color(255, 255, 255));
		pContent3.add(userFechLabel);
		pContent3.add(userFech);
		userPsswLabel.setForeground(new Color(255, 255, 255));
		pContent4.add(userPsswLabel);
		pContent4.add(userPssw);
		pContent.add(pContent1);
		pContent.add(pContent2);
		pContent.add(pContent3);
		pContent.add(pContent4);
		pContent.add(pContent5);
		pContent.add(pContent6);
		pContent.add(pContent7);
		pContent.add(pContent8);
		pBotonAtras.setBackground(new Color(40, 40, 40));
		pContent.add(pBotonAtras);
		
		pTodoLeft.add(pIcon);
		pIcon2.setBackground(new Color(40, 40, 40));
		pTodoLeft.add(pIcon2);		
		pTodoMid.add(pContent);	
		
		
		
		
		
		pTodo.add(pTodoLeft);
		pTodo.add(pTodoMid);
		pTodoRight.setBackground(new Color(40, 40, 40));
		pTodo.add(pTodoRight);
		
		btnConfirmarCambios = new JButton("Confirmar Cambios");
		btnConfirmarCambios.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pBotonAtras.add(btnConfirmarCambios);
		btnAtras = new JButton("Volver");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pBotonAtras.add(btnAtras);
		frame.getContentPane().add(pTodo);
		frame.setVisible(true);
		
		btnAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaConcesionario(conc, clienteMod);
				frame.dispose();
				
			}
		});
		
		
		btnConfirmarCambios.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        confirmarCambios();
		    }			
		});
	}
	private void confirmarCambios() {
		   String dni=userDni.getText();
		   String nombre=userName.getText();
		   SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		   Date date=null;
		   try {
			date=formatoFecha.parse(userFech.getText());
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			date=new Date();
			}
		   String contra=userPssw.getText();
		   Cliente c=new Cliente(dni,nombre,date,contra);
		   Concesionario.cargarClientesEnLista(nomfichClientes);
		   Concesionario.borrarClientePorDNI(nomfichClientes, userDniSeguro);
		   Concesionario.aniadirCliente(c);
		   Concesionario.guardarClientesEnFichero(nomfichClientes);
		   this.clienteMod=c;
		   JOptionPane.showMessageDialog(null, "¡Cambios guardados con exito!","MODIFICADO",JOptionPane.INFORMATION_MESSAGE);
	}
}
