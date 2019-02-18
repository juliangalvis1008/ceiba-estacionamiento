package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.ConsultaVehiculoService;

public class ConsultaVehiculo {
	
	ConsultaVehiculoService consultaVehiculoRepositorio;
	
	public ConsultaVehiculo(ConsultaVehiculoService consultaVehiculoService) {
		
		this.consultaVehiculoRepositorio = consultaVehiculoService;
	}

	public List<Vehiculo> listarVehiculosParqueadosPorTipo(boolean estadoActivo,String tipoVehiculo){
		
		return consultaVehiculoRepositorio.listarVehiculosParqueadosPorTipo(estadoActivo, tipoVehiculo);		
	}
}
