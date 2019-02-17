package co.com.ceiba.estacionamiento.ceibaestacionamiento.integracion;

import java.util.List;

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
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.modelo.Vehiculo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsultaVehiculoIntegracionTest {

	@Autowired
	EntradaVehiculoController entradaVehiculoController;
	
	@Autowired
	SalidaVehiculoController salidaVehiculoController;
	
	@Autowired
	ConsultaVehiculoController consultaVehiculoController;
	
	public static final String NUMERO_PLACA_MOTO = "CMO48C";	
	public static final String NUMERO_PLACA_CARRO = "VHC085";
	public static final int CILINDRAJE_MOTO_MENOR_TOPE = 200;
	
	
	@Test
	public void ConsultarEstadoParqueaderoMotos() {
		VehiculoTestDataBuild moto = new VehiculoTestDataBuild(ConsultaVehiculoIntegracionTest.NUMERO_PLACA_MOTO,
				Constantes.TIPO_VEHICULO_MOTO,ConsultaVehiculoIntegracionTest.CILINDRAJE_MOTO_MENOR_TOPE);
		entradaVehiculoController.ingresarVehiculo(moto.crearVehiculoBuild());
		
		List<Vehiculo> totalEstacionados = consultaVehiculoController.consultarEstadoActualParqueados( moto.build().getTipoVehiculo());
		Assert.assertNotNull(totalEstacionados);
	}
	
	@Test
	public void ConsultarEstadoParqueaderoCarros() {
		VehiculoTestDataBuild carro = new VehiculoTestDataBuild(ConsultaVehiculoIntegracionTest.NUMERO_PLACA_CARRO,
				Constantes.TIPO_VEHICULO_CARRO);
		entradaVehiculoController.ingresarVehiculo(carro.crearVehiculoBuild());
				
		List<Vehiculo> totalEstacionados = consultaVehiculoController.consultarEstadoActualParqueados( carro.build().getTipoVehiculo());
		Assert.assertNotNull(totalEstacionados);
	}
}
