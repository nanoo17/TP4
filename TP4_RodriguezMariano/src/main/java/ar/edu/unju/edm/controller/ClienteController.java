package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.interfaces.IClienteService;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("otroImp")
	IClienteService clienteService;


	@GetMapping("/cliente/mostrar")
	public String crearCliente(Model model) {
		model.addAttribute("modoEditar", false);
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return "cliente";
	}

	@GetMapping("/cliente/editar/{numeroDocumento}")
	public String editarCliente(Model model,@PathVariable(name = "numeroDocumento") int dni) throws Exception{
		try{
			Cliente encontrado = clienteService.encontrarCliente(dni);
			model.addAttribute("unCliente", encontrado);
			model.addAttribute("modoEditar", true);
		}
		catch(Exception e){
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
			model.addAttribute("modoEditar", false);
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return "cliente";
	}
	@GetMapping("/cliente/eliminar/{numeroDocumento}")
	public String eliminarCliente(@PathVariable(name = "numeroDocumento")int dni,Model model) throws Exception{
		try {
			clienteService.eliminarCliente(dni);
		} catch (Exception e) {
			model.addAttribute("usuarioErrorMensaje", e.getMessage());
		}
		return "redirect:/cliente/mostrar";
	}
	
	@PostMapping("/cliente/guardar")
	public String guardarCliente(@ModelAttribute("unCliente") Cliente nuevoCliente, Model model) {
		clienteService.guardarCliente(nuevoCliente);
		return "redirect:/cliente/mostrar";
	}

	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado, Model model){
		clienteService.modificarCliente(clienteModificado);
		return "redirect:/cliente/mostrar";
	}
}
