<%@page import="java.util.ArrayList"%>
<%@page import="br.com.tutorial.model.PessoaModel"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Colaboradores</h1>
<div class='col-md-6'>
<form action="ControllerServelet?acao=salvar" method="post">
	<input class='form-control' name="nome"/>
	<input class='form-control' name="idade"/>
	<input class='btn btn-success' type="submit" value="Salvar"/>
</form>
<table class='table'>
	<thead>
		<tr>
			<th>Nome</th>
			<th>Idade</th>
		</tr>
	</thead>
	<tbody>
	<% ArrayList<PessoaModel> pessoas = (ArrayList<PessoaModel>)request.getAttribute("pessoas");
		for(PessoaModel pessoa : pessoas){
		%>
		<tr>
			<td><%=pessoa.nome%></td>
			<td><%=pessoa.idade%></td>
			<td>
				<form action="ControllerServelet?acao=deletar" method="post">
				<input hidden="true" name="id" value=<%=pessoa.id%>>
				<input class="btn btn-danger" type="submit" value="Remover"/>
				</form>
				</td>
		</tr>
		<%
	}
	%>
	</tbody>
</table>
</div>
</body>
</html>