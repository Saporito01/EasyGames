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


@WebServlet("/addGame")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class AddGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static IGameDao gameDAO = new GameDao();

    public AddGameServlet() {
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
		String[] genere = request.getParameterValues("genere");

		try
		{
				Game model = new Game();
				model.setId(id);
				model.setName(nome);
				model.setDesc(descrizione);
				model.setPlatf(piattaforma);
				model.setQt(Integer.valueOf(quantita));
				model.setPrice(Float.valueOf(prezzo));
				
				gameDAO.doSave(model);
			
			gameDAO.doSaveGenere(id,genere);
			
			for (Part part : request.getParts()) {
				String fileName = part.getSubmittedFileName();
				if (fileName != null && !fileName.equals(""))
						CoverControl.updateCover(id, part.getInputStream());
			}
		}
		catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}

		response.sendRedirect("admin/addGamePage.jsp");
	}

}
