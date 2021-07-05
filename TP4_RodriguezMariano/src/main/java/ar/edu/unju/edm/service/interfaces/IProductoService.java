package ar.edu.unju.edm.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;

@Service
public interface IProductoService {
	
	void guardarProducto(Producto unProducto);
	List<Producto> obtenerProductos();
	Producto obtenerNuevoProducto();
	Producto obtenerUltimoProducto();
	public void modificarProducto(Producto productoModificado);
	public void eliminarProducto(Integer codigo) throws Exception;
	public Producto obtenerProducto(Integer codigo);
	public Producto obtenerProductoCodigo(Integer codigo);
}
