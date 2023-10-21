package gui;

import java.awt.BorderLayout;
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
	private JPanel pNorte,pCentro,pSur,pCentroIzquierda,pCentroDerecha;
	private JLabel lblDniR,lblNomR,lblFNacR,lblConR, lblTituloIzquierda,lblTituloDerecha;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*CREACIÓN DE PANELES Y COMPONENTES*/
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
		
		lblDniR = new JLabel("DNI: ");
		lblNomR = new JLabel("NOMBRE: ");
		lblFNacR = new JLabel("FECHA DE NACIMIENTO: ");
		lblConR = new JLabel("CONTRASEÑA: ");
		
		
		
		txtDniR = new JTextField();
		txtNomR = new JTextField();
		txtFNacR = new JTextField();
		txtConR = new JPasswordField();
		
		pCentroDerecha.add(lblDniR);
		pCentroDerecha.add(txtDniR);
		pCentroDerecha.add(lblNomR);
		pCentroDerecha.add(txtNomR);
		pCentroDerecha.add(lblFNacR);
		pCentroDerecha.add(txtFNacR);
		pCentroDerecha.add(lblConR);
		pCentroDerecha.add(txtConR);
		
		
		btnRegistro = new JButton("REGISTRO");
		btnSalir = new JButton("SALIR");
		pSur.add(btnRegistro);
		pSur.add(btnSalir);
		
		lblTituloDerecha = new JLabel("REGISTRO");
		
		pNorte.add(lblTituloDerecha);
		
		
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
			}
		});
		
		btnSalir.addActionListener((e)->{
			conc.guardarClientesEnFichero(nomfichClientes);
			System.exit(0);
		});
		
		setVisible(true);
	}
}
