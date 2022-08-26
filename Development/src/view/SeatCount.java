package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SeatCount")
public class SeatCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SeatCount() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.sendRedirect(request.getContextPath() + "/ShowSeats.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {

			
			String floors = request.getParameter("floors");
			request.setAttribute("floors", floors);
			
			if(floors == null)
			{
				
				response.sendRedirect("ShowSeats.jsp");
				return;
			}
			
			int floor_no = Integer.parseInt(floors);
			System.out.println(floors);
			System.out.println(floor_no);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/allocate", "root", "1234");
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT COUNT(*) from Allocation where floors=? ;");
			preparedStatement.setInt(1, floor_no);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (floor_no == 1) {
				System.out.println("1st floor");
				if (resultSet.next()) {
					int totalseats = 60;
					String count = resultSet.getString("COUNT(*)");
					System.out.println(count);
					int allocated = Integer.parseInt(count);
					int AvailableInt = totalseats - allocated;
					String available = String.valueOf(AvailableInt);
					// System.out.println(count);
					request.setAttribute("Allocated", count);
					request.setAttribute("Available", available);
					System.out.println(count);
					System.out.println(available);

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowSeats.jsp");
					requestDispatcher.forward(request, response);
				}
			} else if (floor_no == 2) {
				if (resultSet.next()) {
					int totalseats = 88;
					String count = resultSet.getString("COUNT(*)");
					int allocated = Integer.parseInt(count);
					int AvailableInt = totalseats - allocated;
					String available = String.valueOf(AvailableInt);
					// System.out.println(count);
					request.setAttribute("Allocated", count);
					request.setAttribute("Available", available);

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowSeats.jsp");
					requestDispatcher.forward(request, response);
				}
			}

			else if (floor_no == 3) {
				if (resultSet.next()) {
					int totalseats = 88;
					String count = resultSet.getString("COUNT(*)");
					int allocated = Integer.parseInt(count);
					int AvailableInt = totalseats - allocated;
					String available = String.valueOf(AvailableInt);

					request.setAttribute("Allocated", count);
					request.setAttribute("Available", available);

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowSeats.jsp");
					requestDispatcher.forward(request, response);
				}
			}

			else if (floor_no == 4) {
				if (resultSet.next()) {
					int totalseats = 88;
					String count = resultSet.getString("COUNT(*)");
					int allocated = Integer.parseInt(count);
					int AvailableInt = totalseats - allocated;
					String available = String.valueOf(AvailableInt);
					request.setAttribute("Allocated", count);
					request.setAttribute("Available", available);

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowSeats.jsp");
					requestDispatcher.forward(request, response);
				}
			}

			else if (floor_no == 5) {
				if (resultSet.next()) {
					int totalseats = 70;
					String count = resultSet.getString("COUNT(*)");
					int allocated = Integer.parseInt(count);
					int AvailableInt = totalseats - allocated;
					String available = String.valueOf(AvailableInt);

					request.setAttribute("Allocated", count);
					request.setAttribute("Available", available);
					System.out.println("available");

					RequestDispatcher requestDispatcher = request.getRequestDispatcher("ShowSeats.jsp");
					requestDispatcher.forward(request, response);
				}
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
