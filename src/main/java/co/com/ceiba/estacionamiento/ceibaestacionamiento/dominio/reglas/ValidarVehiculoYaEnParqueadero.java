package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.EntradaVehiculoRepositorio;

public class ValidarVehiculoYaEnParqueadero implements ReglasNegocio {
	
	@Autowired
	EntradaVehiculoRepositorio entradaVehiculoRepositorio;
	
	public ValidarVehiculoYaEnParqueadero(EntradaVehiculoRepositorio entradaVehiculoRepositorio) {
		this.entradaVehiculoRepositorio = entradaVehiculoRepositorio;
	}

	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {
		
		if(entradaVehiculoRepositorio.vehiculoYaExiste(vehiculo.getPlaca(), vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO)) {
			throw new Excepcion(Constantes.VEHICULO_YA_EXISTE_EN_PARQUEADERO);
		}	
	}

	
	
	
	
}
