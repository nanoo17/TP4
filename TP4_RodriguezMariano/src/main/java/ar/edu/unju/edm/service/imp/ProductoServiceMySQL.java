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
	public void eliminarProducto(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		/*System.out.println(codigo + " codigo que llega");
		Producto productoEliminar = productoDAO.findById(codigo).orElseThrow(()->new Exception("Producto no encontrado"));
		System.out.println("codigo eliminado "+ productoEliminar);
		productoDAO.delete(productoEliminar);;*/
		productoDAO.deleteById(codigo);
	}


	@Override
	public Producto obtenerProducto(Integer codigo) {
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

	@Override
	public Producto obtenerProductoCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		System.out.println(productoDAO.findById(codigo)+ "codigo encontrado");
		return productoDAO.findById(codigo).orElseThrow();
	}
	
}
