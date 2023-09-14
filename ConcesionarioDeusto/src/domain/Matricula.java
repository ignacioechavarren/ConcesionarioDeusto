package domain;

public class Matricula {
	
	private String letras;
	private int numeros;
	private String pais;
	
	public Matricula(String letras, int numeros, String pais) {
		super();
		this.letras = letras;
		this.numeros = numeros;
		this.pais = pais;
	}
	
	public Matricula() {
		super();
		this.letras = "";
		this.numeros = 0;
		this.pais = "";
	}

	public String getLetras() {
		return letras;
	}

	public void setLetras(String letras) {
		this.letras = letras;
	}

	public int getNumeros() {
		return numeros;
	}

	public void setNumeros(int numeros) {
		this.numeros = numeros;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Matricula [letras=" + letras + ", numeros=" + numeros + ", pais=" + pais + "]";
	}
	
	public void mostrarInformacion() {
        System.out.println("Letras: " + letras);
        System.out.println("Números: " + numeros);
        System.out.println("País: " + pais);
    }
	
	public static void main(String[] args) {
        Matricula matricula1 = new Matricula("GHY", 1753, "España");
        matricula1.mostrarInformacion();
    }
}
