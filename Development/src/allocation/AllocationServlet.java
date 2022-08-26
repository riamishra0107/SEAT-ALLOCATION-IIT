package allocation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.*;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AllocationServlet")
public class AllocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AllocationServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	private boolean isValidSeatNum(String floor, String seatnum) {
		HashMap<String, Integer> floorSeatMap = new HashMap<String, Integer>();
		int seatval = Integer.parseInt(seatnum);
		floorSeatMap.put("1", 60);
		floorSeatMap.put("2", 88);
		floorSeatMap.put("3", 88);
		floorSeatMap.put("4", 88);
		floorSeatMap.put("5", 70);
		System.out.println(floor);
		System.out.println(seatval);
		if (floorSeatMap.get(floor) != null && seatval <= floorSeatMap.get(floor) && seatval > 0) {
			return true;

		} else {
			return false;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Full_name = request.getParameter("Full_name");
		String emp_id = request.getParameter("emp_id");
		String email = request.getParameter("email");
		String floors = request.getParameter("floors");
		String seat_num = request.getParameter("seat_num");

		if ((isValidSeatNum(floors, seat_num)) == false) {
			request.setAttribute("status", "warning");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("Allocate.jsp");
			requestDispatcher.forward(request, response);

			System.out.println("not a valid seat number");
		}
		if (isValidSeatNum(floors, seat_num)) {

			System.out.println("Valid seat number");

			AllocatingSeats allocatingseats = new AllocatingSeats(Full_name, emp_id, email, floors, seat_num);
			AllocationDatabase allocationdatabase = new AllocationDatabase();
			try {
				int a = allocationdatabase.insert(allocatingseats);

				if ((isValidSeatNum(floors, seat_num) && (a == 1))) {

					String to = email;
					String from = "riyamishra070409@gmail.com";
					String password = "ensadqehswcbojrd";
					String sub = "New Seat Allocated";
					Properties properties = new Properties();
					properties.put("mail.smtp.host", "smtp.gmail.com");
					properties.put("mail.smtp.socketFactory.port", "465");
					properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					properties.put("mail.smtp.auth", "true");
					properties.put("mail.smtp.port", "465");

					Session session1 = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(from, password);
						}
					});

					try {
						MimeMessage message = new MimeMessage(session1);
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
						message.setSubject(sub);
						message.setText("Hi! " + allocatingseats.getFull_name() + "," + "Your Seat is on Floor: "
								+ allocatingseats.getFloors() + " Seat Number is: " + allocatingseats.getSeat_num());
						// send message
						Transport.send(message);
						System.out.println("message sent successfully");
					} catch (MessagingException e) {
						throw new RuntimeException(e);
					}
					request.setAttribute("status", "success");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("Allocate.jsp");
					requestDispatcher.forward(request, response);

				} else {

					request.setAttribute("status", "failed");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("Allocate.jsp");
					requestDispatcher.forward(request, response);

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}
}
