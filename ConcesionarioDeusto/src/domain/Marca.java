package domain;
import java.io.Serializable;
public enum Marca implements Serializable, Comparable<Marca>{
	
	ALF("Alfa Romeo"),
	AUD("Audi"), 
	BEN("Bentley"),
	BMW("Bmw"), 
	BUG("Bugatti"),
	CIT("Citroen"),
	DAC("Dacia"),
	FER("Ferrari"),
	FIA("Fiat"),
	FOR("Ford"),
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
	MER("Mercedes-Benz"), 
	MIN("Mini"),
	MIT("Mitsubishi"),
	MUS("Mustang"),
	NIS("Nissan"),
	OPE("Opel"),
	PEU("Peugeot"),
	POR("Porsche"),
	REN("Renault"),
	SEA("Seat"),
	SKO("Skoda"),
	SMA("Smart"),
	SUB("Subaru"),
	SUZ("Suzuki"),
	TES("Tesla"),
	TOY("Toyota"),
	VOL("Volkswagen");

	private String nombre;
	
	Marca(String nombre) {
		this.nombre = nombre;
	}
	
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
