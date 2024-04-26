package it.easygames.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.model.dao.CoverControl;


@WebServlet("/getCover")
public class GetCoverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetCoverServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = (String) request.getParameter("id");
		byte[] bt = null;
		if (id != null) 
		{
			try {
				bt = CoverControl.load(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ServletOutputStream out = response.getOutputStream();
			if (bt != null) 
			{
				out.write(bt);
				response.setContentType("image/jpeg");
			}
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
