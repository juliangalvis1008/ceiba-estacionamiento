package co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;

@Repository
public class SalidaVehiculoRepositorio {

	@Autowired
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	public void salidaVehiculo(Vehiculo vehiculo) {
		
		ParqueaderoEntity parqueaderoEntidad = parqueaderoRepositorio.findByTipovehiculoAndPlacaAndEstadoactivo(vehiculo.getTipoVehiculo(), 
				vehiculo.getPlaca(), Constantes.ESTADO_ACTIVO);
		parqueaderoEntidad.setEstadoActivo(Constantes.ESTADO_INACTIVO);
		parqueaderoEntidad.setFechaSalida(vehiculo.getFechaSalida());
		parqueaderoEntidad.setValorCobro(vehiculo.getValorCobro());
		parqueaderoRepositorio.save(parqueaderoEntidad);		
	}
}
