package co.com.ceiba.estacionamiento.ceibaestacionamiento.builder;

import java.time.LocalDateTime;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;

public class VehiculoTestDataBuild {

	private String placa;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private String tipoVehiculo;
	private int cilindraje;
	private double valorCobro;
	
	public VehiculoTestDataBuild(String placa, String tipoVehiculo,
			int cilindraje) {
		this.placa = placa;
		this.fechaIngreso = LocalDateTime.now();
		this.fechaSalida = null;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.valorCobro = 0;
	}
	
	public VehiculoTestDataBuild(String placa, String tipoVehiculo) {
		this.placa = placa;
		this.fechaIngreso = LocalDateTime.now();
		this.fechaSalida = null;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = 0;
		this.valorCobro = 0;
	}
	
	public VehiculoTestDataBuild(String placa, String tipoVehiculo,
			LocalDateTime fechaIngreso ) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = null;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = 0;
		this.valorCobro = 0;
	}
	
	public VehiculoTestDataBuild(String placa, String tipoVehiculo,
			LocalDateTime fechaIngreso,LocalDateTime fechaSalida ) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = 0;
		this.valorCobro = 0;
	}
	
	public VehiculoTestDataBuild(String placa, String tipoVehiculo,
			LocalDateTime fechaIngreso,LocalDateTime fechaSalida,int cilindraje ) {
		this.placa = placa;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.valorCobro = 0;
	}
	
	public Vehiculo build() {
		
		if(this.tipoVehiculo == Vigilante.TIPO_VEHICULO_CARRO) {
			return new Carro(this.placa,this.tipoVehiculo,this.fechaIngreso,
					this.fechaSalida,this.valorCobro);
		}else {
			return new Moto(this.placa,this.tipoVehiculo,this.fechaIngreso,
					this.fechaSalida,this.valorCobro,this.cilindraje);
		}
	}
	
	
	
	
}
