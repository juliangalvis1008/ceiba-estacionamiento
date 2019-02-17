package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;

public interface ReglasNegocio {
	public void ejecutarRegla(Vehiculo vehiculo);
}
