package co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador.mensaje.Mensajes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.SalidaVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.VehiculoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicios.SalidaVehiculoService;

@RestController
@CrossOrigin("http://localhost:4200")

public class SalidaVehiculoController {

	@Autowired
	SalidaVehiculoService salidaVehiculoRepositorio;
	
	@PostMapping(value = "/sacarVehiculo")
	public Mensajes sacarVehiculo(@RequestBody VehiculoBuild vehiculo) {
		
		SalidaVehiculo salidaVehiculo = new SalidaVehiculo(salidaVehiculoRepositorio);
		vehiculo.setFechaSalida(LocalDateTime.now());
		return new Mensajes(salidaVehiculo.sacarVehiculo(vehiculo.crearVehiculo())); 
	}

}
