package gui;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import db.bd;
import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Pedido;
import main.Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
	private JPanel espacio,espacio1,espacio2,espacio3,espacio4,espacio5,espacio6;
	
	private JLabel lblPoliticas=new JLabel("<html><u>Políticas de Uso del Concesionario:</u></html>");
	private JTextArea politicasTextArea;
	private JLabel lblEmpresa;
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
	private JButton btnAtras,btnaceptarCond;
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
		
		
		espacio4= new JPanel();
        espacio4.setBackground(new Color(40,40,40));
        espacio5=new JPanel();
        espacio5.setBackground(new Color(40,40,40));
        espacio6=new JPanel();
        espacio6.setBackground(new Color(40,40,40));
        pTodoRight.add(espacio4);
        pTodoRight.add(espacio6);
        
		lblPoliticas.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblPoliticas.setForeground(new Color(255, 255, 255));
		lblPoliticas.setBackground(new Color(40,40,40));
		lblPoliticas.setPreferredSize(new Dimension(50,150));
		lblPoliticas.setBorder(new EmptyBorder(70, 0, 0, 0));
		pTodoRight.add(lblPoliticas);
		politicasTextArea = new JTextArea();
        politicasTextArea.setEditable(false);
        politicasTextArea.setLineWrap(true);
        politicasTextArea.setWrapStyleWord(true);
        politicasTextArea.setText("En nuestra calidad de concesionario, las políticas"+
        " de uso que aplicamos reflejan nuestro compromiso con la excelencia, la ética y"+
        " la protección de la privacidad. Buscamos proporcionar a nuestros clientes"+
        " una experiencia excepcional, basada en la transparencia y la integridad. La"+
        " confianza que depositan en nosotros es invaluable, y por ello, nos esforzamos"+
        " por salvaguardar sus datos personales, utilizando esta información de manera"+
        " responsable y exclusiva para fines comerciales legítimos. Además, nos comprometemos"+
        " a ofrecer información clara sobre nuestros servicios, promociones y términos"+
        " comerciales, garantizando una relación de negocios fundamentada en la honestidad,"+
        " la confianza y el respeto mutuo. En la elección de nuestros servicios, los"+
        " clientes aceptan estas políticas, fortaleciendo así la base de una colaboración"+
        " ética y transparente.\r\n");
        politicasTextArea.setBackground(new Color(40,40,40));
        politicasTextArea.setForeground(new Color(255,255,255));
        politicasTextArea.setFont(new Font("Tahoma", Font.PLAIN,15));
        JScrollPane scrollPaneDerecha = new JScrollPane(politicasTextArea);
        scrollPaneDerecha.setBorder(null);
        scrollPaneDerecha.setPreferredSize(new Dimension(600,200));
        pTodoRight.add(scrollPaneDerecha,BorderLayout.WEST);
        
        espacio3=new JPanel();
        espacio3.setBackground(new Color(40,40,40));
        btnaceptarCond=new JButton("ACEPTAR TÉRMINOS");
        btnaceptarCond.setAlignmentX(Component.CENTER_ALIGNMENT);        
        espacio3.add(btnaceptarCond,BorderLayout.CENTER);        
        pTodoRight.add(espacio3);        
        
        pContent.setBackground(new Color(40,40,40));
        pContent.add(espacio5);
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
		pTodoMid.setBackground(new Color(40,40,40));		
		pTodoMid.add(pContent);	
		JPanel infoContactoPanel = new JPanel(new GridLayout(0, 1));
        infoContactoPanel.setBackground(new Color(40, 40, 40));
        espacio=new JPanel();
        espacio.setBackground(new Color(40,40,40));
        

        JLabel lblContacto = new JLabel("<html><u>Información de Contacto</u></html>");
        lblContacto.setForeground(new Color(255, 255, 255));
        lblContacto.setFont(new Font("Tahoma", Font.BOLD, 17));
        infoContactoPanel.add(lblContacto,BorderLayout.CENTER);

        JLabel lblTelefono = new JLabel("Teléfono: +1234567890");
        lblTelefono.setForeground(new Color(255, 255, 255));
        lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
        infoContactoPanel.add(lblTelefono,BorderLayout.CENTER);

        JLabel lblCorreo = new JLabel("Correo: info@concesionariodeusto.com");
        lblCorreo.setForeground(new Color(255, 255, 255));
        lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 15));
        infoContactoPanel.add(lblCorreo,BorderLayout.CENTER);

        JLabel lblUbicacion = new JLabel("Sede: Ibaiondo kalea 13");
        lblUbicacion.setForeground(new Color(255, 255, 255));
        lblUbicacion.setFont(new Font("Tahoma", Font.BOLD, 15));
        infoContactoPanel.add(lblUbicacion, BorderLayout.CENTER);
        espacio1=new JPanel();
        espacio2=new JPanel();
        espacio2.setBackground(new Color(40,40,40));
        espacio1.setBackground(new Color(40,40,40));
        infoContactoPanel.add(espacio);
        infoContactoPanel.add(espacio1);
        infoContactoPanel.add(espacio2);
        
        infoContactoPanel.setBorder(new EmptyBorder(0, 130, 50, 0));
        pTodoLeft.add(infoContactoPanel, BorderLayout.CENTER);
        
		
		
		
		
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
		        try {
					confirmarCambios();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }			
		});
		btnaceptarCond.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pTodoRight.removeAll();
				JOptionPane.showMessageDialog(null, "Usted acepto los términos de uso","CODICIONES ACEPTADAS",JOptionPane.INFORMATION_MESSAGE);
				pTodoRight.revalidate();
				pTodoRight.repaint();
			}
		});
	}
	private void confirmarCambios() throws SQLException {
		   String dni=userDni.getText();
		   String nombre=userName.getText();
		   SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		   Date date=null;
		   try {
			date=formatoFecha.parse(userFech.getText());
			} catch (ParseException e) {
			date=new Date();
			}
		   String contra=userPssw.getText();
		   Cliente c=new Cliente(dni,nombre,date,contra);
		   bd bdd=new bd();
		   bdd.crearBBDD();
		   bdd.borrarCliente(dni);
		   bdd.insertarCliente(c);
		   Concesionario.cargarClientesEnLista(nomfichClientes);
		   Concesionario.borrarClientePorDNI(nomfichClientes, userDniSeguro);
		   Concesionario.aniadirCliente(c);
		   Concesionario.guardarClientesEnFichero(nomfichClientes);
		   this.clienteMod=c;
		   JOptionPane.showMessageDialog(null, "¡Cambios guardados con exito!","MODIFICADO",JOptionPane.INFORMATION_MESSAGE);
	}
}
