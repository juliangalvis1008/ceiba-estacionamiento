package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Carro;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;

public final class ParqueaderoBuild {
	
	private ParqueaderoBuild() {
		throw new IllegalStateException("Utility class");
	}

	public static Vehiculo convertirHaciaDominio(ParqueaderoEntity parqueaderoEntity) {
		
		Vehiculo vehiculo = null;
		if(parqueaderoEntity != null) {
			if(parqueaderoEntity.getTipoVehiculo().compareTo(Constantes.TIPO_VEHICULO_CARRO) ==0) {
				vehiculo = new Carro(parqueaderoEntity.getPlaca(), parqueaderoEntity.getTipoVehiculo(), 
						parqueaderoEntity.getFechaIngreso(), parqueaderoEntity.getFechaSalida(), parqueaderoEntity.getValorCobro());			
			}
						
			if(parqueaderoEntity.getTipoVehiculo().compareTo(Constantes.TIPO_VEHICULO_MOTO)==0) {
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
		
		if(parqueadero.getTipoVehiculo().compareTo(Constantes.TIPO_VEHICULO_MOTO)== 0) {
			parqueadero.setCilindraje(((Moto) vehiculo).getCilindraje());
		}
		
		return parqueadero;
	}
}
