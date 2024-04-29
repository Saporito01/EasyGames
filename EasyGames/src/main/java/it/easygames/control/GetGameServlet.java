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

import it.easygames.model.bean.Game;
import it.easygames.model.dao.GameDaoDriverMan;
import it.easygames.model.dao.IGameDao;


@WebServlet("/getGame")
public class GetGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDao gameDAO = new GameDaoDriverMan();
	
    public GetGameServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idGameAdmin = (String) request.getParameter("idAdmin");
		String idGameHome = (String) request.getParameter("idHome");

		try
		{
			if(idGameHome != null)
			{
				Game game = gameDAO.doRetrieveByKey(idGameHome);
				request.setAttribute("game", game);
				
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common/gamePage.jsp");
				dispatcher.forward(request, response);
			}
			else if(idGameAdmin != null)
			{
				Game game = gameDAO.doRetrieveByKey(idGameAdmin);
				request.setAttribute("game", game);
				
				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/editGames.jsp");
				dispatcher.forward(request, response);
			}
			else 
			{
				Collection<Game> games = gameDAO.doRetrieveAll("nome");
				request.setAttribute("games", games);

				RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common/home_page.jsp");
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
