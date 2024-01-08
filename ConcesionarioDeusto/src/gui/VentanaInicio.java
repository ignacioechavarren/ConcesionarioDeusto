package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
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
		private JFrame frame = new JFrame ("CONCESIONARIO DEUSTO");
		private JPanel pTodo;
		private JPanel pNorte;		
		private JPanel pCentro;
		private JPanel pSur;
		private JLabel inicio;
		private JLabel inicio2;
		private JButton botonI,botonR,botonS;
		
		public VentanaInicio(Concesionario conc){
			super();
			frame.setBounds(300, 100, 600, 400);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setResizable(false);
			pTodo=new JPanel(new GridLayout(0, 1));
			pNorte=new JPanel(new GridLayout(2, 1));
			pNorte.setBackground(new Color(40, 40, 40));
			pNorte.setBorder(new EmptyBorder(90,0,0,0));
			pNorte.setLayout((LayoutManager) new FlowLayout(FlowLayout.CENTER));
			pCentro = new JPanel();
			pCentro.setBackground(new Color(40, 40, 40));
			pCentro.setLayout(new BorderLayout());
			ImageIcon icono = new ImageIcon("resources/imagenes/1366_2000.jpg");
			JLabel etiquetaImagen = new JLabel(icono);
			pCentro.add(etiquetaImagen, BorderLayout.CENTER);
			
			pSur = new JPanel();
			pSur.setBackground(new Color(40, 40, 40));
			
			ImageIcon icon=new ImageIcon("resources/imagenes/logo.png");	
			Image imagen=icon.getImage();
			Image im2=imagen.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			inicio=new JLabel("CONCESIONARIO DEUSTO");
			inicio.setForeground(new Color(255, 165, 0));
			inicio.setFont(new Font("Impact", Font.BOLD, 50));
			inicio2=new JLabel(new ImageIcon(im2));
			inicio2.setForeground(new Color(255, 255, 255));			
			botonS=new JButton("SALIR");
			botonS.setFont(new Font("Tahoma", Font.BOLD, 20));
						
			pNorte.add(inicio);
			pNorte.add(inicio2);
			
			
			botonI=new JButton("INICIAR");
			pSur.add(botonI);
			botonI.setFont(new Font("Tahoma", Font.BOLD, 20));
			
			botonI.addActionListener(e -> {
				VentanaLogin b= new VentanaLogin(conc);
				frame.dispose();
			});
			botonR=new JButton("REGISTRARSE");
			pSur.add(botonR);
			botonR.setFont(new Font("Tahoma", Font.BOLD, 20));
			
			botonR.addActionListener(e -> {
				VentanaRegistro v = new VentanaRegistro();
				frame.dispose();
			});
			pSur.add(botonS);
			pSur.setBorder(new EmptyBorder(110,0,0,0));
			
			pTodo.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			conc.cargarClientesEnLista(nomfichClientes);
			
			botonS.addActionListener(e -> {
				conc.guardarClientesEnFichero(nomfichClientes);
				System.exit(0);				
			});
			
			pTodo.add(pNorte);
			pTodo.add(pCentro);
			pTodo.add(pSur);
			frame.getContentPane().add(pTodo);
			
			frame.setVisible(true);
			}
		}