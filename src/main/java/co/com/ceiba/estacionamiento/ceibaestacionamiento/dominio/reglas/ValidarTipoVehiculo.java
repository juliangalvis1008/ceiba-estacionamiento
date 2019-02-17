package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;

public class ValidarTipoVehiculo implements ReglasNegocio {

	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {
		
		if(!(vehiculo.getTipoVehiculo().equalsIgnoreCase(Constantes.TIPO_VEHICULO_CARRO) || 
				vehiculo.getTipoVehiculo().equalsIgnoreCase(Constantes.TIPO_VEHICULO_MOTO))) {
			
			throw new Excepcion(Constantes.MENSAJE_TIPO_VEHICULO_INVALIDO);
		}
	}

	
	
	
}
