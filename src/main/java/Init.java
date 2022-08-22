import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class Init implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Servlet initialization called");
		
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
		System.out.println("servlet initialization executed");

		
	}
	
}
