package br.com.tutorial.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tutorial.DAO.PessoaDAO;
import br.com.tutorial.model.PessoaModel;

/**
 * Servlet implementation class ControllerServelet
 */
@WebServlet(description = "Controller Servelet", urlPatterns = { "/ControllerServelet" })
public class ControllerServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private PessoaDAO pessoaDAO;
    public ControllerServelet() {
        super();
        pessoaDAO = new PessoaDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		ArrayList<PessoaModel> pessoas;
		try {	
			pessoas = pessoaDAO.pessoas();
			request.setAttribute("pessoas", pessoas);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String action = request.getParameter("acao");
			if(action != null && action.equals("deletar")) {
				doDelete(request, response);
				doGet(request, response);				
			}else {
				String nome = (String)request.getParameter("nome");
				int idade = Integer.parseInt(request.getParameter("idade"));
				PessoaModel pessoa = new PessoaModel();
				pessoa.setNome(nome);
				pessoa.setIdade(idade);
				pessoaDAO.inserirPessoa(pessoa);
				doGet(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			pessoaDAO.removerPessoa(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
