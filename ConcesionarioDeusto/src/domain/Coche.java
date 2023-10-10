package domain;

public class Coche implements Comparable<Coche>{
	/**
	 * 
	 */
	private double precio;
	private int anyo;
	private String modelo;
	private String marca;
	private String matricula;
	
	/**
	 * 
	 * @param precio
	 * @param anyo
	 * @param modelo
	 * @param marca
	 * @param matricula
	 */
	
	public Coche(double precio, int anyo, String modelo, String marca, String matricula) {
		super();
		this.precio = precio;
		this.anyo = anyo;
		this.modelo = modelo;
		this.marca = marca;
		this.matricula = matricula;
	}
	
	/**
	 * 
	 */
	
	public Coche() {
		super();
		this.precio = 0.0;
		this.anyo = 0;
		this.modelo = "";
		this.marca = "";
		this.matricula = "";
	}
	/**
	 * 
	 * @return
 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * 
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	/**
	 * 
	 * @return
	 */
	public int getAnyo() {
		return anyo;
	}
	/**
	 * 
	 * @param anyo
	 */
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	/**
	 * 
	 * @return
	 */
	public String getModelo() {
		return modelo;
	}	
	/**
	 * @param modelo
	 */

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	/**
	 * 
	 * @return
	 */

	public String getMarca() {
		return marca;
	}
	/**
	 * 
	 * @param marca
	 */

	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * 
	 * @return
	 */
	public String getMatricula() {
		return matricula;
	}
	/**
	 * 
	 * @param matricula
	 */

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Coche [precio=" + precio + ", anyo=" + anyo + ", modelo=" + modelo + ", marca=" + marca + ", matricula="
				+ matricula + "]";
	}
	/**
	 * 
	 */
	public void mostrarInformacion() {
        System.out.println("Precio: " + precio);
        System.out.println("Año: " + anyo);
        System.out.println("Modelo: " + modelo);
        System.out.println("Marca: " + marca);
        System.out.println("Matricula: " + matricula);
    }

	@Override
	public int compareTo(Coche o) {
		// TODO Auto-generated method stub
		return this.marca.compareTo(o.marca); //Ordenar ascendentemente por año
	}
	
	
	
}
