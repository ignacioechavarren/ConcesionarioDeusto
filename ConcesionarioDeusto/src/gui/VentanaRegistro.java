package gui;

import java.awt.BorderLayout;
import java.awt.Component;
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

public class VentanaRegistro extends JFrame{
	Concesionario conc = new Concesionario();
	private JPanel pTotal,pNorte,pCentro,pSur,pCentro2,pCentro3,pCentro4,pCentro5,pCentro6,pCentro7,pCentro8;
	private JLabel lblDniR,lblNomR,lblFNacR,lblConR, lblTitulo;
	private JTextField txtDniR, txtNomR,txtFNacR;
	private JPasswordField txtConR;
	private JButton btnRegistro, btnSalir;
	private static final String nomfichClientes = "Clientes.csv";
	private JFrame vActual;
	
	private static Cliente cliente;
	private static List<Coche> carrito;
	
	
	public static Cliente getCliente() {
		return cliente;
	}


	public static List<Coche> getCarrito() {
		return carrito;
	}


	public VentanaRegistro() {
		super();
		vActual = this;
		setBounds(300, 100, 600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		/*CREACIÓN DE PANELES Y COMPONENTES*/
		pTotal=new JPanel(new GridLayout(0,1));
		pNorte = new JPanel();
		pCentro = new JPanel();
		pSur = new JPanel();
		pCentro2 = new JPanel();
		pCentro3 = new JPanel();
		pCentro4 = new JPanel();
		pCentro5 = new JPanel();
		pCentro6 = new JPanel();
		pCentro7 = new JPanel();
		pCentro8 = new JPanel();
						
		lblTitulo=new JLabel("REGISTRO DE USUARIO");
		lblDniR = new JLabel("DNI: ");
		lblNomR = new JLabel("NOMBRE: ");
		lblFNacR = new JLabel("FECHA DE NACIMIENTO: ");
		lblConR = new JLabel("CONTRASEÑA: ");
		
		
		
		txtDniR = new JTextField(20);
		txtNomR = new JTextField(20);
		txtFNacR = new JTextField(20);
		txtConR = new JPasswordField(20);
		
		btnRegistro = new JButton("REGISTRO");
		btnSalir = new JButton("SALIR");
		pSur.add(btnRegistro);
		pSur.add(btnSalir);
				
		pNorte.add(lblTitulo);
		
		pCentro.add(lblDniR);
		pCentro2.add(txtDniR);
		
		pCentro3.add(lblNomR);
		pCentro4.add(txtNomR);
		
		pCentro5.add(lblFNacR);
		pCentro6.add(txtFNacR);
		
		pCentro7.add(lblConR);
		pCentro8.add(txtConR);
		
		pTotal.setAlignmentX(Component.CENTER_ALIGNMENT);				
		/*CARGA DE LAS COLECCIONES*/
		conc.cargarClientesEnLista(nomfichClientes);
		
		
		/*EVENTOS*/
		
		
		btnRegistro.addActionListener((e)->{
			String dni = txtDniR.getText();
			String nom = txtNomR.getText();
			String fNac = txtFNacR.getText();
			String con = txtConR.getText();
			Cliente c = new Cliente(dni, nom, fNac, con);
			if(Concesionario.buscarCliente(dni)!=null) {
				JOptionPane.showMessageDialog(null, "Ya existe un cliente con ese dni","ERROR",JOptionPane.ERROR_MESSAGE);
			}else {
				Concesionario.aniadirCliente(c);
				JOptionPane.showMessageDialog(null, "Cliente registrado con éxito","REGISTRADO",JOptionPane.INFORMATION_MESSAGE);
				conc.guardarClientesEnFichero(nomfichClientes);
			}
		});
		
		btnSalir.addActionListener((e)->{
			conc.guardarClientesEnFichero(nomfichClientes);
			setVisible(false);
		});
		pTotal.add(pNorte);
		pTotal.add(pCentro);
		pTotal.add(pCentro2);
		pTotal.add(pCentro3);
		pTotal.add(pCentro4);
		pTotal.add(pCentro5);
		pTotal.add(pCentro6);
		pTotal.add(pCentro7);
		pTotal.add(pCentro8);
		pTotal.add(pSur);
		getContentPane().add(pTotal);
		
		setVisible(true);
	}
}
