package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import java.time.Duration;
import java.time.LocalDateTime;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;

public class CalcularCobroVehiculos implements ReglasNegocio {
	
	@Override
	public void ejecutarRegla(Vehiculo vehiculo) {

		double tarifaTotalParqueadero = calcularValorTotal(vehiculo);
		vehiculo.setValorCobro(tarifaTotalParqueadero);
		
	}
	
	public double calcularValorTotal(Vehiculo vehiculo) {
		
		int[] tiempoTotal = calcularTiempoTotal(vehiculo.getFechaIngreso(), vehiculo.getFechaSalida());
		double valorTotalParqueo=0;
		
		if(vehiculo.getTipoVehiculo().compareTo(Constantes.TIPO_VEHICULO_CARRO)==0) {
			
			valorTotalParqueo = tiempoTotal[0]*Constantes.VALOR_DIA_CARRO + 
					tiempoTotal[1]*Constantes.VALOR_HORA_CARRO;			
		}else {
			
			Moto moto = (Moto) vehiculo;
		
			valorTotalParqueo = tiempoTotal[0]*Constantes.VALOR_DIA_MOTO + 
					tiempoTotal[1]*Constantes.VALOR_HORA_MOTO;
			
			if(moto.getCilindraje() > Constantes.TOPE_CILINDRAJE_MOTO) {
				valorTotalParqueo += Constantes.VALOR_ADICIONAL_TOPE_CILINDRAJE_MOTO;
			}
		}
		
		return valorTotalParqueo;
	}
	
	public int[] calcularTiempoTotal(LocalDateTime fechaIngreso,LocalDateTime fechaSalida) {
		double numeroHoras = 0;
		double numeroMinutos = 0;
		
		if(Duration.between(fechaIngreso, fechaSalida).toMillis() > Constantes.MILISEGUNDOS_A_MINUTOS) {
			numeroMinutos = Duration.between(fechaIngreso, fechaSalida).toMinutes();
		}else {
			numeroMinutos += 1;
		}
		
		
		numeroHoras = Math.ceil(numeroMinutos/Constantes.MINUTOS_X_HORA_PARQUEO);
		
		double numeroDiasParqueo = Math.floor(numeroHoras/Constantes.TOPE_MAX_HORAS_COBRO_X_DIA); 
		double numeroHorasParqueo = 0;
		
		if(numeroHoras < Constantes.TOPE_MAX_HORAS_COBRO_X_DIA) {
			numeroHorasParqueo = numeroHoras;
		}else {
			numeroHorasParqueo = numeroHoras % (numeroDiasParqueo*Constantes.TOPE_MAX_HORAS_COBRO_X_DIA);
		}
		
		if(numeroHorasParqueo >= Constantes.TOPE_MIN_HORAS_COBRO_X_DIA &&
				numeroHorasParqueo < Constantes.TOPE_MAX_HORAS_COBRO_X_DIA){
			
			numeroHorasParqueo = 0;
			numeroDiasParqueo +=1;
			
		}
		
		return (new int[] { (int)numeroDiasParqueo, (int)numeroHorasParqueo });
		
	}
	
	
}
