package co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.SalidaVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.builder.VehiculoBuild;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.SalidaVehiculoRepositorio;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/salidaVehiculo")
public class SalidaVehiculoController {

	@Autowired
	SalidaVehiculoRepositorio salidaVehiculoRepositorio;
	
	@PutMapping(value = "/sacarVehiculo")
	public String sacarVehiculo(@RequestBody VehiculoBuild vehiculo) {
		SalidaVehiculo salidaVehiculo = new SalidaVehiculo(salidaVehiculoRepositorio);
		 return salidaVehiculo.sacarVehiculo(vehiculo.crearVehiculo());
	}

}
