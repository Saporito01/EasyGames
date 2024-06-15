package it.easygames.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.model.bean.Game;
import it.easygames.model.bean.Ordine;
import it.easygames.model.dao.GameDao;
import it.easygames.model.dao.IGameDao;
import it.easygames.model.dao.OrderControl;


@WebServlet("/UserOrder")
public class UserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserOrderServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String annoMese = request.getParameter("annoMese");
        String accountName = (String) request.getSession().getAttribute("accountName");

        try {
            if (annoMese != null && accountName != null) {
                String[] parts = annoMese.split("-");
                String annoStr = parts[0];
                String meseStr = parts[1];
                int mese = Integer.parseInt(meseStr);
                int anno = Integer.parseInt(annoStr);

                Collection<Ordine> ordini = OrderControl.doRetrieveByDate(mese, anno, accountName);

                // Genera l'HTML per gli ordini
                StringBuilder html = new StringBuilder();
                if(ordini.isEmpty())
                	html.append("<h1>NESSUN RISULTATO</h1>");
                else {
                	for (Ordine ordine : ordini) {
                        html.append("<div class='order'>")
                            .append("<span>Numero dell'ordine: ").append(ordine.getCodice()).append("</span>")
                            .append("<span>Data dell'ordine: ").append(ordine.getData()).append("</span>")
                            .append("<div class='products'>");

                        for (Map.Entry<String, Integer> entry : ordine.getProducts().entrySet()) {
                            String productId = entry.getKey();
                            int quantity = entry.getValue();
                            double price = retrieveProductPrice(productId); // Implementa questa funzione per ottenere il prezzo del prodotto

                            html.append("<span class='title'>").append(retrieveProductName(productId)).append("</span>")
                                .append("<span class='price'>").append(String.format("%.2f", price)).append("&euro;</span>")
                                .append("<span class='quantita'>Quantità ").append(quantity).append("</span><br>");
                        }

                        html.append("</div>")
                            .append("</div>");
                    }
                }

                // Imposta il tipo di contenuto della risposta a HTML
                response.setContentType("text/html");

                // Scrivi la risposta HTML
                PrintWriter out = response.getWriter();
                out.write(html.toString());
                out.close();
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private double retrieveProductPrice(String id) throws SQLException {
    	final IGameDao gameDAO = new GameDao();
    	Game game = gameDAO.doRetrieveByKey(id);
        return game.getPrice();
    }
    
    private String retrieveProductName(String id) throws SQLException {
    	final IGameDao gameDAO = new GameDao();
    	Game game = gameDAO.doRetrieveByKey(id);
        return game.getName();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
