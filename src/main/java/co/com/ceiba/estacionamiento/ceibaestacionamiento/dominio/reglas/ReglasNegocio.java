package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.MensajeExcepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;

public interface ReglasNegocio {
	public MensajeExcepcion ejecutarRegla(Vehiculo vehiculo);
}
