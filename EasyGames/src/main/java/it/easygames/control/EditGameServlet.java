package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import it.easygames.model.bean.Game;
import it.easygames.model.dao.CoverControl;
import it.easygames.model.dao.GameDao;
import it.easygames.model.dao.IGameDao;


@WebServlet("/editGame")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDao gameDAO = new GameDao();
	
    public EditGameServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) request.getParameter("id");
		String nome = (String) request.getParameter("nome");
		String descrizione = (String) request.getParameter("descrizione");
		String piattaforma = (String) request.getParameter("piattaforma");
		String quantita = (String) request.getParameter("quantita");
		String prezzo = (String) request.getParameter("prezzo");
		String idRemove = (String) request.getParameter("idRemove");
		
		try
		{
			if(idRemove != null)
			{
				gameDAO.doDelete(idRemove);
			}
			else
			{
				Game model = new Game();
				model.setId(id);
				model.setName(nome);
				model.setDesc(descrizione);
				model.setPlatf(piattaforma);
				model.setQt(Integer.valueOf(quantita));
				model.setPrice(Float.valueOf(prezzo));
				
				gameDAO.doUpdate(model);
				
				for (Part part : request.getParts()) {
					String fileName = part.getSubmittedFileName();
					if (fileName != null && !fileName.equals(""))
							CoverControl.updateCover(id, part.getInputStream());
				}
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}
		
		response.sendRedirect("/EasyGames/admin/viewGames.jsp");
	}

}
