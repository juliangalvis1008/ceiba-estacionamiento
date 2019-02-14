package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;


import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarCuposDisponibles;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.EntradaVehiculoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ReglasNegocio;

public class EntradaVehiculo {
 
	EntradaVehiculoRepositorio entradaVehiculoRepositorio;
	private List <ReglasNegocio> reglasIngreso = new ArrayList<>(); 
	public EntradaVehiculo(EntradaVehiculoRepositorio entradaVehiculoRepositorio) {
		this.entradaVehiculoRepositorio = entradaVehiculoRepositorio;
		reglasIngreso.add(new ValidarTipoVehiculo());
		reglasIngreso.add(new ValidarPlaca());
		reglasIngreso.add(new ValidarCuposDisponibles(entradaVehiculoRepositorio));
		
	}
	
	public String ingresarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		
		try {
			for (ReglasNegocio regla : reglasIngreso) {
				regla.ejecutarRegla(vehiculo);
			}
			entradaVehiculoRepositorio.ingresarVehiculo(vehiculo);
			mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" fue ingresado correctamente";
			
		} catch (Exception e) {
			
			mensaje = e.getMessage();
		}
		
		return mensaje;
		
	}
	
 
 
}
