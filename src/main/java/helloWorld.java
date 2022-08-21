
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class helloWorld
 */
public class helloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public helloWorld() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am being executed");
		String jdbcURL = "jdbc:h2:mem:test";

		try {
			Driver driver = new org.h2.Driver();
			Class.forName("org.h2.Driver");
			DriverManager.registerDriver(driver);
			Connection connection = DriverManager.getConnection(jdbcURL);

			System.out.println("Connected to H2 in-memory database.");

			String sql = "Create table students (ID int primary key, name varchar(50))";
			Statement statement = connection.createStatement();

			statement.execute(sql);

			System.out.println("Created table students.");

			sql = "Insert into students (ID, name) values (1, 'Nam Ha Minh')";

			int rows = statement.executeUpdate(sql);

			if (rows > 0) {
				System.out.println("Inserted a new row.");
			}

			connection.close();
			System.out.println("I am in try block yet");

		} catch (Exception e) {
			System.out.println("error thrown here " + e.getMessage());
		}
		System.out.println("I am executed");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
