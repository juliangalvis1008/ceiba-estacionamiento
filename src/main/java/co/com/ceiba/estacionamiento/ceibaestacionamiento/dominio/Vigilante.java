package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.CalcularCobroVehiculos;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ReglasNegocio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarCuposDisponibles;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpRepositorio;

public class Vigilante {
	
	public static final String TIPO_VEHICULO_CARRO  = "CARRO";
	public static final String TIPO_VEHICULO_MOTO  = "MOTO";
	
	public static final boolean  ESTADO_ACTIVO  = true;
	public static final boolean  ESTADO_INACTIVO  = false;
	
	public static final int CANTIDAD_MAXIMA_MOTOS = 10;
	public static final int CANTIDAD_MAXIMA_CARROS = 20;
	
	public static final String LETRA_INICIO_PLACA = "A";
	
	public static final double VALOR_HORA_CARRO = 1000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_DIA_MOTO = 4000;
	
	public static final int  TOPE_CILINDRAJE_MOTO = 500;
	
	public static final int TOPE_MIN_HORAS_COBRO_X_DIA = 9;
	public static final int TOPE_MAX_HORAS_COBRO_X_DIA = 24;
	public static final int MINUTOS_X_HORA_PARQUEO = 60;
	
	public static final double  VALOR_ADICIONAL_TOPE_CILINDRAJE_MOTO = 2000;
	
	public static final String MENSAJE_TIPO_VEHICULO_INVALIDO = "El tipo de vehículo a ingresar corresponde a un tipo válido para este estacionamiento";
	public static final String MENSAJE_SOBRECUPO_CARRO = "Actualmente no se cuenta con cupos disponibles para Carros";
	public static final String MENSAJE_SOBRECUPO_MOTOS = "Actualmente no se cuenta con cupos disponibles para Motos";
	public static final String MENSAJE_ACCESO_NO_AUTORIZADO_PLACA = "El vehiculo tiene una placa que no esta habilitada para ingresar al parqueadero el día de hoy";

	public static final String MONEDA = "PESOS";

	private List <ReglasNegocio> reglasIngreso = new ArrayList<>();
	private List <ReglasNegocio> reglasSalida = new ArrayList<>();
	
	ParqueaderoImpRepositorio parqueaderoAdapter;
	public Vigilante(ParqueaderoImpRepositorio parqueaderoAdapter) {
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
						vehiculo.getValorCobro()+ Vigilante.MONEDA.toLowerCase();
			
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		
		return mensaje;
		
	}
	
	public List<Vehiculo> listarVehiculosParqueadosPorTipo(boolean estadoActivo,String tipoVehiculo){
		
		return parqueaderoAdapter.listarVehiculosParqueadosPorTipo(estadoActivo, tipoVehiculo);
		
	}
	
	
}
