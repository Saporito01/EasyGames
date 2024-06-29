package it.easygames.control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.easygames.model.bean.Account;
import it.easygames.model.bean.Cart;
import it.easygames.model.dao.AccountControl;
import it.easygames.model.dao.CartControl;
import it.easygames.model.dao.GameDao;
import it.easygames.model.dao.IGameDao;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	static IGameDao gameDAO = new GameDao();
	
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<String> errors = new ArrayList<>();
		
    	RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("login.jsp");
    	
		if(username == null || username.trim().isEmpty()) {
			errors.add("Il campo username non può essere vuoto!<br>");
		}
        if(password == null || password.trim().isEmpty()) {
        	errors.add("Il campo password non può essere vuoto!");
		}
        if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return;
        }
        
        String hashPassword = toHash(password);
        
        try
        {
        	Collection<Account> admin = AccountControl.getAdmin();
            Collection<Account> users = AccountControl.getUsers();
            
            if(admin != null && admin.size() > 0) {
        		Iterator<?> it = admin.iterator(); 
        		while(it.hasNext()) {
        			Account adminAccount = (Account)it.next();
        			if(username.equals(adminAccount.getNickname()) && hashPassword.equals(adminAccount.getPassword()))
        			{
        				request.getSession().setAttribute("isAdmin", Boolean.TRUE);
        				request.getSession().setAttribute("accountName", adminAccount.getNickname());
        				
        				
        				//sincronizzazione dei due carrelli (quello in sessione e quello nel database)
        	    		Cart cart = (Cart) request.getSession().getAttribute("cart");
        	    		if(cart == null) {
        	    			cart = new Cart((String)request.getSession().getAttribute("accountName"));
        	    			request.getSession().setAttribute("cart", cart);
        	    		}
        	    		cart.setUserId((String)request.getSession().getAttribute("accountName"));
    	    			Collection<String> gameId = CartControl.getCartGames(cart.getUserId());
    	    			for(String id : gameId)
    	    				cart.addProduct(id);
    	    			CartControl.updateCart(cart);
        	    		
        				response.sendRedirect("admin/gestione.jsp");
        				return;
        			}
        		}
            }
        	
        	if(users != null && users.size() > 0)
        	{
        		Iterator<?> it = users.iterator();
        	    while(it.hasNext()) {
        	    	Account userAccount = (Account)it.next();
        	    	if(username.equals(userAccount.getNickname()) && hashPassword.equals(userAccount.getPassword()))
        	    	{
        	    		request.getSession().setAttribute("isAdmin", Boolean.FALSE);
        	    		request.getSession().setAttribute("accountName", userAccount.getNickname());
        	    		
        	    		
        	    		//sincronizzazione dei due carrelli (quello in sessione e quello nel database)
        	    		Cart cart = (Cart) request.getSession().getAttribute("cart");
        	    		if(cart == null) {
        	    			cart = new Cart((String)request.getSession().getAttribute("accountName"));
        	    			request.getSession().setAttribute("cart", cart);
        	    		}
        	    		cart.setUserId((String)request.getSession().getAttribute("accountName"));
    	    			Collection<String> gameId = CartControl.getCartGames(cart.getUserId());
    	    			for(String id : gameId)
    	    				cart.addProduct(id);
    	    			CartControl.updateCart(cart);
        	    		
        	    		response.sendRedirect("/EasyGames/");
        	    		return;
        	    	}
        	    }
        	}
        	
        	errors.add("Username o password non validi!");
    		request.setAttribute("errors", errors);
    		dispatcherToLoginPage.forward(request, response);
    		
        }
        catch(SQLException e)
		{
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	private String toHash(String password) {
        String hashString = null;
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < hash.length; i++) {
                hashString += Integer.toHexString( 
                                  (hash[i] & 0xFF) | 0x100 ).toLowerCase().substring(1,3);
            }
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashString;
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
