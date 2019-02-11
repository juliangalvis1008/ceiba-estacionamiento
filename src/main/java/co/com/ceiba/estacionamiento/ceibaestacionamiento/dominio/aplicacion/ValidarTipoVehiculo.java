package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;

public class ValidarTipoVehiculo implements ReglasNegocio {

	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {
		
		if(!(vehiculo.getTipoVehiculo().equalsIgnoreCase(Vigilante.TIPO_VEHICULO_CARRO) || 
				vehiculo.getTipoVehiculo().equalsIgnoreCase(Vigilante.TIPO_VEHICULO_MOTO))) {
			
			throw new Excepcion(Vigilante.MENSAJE_TIPO_VEHICULO_INVALIDO);
		}
	}

	
	
	
}
