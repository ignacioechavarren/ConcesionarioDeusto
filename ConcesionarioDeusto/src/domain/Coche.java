package domain;

public class Coche {

	private double precio;
	private int anyo;
	private String modelo;
	private String marca;
	private Matricula matricula;
	
	public Coche(double precio, int año, String modelo, String marca, Matricula matricula) {
		super();
		this.precio = precio;
		this.anyo = año;
		this.modelo = modelo;
		this.marca = marca;
		this.matricula = matricula;
	}
	
	public Coche() {
		super();
		this.precio = 0.0;
		this.anyo = 0;
		this.modelo = "";
		this.marca = "";
		this.matricula = new Matricula();
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Matricula getMatricula() {
		return matricula;
	}

	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Coche [precio=" + precio + ", anyo=" + anyo + ", modelo=" + modelo + ", marca=" + marca + ", matricula="
				+ matricula + "]";
	}
	
	public void mostrarInformacion() {
        System.out.println("Precio: " + precio);
        System.out.println("Año: " + anyo);
        System.out.println("Modelo: " + modelo);
        System.out.println("Marca: " + marca);
        System.out.println("Matricula: " + matricula);
    }
	
	public static void main(String[] args) {
        Coche coche1 = new Coche(1, 2000, "R8", "Audi", new Matricula());
        coche1.mostrarInformacion();
    }
	
}
