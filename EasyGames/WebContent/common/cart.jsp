<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.easygames.model.bean.Game,it.easygames.model.bean.Cart,it.easygames.model.dao.*"%>
    
<%
	Cart cart = (Cart) request.getSession().getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carrello</title>
</head>
<body>

<header>
<a href="/EasyGames/"><img src="./images/logo.png" width="290" height="65" alt="Logo"></a>
</header>

<% if(cart != null) { %>
		<h2>Cart</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Quantita'</th>
		</tr>
		<% 
		final IGameDao gameDAO = new GameDao();
		Map<String,Integer> prodCart = cart.getProducts();
		List<String> gameId = new ArrayList<>(prodCart.keySet());
		for(String id : gameId){
			Game game = gameDAO.doRetrieveByKey(id);
		%>
		<tr>
			<td><%=game.getName()%></td>
			<td><a href="./CartServlet?azione=rimuovi&id=<%=id%>">-</a><%=prodCart.get(id)%><a href="./CartServlet?azione=aggiungi&id=<%=id%>">+</a></td>
			<td><a href="./CartServlet?azione=elimina&id=<%=id%>">ELIMINA</a></td>
		</tr>
		<% } %>
	</table>
	<% } %>
	
	<%if(cart != null && !cart.getProducts().isEmpty()){%>
	<a href="./common/order.jsp"><button>COMPLETA ORDINE</button></a>
	<% } %>
	
</body>
</html>