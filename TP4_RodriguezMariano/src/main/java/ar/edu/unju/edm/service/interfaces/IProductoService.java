package ar.edu.unju.edm.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;

@Service
public interface IProductoService {
	
	void guardarProducto(Producto unProducto);
	void eliminarProducto(int codigo);
	Producto obtenerProducto(int codigo);
	List<Producto> obtenerProductos();
	Producto obtenerNuevoProducto();
	Producto obtenerUltimoProducto();
	void modificarProducto(Producto productoModificado);
}
