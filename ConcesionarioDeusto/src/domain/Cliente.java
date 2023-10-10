package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cliente implements Comparable<Cliente>{
    
	/**
	 * 
	 */
   
	private String dni;
	private String nombre;
	private Date fNac;
	private String contrasenia;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	

	public Cliente() {
		super();
	}
	public Cliente(String dni, String nombre, Date fNac, String c) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.fNac = fNac;
		this.contrasenia = c;
	}
   

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
	
	
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getfNac() {
		return fNac;
	}
	
	public String getfNacStr() {
		return sdf.format(fNac);
	}
	
	public void setfNac(Date fNac) {
		this.fNac = fNac;
	}
	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", fNac=" + getfNacStr() + "]";
	}
	public void mostrarInformacion() {
		 System.out.println("Dni: " + dni);
        System.out.println("fnac: " + fNac);
        System.out.println("contrasena: " + contrasenia);
           }
	@Override
	public int compareTo(Cliente o) {
		return  this.dni.compareTo(o.dni);
	}
	
    
}
