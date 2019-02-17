package co.com.ceiba.estacionamiento.ceibaestacionamiento.integracion;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.ConstantesTest;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.builder.VehiculoTestDataBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.ConsultaVehiculoController;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.EntradaVehiculoController;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.SalidaVehiculoController;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntradaVehiculoIntegracionTest {

	@Autowired
	EntradaVehiculoController entradaVehiculoController;
	
	@Autowired
	SalidaVehiculoController salidaVehiculoController;
	
	@Autowired
	ConsultaVehiculoController consultaVehiculoController;
	
	@Test
	public void ingresarCarroValido() {
		VehiculoTestDataBuild carro = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_CARRO,
				Constantes.TIPO_VEHICULO_CARRO);
		entradaVehiculoController.ingresarVehiculo(carro.crearVehiculoBuild());
		
		Assert.assertNotNull(consultaVehiculoController.consultarEstadoActualParqueados( Constantes.TIPO_VEHICULO_CARRO));
		
	}
	
	@Test
	public void ingresarCarroNoValido() {
		VehiculoTestDataBuild carro = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_INICIAL_NO_VALIDA,
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-09T06:00:00"));
		
		if(LocalDateTime.now().getDayOfWeek() != DayOfWeek.MONDAY && LocalDateTime.now().getDayOfWeek() != DayOfWeek.SUNDAY) {
			Assert.assertEquals(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA,entradaVehiculoController.ingresarVehiculo(carro.crearVehiculoBuild()));
		}else {
			Assert.assertNotEquals(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA,entradaVehiculoController.ingresarVehiculo(carro.crearVehiculoBuild()));
		}
		
	}
	
	@Test
	public void ingresarMotoValida() {
		VehiculoTestDataBuild moto = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_MOTO,
				Constantes.TIPO_VEHICULO_MOTO,ConstantesTest.CILINDRAJE_MOTO_MENOR_TOPE);
		entradaVehiculoController.ingresarVehiculo(moto.crearVehiculoBuild());
		
		Assert.assertNotNull(consultaVehiculoController.consultarEstadoActualParqueados( moto.build().getTipoVehiculo()));
		
	}
	
	@Test
	public void ingresarMotoNoNoValida() {
		VehiculoTestDataBuild moto = new VehiculoTestDataBuild(ConstantesTest.NUMERO_PLACA_INICIAL_NO_VALIDA,
				Constantes.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-09T06:00:00"));
				
		if(LocalDateTime.now().getDayOfWeek() != DayOfWeek.MONDAY && LocalDateTime.now().getDayOfWeek() != DayOfWeek.SUNDAY) {
			Assert.assertEquals(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA,entradaVehiculoController.ingresarVehiculo(moto.crearVehiculoBuild()));
		}else {
			Assert.assertNotEquals(Constantes.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA,entradaVehiculoController.ingresarVehiculo(moto.crearVehiculoBuild()));
		}		
	}
	
}
