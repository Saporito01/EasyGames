package it.easygames.control;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(filterName = "/AccessOrderPage", urlPatterns = "/*")
public class AccessOrderPage extends HttpFilter implements Filter {
	
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String accountName = (String) httpServletRequest.getSession().getAttribute("accountName");
		String path = httpServletRequest.getServletPath();
		
		if(path.contains("/common/order.jsp") && accountName == null)
		{
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
    public void destroy() {
	}

	private static final long serialVersionUID = 1L;
}
