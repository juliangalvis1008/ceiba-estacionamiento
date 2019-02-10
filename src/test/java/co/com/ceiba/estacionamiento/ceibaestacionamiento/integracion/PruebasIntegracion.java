package co.com.ceiba.estacionamiento.ceibaestacionamiento.integracion;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.builder.VehiculoTestDataBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.ParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoAdapter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebasIntegracion {
	
	@Autowired
	ParqueaderoAdapter parqueaderoAdapter;
	
	@Autowired 
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Test
	public void ingresarCarro() {
		Vehiculo carro = new VehiculoTestDataBuild(Constantes.NUMERO_PLACA_CARRO,
				Constantes.TIPO_VEHICULO_CARRO).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.ingresarVehiculo(carro);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_ACTIVO, carro.getTipoVehiculo()));
		
	}
	
	@Test
	public void ingresarMoto() {
		Vehiculo moto = new VehiculoTestDataBuild(Constantes.NUMERO_PLACA_MOTO,
				Constantes.TIPO_VEHICULO_MOTO,Constantes.CILINDRAJE_MOTO_MENOR_TOPE).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.ingresarVehiculo(moto);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_ACTIVO, moto.getTipoVehiculo()));
		
	}
	
	@Test
	public void SacarCarro() {
		Vehiculo carro = new VehiculoTestDataBuild(Constantes.NUMERO_PLACA_CARRO,
				Constantes.TIPO_VEHICULO_CARRO).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.sacarVehiculo(carro);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_INACTIVO, carro.getTipoVehiculo()));
		
	}
	
	@Test
	public void sacarMoto() {
		Vehiculo moto = new VehiculoTestDataBuild(Constantes.NUMERO_PLACA_MOTO,
				Constantes.TIPO_VEHICULO_MOTO,Constantes.CILINDRAJE_MOTO_MENOR_TOPE).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.sacarVehiculo(moto);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_INACTIVO, moto.getTipoVehiculo()));
	}	
	
	@Test
	public void sacarCarro() {
		Vehiculo carro = new VehiculoTestDataBuild(Constantes.NUMERO_PLACA_CARRO,
				Constantes.TIPO_VEHICULO_CARRO).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		vigilante.sacarVehiculo(carro);
		
		Assert.assertNotNull(parqueaderoAdapter.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_INACTIVO, carro.getTipoVehiculo()));
		
	}
	
	@Test
	public void ConsultarEstadoParqueaderoMotos() {
		Vehiculo moto = new VehiculoTestDataBuild(Constantes.NUMERO_PLACA_MOTO,
				Constantes.TIPO_VEHICULO_MOTO,Constantes.CILINDRAJE_MOTO_MENOR_TOPE).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		parqueaderoAdapter.ingresarVehiculo(moto);
	    List<Vehiculo> totalEstacionados = vigilante.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_ACTIVO, moto.getTipoVehiculo());
		Assert.assertNotNull(totalEstacionados);
	}
	
	@Test
	public void ConsultarEstadoParqueaderoCarros() {
		Vehiculo carro = new VehiculoTestDataBuild(Constantes.NUMERO_PLACA_CARRO,
				Constantes.TIPO_VEHICULO_CARRO).build();
		Vigilante vigilante = new Vigilante(parqueaderoAdapter);
		parqueaderoAdapter.ingresarVehiculo(carro);
	    List<Vehiculo> totalEstacionados = vigilante.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_ACTIVO, carro.getTipoVehiculo());
		Assert.assertNotNull(totalEstacionados);
	}

}
