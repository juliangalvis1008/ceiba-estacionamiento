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
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.VehiculoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarCuposDisponibles;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.ParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.EntradaVehiculoService;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.constantes.ConstantesTest;

@RunWith(MockitoJUnitRunner.class)
public class EntradaVehiculoUnitTest {
	
	@Mock
	EntradaVehiculoService entradaVehiculoRepositorio;
	
	@Mock
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	@InjectMocks
	ValidarCuposDisponibles validarCuposDisponibles;
	
	@InjectMocks
	ValidarTipoVehiculo validarTipoVehiculo;
	
	@InjectMocks 
	ValidarPlaca validarPlaca;
	
	@Test
	public void validarTipoVehiculoCarroTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_CARRO, 
				Constantes.TIPO_VEHICULO_CARRO).build();
		
		try {
			validarTipoVehiculo.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Constantes.MENSAJE_TIPO_VEHICULO_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void validarTipoVehiculoMotoTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				Constantes.TIPO_VEHICULO_MOTO).build();
		
		try {
			validarTipoVehiculo.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Constantes.MENSAJE_TIPO_VEHICULO_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void validarTipoVehiculoIncorrectoTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				ConstantesTest.TIPO_VEHICULO_INCORRECTO).build();
		
		try {
			validarTipoVehiculo.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Constantes.MENSAJE_TIPO_VEHICULO_INVALIDO, e.getMessage());
		}
	}
	
	@Test
	public void validarSiDisponibilidadCarrosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_CARRO, 
				Constantes.TIPO_VEHICULO_CARRO).build();
		
		when(entradaVehiculoRepositorio.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Constantes.ESTADO_ACTIVO)).thenReturn(ConstantesTest.CANTIDAD_CARROS_MENOR_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Constantes.MENSAJE_SOBRECUPO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarNoDisponibilidadCarrosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_CARRO, 
				Constantes.TIPO_VEHICULO_CARRO).build();
		
		when(entradaVehiculoRepositorio.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Constantes.ESTADO_ACTIVO)).thenReturn(ConstantesTest.CANTIDAD_CARROS_IGUAL_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Constantes.MENSAJE_SOBRECUPO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarSiDisponibilidadMotosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				Constantes.TIPO_VEHICULO_CARRO).build();
		
		when(entradaVehiculoRepositorio.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Constantes.ESTADO_ACTIVO)).thenReturn(ConstantesTest.CANTIDAD_MOTOS_MENOR_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Constantes.MENSAJE_SOBRECUPO_CARRO, e.getMessage());
		}
	}
	
	@Test
	public void validarNoDisponibilidadMotosTest() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO, 
				Constantes.TIPO_VEHICULO_MOTO).build();
		
		when(entradaVehiculoRepositorio.cantidadCuposUsados(vehiculo.getTipoVehiculo(), 
				Constantes.ESTADO_ACTIVO)).thenReturn(ConstantesTest.CANTIDAD_MOTOS_IGUAL_AL_TOPE);
		
		try {
			validarCuposDisponibles.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Constantes.MENSAJE_SOBRECUPO_MOTOS, e.getMessage());
		}
	}
	
	@Test
	public void validarPlacaSiValidaDomingo() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_INICIAL_NO_VALIDA, 
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-10T06:00:00")).build();
		
		try {
			validarPlaca.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA, e.getMessage());
		}
	}
	
	@Test
	public void validarPlacaSiValidaLunes() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_INICIAL_NO_VALIDA, 
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-11T07:00:00")).build();
		
		try {
			validarPlaca.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertNotEquals(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA, e.getMessage());
		}
	}
	
	
	@Test
	public void validarPlacaNoValidaRestoSemana() {
		Vehiculo vehiculo = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_INICIAL_NO_VALIDA, 
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-14T09:00:00")).build();
		
		try {
			validarPlaca.ejecutarRegla(vehiculo);
		} catch (Exception e) {
			Assert.assertEquals(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA, e.getMessage());
		}
	}
	
	@Test
	public void validarConstantesPlaca() {
				
		Assert.assertNotNull(ConstantesTest.NUMERO_PLACA_MOTO);
		Assert.assertNotNull(ConstantesTest.NUMERO_PLACA_MOTO);
		Assert.assertNotNull(ConstantesTest.NUMERO_PLACA_INICIAL_NO_VALIDA);
		
	}
	
	@Test
	public void validarConstantesTipoVehiculo() {
				
		Assert.assertNotNull(ConstantesTest.TIPO_VEHICULO_INCORRECTO);
				
	}
	
	@Test
	public void validarConstantesCilindraje() {
				
		Assert.assertNotNull(ConstantesTest.CILINDRAJE_MOTO_MAYOR_TOPE);
		Assert.assertNotNull(ConstantesTest.CILINDRAJE_MOTO_MENOR_TOPE);
				
	}
	
	@Test
	public void validarConstantesTope() {
				
		Assert.assertNotNull(ConstantesTest.CANTIDAD_CARROS_IGUAL_AL_TOPE);
		Assert.assertNotNull(ConstantesTest.CANTIDAD_CARROS_MENOR_AL_TOPE);
		Assert.assertNotNull(ConstantesTest.CANTIDAD_MOTOS_IGUAL_AL_TOPE);
		Assert.assertNotNull(ConstantesTest.CANTIDAD_MOTOS_MENOR_AL_TOPE);
	}
	
	@Test
	public void validarCrearVehiculoMoto() {
		Vehiculo vehiculo = new VehiculoBuild(ConstantesTest.NUMERO_PLACA_MOTO,
				LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T06:00:00"),
				Constantes.TIPO_VEHICULO_MOTO,ConstantesTest.CILINDRAJE_MOTO_MENOR_TOPE,0).crearVehiculo();
		
		Assert.assertNotNull(vehiculo);
		
	}
	
	@Test
	public void validarCrearVehiculoCarro() {
		Vehiculo vehiculo = new VehiculoBuild(ConstantesTest.NUMERO_PLACA_CARRO,
				LocalDateTime.parse("2019-02-08T02:00:00"),
				LocalDateTime.parse("2019-02-08T06:00:00"),
				Constantes.TIPO_VEHICULO_CARRO,0,0).crearVehiculo();
		
		Assert.assertNotNull(vehiculo);
		
	}
}
