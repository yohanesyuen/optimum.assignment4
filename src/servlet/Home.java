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
 * Servlet implementation class Home
 */
// @WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String fragments = "/fragments/";
		String template_file = "login_partial.jsp";
		if (session.getAttribute("UID") != null) {
			int uid = (Integer) session.getAttribute("UID");
			UserDAO dao = new UserDAOImpl();
			User refUser = dao.getUser(uid);
			if (refUser != null) {
				if (refUser.getRole().equals("Admin")) {
					template_file = "admin_home_partial.jsp";
					System.out.println("God is in!");
				} else {
					template_file = "user_home_partial.jsp";
				}
			}
		}
		template_file = fragments + template_file;
		request.setAttribute("template_file", template_file);
		request.getRequestDispatcher("/template.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub doGet(request, response); }
	 */
}
