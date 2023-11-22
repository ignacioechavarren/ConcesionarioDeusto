package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.imageio.ImageIO;
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
	private JPanel pSur;
	private JPanel pOeste;
	private JPanel pEste,pViewProfile,pAreaCarrito,pEsteTop,pEsteTop1,pEsteTop2,pBtnVerReservas;
	private JPanel pCentro;
	private JPanel pNorte;
	private JButton btnVolver;
	private JButton btnAniadirCocheAlaReserva;
	private JButton btnVerReservas;
	private JButton btnFinalizarReservas, viewProfile,  btnConfirmarCambios;;
	private JTextArea areaCarrito;
	private DefaultListModel<Coche> modeloListaCoches; //El modelo guarda la información, los artículos
	private JList<Coche> listaCoches; //La JList presenta/visualiza esos artículos
	private JLabel lblBusqueda;
	private JTextField txtBusqueda;
	private DefaultTableModel modeloDatosCoches;
	private List<Coche> coches;
	private JTable tablaCoches;
	private Coche cocheSel;
	private int mouseRowPersonajes = -1;

	
	public VentanaConcesionario(Concesionario conc, Cliente cliente) {
		
		super();
		getContentPane().setBackground(new Color(0, 128, 255));
		setResizable(false);
		this.coches = new ArrayList<Coche>(conc.getCoches());
		this.iniciarTabla();
		this.cargarCoches();
				
		JScrollPane panelCoches = new JScrollPane(this.tablaCoches);
		panelCoches.setBorder(new TitledBorder("Coches disponibles"));
		
		setBounds(300, 200, 800, 400);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		pSur = new JPanel();
		pSur.setBackground(new Color(0, 128, 255));
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		btnAniadirCocheAlaReserva = new JButton("AÑADIR COCHE A LA RESERVA");
		
		ImageIcon icon=new ImageIcon("imagenes/perfil.png");	
		Image imagen=icon.getImage();
		Image im2=imagen.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon icon2=new ImageIcon("imagenes/carrito.png");	
		Image imagen2=icon2.getImage();
		Image im3=imagen2.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		
		viewProfile=new JButton(new ImageIcon(im2));
		viewProfile.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAniadirCocheAlaReserva.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnAniadirCocheAlaReserva);
		btnVerReservas = new JButton(new ImageIcon(im3));
		btnVerReservas.setFont(new Font("Tahoma", Font.BOLD, 10));		
		btnFinalizarReservas = new JButton("FINALIZAR RESERVA");
		btnFinalizarReservas.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnFinalizarReservas);
		btnVolver = new JButton("VOLVER");
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 10));
		pSur.add(btnVolver);
		
		pOeste = new JPanel();
		pOeste.setBackground(new Color(0, 128, 255));
		getContentPane().add(pOeste, BorderLayout.WEST);
		
		
				
		pCentro = new JPanel();
		pCentro.setBackground(new Color(0, 128, 255));
		areaCarrito = new JTextArea(20, 30);
		pCentro.add(panelCoches);
		panelCoches.setPreferredSize(new Dimension(800, 700));
		getContentPane().add(pCentro, BorderLayout.CENTER);
		
		pEste=new JPanel(new GridLayout(0,1));
		pEsteTop=new JPanel(new GridLayout(0,1));
		pEsteTop1=new JPanel(new GridLayout(0,1));
		pEsteTop2=new JPanel();
		pEsteTop2.setBackground(new Color(0, 128, 255));
		
		
		
		pViewProfile=new JPanel();
		pBtnVerReservas=new JPanel();
		pBtnVerReservas.setBorder(new EmptyBorder(0,250,0,0));
		pBtnVerReservas.setBackground(new Color(0, 128, 255));
		pViewProfile.setBorder(new EmptyBorder(0,250,0,0));
		pViewProfile.setBackground(new Color(0, 128, 255));
		pAreaCarrito=new JPanel();
		pAreaCarrito.setBackground(new Color(0, 128, 255));
		pEste.setBackground(new Color(0, 128, 255));		
		getContentPane().add(pEste, BorderLayout.EAST);
		pViewProfile.add(viewProfile);
		pBtnVerReservas.add(btnVerReservas);
		pAreaCarrito.add(areaCarrito);
		pEsteTop1.add(pViewProfile);
		pEsteTop1.add(pBtnVerReservas);
		pEsteTop.add(pEsteTop1);
		pEsteTop.add(pEsteTop2);		
		pEste.add(pEsteTop);
		pEste.add(pAreaCarrito);
		
		pNorte = new JPanel();
		pNorte.setBackground(new Color(0, 128, 255));
		JLabel lblCantidadReservas = new JLabel("AÑADIDOS A LA RESERVA: " );
		lblCantidadReservas.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblCantidadReservas.setForeground(new Color(255, 255, 255));
		lblCantidadReservas.setBounds(369, 285, 332, 40);
		pNorte.add(lblCantidadReservas);
		getContentPane().add(pNorte, BorderLayout.NORTH);
		
		JComboBox<String> comboBoxTipo = new JComboBox<String>();
		comboBoxTipo.setBounds(265, 296, 94, 22);
		pOeste.add(comboBoxTipo);
		
		comboBoxTipo.addItem("Todos");
		
		for (Marca m : Marca.values()) {
			comboBoxTipo.addItem(m.getMarca());
		}
		
		ArrayList<Coche> cocheTipo = new ArrayList<Coche>();
		List<Coche>reservas=new ArrayList<Coche>();
		
		
		List<Coche>coches1=new ArrayList<>(conc.getCoches());
		modeloListaCoches = new DefaultListModel<>();
		

		
		
		comboBoxTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				   String marcaSeleccionada = comboBoxTipo.getSelectedItem().toString();
			        // Limpia el modelo de la lista de coches

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
			   							   cocheSel=new Coche(precio,anyo,marca,modelo,matricula);
			   							   filaAnt=filaSeleccionada;
			   							   }
			   						   System.out.println("Fila seleccionada: " + filaSeleccionada);
			   						   System.out.println(cocheSel);
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
				   							   cocheSel=new Coche(precio,anyo,marca,modelo,matricula);
				   							   filaAnt=filaSeleccionada;
				   							   }
				   						   System.out.println("Fila seleccionada: " + filaSeleccionada);
				   						   System.out.println(cocheSel);
				   						   }
				   					   }
				   				   }
				   			   });
			        }
			    }
				
		});
	
		btnVolver.addActionListener((e)->{
			dispose();
			new VentanaInicio(conc);
			
		});
		
		btnConfirmarCambios = new JButton("Confirmar Cambios");
		btnConfirmarCambios.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        confirmarCambios();
		    }

			
				private void confirmarCambios() {
				    String nuevoContenido = areaCarrito.getText();

				    // Aplicar los cambios al objeto cliente o a tu estructura de datos
				    cliente.setNombre(nuevoContenido);

				  
				    // Guardar los cambios en un archivo CSV
				    try (Scanner sc  = new Scanner((Readable) new FileWriter("datos_clientes.csv"))) {
				        // Aquí defines cómo deseas estructurar la información en el archivo CSV
				        // En este ejemplo, se guarda el contenido del área de carrito en una sola columna
				        String[] datos = {cliente.getNombre(), cliente.getDni(), nuevoContenido};
				        

				        System.out.println("Cambios confirmados y guardados en datos_clientes.csv");
				    } catch (IOException e) {
				        e.printStackTrace();
				        // Manejar la excepción adecuadamente según tus necesidades
				    }
				
			}
		});
		
		pEsteTop2.add(btnConfirmarCambios);

		
		List<Coche>cochesCarrito=new ArrayList<Coche>();
		btnAniadirCocheAlaReserva.addActionListener(new ActionListener() {
			
			
			   @Override
               public void actionPerformed(ActionEvent e) {
				        if (cocheSel != null) {		
				        	cochesCarrito.add(cocheSel);
				            Main.carrito.add(cocheSel);
				            lblCantidadReservas.setText("AÑADIDOS AL PEDIDO: " + Main.carrito.size());
				            
				        } else {
				            System.out.println("Por favor, selecciona un coche antes de añadirlo a la reserva.");
				        }
				        
				        String texto = "CLIENTE: "+cliente.getNombre() +"\n"+"DNI:"+cliente.getDni()+"\n"+"F.N:"+cliente.getfNac()+"\n\n";
						texto = texto + "COCHES EN EL CARRITO: \n";
						//Recorremos el carrito para añadir los articulos al texto
						for(Coche co: cochesCarrito) {
							texto = texto + co.toString() + "\n";
						}
						areaCarrito.setText(texto);
				    }
		});
		viewProfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaProfile(cliente, conc);
				dispose();
				
			}
		});
		
		btnVerReservas.addActionListener((e)->{
			new VentanaCarrito(conc,cliente, reservas);
			dispose();
		});
		
		btnFinalizarReservas.addActionListener((e)->{
			for (Coche c : reservas) {
				Concesionario.aniadirReserva(cliente, c);
			}
			JOptionPane.showMessageDialog(null, "Enhorabuena, ha realizado la reserva correctamente","RESERVA FINALIZADA",JOptionPane.INFORMATION_MESSAGE);
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
		                cocheSel=new Coche(precio,anyo,marca,modelo,matricula);
		                filaAnt=filaSeleccionada;
		                }
		            System.out.println("Fila seleccionada: " + filaSeleccionada);
		            System.out.println(cocheSel);
		            }
		    		}
		    	}
		});
	
		this.tablaCoches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				//Se resetea la fila/columna sobre la que está el ratón				
				mouseRowPersonajes = -1;
			}
		});
		
		this.tablaCoches.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				//Se actualiza la fila/columna sobre la que está el ratón
				mouseRowPersonajes = tablaCoches.rowAtPoint(e.getPoint());
				
				//Se provoca el redibujado de la tabla
				tablaCoches.repaint();
			}			
		});
	}

	private void iniciarTabla() {

		Vector<String> cabeceraCoches = new Vector<String>(Arrays.asList( "Precio", "Año", "Marca", "Modelo", "Matrícula"));
		this.modeloDatosCoches = new DefaultTableModel(new Vector<Vector<Object>>(), cabeceraCoches);
		this.tablaCoches = new JTable(this.modeloDatosCoches);
//		for (TableRow c : Collections.list (this.tablaCoches.getRowModel().getRows()))
//			c.setHeight(100);
		this.tablaCoches.setRowHeight(60);
		this.tablaCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableCellRenderer cellRenderer = (table, value, isSelected, hasFocus, row, column) -> {
			if(value instanceof Marca) {
				ImageDisplayer id = new ImageDisplayer ();
				
				Marca m = (Marca) value;
				
				switch (m) { 
					case SEA:
					try {
						id.setImage (ImageIO.read (new File ("imagenes/SEAT_Logo_from_2017.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case BMW:
					try {
						id.setImage (ImageIO.read (new File ("imagenes/bmw_logo_PNG19712.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case OPE:
					try {
						id.setImage (ImageIO.read (new File ("imagenes/Opel-Logo_2017.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case TOY:
					try {
						id.setImage (ImageIO.read (new File ("imagenes/toyota.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case FOR:
					try {
						id.setImage (ImageIO.read (new File ("imagenes/Ford-Motor-Company-Logo.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case HON:
					try {
						id.setImage (ImageIO.read (new File ("imagenes/Logo_Honda_F1.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						break;
					case VOL:
					try {
						id.setImage (ImageIO.read (new File ("imagenes/Volkswagen_Logo_till_1995.svg.png")));
					} catch (IOException e) {
						// TODO Auto-generated catch block
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

}
