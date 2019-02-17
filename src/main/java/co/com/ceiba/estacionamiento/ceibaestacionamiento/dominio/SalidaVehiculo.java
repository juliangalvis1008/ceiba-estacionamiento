package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.Excepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepciones.MensajeExcepcion;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.CalcularCobroVehiculos;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ReglasNegocio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.SalidaVehiculoService;

public class SalidaVehiculo {

	SalidaVehiculoService salidaVehiculoRepositorio;
	
	private List <ReglasNegocio> reglasSalida = new ArrayList<>(); 
	public SalidaVehiculo(SalidaVehiculoService salidaVehiculoRepositorio) {
		this.salidaVehiculoRepositorio = salidaVehiculoRepositorio;
		reglasSalida.add(new CalcularCobroVehiculos());
			
	}
	
	public String sacarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		MensajeExcepcion respuesta;
		
		try {
			for (ReglasNegocio regla : reglasSalida) {
				respuesta = regla.ejecutarRegla(vehiculo);
				
				if(respuesta.isEstado() == false) {
					mensaje = respuesta.getMensaje();
					break;
				}
			}
			
			
			if(mensaje.compareTo("")==0) {
				salidaVehiculoRepositorio.salidaVehiculo(vehiculo);
				mensaje = "El valor de parqueo para el vehiculo "+vehiculo.getPlaca()+" es de $" +
						vehiculo.getValorCobro()+" "+ Constantes.MONEDA.toLowerCase();
			}
		} catch (Exception e) {
			
			throw new Excepcion(e.getMessage());
		}
		
		return mensaje;
		
	}
}
