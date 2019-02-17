package co.com.ceiba.estacionamiento.ceibaestacionamiento.unitarias;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.builder.VehiculoTestDataBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.CalcularCobroVehiculos;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.constantes.ConstantesTest;

@RunWith(MockitoJUnitRunner.class)
public class SalidaVehiculoUnitTest {

	@InjectMocks
	CalcularCobroVehiculos calcularCobroVehiculos;
	
	
	@Test
	public void validarTiempoMenorUnaHora() {
		
		int[] tiempoTotal = calcularCobroVehiculos.calcularTiempoTotal(LocalDateTime.parse("2019-02-09T05:00:00"), 
				LocalDateTime.parse("2019-02-09T05:26:00"));
		
		Assert.assertEquals(0,tiempoTotal[0]);
		Assert.assertEquals(1,tiempoTotal[1]);
	}
	
	@Test
	public void validarTiempoMayorUnaHoraMenorTopeMin() {
		
		int[] tiempoTotal = calcularCobroVehiculos.calcularTiempoTotal(LocalDateTime.parse("2019-02-09T05:00:00"), 
				LocalDateTime.parse("2019-02-09T10:26:00"));
		
		Assert.assertEquals(0,tiempoTotal[0]);
		Assert.assertEquals(6,tiempoTotal[1]);
	}
	
	@Test
	public void validarTiempoMayorTopeMinMenorTopeMax() {
		
		int[] tiempoTotal = calcularCobroVehiculos.calcularTiempoTotal(LocalDateTime.parse("2019-02-09T05:00:00"), 
				LocalDateTime.parse("2019-02-09T16:00:00"));
		
		Assert.assertEquals(1,tiempoTotal[0]);
		Assert.assertEquals(0,tiempoTotal[1]);
	}
	
	@Test
	public void validarTiempoMayorTopeMax() {
		
		int[] tiempoTotal = calcularCobroVehiculos.calcularTiempoTotal(LocalDateTime.parse("2019-02-09T05:00:00"), 
				LocalDateTime.parse("2019-02-10T07:00:00"));
		
		Assert.assertEquals(1,tiempoTotal[0]);
		Assert.assertEquals(2,tiempoTotal[1]);
	}
	
	
	@Test
	public void validarCobroCarrosTiempoMenorTopeMin() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_CARRO, 
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T09:00:00")).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(7000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroCarrosTiempoMayorTopeMinAndMenorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_CARRO, 
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T14:00:00")).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(8000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroCarrosTiempoMayorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_CARRO, 
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-09T05:00:00")).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(11000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosTiempoMenorTopeMin() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				Constantes.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T09:00:00"),
				ConstantesTest.CILINDRAJE_MOTO_MENOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(3500, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosTiempoMayorTopeMinAndMenorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				Constantes.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T14:00:00"),
				ConstantesTest.CILINDRAJE_MOTO_MENOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(4000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosTiempoMayorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				Constantes.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-09T05:00:00"),
				ConstantesTest.CILINDRAJE_MOTO_MENOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(5500, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosValorAdicionalTopeCilindraje() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				Constantes.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T12:00:00"),
				ConstantesTest.CILINDRAJE_MOTO_MAYOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(6000, vehiculo.getValorCobro(),0);
		
	}
}
