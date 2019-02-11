package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion;

import java.time.Duration;
import java.time.LocalDateTime;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;

public class CalcularCobroVehiculos implements ReglasNegocio {
	
	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {

		double tarifaTotalParqueadero = calcularValorTotal(vehiculo);
		vehiculo.setValorCobro(tarifaTotalParqueadero);
		
	}
	
	public double calcularValorTotal(Vehiculo vehiculo) {
		
		int[] tiempoTotal = calcularTiempoTotal(vehiculo.getFechaIngreso(), vehiculo.getFechaSalida());
		double valorTotalParqueo=0;
		
		if(vehiculo.getTipoVehiculo() == Vigilante.TIPO_VEHICULO_CARRO) {
			
			valorTotalParqueo = tiempoTotal[0]*Vigilante.VALOR_DIA_CARRO + 
					tiempoTotal[1]*Vigilante.VALOR_HORA_CARRO;			
		}else {
			
			Moto moto = (Moto) vehiculo;
		
			valorTotalParqueo = tiempoTotal[0]*Vigilante.VALOR_DIA_MOTO + 
					tiempoTotal[1]*Vigilante.VALOR_HORA_MOTO;
			
			if(moto.getCilindraje() > Vigilante.TOPE_CILINDRAJE_MOTO) {
				valorTotalParqueo += Vigilante.VALOR_ADICIONAL_TOPE_CILINDRAJE_MOTO;
			}
		}
		
		return valorTotalParqueo;
	}
	
	public int[] calcularTiempoTotal(LocalDateTime fechaIngreso,LocalDateTime fechaSalida) {
		double numeroHoras = 0;
		
		
		double numeroMinutos = Duration.between(fechaIngreso, fechaSalida).toMinutes();
		numeroHoras = Math.ceil(numeroMinutos/Vigilante.MINUTOS_X_HORA_PARQUEO);
		
		double numeroDiasParqueo = Math.floor(numeroHoras/Vigilante.TOPE_MAX_HORAS_COBRO_X_DIA); 
		double numeroHorasParqueo = 0;
		
		if(numeroHoras < Vigilante.TOPE_MAX_HORAS_COBRO_X_DIA) {
			numeroHorasParqueo = numeroHoras;
		}else {
			numeroHorasParqueo = numeroHoras % (numeroDiasParqueo*Vigilante.TOPE_MAX_HORAS_COBRO_X_DIA);
		}
		
		if(numeroHorasParqueo >= Vigilante.TOPE_MIN_HORAS_COBRO_X_DIA &&
				numeroHorasParqueo < Vigilante.TOPE_MAX_HORAS_COBRO_X_DIA){
			
			numeroHorasParqueo = 0;
			numeroDiasParqueo +=1;
			
		}
		
		return (new int[] { (int)numeroDiasParqueo, (int)numeroHorasParqueo });
		
	}
	
	
}
