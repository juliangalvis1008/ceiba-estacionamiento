package co.com.ceiba.estacionamiento.ceibaestacionamiento.builder;

import java.time.LocalDateTime;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.VehiculoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Carro;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;

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
	
	public VehiculoBuild crearVehiculoBuild() {
		
		return new VehiculoBuild(this.placa,this.fechaIngreso,this.fechaSalida,
				this.tipoVehiculo,this.cilindraje,this.valorCobro);
	}
	
	public Vehiculo build() {
		
		if(this.tipoVehiculo == Constantes.TIPO_VEHICULO_CARRO) {
			return new Carro(this.placa,this.tipoVehiculo,this.fechaIngreso,
					this.fechaSalida,this.valorCobro);
		}else {
			return new Moto(this.placa,this.tipoVehiculo,this.fechaIngreso,
					this.fechaSalida,this.valorCobro,this.cilindraje);
		}
	}
	
	
	
	
}
