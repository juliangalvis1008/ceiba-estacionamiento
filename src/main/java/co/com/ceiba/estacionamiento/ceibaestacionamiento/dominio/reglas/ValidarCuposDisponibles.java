package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.EntradaVehiculoRepositorio;

public class ValidarCuposDisponibles implements ReglasNegocio {
	
	@Autowired
	EntradaVehiculoRepositorio entradaVehiculoRepositorio;
	
	public ValidarCuposDisponibles(EntradaVehiculoRepositorio entradaVehiculoRepositorio) {
		this.entradaVehiculoRepositorio = entradaVehiculoRepositorio;
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

		int cantidadTotal = entradaVehiculoRepositorio.cantidadCuposUsados(vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO);
		if(cantidadTotal > cantidadMaxima) {
			throw new Excepcion(mensajeSobrecupo);
		}
		
	}
	
	
	
}
