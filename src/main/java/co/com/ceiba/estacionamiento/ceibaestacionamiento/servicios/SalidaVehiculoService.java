package co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.ParqueaderoRepositorio;

@Service
public class SalidaVehiculoService {

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
