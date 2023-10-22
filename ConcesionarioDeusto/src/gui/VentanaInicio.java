package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Concesionario;

	public class VentanaInicio extends JFrame{
		private static final String nomfichClientes = "Clientes.csv";
		private JPanel pTodo,pNorte,pCentro,pCentro2,pSur;
		private JLabel inicio;
		private JButton botonI,botonR,botonS;
		public VentanaInicio(Concesionario conc){
			super();
			setBounds(300, 100, 600, 400);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			pTodo=new JPanel(new GridLayout(0, 1));
			pNorte=new JPanel();
			pCentro = new JPanel();
			pCentro2 = new JPanel();
			pSur = new JPanel();
			
			inicio=new JLabel("CONCESIONARIO DEUSTO");
			
			botonI=new JButton("INICIAR");
			botonR=new JButton("REGISTRARSE");
			botonS=new JButton("SALIR");
			
			pNorte.add(inicio);
			pCentro2.add(botonR);
			pCentro.add(botonI);
			pSur.add(botonS);
			
			pTodo.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			conc.cargarClientesEnLista(nomfichClientes);
			
			botonR.addActionListener(e -> {
				VentanaRegistro v = new VentanaRegistro();
				setVisible(false);
			});
			
			botonI.addActionListener(e -> {
				VentanaLogin b= new VentanaLogin(conc);
				setVisible(false);
			});
			
			botonS.addActionListener(e -> {
				System.exit(0);
				conc.guardarClientesEnFichero(nomfichClientes);
			});
			
			pTodo.add(pNorte);
			pTodo.add(pCentro);
			pTodo.add(pCentro2);
			pTodo.add(pSur);
			getContentPane().add(pTodo);
			
			setVisible(true);
			}
		} 