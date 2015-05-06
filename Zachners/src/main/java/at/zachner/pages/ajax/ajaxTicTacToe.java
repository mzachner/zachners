package at.zachner.pages.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.zachner.pages.TicTacToe;

//Dieser AjaxCall funktioniert beim InternetExplorer nicht.

@WebServlet(name="ajaxTicTacToe", urlPatterns={"/ajaxTicTacToe"})
public class ajaxTicTacToe extends javax.servlet.http.HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public void init()
	  {
	      // Do required initialization
	  }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		  response.setContentType("text/plain");
	      response.setCharacterEncoding("UTF-8");
	      
		  Object obj = request.getSession().getAttribute("ticTacToe");
		  if (obj instanceof TicTacToe) {
			  TicTacToe ticTacToe = (TicTacToe) obj;
			  PrintWriter out = response.getWriter();
			  out.print(ticTacToe.isRefreshPageRequired());
		  }
		  else {
			  //TODO Fehlerbehandlung
		  }
	}
	
	public void destroy()
	  {
	      // do nothing.
	  }

}
