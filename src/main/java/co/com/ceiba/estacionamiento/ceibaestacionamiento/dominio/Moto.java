package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.time.LocalDateTime;

public class Moto extends Vehiculo{
	private int cilindraje;

	public Moto(String placa, String tipoVehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida,
			double valorCobro,int cilindraje) {
		super(placa, tipoVehiculo, fechaIngreso, fechaSalida, valorCobro);
		this.cilindraje = cilindraje;
	}

	public int getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	
	
}
