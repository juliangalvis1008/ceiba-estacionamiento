package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.MensajeExcepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.EntradaVehiculoService;

public class ValidarCuposDisponibles implements ReglasNegocio {
	
	@Autowired
	EntradaVehiculoService entradaVehiculoRepositorio;
	
	public ValidarCuposDisponibles(EntradaVehiculoService entradaVehiculoRepositorio) {
		this.entradaVehiculoRepositorio = entradaVehiculoRepositorio;
	}

	@Override
	public MensajeExcepcion ejecutarRegla(Vehiculo vehiculo) {
		
		if (vehiculo.getTipoVehiculo().equalsIgnoreCase(Constantes.TIPO_VEHICULO_CARRO)) {
			return verificarDisponibilidad(vehiculo,Constantes.CANTIDAD_MAXIMA_CARROS,Constantes.MENSAJE_SOBRECUPO_CARRO);
			
		}else {
			return verificarDisponibilidad(vehiculo,Constantes.CANTIDAD_MAXIMA_MOTOS,Constantes.MENSAJE_SOBRECUPO_MOTOS);

		}			
	}

	private MensajeExcepcion verificarDisponibilidad(Vehiculo vehiculo, int cantidadMaxima, String mensajeSobrecupo) {

		int cantidadTotal = entradaVehiculoRepositorio.cantidadCuposUsados(vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO);
		if(cantidadTotal > cantidadMaxima) {
			return new MensajeExcepcion(mensajeSobrecupo,false);
		}
		
		return new MensajeExcepcion("",true);
		
	}
	
	
	
}
