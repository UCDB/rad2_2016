package cadastrocliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
@WebServlet(value="/clientes")
public class ClienteController extends HttpServlet {
	
	//Métodos de Regra de negócio
	private List<Cliente> listaCliente =  new ArrayList<Cliente>();
	 
	 private int idGerado = 1;
	
	 private Cliente cadastrar(Cliente cli){
		 cli.setId(idGerado++);
		 listaCliente.add(cli);
		 return cli;
	 }

	 
	 //Método do controller
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Captura da tela
			String nome = req.getParameter("nome");
			String email =  req.getParameter("email");
			//Instancia Objeto
			Cliente cli =  new Cliente();
			cli.setNome(nome);
			cli.setEmail(email);
				
			//Grava
			cli = cadastrar(cli);
			
			//Retornar pra tela  //JSON {  id: '1 , nome: 'jao', email: 'jao@htcursos.com' } => Objeto JS
			resp.getWriter().println(cli.getId() + " "+  cli.getEmail() + " "+ cli.getNome());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dados = "{ \"clientes\": [ {\"id\": \"1\", \"nome\":\"jao do controller\", \"email\": \"jao@x.com\" } , {\"id\": \"2\", \"nome\":\"ze\", \"email\": \"ze@x.com\" } ] }";
		resp.getWriter().println(dados);
	}
}
