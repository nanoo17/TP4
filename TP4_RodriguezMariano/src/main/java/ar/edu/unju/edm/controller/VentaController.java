package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.model.Venta;
import ar.edu.unju.edm.service.interfaces.IClienteService;
import ar.edu.unju.edm.service.interfaces.IVentaService;
import ar.edu.unju.edm.service.interfaces.IProductoService;

@Controller
public class VentaController {
	
	@Autowired
	@Qualifier("sqlimp")
	IProductoService iProductoService;
	
	@Autowired
	@Qualifier("otroImp")
	IClienteService clienteService;
	
	@Autowired
	Producto productoSeleccionado;
	
	@Autowired
	IVentaService iVentas;
	
	@GetMapping("/producto/ventas")
	public String mostrarVentas(Model model) {
		model.addAttribute("productos",iProductoService.obtenerProductos());
		return ("resultado");				
	}
	
	
	@GetMapping("/producto/vender/{codigo}")	
	public String realizarVenta(Model model, @PathVariable(name="codigo") Integer codigo) throws Exception {
		Venta venta = new Venta();		
		try {		
			System.out.println("entran2" + codigo);
			productoSeleccionado = iProductoService.obtenerProductoCodigo(codigo);
			System.out.println(productoSeleccionado.getCodigo());
			venta = iVentas.crearVenta();		
			venta.setProducto(productoSeleccionado);
			model.addAttribute("venta",venta);
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());		
		}		
		return "modal-venta";
	}
	
	@PostMapping("/producto/vender")
	public String guardarNuevoProducto(@ModelAttribute("venta") Venta unaVenta, Model model){
		iVentas.guardarVenta(unaVenta);
		productoSeleccionado.setStock(productoSeleccionado.getStock() - unaVenta.getCantProductos());
		iProductoService.guardarProducto(productoSeleccionado);
		System.out.println(productoSeleccionado.getStock());
		return("redirect:/producto/ventas");
	}

}