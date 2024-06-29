package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.model.bean.Cart;
import it.easygames.model.bean.Game;
import it.easygames.model.dao.GameDao;
import it.easygames.model.dao.IGameDao;

@WebServlet("/GetCartProducts")
public class GetCartProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static IGameDao gameDAO = new GameDao();
	
    public GetCartProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Collection<Game> games = new ArrayList<>();
		
		String page = request.getParameter("page");
		String strForward;
		if(page.equals("cart"))
			strForward = "/common/cart.jsp";
		else
			strForward = "/common/order.jsp";
		
		try
		{
			if(cart != null) {
				Map<String,Integer> prodCart = cart.getProducts();
				List<String> gameId = new ArrayList<>(prodCart.keySet());
				for(String id : gameId) {
					Game game = gameDAO.doRetrieveByKey(id);
					games.add(game);
				}
				request.setAttribute("gameList", games);
			}
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(strForward);
			dispatcher.forward(request, response);
		}
		catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
