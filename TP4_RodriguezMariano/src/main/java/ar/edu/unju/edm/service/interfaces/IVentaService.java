package ar.edu.unju.edm.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Venta;
@Service
public interface IVentaService {
	public void guardarVenta(Venta unaVenta);
	public Venta crearVenta();
	public List<Venta> obtenerTodaVenta();
	public Venta encontrarUnaVenta(int id) throws Exception;
	public void eliminarVenta(int id);
}
