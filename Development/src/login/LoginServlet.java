package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("Served at: ").append(request.getContentType());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LoginBean loginbean = new LoginBean();
		loginbean.setUsername(username);
		loginbean.setPassword(password);

		loginDatabase logindatabase = new loginDatabase();
		if (logindatabase.validate(loginbean)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			try {
				response.sendRedirect("OtLogin.jsp");

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		else {
			try {
				request.setAttribute("status", "failed");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
				requestDispatcher.forward(request, response);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
