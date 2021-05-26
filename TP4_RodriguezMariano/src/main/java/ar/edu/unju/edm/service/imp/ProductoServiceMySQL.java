package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IproductoDAO;
import ar.edu.unju.edm.service.interfaces.IProductoService;

@Service
@Qualifier("sqlimp")
public class ProductoServiceMySQL implements IProductoService{
@Autowired
Producto unProducto;
@Autowired
IproductoDAO productoDAO;
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}

	@Override
	public void eliminarProducto(int codigo) throws Exception {
		// TODO Auto-generated method stub
		Producto productoEliminar = productoDAO.findBycodigo(codigo).orElseThrow(()-> new Exception("Producto no encontrado"));
		productoDAO.delete(productoEliminar);;
	}

	@Override
	public Producto obtenerProducto(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
	 return (List<Producto>) productoDAO.findAll();
	}

	@Override
	public Producto obtenerNuevoProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		
	}

}
