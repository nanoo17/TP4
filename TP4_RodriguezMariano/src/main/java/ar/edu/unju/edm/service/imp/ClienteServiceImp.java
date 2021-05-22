package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.interfaces.IClienteService;
import ar.edu.unju.edm.util.ListaClientes;

@Service
public class ClienteServiceImp implements IClienteService {

	public List<Cliente> clientes = ListaClientes.clientes;

	@Autowired
	Cliente unCliente;

	@Override
	public void guardarCliente(Cliente unCliente) {
		clientes.add(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {

		return clientes;
	}

	@Override
	public Cliente encontrarCliente(int dni) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNumeroDocumento() == dni) {
				return clientes.get(i);
			}
		}
		return null;
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNumeroDocumento() == clienteModificado.getNumeroDocumento()) {
				clientes.set(i, clienteModificado);
				break;
			}
		}
	}

	@Override
	public void eliminarCliente(int dni) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNumeroDocumento() == dni) {
				clientes.remove(i);
				break;
			}
		}
	}
}
