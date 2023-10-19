package gui;

import java.awt.BorderLayout;
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
	private JPanel pNorte,pCentro,pSur,pCentroIzquierda,pCentroDerecha;;
	private JLabel inicio,dni,contrasena;
	private JTextField textfield;
	private JPasswordField password;
	private JButton botonI,botonS;	

	public VentanaLogin(Concesionario conc) {
		super();
		setBounds(300, 100, 600, 400);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		pNorte = new JPanel(new GridLayout(1, 2));
		pCentro = new JPanel(new GridLayout(1, 2));
		pSur = new JPanel();
		pCentroIzquierda = new JPanel(new GridLayout(2, 2));
		pCentroDerecha = new JPanel(new GridLayout(4, 2));
		
		pCentro.add(pCentroIzquierda);
		pCentro.add(pCentroDerecha);
		
		getContentPane().add(pNorte, BorderLayout.NORTH);
		getContentPane().add(pCentro, BorderLayout.CENTER);
		getContentPane().add(pSur, BorderLayout.SOUTH);
		
		inicio=new JLabel("INICIO DE SESIÓN");
		dni=new JLabel("DNI: ");
		contrasena=new JLabel("CONTRASEÑA: ");
		
		textfield=new JTextField();
		password=new JPasswordField();
		
		botonI=new JButton("INICIAR");
		botonS=new JButton("VOLVER");
		
		pNorte.add(inicio);
		
		pCentro.add(dni);
		pCentro.add(textfield);
		pCentro.add(contrasena);
		pCentro.add(password);
		
		pSur.add(botonI);
		pSur.add(botonS);
		
		conc.cargarClientesEnLista(nomfichClientes);
		conc.cargarClientesEnLista(nomfichClientes);
		
		botonI.addActionListener((e)->{
			
			if(Concesionario.buscarCliente(textfield.getText())!=null&&Concesionario.getPassword(textfield.getText())==password.getText()) {
				//abrir siguiente ventana
			}
			else{
				JOptionPane.showMessageDialog(null, "Los datos no son correctos","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			});
		botonS.addActionListener((e)->{
			System.exit(0);
			conc.guardarClientesEnFichero(nomfichClientes);
		});
		
		setVisible(true);
	}

}
