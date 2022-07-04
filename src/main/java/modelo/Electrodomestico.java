package modelo;

public class Electrodomestico {
	
	private int id;
	private String nombre;
	private String falla;
	private Cliente cliente;
	
	public Electrodomestico() {
		
	}
	
	public Electrodomestico(String nombre, String falla, Cliente cliente) {
		this.nombre 	= nombre;
		this.falla 		= falla;
		this.cliente    = cliente;
	}
	
	public Electrodomestico(int id, String nombre, String falla, Cliente cliente) {
		this.id 		= id;
		this.nombre 	= nombre;
		this.falla 		= falla;
		this.cliente    = cliente;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFalla() {
		return falla;
	}

	public void setFalla(String falla) {
		this.falla = falla;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setTelefono(String telefonoCliente) {
			
	}

	public void setDireccion(String direccionCliente) {
			
	}

}
