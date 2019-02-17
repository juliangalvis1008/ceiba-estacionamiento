package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones;


public class MensajeExcepcion {
	private String mensaje;
	private boolean estado;
	
	public MensajeExcepcion() {}

	public MensajeExcepcion(String mensaje, boolean estado) {
		this.mensaje = mensaje;
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}

	
