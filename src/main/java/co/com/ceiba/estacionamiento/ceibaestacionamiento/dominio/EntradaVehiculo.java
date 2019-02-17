package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarCuposDisponibles;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarVehiculoYaEnParqueadero;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.EntradaVehiculoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ReglasNegocio;

public class EntradaVehiculo {
 
	EntradaVehiculoRepositorio entradaVehiculoRepositorio;
	private List <ReglasNegocio> reglasIngreso = new ArrayList<>(); 
	public EntradaVehiculo(EntradaVehiculoRepositorio entradaVehiculoRepositorio) {
		this.entradaVehiculoRepositorio = entradaVehiculoRepositorio;
		reglasIngreso.add(new ValidarTipoVehiculo());
		reglasIngreso.add(new ValidarPlaca());
		reglasIngreso.add(new ValidarCuposDisponibles(entradaVehiculoRepositorio));
		reglasIngreso.add(new ValidarVehiculoYaEnParqueadero(entradaVehiculoRepositorio));
		
	}
	
	public String ingresarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		final Logger LOGGER = Logger.getLogger("registro.ingreso.vehiculo");
		try {
			for (ReglasNegocio regla : reglasIngreso) {
				regla.ejecutarRegla(vehiculo);
			}
			entradaVehiculoRepositorio.ingresarVehiculo(vehiculo);
			mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" fue ingresado correctamente";
			
		} catch (Exception e) {
			    LOGGER.info(e.getMessage());
				mensaje = e.getMessage();
		}
		
		return mensaje;
		
	}
	
 
 
}
