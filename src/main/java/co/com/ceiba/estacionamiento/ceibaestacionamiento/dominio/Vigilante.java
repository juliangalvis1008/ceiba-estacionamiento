package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.CalcularCobroVehiculos;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ReglasNegocio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarCuposDisponibles;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoAdapter;

public class Vigilante {

	private List <ReglasNegocio> reglasIngreso = new ArrayList<>();
	private List <ReglasNegocio> reglasSalida = new ArrayList<>();
	ParqueaderoAdapter parqueaderoAdapter;
	public Vigilante(ParqueaderoAdapter parqueaderoAdapter) {
		this.parqueaderoAdapter = parqueaderoAdapter;
		reglasIngreso.add(new ValidarTipoVehiculo());
		reglasIngreso.add(new ValidarPlaca());
		reglasIngreso.add(new ValidarCuposDisponibles(parqueaderoAdapter));
		reglasSalida.add(new CalcularCobroVehiculos());
	}
	
	
	
	public String ingresarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		try {
			for (ReglasNegocio regla : reglasIngreso) {
				regla.ejecutarRegla(vehiculo);
			}
			parqueaderoAdapter.ingresarVehiculo(vehiculo);
			mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" fue ingresado correctamente";
			
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		
		return mensaje;
		
	}
	
	public String sacarVehiculo (Vehiculo vehiculo) {
		String mensaje= ""; 
		try {
			for (ReglasNegocio regla : reglasSalida) {
				regla.ejecutarRegla(vehiculo);
			}
			parqueaderoAdapter.salidaVehiculo(vehiculo);
			mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" fue liquidado correctamente con una tarifa de $" +
						vehiculo.getValorCobro()+ Constantes.MONEDA.toLowerCase();
			
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		
		return mensaje;
		
	}
	
	public List<Vehiculo> listarVehiculosParqueadosPorTipo(boolean estadoActivo,String tipoVehiculo){
		
		return parqueaderoAdapter.listarVehiculosParqueadosPorTipo(estadoActivo, tipoVehiculo);
		
	}
	
	
}
