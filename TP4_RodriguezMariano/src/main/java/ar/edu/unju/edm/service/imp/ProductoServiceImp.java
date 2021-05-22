package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.interfaces.IProductoService;
import ar.edu.unju.edm.util.ListaProductos;

@Service
public class ProductoServiceImp implements IProductoService {

	private static final Log LOGGER= LogFactory.getLog(ProductoServiceImp.class) ;

	@Autowired
	Producto unProducto;
	
	public List<Producto> listaDeProductos = ListaProductos.productos;

	@Override
	public void guardarProducto(Producto unProducto) {
		listaDeProductos.add(unProducto);
		LOGGER.info("Producto Guardado: " + unProducto.getCodigo());
	}


	@Override
	public void eliminarProducto(int codigo) {
		for(int i = 0; i < listaDeProductos.size(); i++){
			if(listaDeProductos.get(i).getCodigo() == codigo){
				listaDeProductos.remove(i);
				break;
			}
		}
	}

	@Override
	public Producto obtenerProducto(int codigo) {
		for(int i = 0; i < listaDeProductos.size(); i++){
			if(listaDeProductos.get(i).getCodigo() == codigo){
				return listaDeProductos.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Producto> obtenerProductos() {
		return listaDeProductos;
	}

	@Override
	public Producto obtenerNuevoProducto() {
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		int index = listaDeProductos.size() - 1;
		LOGGER.info("Ultimo producto Guardado: " + listaDeProductos.get(index).getCodigo());
		return listaDeProductos.get(index);
	}

	@Override
	public void modificarProducto(Producto productoModificado) {
		for(int i = 0; i < listaDeProductos.size(); i++){
			if(listaDeProductos.get(i).getCodigo() == productoModificado.getCodigo()){
				listaDeProductos.set(i, productoModificado);
				break;
			}
		}
	}
}
