package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.interfaces.IProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	@Qualifier("sqlimp")
	IProductoService  iProductoService;
	

	@GetMapping("/producto")
	public ModelAndView cargarProducto() {
		ModelAndView modelView = new ModelAndView("producto");
		modelView.addObject("modoEditar", false);
		modelView.addObject("unProducto", iProductoService.obtenerNuevoProducto());
		return modelView;
	}
	
	
	@GetMapping("/volver")
	public String cargarNuevoProducto() {
		return ("redirect:/producto");
	}
	@GetMapping("/volver/resultado")
	public String volverAResultado(Model model) {
		model.addAttribute("productos", iProductoService.obtenerProductos());
		return "resultado";
	}


	@GetMapping("/ultimo-producto")
	public String mostrarUltimoProducto(Model model) {
		try {
			model.addAttribute("ultimo", iProductoService.obtenerUltimoProducto());
			return "ultimo-producto";
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			model.addAttribute("productos", iProductoService.obtenerProductos());
			return "resultado";
		}
		
	}
	@GetMapping("/producto/editar/{codigo}")
	public String editarProducto(@PathVariable(name = "codigo")Integer codigo, Model model) throws Exception{
		try {
			Producto encontrado = iProductoService.obtenerProducto(codigo);
			model.addAttribute("unProducto", encontrado);
			model.addAttribute("modoEditar", true);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			model.addAttribute("modoEditar", false);
		}
		model.addAttribute("productos", iProductoService.obtenerProductos());
		return "producto";
	}
	@GetMapping("/producto/eliminar/{codigoProducto}")
	public String eliminarProducto(@PathVariable(name = "codigoProducto") Integer codigo,Model model) throws Exception{
		try {
			System.out.println("entrando a borrar producto"+ codigo);
			iProductoService.eliminarProducto(codigo);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
		}
		//model.addAttribute("productos", iProductoService.obtenerProductos());
		return "redirect:/producto/mostrar";
	}

	@GetMapping("/producto/mostrar")
	public String mostrarProductos(Model modelo){
		modelo.addAttribute("productos", iProductoService.obtenerProductos());
		return "resultado";
	}
	
	@PostMapping("/producto/guardar")
	public String productoGuardar(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		iProductoService.guardarProducto(nuevoProducto);
		model.addAttribute("productos", iProductoService.obtenerProductos());
		return "resultado";
	}

	@PostMapping("/producto/modificar")
	public String productoModificar(@ModelAttribute(name = "unProducto")Producto productoModificado,Model model){
		iProductoService.modificarProducto(productoModificado);
		model.addAttribute("productos", iProductoService.obtenerProductos());
		return "resultado";
	}
}
