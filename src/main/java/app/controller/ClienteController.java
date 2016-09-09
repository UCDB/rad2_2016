package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;

@RestController
public class ClienteController {

	// Métodos de Regra de negócio
	private List<Cliente> listaCliente = new ArrayList<Cliente>();

	private int idGerado = 1;

	private Cliente cadastrar(Cliente cli) {
		cli.setId(idGerado++);
		listaCliente.add(cli);
		return cli;
	}

	// [ {id:1, nome: jao} , {id:2, nome:ze } , {id:3, nome:maria} ]
	// 0 1 2
	private Cliente alterar(Cliente cli) {
		// Buscar pelo indice pelo ID
		int indice = buscar(cli);
		if (indice != -1) {
			listaCliente.set(indice, cli);
		}
		return cli;
	}

	private int buscar(Cliente cli) {
		for (int i = 0; i < listaCliente.size(); i++) {
			if (cli.getId().equals(listaCliente.get(i).getId())) {
				return i;
			}
		}
		return -1;
	}

	private Cliente buscar(Integer id) {

		for (Cliente c : listaCliente) {
			if (c.getId().equals(id)) {
				return c;
			}
		}

		return null;
	}

	public void excluir(Integer id) {

		Cliente c = new Cliente();
		c.setId(id);
		int indice = buscar(c);
		listaCliente.remove(indice);
	}

	// Métodos Controlller
	@RequestMapping(value = "/clientes", method = RequestMethod.POST)
	public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
		cliente = cadastrar(cliente);
		return cliente;
	}

	// Métodos Controlller
	@RequestMapping(value = "/clientes", method = RequestMethod.PUT)
	public Cliente alterarCliente(@RequestBody Cliente cliente) {
		cliente = alterar(cliente);
		return cliente;
	}

	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
	public List<Cliente> listarClientes() {
		return listaCliente;
	}

	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
	public Cliente buscarClientePorId(@PathVariable("id") Integer id) {

		return buscar(id);
	}

	@RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
	public void excluirCliente(@PathVariable("id") Integer id) {

		excluir(id);
	}

}
