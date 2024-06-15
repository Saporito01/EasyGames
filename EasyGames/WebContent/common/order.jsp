<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Cart,it.easygames.model.bean.Game,it.easygames.model.dao.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="../images/logo_scheda.png"/>
    <link rel="stylesheet" type="text/css" href="../css/order_style.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cart_style.css"/>
    <title>Conferma ordine</title>
</head>
<body>
    <div class="container">
        <h1>Completa il tuo ordine</h1>
        <form action="../OrderServlet" method="post">
            <div>
                <label for="name">Nome:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div>
                <label for="address">Indirizzo di fatturazione:</label>
                <textarea id="address" name="address" rows="4" required></textarea>
            </div>
            <div>
                <label for="products">Prodotti:</label>
                
                <table>
                <%Cart cart = (Cart) request.getSession().getAttribute("cart");
                final IGameDao gameDAO = new GameDao();
				Map<String,Integer> prodCart = cart.getProducts();
				List<String> gameId = new ArrayList<>(prodCart.keySet());
				for(String id : gameId){
					Game game = gameDAO.doRetrieveByKey(id);
				%>
				<tr>
					<td><span class="title"><%=game.getName()%>&nbsp;&nbsp;</span></td>
					<td><span class="price"><%=String.format(java.util.Locale.US,"%.2f",game.getPrice())%>&euro;</span></td>
					<td><span class="quantita">Quantità<%=prodCart.get(game.getId())%></span></td>
				</tr>
				<% } %>
				</table>
                
            </div>
            <div>
                <label for="payment">Metodo di Pagamento:</label>
                <select id="payment" name="payment" required>
                    <option value="">Seleziona un metodo di pagamento</option>
                    <option value="credit_card">Carta di Credito</option>
                    <option value="paypal">PayPal</option>
                    <option value="bank_transfer">Bonifico Bancario</option>
                </select>
            </div>
            <div>
                <label for="card_number">Numero Carta di Credito (se applicabile):</label>
                <input type="text" id="card_number" name="card_number">
            </div>
            <div>
                <label for="date">Data di Scadenza (MM/AA):</label>
                <input type="month" id="date" name="date">
            </div>
            <div>
                <label for="cvv">CVV:</label>
                <input type="text" id="cvv" name="cvv">
            </div>
            <button type="submit">CONFERMA ORDINE</button>
        </form>
    </div>
</body>
</html>