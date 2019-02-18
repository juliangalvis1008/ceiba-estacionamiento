package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Carro;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;

public class VehiculoBuild {
	
	private String placa;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaIngreso;
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime fechaSalida;
	private String tipoVehiculo;
	private int cilindraje;
	private double valorCobro;
	// for deserialisation
    public VehiculoBuild() {}  
	
	public VehiculoBuild(String placa, LocalDateTime fechaIngreso, LocalDateTime fechaSalida, String tipoVehiculo,
			int cilindraje, double valorCobro) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.valorCobro = valorCobro;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	public double getValorCobro() {
		return valorCobro;
	}
	public void setValorCobro(double valorCobro) {
		this.valorCobro = valorCobro;
	}
	
	public Vehiculo crearVehiculo() {
		
		if(getTipoVehiculo().compareTo(Constantes.TIPO_VEHICULO_CARRO) == 0) {
			return new Carro(getPlaca(),getTipoVehiculo(),getFechaIngreso(),
					getFechaSalida(),getValorCobro());
		}else {
			return new Moto(getPlaca(),getTipoVehiculo(),getFechaIngreso(),
					getFechaSalida(),getValorCobro(),getCilindraje());
		}
	}

}
