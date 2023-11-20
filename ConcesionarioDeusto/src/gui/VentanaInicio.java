package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Concesionario;

	public class VentanaInicio extends JFrame{
		private static final String nomfichClientes = "Clientes.csv";
		private JPanel pTodo;
		private JPanel pNorte;
		private JPanel pCentro;
		private JPanel pSur;
		private JLabel inicio;
		private JButton botonI,botonR,botonS;
		public VentanaInicio(Concesionario conc){
			super();
			setBounds(300, 100, 600, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pTodo=new JPanel(new GridLayout(0, 1));
			pNorte=new JPanel();
			pNorte.setBackground(new Color(0, 128, 255));
			pCentro = new JPanel();
			pCentro.setBackground(new Color(0, 128, 255));
			pCentro.setLayout(new BorderLayout());
			ImageIcon icono = new ImageIcon("imagenes/1366_2000.jpg");
			JLabel etiquetaImagen = new JLabel(icono);
			pCentro.add(etiquetaImagen, BorderLayout.CENTER);
			add(pCentro);
			setVisible(true);
			pSur = new JPanel();
			pSur.setBackground(new Color(0, 128, 255));
			
			inicio=new JLabel("CONCESIONARIO DEUSTO");
			inicio.setForeground(new Color(255, 255, 255));
			botonS=new JButton("SALIR");
			botonS.setFont(new Font("Tahoma", Font.BOLD, 10));
			
			pNorte.add(inicio);
			
			
			botonI=new JButton("INICIAR");
			pSur.add(botonI);
			botonI.setFont(new Font("Tahoma", Font.BOLD, 10));
			
			botonI.addActionListener(e -> {
				VentanaLogin b= new VentanaLogin(conc);
				dispose();
			});
			botonR=new JButton("REGISTRARSE");
			pSur.add(botonR);
			botonR.setFont(new Font("Tahoma", Font.BOLD, 10));
			
			botonR.addActionListener(e -> {
				VentanaRegistro v = new VentanaRegistro();
				dispose();
			});
			pSur.add(botonS);
			
			pTodo.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			conc.cargarClientesEnLista(nomfichClientes);
			
			botonS.addActionListener(e -> {
				System.exit(0);
				conc.guardarClientesEnFichero(nomfichClientes);
			});
			
			pTodo.add(pNorte);
			pTodo.add(pCentro);
			pTodo.add(pSur);
			getContentPane().add(pTodo);
			
			setVisible(true);
			}
		}