package co.com.ceiba.estacionamiento.ceibaestacionamiento.unitarias;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.builder.VehiculoTestDataBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.CalcularCobroVehiculos;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarCuposDisponibles;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.aplicacion.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.ParqueaderoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.VehiculoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.ParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpRepositorio;



@RunWith(MockitoJUnitRunner.class)

public class PruebasUnitarias {

	@Mock
	ParqueaderoImpRepositorio parqueaderoAdapter;
	
	@Mock
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	@InjectMocks
	ValidarCuposDisponibles validarCuposDisponibles;
	
	@InjectMocks
	ValidarTipoVehiculo validarTipoVehiculo;
	
	@InjectMocks 
	ValidarPlaca validarPlaca;
	
	@InjectMocks
	CalcularCobroVehiculos calcularCobroVehiculos;
	
	ParqueaderoBuild parqueaderoBuild;
	
	public static final String NUMERO_PLACA_MOTO = "CMO48C";	
	public static final String NUMERO_PLACA_CARRO = "VHC085";
	public static final String NUMERO_PLACA_INICIAL_NO_VALIDA = "AYC088";
	public static final int CILINDRAJE_MOTO_MENOR_TOPE = 200;
	public static final int CILINDRAJE_MOTO_MAYOR_TOPE = 650;
	public static final String TIPO_VEHICULO_INCORRECTO  = "BICICLETA";
	public static final int CANTIDAD_CARROS_IGUAL_AL_TOPE = 20;
	public static final int CANTIDAD_CARROS_MENOR_AL_TOPE = 12;
	public static final int CANTIDAD_MOTOS_IGUAL_AL_TOPE = 10;
	public static final int CANTIDAD_MOTOS_MENOR_AL_TOPE = 6;
		
	@Test
	public void validarTipoVehiculoCarroTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO, 
				Vigilante.TIPO_VEHICULO_CARRO).build();
		
		try {
			validarTipoVehiculo.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Vigilante.MENSAJE_TIPO_VEHICULO_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void validarTipoVehiculoMotoTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				Vigilante.TIPO_VEHICULO_MOTO).build();
		
		try {
			validarTipoVehiculo.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Vigilante.MENSAJE_TIPO_VEHICULO_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void validarTipoVehiculoIncorrectoTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				PruebasUnitarias.TIPO_VEHICULO_INCORRECTO).build();
		
		try {
			validarTipoVehiculo.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Vigilante.MENSAJE_TIPO_VEHICULO_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void validarSiDisponibilidadCarrosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO, 
				Vigilante.TIPO_VEHICULO_CARRO).build();
		
		when(parqueaderoAdapter.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Vigilante.ESTADO_ACTIVO)).thenReturn(PruebasUnitarias.CANTIDAD_CARROS_MENOR_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Vigilante.MENSAJE_SOBRECUPO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarNoDisponibilidadCarrosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO, 
				Vigilante.TIPO_VEHICULO_CARRO).build();
		
		when(parqueaderoAdapter.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Vigilante.ESTADO_ACTIVO)).thenReturn(PruebasUnitarias.CANTIDAD_CARROS_IGUAL_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Vigilante.MENSAJE_SOBRECUPO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarSiDisponibilidadMotosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				Vigilante.TIPO_VEHICULO_CARRO).build();
		
		when(parqueaderoAdapter.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Vigilante.ESTADO_ACTIVO)).thenReturn(PruebasUnitarias.CANTIDAD_MOTOS_MENOR_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Vigilante.MENSAJE_SOBRECUPO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarNoDisponibilidadMotosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				Vigilante.TIPO_VEHICULO_MOTO).build();
		
		when(parqueaderoAdapter.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Vigilante.ESTADO_ACTIVO)).thenReturn(PruebasUnitarias.CANTIDAD_MOTOS_IGUAL_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Vigilante.MENSAJE_SOBRECUPO_MOTOS, e.getMessage());
		}
	}
	
	@Test
	public void validarPlacaSiValidaDomingo() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_INICIAL_NO_VALIDA, 
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-10T06:00:00")).build();
		
		try {
			validarPlaca.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Vigilante.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA, e.getMessage());
		}
	}
	
	@Test
	public void validarPlacaSiValidaLunes() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_INICIAL_NO_VALIDA, 
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-11T07:00:00")).build();
		
		try {
			validarPlaca.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Vigilante.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA, e.getMessage());
		}
	}
	
	
	@Test
	public void validarPlacaNoValidaRestoSemana() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_INICIAL_NO_VALIDA, 
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-14T09:00:00")).build();
		
		try {
			validarPlaca.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Vigilante.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA, e.getMessage());
		}
	}
	
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
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO, 
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T09:00:00")).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(7000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroCarrosTiempoMayorTopeMinAndMenorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO, 
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T14:00:00")).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(8000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroCarrosTiempoMayorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO, 
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-09T05:00:00")).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(11000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosTiempoMenorTopeMin() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				Vigilante.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T09:00:00"),
				PruebasUnitarias.CILINDRAJE_MOTO_MENOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(3500, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosTiempoMayorTopeMinAndMenorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				Vigilante.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T14:00:00"),
				PruebasUnitarias.CILINDRAJE_MOTO_MENOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(4000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosTiempoMayorTopeMax() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				Vigilante.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-09T05:00:00"),
				PruebasUnitarias.CILINDRAJE_MOTO_MENOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(5500, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCobroMotosValorAdicionalTopeCilindraje() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO, 
				Vigilante.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T12:00:00"),
				PruebasUnitarias.CILINDRAJE_MOTO_MAYOR_TOPE).build();
		
		calcularCobroVehiculos.ejecutarRegla(vehiculo);		
		Assert.assertEquals(6000, vehiculo.getValorCobro(),0);
		
	}
	
	@Test
	public void validarCrearVehiculoMoto() {
		Vehiculo vehiculo = new VehiculoBuild(PruebasUnitarias.NUMERO_PLACA_MOTO,
				LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T06:00:00"),
				Vigilante.TIPO_VEHICULO_MOTO,PruebasUnitarias.CILINDRAJE_MOTO_MENOR_TOPE,0).crearVehiculo();
		
		Assert.assertNotNull(vehiculo);
		
	}
	
	@Test
	public void validarCrearVehiculoCarro() {
		Vehiculo vehiculo = new VehiculoBuild(PruebasUnitarias.NUMERO_PLACA_CARRO,
				LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T06:00:00"),
				Vigilante.TIPO_VEHICULO_CARRO,0,0).crearVehiculo();
		
		Assert.assertNotNull(vehiculo);
		
	}
	
	
}
