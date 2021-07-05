package ar.edu.unju.edm.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.edm.model.Producto;

public interface IproductoDAO extends CrudRepository<Producto,Integer>{

	@Query("from Producto c order by c.codigo")
	public List<Producto> obtenerProductos();
}
