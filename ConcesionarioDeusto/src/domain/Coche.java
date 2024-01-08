package domain;

public class Coche implements Comparable<Coche>{
	
	private double precio;
	private int anyo;
	private String modelo;
	private Marca marca;
	private String matricula;
	
	public Coche(double precio, int anyo, String modelo, Marca marca, String matricula) {
		super();
		this.precio = precio;
		this.anyo = anyo;
		this.modelo = modelo;
		this.marca = marca;
		this.matricula = matricula;
	}
	
	public Coche(double precio, int anyo, String modelo, Marca marca) {
		super();
		this.precio = precio;
		this.anyo = anyo;
		this.modelo = modelo;
		this.marca = marca;
		this.matricula = generarMatricula(4, 3);
	}
	
	public Coche() {
		super();
		this.precio = 0.0;
		this.anyo = 0;
		this.modelo = "";
		this.marca = null;
		this.matricula = "";
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@Override
	public String toString() {
		return "Coche [precio=" + precio + ", anyo=" + anyo + ", modelo=" + modelo + ", marca=" + marca + ", matricula="
				+ matricula + "]";
	}
	public static String generarMatricula(int numNumeros, int numLetras) {
        String numeros = "0123456789";
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        return generarMatriculaRecursiva(numeros, letras, numNumeros, numLetras, "");
    }

    public static String generarMatriculaRecursiva(String numeros, String letras, int numNumeros, int numLetras, String c) {
        if (numNumeros == 0 && numLetras == 0) {
            return c;
        } else if (numNumeros > 0) {
            int index = (int) (numeros.length() * Math.random());
            String nuevoc = c + numeros.charAt(index);
            return generarMatriculaRecursiva(numeros, letras, numNumeros - 1, numLetras, nuevoc);
        } else {
            int index = (int) (letras.length() * Math.random());
            String nuevoc = c + letras.charAt(index);
            return generarMatriculaRecursiva(numeros, letras, numNumeros, numLetras - 1, nuevoc);
        }
    }

	public void mostrarInformacion() {
        System.out.println("Precio: " + precio);
        System.out.println("Año: " + anyo);
        System.out.println("Modelo: " + modelo);
        System.out.println("Marca: " + marca);
        System.out.println("Matricula: " + matricula);
    }

	@Override
	public int compareTo(Coche o) {
		return this.marca.compareTo(o.marca); 
	}
	
	
	
}
