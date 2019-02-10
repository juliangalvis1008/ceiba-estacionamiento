package co.com.ceiba.estacionamiento.ceibaestacionamiento.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.entidad.ParqueaderoEntity;

@Repository
public interface ParqueaderoRepositorio extends JpaRepository<ParqueaderoEntity, Long> {
  
	public int countByTipovehiculoAndEstadoactivo(String tipovehiculo,boolean estadoactivo);
	public ParqueaderoEntity findByTipovehiculoAndPlacaAndEstadoactivo(String tipovehiculo,String placa,boolean estadoactivo);
	public List<ParqueaderoEntity> findByEstadoactivo(boolean estadoactivo);
	public List<ParqueaderoEntity> findByEstadoactivoAndTipovehiculo(boolean estadoactivo,String tipovehiculo);
  
}
