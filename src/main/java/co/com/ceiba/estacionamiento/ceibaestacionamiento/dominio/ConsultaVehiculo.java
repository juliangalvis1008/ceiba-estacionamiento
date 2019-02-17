package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.ConsultaVehiculoRepositorio;

public class ConsultaVehiculo {
	
	ConsultaVehiculoRepositorio consultaVehiculoRepositorio;
	
	public ConsultaVehiculo(ConsultaVehiculoRepositorio consultaVehiculoRepositorio) {
		
		this.consultaVehiculoRepositorio = consultaVehiculoRepositorio;
	}

	public List<Vehiculo> listarVehiculosParqueadosPorTipo(boolean estadoActivo,String tipoVehiculo){
		
		return consultaVehiculoRepositorio.listarVehiculosParqueadosPorTipo(estadoActivo, tipoVehiculo);		
	}
}
