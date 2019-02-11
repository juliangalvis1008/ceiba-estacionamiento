package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;

public final class ParqueaderoBuild {
	
	private ParqueaderoBuild() {
		throw new IllegalStateException("Utility class");
	}

	public static Vehiculo convertirHaciaDominio(ParqueaderoEntity parqueaderoEntity) {
		
		Vehiculo vehiculo = null;
		if(parqueaderoEntity != null) {
			if(parqueaderoEntity.getTipoVehiculo() == Constantes.TIPO_VEHICULO_CARRO) {
				vehiculo = new Carro(parqueaderoEntity.getPlaca(), parqueaderoEntity.getTipoVehiculo(), 
						parqueaderoEntity.getFechaIngreso(), parqueaderoEntity.getFechaSalida(), parqueaderoEntity.getValorCobro());			
			}
						
			if(parqueaderoEntity.getTipoVehiculo() == Constantes.TIPO_VEHICULO_MOTO) {
				vehiculo = new Moto(parqueaderoEntity.getPlaca(), parqueaderoEntity.getTipoVehiculo(), 
						parqueaderoEntity.getFechaIngreso(), parqueaderoEntity.getFechaSalida(), parqueaderoEntity.getValorCobro(),
						parqueaderoEntity.getCilindraje());
			}
		}
		return vehiculo;
	}
	
	public static ParqueaderoEntity convertirHaciaEntidad(Vehiculo vehiculo) {
		
		ParqueaderoEntity parqueadero = new ParqueaderoEntity();
		parqueadero.setPlaca(vehiculo.getPlaca());
		parqueadero.setTipoVehiculo(vehiculo.getTipoVehiculo());
		parqueadero.setFechaIngreso(vehiculo.getFechaIngreso());
		parqueadero.setFechaSalida(vehiculo.getFechaSalida());
		parqueadero.setValorCobro(vehiculo.getValorCobro());
		
		if(parqueadero.getTipoVehiculo() == Constantes.TIPO_VEHICULO_MOTO) {
			parqueadero.setCilindraje(((Moto) vehiculo).getCilindraje());
		}
		
		return parqueadero;
	}
}
