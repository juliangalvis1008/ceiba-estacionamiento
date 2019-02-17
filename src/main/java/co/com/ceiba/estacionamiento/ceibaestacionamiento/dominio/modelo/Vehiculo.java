package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo;

import java.time.LocalDateTime;

public class Vehiculo {
	
	private String placa;
	private String tipoVehiculo;
	private LocalDateTime fechaIngreso; 
	private LocalDateTime fechaSalida; 
	private double valorCobro;
	
	
	
	public Vehiculo(String placa, String tipoVehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaSalida,
			double valorCobro) {
		
		this.placa = placa;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.valorCobro = valorCobro;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
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
	public double getValorCobro() {
		return valorCobro;
	}
	public void setValorCobro(double valorCobro) {
		this.valorCobro = valorCobro;
	}
	

}
