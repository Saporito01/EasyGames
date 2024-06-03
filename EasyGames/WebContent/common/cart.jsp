<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Game,it.easygames.model.bean.Cart,it.easygames.model.dao.*"%>

<%
	Cart cart = (Cart) request.getSession().getAttribute("cart");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Carrello</title>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

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

<%@include file="/fragment/footer.jsp" %>

</body>
</html>