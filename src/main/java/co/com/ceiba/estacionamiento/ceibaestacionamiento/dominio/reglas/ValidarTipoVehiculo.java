package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.MensajeExcepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;

public class ValidarTipoVehiculo implements ReglasNegocio {

	@Override
	public MensajeExcepcion ejecutarRegla(Vehiculo vehiculo) {
		
		if(!(vehiculo.getTipoVehiculo().equalsIgnoreCase(Constantes.TIPO_VEHICULO_CARRO) || 
				vehiculo.getTipoVehiculo().equalsIgnoreCase(Constantes.TIPO_VEHICULO_MOTO))) {
			
			return new MensajeExcepcion(Constantes.MENSAJE_TIPO_VEHICULO_INVALIDO,false);
		}
		
		return new MensajeExcepcion("",true);
	}

	
	
	
}
