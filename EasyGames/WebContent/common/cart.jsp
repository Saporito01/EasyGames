<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Game,it.easygames.model.bean.Cart,it.easygames.model.dao.*"%>

<%
Cart cart = (Cart) request.getSession().getAttribute("cart");

Collection<?> model = (Collection<?>) request.getAttribute("gameList");
if(model == null) {
	request.getRequestDispatcher("../GetCartProducts?page=cart").forward(request, response);
	return;
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png" href="images/logo_scheda.png"/>
<link rel="stylesheet" type="text/css" href="css/cart_style.css"/>
<title>Carrello</title>
</head>
<body>

<%@include file="/fragment/header.jsp" %>

<div class="container">
<div class="main">
<%
	if(model.isEmpty()){
%>
		<h2>Il tuo carrello è vuoto</h2>
<%
	}
	else {
%>
		
		<h2>Carrello</h2>
		<table>
		<tr>
			<th>Nome</th>
			<th>Quantità</th>
		</tr>
		<%
		if(model != null && model.size() > 0) {
			Iterator<?> it = model.iterator();
			Map<String,Integer> prodCart = cart.getProducts();
			while(it.hasNext()) {
				Game game = (Game)it.next();
		%>
		<tr>
			<td><%=game.getName()%></td>
			<td><a href="./CartServlet?azione=rimuovi&id=<%=game.getId()%>">-</a><%=prodCart.get(game.getId())%><a href="./CartServlet?azione=aggiungi&id=<%=game.getId()%>">+</a></td>
			<td><a href="./CartServlet?azione=elimina&id=<%=game.getId()%>">ELIMINA</a></td>
		</tr>
	<%
			}
		}
	}
	%>
	</table>
	
	<%if(cart != null && !cart.getProducts().isEmpty()){%>
	<a href="./common/order.jsp"><button>COMPLETA ORDINE</button></a>
	<% } %>
</div>
</div>

<%@include file="/fragment/footer.jsp" %>

</body>
</html>