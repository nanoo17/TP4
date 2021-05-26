package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IclienteDAO;
import ar.edu.unju.edm.service.interfaces.IClienteService;
@Service
@Qualifier("otroImp")
public class ClienteServiceMySQL implements IClienteService{
@Autowired
Cliente unCliente;
@Autowired
IclienteDAO clienteDAO;
	@Override
	public void guardarCliente(Cliente unCliente) {
		// TODO Auto-generated method stub
		clienteDAO.save(unCliente);
	}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	   @Override
	    public Cliente encontrarCliente(int dni) throws Exception{
	        return clienteDAO.findBynumeroDocumento(dni).orElseThrow(()->new Exception("El cliente no existe"));
	    }

	 @Override
	    public void modificarCliente(Cliente clienteModificado) throws Exception {
	        Cliente modificarCliente = encontrarCliente(clienteModificado.getNumeroDocumento());
	        cambiarCliente(clienteModificado, modificarCliente);
	        clienteDAO.save(modificarCliente);
	    }

	@Override
	public void eliminarCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		Cliente clienteEliminar = clienteDAO.findBynumeroDocumento(dni).orElseThrow(()-> new Exception("el cliente no fue encontrado"));
		clienteDAO.delete(clienteEliminar);
	}
    private void cambiarCliente(Cliente desde, Cliente hacia) {
		hacia.setNumeroDocumento(desde.getNumeroDocumento());
		hacia.setTipoDeDocumento(desde.getTipoDeDocumento());
		hacia.setFechaNacimiento(desde.getFechaNacimiento());
		hacia.setCodigoAreaTelefono(desde.getCodigoAreaTelefono());
		hacia.setNumeroTelefono(desde.getNumeroTelefono());
        hacia.setFechaUltimaCompra(desde.getFechaUltimaCompra());
		hacia.setEmail(desde.getEmail());
        hacia.setNombreApellido(desde.getNombreApellido());
	}
}
