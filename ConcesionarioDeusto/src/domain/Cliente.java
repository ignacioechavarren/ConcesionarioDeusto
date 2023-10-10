package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente implements Comparable<Cliente>{
    
	/**
	 * atributos 
	 */
   
	private String dni;
	private String nombre;
	private Date fNac;
	private String contrasenia;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	
	/**
	 * constructor vacio cliente
	 */
	public Cliente() {
		super();
	}
	
	/**
	 * 
	 * @param dni
	 * @param nombre
	 * @param fNac
	 * @param c
	 */
	public Cliente(String dni, String nombre, Date fNac, String c) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fNac = fNac;
		this.contrasenia = c;
	}
   
	/**
	 * 
	 * @param dni
	 * @param nombre
	 * @param fNac
	 * @param c
	 */
    public Cliente(String dni, String nombre, String fNac, String c) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		try {
			this.fNac = sdf.parse(fNac);
		} catch (ParseException e) {
			this.fNac = new Date(0);
		}
		this.contrasenia = c;
	}
	
	/**
	 * 
	 * @return devolver contrasenia
	 */
	public String getContrasenia() {
		return contrasenia;
	}
	/**
	 * 
	 * @param contrasenia
	 */
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	/**
	 * 
	 * @return devolver dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * 
	 * @param dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * 
	 * @return devolver nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 * @return devolver fecha nacimiento
	 */
	public Date getfNac() {
		return fNac;
	}
	/**
	 * 
	 * @return
	 */
	public String getfNacStr() {
		return sdf.format(fNac);
	}
	/**
	 * 
	 * @param fNac
	 */
	public void setfNac(Date fNac) {
		this.fNac = fNac;
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", fNac=" + getfNacStr() + "]";
	}
	
	/**
	 * 
	 */
	public void mostrarInformacion() {
		 System.out.println("Dni: " + dni);
        System.out.println("fnac: " + fNac);
        System.out.println("contrasena: " + contrasenia);
           }
	
	/**
	 * 
	 */
	@Override
	public int compareTo(Cliente o) {
		return  this.dni.compareTo(o.dni);
	}
	
    
}
