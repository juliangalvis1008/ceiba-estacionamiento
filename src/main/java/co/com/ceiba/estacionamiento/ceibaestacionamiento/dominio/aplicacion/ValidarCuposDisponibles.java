package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpRepositorio;

public class ValidarCuposDisponibles implements ReglasNegocio {
	
	@Autowired
	ParqueaderoImpRepositorio parqueaderoAdapter;
	
	public ValidarCuposDisponibles(ParqueaderoImpRepositorio parqueaderoAdapter) {
		this.parqueaderoAdapter = parqueaderoAdapter;
	}

	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {
		
		if (vehiculo.getTipoVehiculo().equalsIgnoreCase(Vigilante.TIPO_VEHICULO_CARRO)) {
			verificarDisponibilidad(vehiculo,Vigilante.CANTIDAD_MAXIMA_CARROS,Vigilante.MENSAJE_SOBRECUPO_CARRO);
			
		}else {
			verificarDisponibilidad(vehiculo,Vigilante.CANTIDAD_MAXIMA_MOTOS,Vigilante.MENSAJE_SOBRECUPO_MOTOS);

		}			
	}

	private void verificarDisponibilidad(Vehiculo vehiculo, int cantidadMaxima, String mensajeSobrecupo) {

		int cantidadTotal = parqueaderoAdapter.cantidadCuposUsados(vehiculo.getTipoVehiculo(), Vigilante.ESTADO_ACTIVO);
		if(cantidadTotal > cantidadMaxima) {
			throw new Excepcion(mensajeSobrecupo);
		}
		
	}
	
	
	
}
