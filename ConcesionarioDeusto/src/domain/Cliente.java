package domain;

public class Cliente {
    
	/**
	 * 
	 */
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    
   /**
    * 
    * @param id
    * @param nombre
    * @param apellido
    * @param direccion
    * @param telefono
    */
    public Cliente(int id, String nombre, String apellido, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    /**
     * 
     */
    public Cliente() {
        this.id = 0;
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.telefono = "";
    }

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * 
     * @return
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
     * @return
     */
    public String getApellido() {
        return apellido;
    }
    
    /**
     * 
     * @param apellido
     */

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    /**
     * 
     * @return
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * 
     * @param direccion
     */

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * 
     * @return
     */
    public String getTelefono() {
        return telefono;
    }
    
    /**
     * 
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * 
     */
    @Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
				+ ", telefono=" + telefono + "]";
	}
    /**
     * 
     */
	public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Direccion: " + direccion);
        System.out.println("Telefono: " + telefono);
    }
	/**
	 * 
	 * @param args
 */
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente(1, "Juan", "Perez", "Parque de los Lirios", "678458377");
        cliente1.mostrarInformacion();
    }
}
