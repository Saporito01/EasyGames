<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,it.easygames.model.bean.Cart,it.easygames.model.bean.Game,it.easygames.model.dao.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conferma ordine</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: auto;
            background: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        form {
            display: grid;
            gap: 10px;
        }
        label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        input, select, textarea {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
        }
        button {
            padding: 15px;
            background: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background: #218838;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Completa il tuo ordine</h1>
        <form action="../OrderServlet" method="POST">
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
                
                <%Cart cart = (Cart) request.getSession().getAttribute("cart");
                final IGameDao gameDAO = new GameDao();
				Map<String,Integer> prodCart = cart.getProducts();
				List<String> gameId = new ArrayList<>(prodCart.keySet());
				for(String id : gameId){
					Game game = gameDAO.doRetrieveByKey(id);
				%>
					<div>
					<img src="../getCover?id=<%=game.getId()%>" width="381" height="218" alt="copertina">
					<span class="title"><%=game.getName()%>&nbsp;&nbsp;</span><span class="price"><%=String.format(java.util.Locale.US,"%.2f",game.getPrice())%>&euro;</span>
					</div>
					
				<% } %>
                
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