package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import java.time.DayOfWeek;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;

public class ValidarPlaca implements ReglasNegocio {

	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {
		
		if(vehiculo.getPlaca().toUpperCase().startsWith(Constantes.LETRA_INICIO_PLACA) &&
				!(vehiculo.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.SUNDAY)) &&
				!(vehiculo.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.MONDAY))) {
			
			throw new Excepcion(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA);
		}
		
		
	}

	
}
