package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.OverlayLayout;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Cliente;
import domain.Coche;
import domain.Concesionario;
import domain.Marca;
import main.Main;


public class VentanaConcesionario extends JFrame{
	private JFrame frame=new JFrame("CONCESIONARIO DEUSTO");
	private static final Logger logger=Logger.getLogger(VentanaConcesionario.class.getName());
	private JPanel pSur;
	private JPanel pOeste;
	private JPanel pEste,pViewProfile,pEsteTop,pEsteTop1,pEsteTop2,pEsteTop3,pBtnVerReservas,pRelleno;
	private JPanel pCentro;
	private JPanel pNorte;
	private JButton btnVolver;
	private JButton btnAniadirCocheAlaReserva;
	private JButton btnVerReservas;
	private JButton viewProfile;
	private JButton simulador= new JButton("SUGERIR COMPRAS");
	private JTextArea areaCarrito;
	private DefaultListModel<Coche> modeloListaCoches; 
	private JList<Coche> listaCoches; 
	private JLabel lblBusqueda,contador,soporte;
	private JTextField txtBusqueda;
	private DefaultTableModel modeloDatosCoches;
	private List<Coche> coches;
	private JTable tablaCoches;
	private Coche cocheSel;
	private int mouseRowPersonajes = -1;
	private int value=Main.carrito.size();
	private  boolean ventanaAbierta = false;

	
	public VentanaConcesionario(Concesionario conc, Cliente cliente) {
		
		super();
		frame.getContentPane().setBackground(new Color(0, 128, 255));
		frame.setResizable(false);
		this.coches = new ArrayList<Coche>(conc.getCoches());
		this.iniciarTabla();
		this.cargarCoches();
				
		JScrollPane panelCoches = new JScrollPane(this.tablaCoches);
		panelCoches.setBorder(new TitledBorder("Coches disponibles"));
		
		frame.setBounds(300, 200, 800, 400);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		contador = new JLabel("" + value);
		contador.setForeground(Color.GRAY);
		contador.setFont(new Font("Verdana", Font.BOLD, 15));
		contador.setAlignmentX(Component.LEFT_ALIGNMENT);
		contador.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		contador.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pSur = new JPanel();
		pSur.setBackground(new Color(40, 40, 40));
		pSur.setBorder(new EmptyBorder(0, 12, 0, 0));
		frame.getContentPane().add(pSur,BorderLayout.SOUTH);
		
		btnAniadirCocheAlaReserva = new JButton("AÑADIR COCHE AL CARRITO");
		
		ImageIcon icon=new ImageIcon("resources/imagenes/perfil.png");	
		Image imagen=icon.getImage();
		Image im2=imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon icon2=new ImageIcon("resources/imagenes/carrito.png");	
		Image imagen2=icon2.getImage();
		Image im3=imagen2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		viewProfile=new JButton(new ImageIcon(im2));
		viewProfile.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAniadirCocheAlaReserva.setFont(new Font("Tahoma", Font.BOLD, 15));
		simulador.setFont(new Font("Tahoma", Font.BOLD, 15));
		pSur.add(simulador);
		pSur.add(btnAniadirCocheAlaReserva);
		
		ImageIcon ic = new ImageIcon(im3);
		ic.setImage(ic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		soporte = new JLabel(ic);
		
		OverlayLayout overlayLayout = new OverlayLayout(soporte);
		soporte.setLayout(overlayLayout);
		soporte.add(contador);

		btnVerReservas = new JButton();
		btnVerReservas.add(soporte);
		btnVerReservas.setFont(new Font("Tahoma", Font.BOLD, 10));

		
		
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 15));
		pSur.add(btnVolver);
		
		pOeste = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pOeste.setBackground(new Color(40, 40, 40));
		frame.getContentPane().add(pOeste, BorderLayout.WEST);
		
		
				
		pCentro = new JPanel();
		pCentro.setBackground(new Color(40, 40, 40));
		areaCarrito = new JTextArea(20, 30);
		String texto = "CLIENTE: "+cliente.getNombre() +"\n"+"DNI:"+cliente.getDni()+"\n"+"F.N:"+cliente.getfNac()+"\n\n";
		texto = texto + "COCHES EN EL CARRITO: \n";		
		for(Coche co: Main.carrito) {
			texto = texto + co.toString() + "\n";
		}
		areaCarrito.setText(texto);
		pCentro.setBorder(new EmptyBorder(0, 240, 0, 0));
		pCentro.add(panelCoches);
		panelCoches.setPreferredSize(new Dimension(800, 700));
		frame.getContentPane().add(pCentro, BorderLayout.CENTER);
		
		pEste=new JPanel(new GridLayout(0,1));
		pEsteTop=new JPanel(new GridLayout(0,1));
		pEsteTop.setBackground(new Color(40, 40, 40));
		pEsteTop1=new JPanel(new GridLayout(0,1));
		pEsteTop1.setBackground(new Color(40, 40, 40));
		pEsteTop2=new JPanel();
		pEsteTop2.setForeground(new Color(0, 0, 0));
		pEsteTop2.setBackground(new Color(40, 40, 40));
		pEsteTop3=new JPanel();
		pEsteTop3.setForeground(new Color(0, 0, 0));
		pEsteTop3.setBackground(new Color(40, 40, 40));
		
		
		
		
		pViewProfile=new JPanel();
		pBtnVerReservas=new JPanel();
		pBtnVerReservas.setBorder(new EmptyBorder(0,250,0,0));
		pBtnVerReservas.setBackground(new Color(40, 40, 40));
		pViewProfile.setBorder(new EmptyBorder(0,250,0,0));
		pViewProfile.setBackground(new Color(40, 40, 40));
		
		pEste.setBackground(new Color(40, 40, 40));		
		frame.getContentPane().add(pEste, BorderLayout.EAST);
		pViewProfile.add(viewProfile);
		pBtnVerReservas.add(btnVerReservas);		
		pRelleno= new JPanel();
		pRelleno.setBackground(new Color(40,40,40));
		pEsteTop1.add(pViewProfile);
		pEsteTop1.add(pBtnVerReservas);
		pEsteTop1.add(pRelleno);
		pEsteTop.add(pEsteTop1);
		pEsteTop.add(pEsteTop2);
		pEsteTop.add(pEsteTop3);
		pEste.add(pEsteTop);
		
		
		pNorte = new JPanel();
		pNorte.setBackground(new Color(40, 40, 40));
		JLabel lblCantidadReservas = new JLabel("CONCESIONARIO DEUSTO" );
		lblCantidadReservas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCantidadReservas.setForeground(new Color(255, 165, 0));
		lblCantidadReservas.setBounds(369, 285, 332, 40);
		pNorte.add(lblCantidadReservas);
		frame.getContentPane().add(pNorte, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(265, 296, 94, 22);
		pOeste.add(comboBoxTipo);
		
		comboBoxTipo.addItem("Todos");
		
		for (Marca m : Marca.values()) {
			comboBoxTipo.addItem(m.getMarca());
		}
		
		ArrayList<Coche> cocheTipo = new ArrayList<Coche>();
		List<Coche>reservas=Main.carrito;
	
		List<Coche>coches1=new ArrayList<>(conc.getCoches());
		modeloListaCoches = new DefaultListModel<>();
		
		comboBoxTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				   String marcaSeleccionada = comboBoxTipo.getSelectedItem().toString();
			        if (marcaSeleccionada.equals("Todos")) {
			           coches=new ArrayList<>(conc.getCoches());
			           pCentro.removeAll();
			           pCentro.revalidate();
			           pCentro.repaint();
			           iniciarTabla();
			           cargarCoches();
			           JScrollPane panelCoches = new JScrollPane(tablaCoches);
			   		   panelCoches.setBorder(new TitledBorder("Coches disponibles"));
			   		   pCentro.add(panelCoches);
			   		   panelCoches.setPreferredSize(new Dimension(800, 650));
			   		   tablaCoches.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			   			   int filaAnt = -1;
			   			   @Override
			   			   public void valueChanged(ListSelectionEvent e) {
			   				   if (!e.getValueIsAdjusting()) {
			   					   if (!tablaCoches.getSelectionModel().isSelectionEmpty()) {
			   						   int filaSeleccionada = tablaCoches.getSelectedRow();
			   						   if (filaSeleccionada != -1&&filaSeleccionada!=filaAnt) {
			   							   Double precio=(Double)tablaCoches.getValueAt(filaSeleccionada,0);
			   							   Integer anyo=Integer.parseInt(tablaCoches.getValueAt(filaSeleccionada,1).toString());
			   							   String modelo=tablaCoches.getValueAt(filaSeleccionada,2).toString();
			   							   Marca marca=Marca.getPorID(tablaCoches.getValueAt(filaSeleccionada,3).toString());
			   							   String matricula=tablaCoches.getValueAt(filaSeleccionada,4).toString();
			   							   cocheSel=new Coche(precio,anyo,modelo,marca,matricula);
			   							   filaAnt=filaSeleccionada;
			   							   }
			   						   logger.info("Fila seleccionada: " + filaSeleccionada);
			   						   logger.info(cocheSel.toString());
			   						   }
			   					   }
			   				   }
			   			   });
			        } else {
			        	coches=new ArrayList<Coche>();
			            for (Coche c : coches1) {			            	
			                if (c.getMarca().equals(Marca.getPorID(marcaSeleccionada))) {
			                    coches.add(c);
			                    }
			            }
			            pCentro.removeAll();
			            pCentro.revalidate();
			            pCentro.repaint();
			            iniciarTabla();
			            cargarCoches();
			            JScrollPane panelCoches = new JScrollPane(tablaCoches);
			    		panelCoches.setBorder(new TitledBorder("Coches disponibles"));
			    		pCentro.add(panelCoches);
				   		panelCoches.setPreferredSize(new Dimension(800, 650));
				   		tablaCoches.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				   			   int filaAnt = -1;
				   			   @Override
				   			   public void valueChanged(ListSelectionEvent e) {
				   				   if (!e.getValueIsAdjusting()) {
				   					   if (!tablaCoches.getSelectionModel().isSelectionEmpty()) {
				   						   int filaSeleccionada = tablaCoches.getSelectedRow();
				   						   if (filaSeleccionada != -1&&filaSeleccionada!=filaAnt) {
				   							   Double precio=(Double)tablaCoches.getValueAt(filaSeleccionada,0);
				   							   Integer anyo=Integer.parseInt(tablaCoches.getValueAt(filaSeleccionada,1).toString());
				   							   String modelo=tablaCoches.getValueAt(filaSeleccionada,2).toString();
				   							   Marca marca=Marca.getPorID(tablaCoches.getValueAt(filaSeleccionada,3).toString());
				   							   String matricula=tablaCoches.getValueAt(filaSeleccionada,4).toString();
				   							   cocheSel=new Coche(precio,anyo,modelo,marca,matricula);
				   							   filaAnt=filaSeleccionada;
				   							   }
				   						   logger.info("Fila seleccionada: " + filaSeleccionada);
				   						   logger.info(cocheSel.toString());
				   						   }
				   					   }
				   				   }
				   			   });
			        }
			    }
				
		});
	
		btnVolver.addActionListener((e)->{
			frame.dispose();
			new VentanaInicio(conc);
			
		});

		simulador.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!ventanaAbierta) {
				VentanaCombinaciones vc=new VentanaCombinaciones(Concesionario.getCoches());
				logger.info("Nuevo simulador de compre en marcha.");
				vc.setAlwaysOnTop(true);
				vc.addWindowListener(new WindowListener(){					
					@Override
					public void windowOpened(WindowEvent e){
						ventanaAbierta=true;						
					}					
					@Override
					public void windowIconified(WindowEvent e){						
					}					
					@Override
					public void windowDeiconified(WindowEvent e){
					}					
					@Override
					public void windowDeactivated(WindowEvent e) {						
					}					
					@Override
					public void windowClosing(WindowEvent e) {						
					}					
					@Override
					public void windowClosed(WindowEvent e) {
						ventanaAbierta=false;						
					}					
					@Override
					public void windowActivated(WindowEvent e) {						
					}
				});
				}else {
					JOptionPane.showMessageDialog(null, "La ventana ya esta abierta","ERROR",JOptionPane.ERROR_MESSAGE);
					logger.warning("La ventana ya esta abierta");
				}
			}
		});
		
		btnAniadirCocheAlaReserva.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (cocheSel != null) {   
		            if (cocheYaEnCarrito(cocheSel)) {
		                JOptionPane.showMessageDialog(null, "Este coche ya está en el carrito", "Error", JOptionPane.ERROR_MESSAGE);
		                logger.warning("Este coche ya está en el carrito");
		            } else {
		                Main.carrito.add(cocheSel);
		                value = Main.carrito.size();
		                contador.setText("" + value);

		                String texto = "CLIENTE: " + cliente.getNombre() + "\n" + "DNI:" + cliente.getDni() + "\n" + "F.N:"
		                        + cliente.getfNac() + "\n\n";
		                texto = texto + "COCHES EN EL CARRITO: \n";		                
		                for (Coche co : Main.carrito) {
		                    texto = texto + co.toString() + "\n";
		                }
		                areaCarrito.setText(texto);
		            }
		        } else {
		            logger.info("Por favor, selecciona un coche antes de añadirlo al carrito.");
		        }
		    }
		});
		
		viewProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaProfile(cliente, conc);
				frame.dispose();
				
			}
		});
		
		btnVerReservas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new VentanaCarrito(conc, cliente, Main.carrito);
				} catch (IOException e1){					
					e1.printStackTrace();
					logger.warning("No se pudo iniciar la ventana carrito."+e.toString());
				}
				frame.dispose();
				
			}
		});
		
		tablaCoches.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			int filaAnt = -1;
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		    	if (!e.getValueIsAdjusting()) {
		    		if (!tablaCoches.getSelectionModel().isSelectionEmpty()) {
		    		int filaSeleccionada = tablaCoches.getSelectedRow();
		            if (filaSeleccionada != -1&&filaSeleccionada!=filaAnt) {
		                
		                Double precio=(Double)tablaCoches.getValueAt(filaSeleccionada,0);
		                Integer anyo=Integer.parseInt(tablaCoches.getValueAt(filaSeleccionada,1).toString());
		                String modelo=tablaCoches.getValueAt(filaSeleccionada,2).toString();
		                Marca marca=Marca.valueOf(tablaCoches.getValueAt(filaSeleccionada,3).toString());
		                String matricula=tablaCoches.getValueAt(filaSeleccionada,4).toString();
		                cocheSel=new Coche(precio,anyo,modelo,marca,matricula);
		                filaAnt=filaSeleccionada;
		                }
		            logger.info("Fila seleccionada: " + filaSeleccionada);
		            logger.info(cocheSel.toString());
		            }
		    		}
		    	}
		});
	
		this.tablaCoches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {			
				mouseRowPersonajes = -1;
			}
		});
		
		this.tablaCoches.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseRowPersonajes = tablaCoches.rowAtPoint(e.getPoint());
				tablaCoches.repaint();
			}			
		});
	}

	private void iniciarTabla() {

		Vector<String> cabeceraCoches = new Vector<String>(Arrays.asList( "Precio", "Año","Modelo", "Marca", "Matrícula"));
		this.modeloDatosCoches = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraCoches);
		this.tablaCoches = new JTable(this.modeloDatosCoches);
		this.tablaCoches.setRowHeight(60);
		this.tablaCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			if(value instanceof Marca) {
				ImageDisplayer id = new ImageDisplayer ();
				
				Marca m = (Marca) value;
				
				switch (m) { 
					case SEA:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/SEAT_Logo_from_2017.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case BMW:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/bmw_logo_PNG19712.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case OPE:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Opel-Logo_2017.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case TOY:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/toyota.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case FOR:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Ford-Motor-Company-Logo.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case HON:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Logo_Honda_F1.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case VOL:
					try {
						id.setImage (ImageIO.read (new File ("resources/imagenes/Volkswagen_Logo_till_1995.svg.png")));
					} catch (IOException e) {
						e.printStackTrace();
					}
						break;
					default:
				}
				
				return id;
			}
			
			JLabel resultado = new JLabel(value.toString());
			
			if (table.equals(tablaCoches)) {
				if (row % 2 == 0) {
					resultado.setBackground(new Color(250, 249, 249));
				} else {
					resultado.setBackground(new Color(255, 250, 240));
				}
			}	
			
			if (isSelected || (table.equals(tablaCoches) && row == mouseRowPersonajes)) {
				resultado.setBackground(table.getSelectionBackground());
				resultado.setForeground(table.getSelectionForeground());			
			}		
			
			resultado.setOpaque(true);
			return resultado;
		};
		
		this.tablaCoches.setDefaultRenderer(Object.class, cellRenderer);
		this.tablaCoches.setRowHeight(100);	
		}
	
	private void cargarCoches() {
		
		this.modeloDatosCoches.setRowCount(0);
		this.coches.forEach(e -> this.modeloDatosCoches.addRow(
				new Object[] {e.getPrecio(), e.getAnyo(), e.getModelo(), e.getMarca(), e.getMatricula()} )
		);
		
	}
	
	private boolean cocheYaEnCarrito(Coche coche) {
	    for (Coche c : Main.carrito) {
	        if (c.equals(coche)) {
	            return true;
	        }
	    }
	    return false;
	}

}