package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.time.LocalDateTime;

public class Carro extends Vehiculo {

	public Carro(String placa, String tipoVehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida,
			double valorCobro) {
		super(placa, tipoVehiculo, fechaIngreso, fechaSalida, valorCobro);		
	}
}
