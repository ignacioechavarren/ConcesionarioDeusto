package domain;
import java.io.Serializable;
public enum Marca implements Serializable, Comparable<Marca>{
	
	FER("Ferrari"),
	BMW("Bmw"), 
	AUD("Audi"), 
	REN("Renault"),
	MER("Mercedes-Benz"), 
	BUG("Bugatti"),
	BEN("Bentley"),
	ALF("Alfa Romeo"),
	MUS("Mustang"),
	CIT("Citroen"),
	DAC("Dacia"),
	FIA("Fiat"),
	FOR("Ford"),
	SEA("Seat"),
	HON("Honda"),
	HYU("Hyundai"),
	JAG("Jaguar"),
	JEE("Jeep"),
	KIA("Kia"),
	LAM("Lamborghini"),
	LAN("Land Rover"),
	LEX("Lexus"),
	MAS("Maserati"),
	MAZ("Mazda"),
	MIN("Mini"),
	MIT("Mitsubishi"),
	NIS("Nissan"),
	OPE("Opel"),
	PEU("Peugeot"),
	POR("Porsche"),
	SKO("Skoda"),
	SMA("Smart"),
	SUB("Subaru"),
	SUZ("Suzuki"),
	TES("Tesla"),
	TOY("Toyota"),
	VOL("Volkswagen");
	//Acabado
	
	/**
	 * 
	 */
	private String nombre;
	
	/**
	 * 
	 * @param nombre
	 */
	Marca(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMarca() {
		return this.nombre;
	}
	
	 public static Marca getPorID(String texto) {
		 Marca mar = null;
	        for (Marca marca : Marca.values()) {
	            if (marca.getMarca().equalsIgnoreCase(texto)) {
	                mar=marca;
	            }
	            
	        }
		return mar;
		}
	
	 public static String getIdFromValue(Marca marca) {
	        return marca.getMarca();
	    }
}
