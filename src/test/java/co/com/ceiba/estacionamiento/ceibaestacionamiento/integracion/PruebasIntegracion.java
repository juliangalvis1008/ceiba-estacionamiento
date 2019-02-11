package co.com.ceiba.estacionamiento.ceibaestacionamiento.integracion;


import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.builder.VehiculoTestDataBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.ParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoAdapter;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.unitarias.PruebasUnitarias;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebasIntegracion {
	
	@Autowired
	ParqueaderoAdapter parqueaderoAdapter;
	
	@Autowired 
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Test
	public void ingresarCarroValido() {
		Vehiculo carro = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO,
				Vigilante.TIPO_VEHICULO_CARRO).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.ingresarVehiculo(carro);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Vigilante.ESTADO_ACTIVO, carro.getTipoVehiculo()));
		
	}
	
	@Test
	public void ingresarCarroNoValido() {
		Vehiculo carro = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_INICIAL_NO_VALIDA,
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-09T06:00:00")).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
				
		Assert.assertEquals(Vigilante.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA,vigilante.ingresarVehiculo(carro) );
		
	}
	
	@Test
	public void ingresarMotoValida() {
		Vehiculo moto = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO,
				Vigilante.TIPO_VEHICULO_MOTO,PruebasUnitarias.CILINDRAJE_MOTO_MENOR_TOPE).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.ingresarVehiculo(moto);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Vigilante.ESTADO_ACTIVO, moto.getTipoVehiculo()));
		
	}
	
	@Test
	public void ingresarMotoNoNoValida() {
		Vehiculo moto = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_INICIAL_NO_VALIDA,
				Vigilante.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-09T06:00:00")).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
				
		Assert.assertEquals(Vigilante.MENSAJE_ACCESO_NO_AUTORIZADO_PLACA,vigilante.ingresarVehiculo(moto) );
		
	}
		
	@Test
	public void sacarMoto() {
		Vehiculo moto = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO,
				Vigilante.TIPO_VEHICULO_MOTO,LocalDateTime.parse("2019-02-10T06:00:00"),
				LocalDateTime.parse("2019-02-10T10:00:00"),PruebasUnitarias.CILINDRAJE_MOTO_MAYOR_TOPE).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.ingresarVehiculo(moto);
		vigilante.sacarVehiculo(moto);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Vigilante.ESTADO_INACTIVO, moto.getTipoVehiculo()));
	}
	
	@Test
	public void sacarCarro() {
		Vehiculo carro = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO,
				Vigilante.TIPO_VEHICULO_CARRO,LocalDateTime.parse("2019-02-10T06:00:00"),
				LocalDateTime.parse("2019-02-10T11:00:00")).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.ingresarVehiculo(carro);
		vigilante.sacarVehiculo(carro);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Vigilante.ESTADO_ACTIVO, carro.getTipoVehiculo()));
		
	}
	
	@Test
	public void ConsultarEstadoParqueaderoMotos() {
		Vehiculo moto = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_MOTO,
				Vigilante.TIPO_VEHICULO_MOTO,PruebasUnitarias.CILINDRAJE_MOTO_MENOR_TOPE).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		parqueaderoAdapter.ingresarVehiculo(moto);
	    List<Vehiculo> totalEstacionados = vigilante.listarVehiculosParqueadosPorTipo(Vigilante.ESTADO_ACTIVO, moto.getTipoVehiculo());
		Assert.assertNotNull(totalEstacionados);
	}
	
	@Test
	public void ConsultarEstadoParqueaderoCarros() {
		Vehiculo carro = new VehiculoTestDataBuild(PruebasUnitarias.NUMERO_PLACA_CARRO,
				Vigilante.TIPO_VEHICULO_CARRO).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		parqueaderoAdapter.ingresarVehiculo(carro);
	    List<Vehiculo> totalEstacionados = vigilante.listarVehiculosParqueadosPorTipo(Vigilante.ESTADO_ACTIVO, carro.getTipoVehiculo());
		Assert.assertNotNull(totalEstacionados);
	}

}
