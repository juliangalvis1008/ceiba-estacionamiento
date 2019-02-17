package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones;

public class Excepcion extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public Excepcion(Exception e) {
		super(e.getMessage());
		}
}