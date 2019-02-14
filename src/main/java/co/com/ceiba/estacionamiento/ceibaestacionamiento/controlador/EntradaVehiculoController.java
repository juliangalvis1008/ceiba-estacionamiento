package co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.EntradaVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.VehiculoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.EntradaVehiculoRepositorio;


@RestController
@CrossOrigin("http://localhost:4200")

public class EntradaVehiculoController {

	@Autowired
	EntradaVehiculoRepositorio entradaVehiculoRepositorio;

	@PostMapping(value = "/ingresarVehiculo")
	public String ingresarVehiculo(@RequestBody VehiculoBuild vehiculoBuild) {

		EntradaVehiculo entradaVehiculo = new EntradaVehiculo(entradaVehiculoRepositorio);
		return entradaVehiculo.ingresarVehiculo(vehiculoBuild.crearVehiculo());

	}	
}