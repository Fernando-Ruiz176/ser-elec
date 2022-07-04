package modelo;

import java.time.LocalDate;

public class OrdenDeTrabajo {
	
	private int id;
	private String estado;
	private Electrodomestico electrodomestico;
	private LocalDate fechaSolicitud;
	private LocalDate fechaEntrega;
	
	public OrdenDeTrabajo() {
		
	}

	public OrdenDeTrabajo(String estado, LocalDate fechaSolicitud, LocalDate fechaEntrega, Electrodomestico electrodomestico) {
		this.estado					 = estado;
		this.fechaSolicitud 		 = fechaSolicitud;
		this.fechaEntrega 			 = fechaEntrega;
		this.electrodomestico    	 = electrodomestico;
	}

	public OrdenDeTrabajo(int id, String estado, LocalDate fechaSolicitud, LocalDate fechaEntrega, Electrodomestico electrodomestico) {
		this.id 					 = id;
		this.estado					 = estado;
		this.fechaSolicitud 		 = fechaSolicitud;
		this.fechaEntrega 			 = fechaEntrega;
		this.electrodomestico   	 = electrodomestico;
	}
	
	public OrdenDeTrabajo(int id, String estado) {
		this.id 					  = id;
		this.estado 				  = estado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Electrodomestico getElectrodomestico() {
		return electrodomestico;
	}

	public void setElectrodomestico(Electrodomestico electrodomestico) {
		this.electrodomestico = electrodomestico;
	}

	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


}
