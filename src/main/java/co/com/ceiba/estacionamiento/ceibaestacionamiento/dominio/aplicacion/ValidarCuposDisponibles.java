package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoAdapter;

public class ValidarCuposDisponibles implements ReglasNegocio {
	
	@Autowired
	ParqueaderoAdapter parqueaderoAdapter;
	
	public ValidarCuposDisponibles(ParqueaderoAdapter parqueaderoAdapter) {
		this.parqueaderoAdapter = parqueaderoAdapter;
	}

	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {
		
		if (vehiculo.getTipoVehiculo().equalsIgnoreCase(Constantes.TIPO_VEHICULO_CARRO)) {
			verificarDisponibilidad(vehiculo,Constantes.CANTIDAD_MAXIMA_CARROS,Constantes.MENSAJE_SOBRECUPO_CARRO);
			
		}else {
			verificarDisponibilidad(vehiculo,Constantes.CANTIDAD_MAXIMA_MOTOS,Constantes.MENSAJE_SOBRECUPO_MOTOS);

		}			
	}

	private void verificarDisponibilidad(Vehiculo vehiculo, int cantidadMaxima, String mensajeSobrecupo) {

		int cantidadTotal = parqueaderoAdapter.cantidadCuposUsados(vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO);
		if(cantidadTotal > cantidadMaxima) {
			throw new Excepcion(mensajeSobrecupo);
		}
		
	}
	
	
	
}
