package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.MensajeExcepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.EntradaVehiculoService;

public class ValidarVehiculoYaEnParqueadero implements ReglasNegocio {
	
	@Autowired
	EntradaVehiculoService entradaVehiculoService;
	
	public ValidarVehiculoYaEnParqueadero(EntradaVehiculoService entradaVehiculoService) {
		this.entradaVehiculoService = entradaVehiculoService;
	}

	@Override
	public MensajeExcepcion ejecutarRegla(Vehiculo vehiculo) {
		
		if(entradaVehiculoService.vehiculoYaExiste(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO)) {
			return new MensajeExcepcion(Constantes.VEHICULO_YA_EXISTE_EN_PARQUEADERO,false);
		}	
		
		return new MensajeExcepcion("",true);
	}

	
	
	
	
}
