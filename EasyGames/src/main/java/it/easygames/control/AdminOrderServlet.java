package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.ArrayList;

import it.easygames.model.bean.Ordine;
import it.easygames.model.dao.OrderControl;


@WebServlet("/AdminOrder")
public class AdminOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminOrderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data1 = (String) request.getParameter("data1");
		String data2 = (String) request.getParameter("data2");
		String accountName = (String) request.getParameter("account");
		
		try
		{			
			if(accountName != null)
			{
				Collection<Ordine> ordini = OrderControl.doRetrieveByDate(data1, data2, accountName);
				request.setAttribute("orderList", ordini);
			}
			
			ArrayList<String> account = OrderControl.loadOrderAccount();
			request.setAttribute("accounts", account);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/ordersPage.jsp");
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
