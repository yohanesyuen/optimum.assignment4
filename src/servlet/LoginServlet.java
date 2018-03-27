package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
/*@WebServlet("/LoginServlet")*/
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO dao = new UserDAOImpl();
		User userRef = dao.authenticatePassword(username, password);
		if (userRef != null) {
			request.getRequestDispatcher("home").include(request, response);
			HttpSession session = request.getSession();
			User refUser = new User();
			refUser.setName(userRef.getName());
			session.setAttribute("UID", userRef.getUserID());
			response.sendRedirect("home");
		}
	}

}
