package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.model.bean.Cart;
import it.easygames.model.bean.Ordine;
import it.easygames.model.dao.CartControl;
import it.easygames.model.dao.OrderControl;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Ottieni la data corrente
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(dateFormatter);

        // Ottieni l'ora corrente
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(timeFormatter);
        
        Cart cart = (Cart)request.getSession().getAttribute("cart");
        Map<String,Integer> products = cart.getProducts();
		List<String> gameId = new ArrayList<>(products.keySet());
        String user = (String)request.getSession().getAttribute("accountName");
        
        Ordine ordine = new Ordine();
        ordine.setAccount(user);
        ordine.setData(formattedDate);
        ordine.setOra(formattedTime);
        for(String id : gameId)
        	ordine.addProduct(id, products.get(id));
        
        try {
			OrderControl.doSave(ordine);
			cart.clear();
			CartControl.updateCart(cart);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/confirmOrder.html");
        dispatcher.forward(request, response);
	}

}
