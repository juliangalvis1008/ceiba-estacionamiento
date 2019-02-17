package co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.ConsultaVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio.ConsultaVehiculoRepositorio;

@RestController
@CrossOrigin("http://localhost:4200")
public class ConsultaVehiculoController {

	@Autowired
	ConsultaVehiculoRepositorio consultaVehiculoRepositorio;
	
	@RequestMapping(value = "/listarVehiculosParqueados/{tipovehiculo}",method = RequestMethod.GET)
	public List<Vehiculo> consultarEstadoActualParqueados(@PathVariable("tipovehiculo") String tipoVehiculo) {
		ConsultaVehiculo consultaVehiculo = new ConsultaVehiculo(consultaVehiculoRepositorio);
		return consultaVehiculo.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_ACTIVO,tipoVehiculo);
	}
	
	@RequestMapping(value = "/listarVehiculosLiquidados/{tipovehiculo}",method = RequestMethod.GET)
	public List<Vehiculo> consultarEstadoActualLiquidados(@PathVariable("tipovehiculo") String tipoVehiculo) {
		ConsultaVehiculo consultaVehiculo = new ConsultaVehiculo(consultaVehiculoRepositorio);
		return consultaVehiculo.listarVehiculosParqueadosPorTipo(Constantes.ESTADO_INACTIVO,tipoVehiculo);
	}
	
	}
