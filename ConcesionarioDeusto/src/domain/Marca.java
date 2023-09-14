package domain;
import java.io.Serializable;
public enum Marca implements Serializable{
	FER("Ferrari"),
	BMW("BMW"), 
	AU("Audi"), 
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
	private String nombre;
	
	Marca(String nombre) {
		this.nombre = nombre;
	}
	
	public String getMarca() {
		return this.nombre;
	}
	
	
}
