package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarCuposDisponibles;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarVehiculoYaEnParqueadero;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.EntradaVehiculoService;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.MensajeExcepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ReglasNegocio;

public class EntradaVehiculo {
 
	EntradaVehiculoService entradaVehiculoRepositorio;
	private List <ReglasNegocio> reglasIngreso = new ArrayList<>(); 
	public EntradaVehiculo(EntradaVehiculoService entradaVehiculoRepositorio) {
		this.entradaVehiculoRepositorio = entradaVehiculoRepositorio;
		reglasIngreso.add(new ValidarTipoVehiculo());
		reglasIngreso.add(new ValidarPlaca());
		reglasIngreso.add(new ValidarCuposDisponibles(entradaVehiculoRepositorio));
		reglasIngreso.add(new ValidarVehiculoYaEnParqueadero(entradaVehiculoRepositorio));
		
	}
	
	public String ingresarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		MensajeExcepcion respuesta;
		
		try {
			for (ReglasNegocio regla : reglasIngreso) {
				respuesta = regla.ejecutarRegla(vehiculo);
				
				if(!respuesta.isEstado()) {
					mensaje = respuesta.getMensaje();
					break;
				}
			}
			
			if(mensaje.compareTo("")==0) {
				entradaVehiculoRepositorio.ingresarVehiculo(vehiculo);
				mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" fue ingresado correctamente";
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return mensaje;
		
	}
	
 
 
}
