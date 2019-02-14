package co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.ParqueaderoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;

@Repository
public class ConsultaVehiculoRepositorio {

	@Autowired
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	public List<Vehiculo> listarVehiculosParqueadosPorTipo(boolean estadoActivo,String tipoVehiculo){
		
		List<Vehiculo> vehiculos = new ArrayList<>();
		
		List<ParqueaderoEntity> parqueaderoEntity =parqueaderoRepositorio.findByEstadoactivoAndTipovehiculo(estadoActivo,tipoVehiculo);
		for(ParqueaderoEntity parqueadero : parqueaderoEntity) {
			
			vehiculos.add(ParqueaderoBuild.convertirHaciaDominio(parqueadero));
		}
		
		return vehiculos;
		
	}
}
