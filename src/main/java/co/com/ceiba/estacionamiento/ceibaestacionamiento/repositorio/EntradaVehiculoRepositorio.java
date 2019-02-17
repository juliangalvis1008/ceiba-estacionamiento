package co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.ParqueaderoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;

@Repository
public class EntradaVehiculoRepositorio {
	
	@Autowired
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	public void ingresarVehiculo (Vehiculo vehiculo) {
		ParqueaderoEntity parqueaderoEntidad =  ParqueaderoBuild.convertirHaciaEntidad(vehiculo);
		parqueaderoEntidad.setEstadoActivo(Constantes.ESTADO_ACTIVO);
		parqueaderoRepositorio.save(parqueaderoEntidad);
	}
	
	public int cantidadCuposUsados(String tipoVehiculo,boolean estadoActivo) {
		return parqueaderoRepositorio.countByTipovehiculoAndEstadoactivo(tipoVehiculo, estadoActivo);
		
	}
	
	public boolean vehiculoYaExiste(String placa,String tipoVehiculo,boolean estadoActivo) {
		return parqueaderoRepositorio.countByTipovehiculoAndPlacaAndEstadoactivo(tipoVehiculo, placa, estadoActivo) >=1;
		
	}

}
