package gui;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import domain.Cliente;

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
	private JPanel pNorte;
	
	private JLabel userIcon;
	private JLabel userNameLabel= new JLabel("Nombre:");
	private JTextArea userName;
	private JLabel userDniLabel= new JLabel("Dni:");
	private JTextArea userDni;
	private JLabel userFechLabel= new JLabel("Fecha de Nacimiento");
	private JTextArea userFech;
	private JLabel userPsswLabel= new JLabel("Contraseña");
	private JTextArea userPssw;
	public VentanaProfile(Cliente cliente) {
		super();
		frame.setBounds(300, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		
		pContent1=new JPanel();
		pContent2=new JPanel();
		pContent3=new JPanel();
		pContent4=new JPanel();
		pContent5=new JPanel();
		pContent6=new JPanel();
		pContent7=new JPanel();
		pContent8=new JPanel();
		
		ImageIcon icon=new ImageIcon("imagenes/perfil.png");	
		Image imagen=icon.getImage();
		Image im2=imagen.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
		userIcon=new JLabel(new ImageIcon(im2));
		userIcon.setBorder(new EmptyBorder(50,0,0,50));
		userName=new JTextArea(cliente.getNombre());
		userDni=new JTextArea(cliente.getDni());
		userFech=new JTextArea(cliente.getfNacStr());
		userPssw=new JTextArea(cliente.getContrasenia());
		pIcon.add(userIcon);
		pContent1.add(userNameLabel);
		pContent1.add(userName);
		pContent2.add(userDniLabel);
		pContent2.add(userDni);
		pContent3.add(userFechLabel);
		pContent3.add(userFech);
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
		
		
		pTodoLeft.add(pIcon);
		pTodoLeft.add(pIcon2);		
		pTodoMid.add(pContent);		
		
		pTodo.add(pTodoLeft);
		pTodo.add(pTodoMid);
		pTodo.add(pTodoRight);
		frame.add(pTodo);
		frame.setVisible(true);
	}
	
}
