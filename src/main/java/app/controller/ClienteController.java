package app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Cliente;
@RestController(value="/clientes")
public class ClienteController{
	
	//Métodos de Regra de negócio
	private List<Cliente> listaCliente =  new ArrayList<Cliente>();
	 
	 private int idGerado = 1;
	
	 private Cliente cadastrar(Cliente cli){
		 cli.setId(idGerado++);
		 listaCliente.add(cli);
		 return cli;
	 }
	 
	 private Cliente buscar(Integer id){
		 
		 for (Cliente c: listaCliente){
			 if (id==c.getId()){
				 return c;
			 }
		 }
		 return null;
	 }

	 
	 //Métodos Controlller
	 @RequestMapping(method=RequestMethod.POST)
	 public Cliente salvar (@RequestBody Cliente cliente){ 
		 	cliente = cadastrar(cliente);
			return cliente;
	}
	
	@RequestMapping(value="/", 	method=RequestMethod.GET) 
	public List<Cliente> listar(){
		return listaCliente;
	}
	
	@RequestMapping(value="/{id}" ,method=RequestMethod.GET)
	public Cliente buscarPorId(@RequestParam ("id") Integer id){
		return buscar(id);
	}
	
}
