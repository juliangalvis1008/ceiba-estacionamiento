package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.CalcularCobroVehiculos;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ReglasNegocio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.SalidaVehiculoRepositorio;

public class SalidaVehiculo {

	SalidaVehiculoRepositorio salidaVehiculoRepositorio;
	
	private List <ReglasNegocio> reglasSalida = new ArrayList<>(); 
	public SalidaVehiculo(SalidaVehiculoRepositorio salidaVehiculoRepositorio) {
		this.salidaVehiculoRepositorio = salidaVehiculoRepositorio;
		reglasSalida.add(new CalcularCobroVehiculos());
			
	}
	
	public String sacarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		final Logger LOGGER = Logger.getLogger("registro.salida.vehiculo");
		try {
			for (ReglasNegocio regla : reglasSalida) {
				regla.ejecutarRegla(vehiculo);
			}
			salidaVehiculoRepositorio.salidaVehiculo(vehiculo);
			mensaje = "El valor de parqueo para el vehiculo "+vehiculo.getPlaca()+" es de $" +
						vehiculo.getValorCobro()+" "+ Constantes.MONEDA.toLowerCase();
			
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			mensaje = e.getMessage();
		}
		
		return mensaje;
		
	}
}
