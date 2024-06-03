package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.model.bean.Ordine;
import it.easygames.model.dao.OrderControl;

/**
 * Servlet implementation class UserOrderServlet
 */
@WebServlet("/UserOrder")
public class UserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data1 = (String) request.getParameter("data1");
		String data2 = (String) request.getParameter("data2");
		String accountName = (String) request.getSession().getAttribute("accountName");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/ordersView.jsp");
		
		try
		{
			if(data1 != null && data2 != null)
			{
				Collection<Ordine> ordini = OrderControl.doRetrieveByDate(data1, data2, accountName);
				request.setAttribute("orderList", ordini);
				dispatcher.forward(request, response);
			}
			
			if(accountName != null) {
				Collection<Ordine> ordini = OrderControl.loadForUser(accountName);
				request.setAttribute("orderList", ordini);
				dispatcher.forward(request, response);
			}
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
