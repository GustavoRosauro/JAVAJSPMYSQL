package br.com.tutorial.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import br.com.tutorial.model.PessoaModel;

public class PessoaDAO {
	public ArrayList<PessoaModel> pessoas() throws SQLException{
		try {
			Connection conn = Conn.getConn();
			ArrayList<PessoaModel> pessoas = new ArrayList<PessoaModel>();
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM PESSOA ORDER BY NOME");
			while(result.next()) {
				PessoaModel p = new PessoaModel();
				p.setId(result.getLong("id"));
				p.setNome(result.getString("nome"));
				p.setIdade(result.getInt("idade"));
				pessoas.add(p);
			}
			result.close();
			conn.close();
			return pessoas;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
	public void inserirPessoa(PessoaModel pessoa) throws SQLException {
		try {
			Connection conn = Conn.getConn();
			String sql = "INSERT INTO PESSOA (NOME,IDADE) VALUES (?,?)";
			java.sql.PreparedStatement preparedsmt = conn.prepareStatement(sql);
			preparedsmt.setString(1, pessoa.nome);
			preparedsmt.setInt(2,pessoa.idade);
			preparedsmt.execute();
			conn.close();
		}catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
	public void removerPessoa(long id) throws SQLException {
		try {			Connection conn = Conn.getConn();
			String sql = "DELETE FROM PESSOA WHERE ID = "+Long.toString(id);
		    PreparedStatement preparedsmt = conn.prepareStatement(sql);
		    preparedsmt.execute();
		    conn.close();
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}
	}
}
