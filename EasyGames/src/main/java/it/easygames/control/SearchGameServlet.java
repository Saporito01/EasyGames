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

import it.easygames.model.bean.Game;
import it.easygames.model.dao.GameDao;
import it.easygames.model.dao.IGameDao;


@WebServlet("/searchGame")
public class SearchGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDao gameDAO = new GameDao();
       
    public SearchGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String piattaforma = (String) request.getParameter("piattaforma");
		String nome = (String) request.getParameter("search");
		String path = (String) request.getParameter("path");
		
		try {
			RequestDispatcher dispatcher = null;
			
			if(piattaforma.equals("tutto") && nome.equals(""))
			{
				dispatcher = getServletContext().getRequestDispatcher("/common/home_page.jsp");
			}
			else if(nome != null)
			{
				Collection<Game> gameList = gameDAO.searchBarGame(nome, piattaforma);
				request.setAttribute("gameSearch", gameList);
				
				if(path.contains("/admin/"))
					dispatcher = getServletContext().getRequestDispatcher("/admin/searchView.jsp");
				else dispatcher = getServletContext().getRequestDispatcher("/common/search.jsp");
			}
			
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