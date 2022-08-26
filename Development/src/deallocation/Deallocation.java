package deallocation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Deallocation")
public class Deallocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Deallocation() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allocate", "root", "1234");
			String Full_name = request.getParameter("Full_name");
			String emp_id = request.getParameter("emp_id");

			PreparedStatement preparedStatement = connection
					.prepareStatement("Delete from Allocation where Full_name=? and emp_id=?");
			preparedStatement.setString(1, Full_name);
			preparedStatement.setString(2, emp_id);

			int i = preparedStatement.executeUpdate();

			if (i > 0) {

				request.setAttribute("status", "success");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("deallocation.jsp");
				requestDispatcher.forward(request, response);
			} else {
				request.setAttribute("status", "failed");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("deallocation.jsp");
				requestDispatcher.forward(request, response);

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
