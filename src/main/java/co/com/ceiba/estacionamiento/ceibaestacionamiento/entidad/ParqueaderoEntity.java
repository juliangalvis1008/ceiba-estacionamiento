package co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity 
@Table(
	    uniqueConstraints={@UniqueConstraint(columnNames={"placa", "estadoactivo","tipovehiculo","fechaingreso","fechasalida"})}
	)
public class ParqueaderoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String placa;
	
	@Column
	private LocalDateTime fechaingreso;
	
	@Column
	private LocalDateTime fechasalida;
	
	@Column
	private String tipovehiculo;
	
	@Column
	private int cilindraje;
	
	@Column
	private double valorcobro;
	
	@Column
	private boolean estadoactivo;
	
	
	public ParqueaderoEntity() {
		
	}
	
	public ParqueaderoEntity( String placa, LocalDateTime fechaIngreso, LocalDateTime fechaSalida,
			String tipoVehiculo, int cilindraje, double valorCobro, boolean estadoActivo) {
		
		
		this.placa = placa;
		this.fechaingreso = fechaIngreso;
		this.fechasalida = fechaSalida;
		this.tipovehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.valorcobro = valorCobro;
		this.estadoactivo = estadoActivo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public LocalDateTime getFechaIngreso() {
		return fechaingreso;
	}
	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaingreso = fechaIngreso;
	}
	public LocalDateTime getFechaSalida() {
		return fechasalida;
	}
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechasalida = fechaSalida;
	}
	public String getTipoVehiculo() {
		return tipovehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipovehiculo = tipoVehiculo;
	}
	public int getCilindraje() {
		return cilindraje;
	}
	public void setCilindraje(int cilindraje) {
		this.cilindraje = cilindraje;
	}
	public double getValorCobro() {
		return valorcobro;
	}
	public void setValorCobro(double valorCobro) {
		this.valorcobro = valorCobro;
	}
	public boolean getEstadoActivo() {
		return estadoactivo;
	}
	public void setEstadoActivo(boolean estadoActivo) {
		this.estadoactivo = estadoActivo;
	}
	

}
