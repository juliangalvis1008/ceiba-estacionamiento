package co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.ParqueaderoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.ParqueaderoRepositorio;

@Service
public class ParqueaderoAdapter {
	
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
	
	public void salidaVehiculo(Vehiculo vehiculo) {
		ParqueaderoEntity parqueaderoEntidad = parqueaderoRepositorio.findByTipovehiculoAndPlacaAndEstadoactivo(vehiculo.getTipoVehiculo(), 
				vehiculo.getPlaca(), Constantes.ESTADO_ACTIVO);
		parqueaderoEntidad.setEstadoActivo(Constantes.ESTADO_INACTIVO);
		parqueaderoRepositorio.save(parqueaderoEntidad);		
	}
	
	public List<Vehiculo> listarVehiculosParqueadosPorTipo(boolean estadoActivo,String tipoVehiculo){
		
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		
		List<ParqueaderoEntity> parqueaderoEntity =parqueaderoRepositorio.findByEstadoactivoAndTipovehiculo(estadoActivo,tipoVehiculo);
		for(ParqueaderoEntity parqueadero : parqueaderoEntity) {
			
			vehiculos.add(ParqueaderoBuild.convertirHaciaDominio(parqueadero));
		}
		
		return vehiculos;
		
	}
	
	
}
