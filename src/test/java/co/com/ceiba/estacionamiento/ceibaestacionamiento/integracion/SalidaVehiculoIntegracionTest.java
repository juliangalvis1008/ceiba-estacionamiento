package co.com.ceiba.estacionamiento.ceibaestacionamiento.integracion;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.builder.VehiculoTestDataBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.ConsultaVehiculoController;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.EntradaVehiculoController;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.SalidaVehiculoController;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalidaVehiculoIntegracionTest {
	
	@Autowired
	EntradaVehiculoController entradaVehiculoController;
	
	@Autowired
	SalidaVehiculoController salidaVehiculoController;
	
	@Autowired
	ConsultaVehiculoController consultaVehiculoController;
	
	public static final String NUMERO_PLACA_MOTO = "CMO48C";	
	public static final String NUMERO_PLACA_CARRO = "VHC085";
	public static final int CILINDRAJE_MOTO_MAYOR_TOPE = 650;
	
		
	@Test
	public void sacarMoto() {
		VehiculoTestDataBuild moto = new VehiculoTestDataBuild(SalidaVehiculoIntegracionTest.NUMERO_PLACA_MOTO,
				Constantes.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-10T06:00:00"),
				LocalDateTime.parse("2019-02-10T10:00:00"),SalidaVehiculoIntegracionTest.CILINDRAJE_MOTO_MAYOR_TOPE);
		entradaVehiculoController.ingresarVehiculo(moto.crearVehiculoBuild());
		salidaVehiculoController.sacarVehiculo(moto.crearVehiculoBuild());
		
		Assert.assertNotNull(consultaVehiculoController.consultarEstadoActualParqueados( moto.build().getTipoVehiculo()));
	}
	
	@Test
	public void sacarCarro() {
		VehiculoTestDataBuild carro = new VehiculoTestDataBuild(SalidaVehiculoIntegracionTest.NUMERO_PLACA_CARRO,
				Constantes.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-10T06:00:00"),
				LocalDateTime.parse("2019-02-10T11:00:00"));
		entradaVehiculoController.ingresarVehiculo(carro.crearVehiculoBuild());
		salidaVehiculoController.sacarVehiculo(carro.crearVehiculoBuild());
		
		Assert.assertNotNull(consultaVehiculoController.consultarEstadoActualParqueados( carro.build().getTipoVehiculo()));
	}
}
