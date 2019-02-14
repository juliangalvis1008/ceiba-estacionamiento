package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.CalcularCobroVehiculos;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ReglasNegocio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.SalidaVehiculoRepositorio;

public class SalidaVehiculo {

	SalidaVehiculoRepositorio salidaVehiculoRepositorio;
	
	private List <ReglasNegocio> reglasSalida = new ArrayList<>(); 
	public SalidaVehiculo(SalidaVehiculoRepositorio salidaVehiculoRepositorio) {
		this.salidaVehiculoRepositorio = salidaVehiculoRepositorio;
		reglasSalida.add(new CalcularCobroVehiculos());
			
	}
	
	public String sacarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		try {
			for (ReglasNegocio regla : reglasSalida) {
				regla.ejecutarRegla(vehiculo);
			}
			salidaVehiculoRepositorio.salidaVehiculo(vehiculo);
			mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" fue liquidado correctamente con una tarifa de $" +
						vehiculo.getValorCobro()+ Constantes.MONEDA.toLowerCase();
			
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		
		return mensaje;
		
	}
}
