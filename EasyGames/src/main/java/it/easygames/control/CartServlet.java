package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.easygames.model.dao.CartControl;
import it.easygames.model.dao.GameDao;
import it.easygames.model.dao.IGameDao;
import it.easygames.model.bean.Cart;


@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDao gameDAO = new GameDao();
       
    public CartServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String accountName = (String) session.getAttribute("accountName");
		
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			if(accountName == null)
				cart = new Cart("ospite");
			else 
				cart = new Cart(accountName);
			
			session.setAttribute("cart", cart);
		}
		
		// Esegui l'operazione richiesta (ad esempio, aggiunta o rimozione di un articolo)
        String azione = request.getParameter("azione");
        String gameId = request.getParameter("id");

        if(azione != null) {
        	if(azione.equals("aggiungi"))
                cart.addProduct(gameId);
            else if(azione.equals("rimuovi"))
                cart.removeProduct(gameId);
        }
        
        try {
			if(!(cart.getUserId().equals("ospite")))
				CartControl.updateCart(cart);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // Reindirizza l'utente alla pagina del carrello
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/cart.jsp");
        dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
