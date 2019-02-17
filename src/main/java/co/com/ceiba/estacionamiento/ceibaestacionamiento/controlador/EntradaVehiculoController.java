package co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.mensaje.Mensajes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.EntradaVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.VehiculoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.EntradaVehiculoService;


@RestController
@CrossOrigin("http://localhost:4200")

public class EntradaVehiculoController {

	@Autowired
	EntradaVehiculoService entradaVehiculoRepositorio;

	@PostMapping(value = "/ingresarVehiculo")
	public Mensajes ingresarVehiculo(@RequestBody VehiculoBuild vehiculo) {

		EntradaVehiculo entradaVehiculo = new EntradaVehiculo(entradaVehiculoRepositorio);
		vehiculo.setFechaIngreso(LocalDateTime.now());
		return new Mensajes(entradaVehiculo.ingresarVehiculo(vehiculo.crearVehiculo()));

	}	
}