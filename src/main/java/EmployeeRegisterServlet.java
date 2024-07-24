import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees(employee_id, full_name, password, role, email, date_of_birth, mobile_number) VALUES(?,?,?,?,?,?,?)";
    private static final String CHECK_EMPLOYEE = "SELECT COUNT(*) FROM employees WHERE employee_id = ? OR email = ? OR mobile_number = ?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employ";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bankingsystem@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String mobileNumber = request.getParameter("mobileNumber");

        String employeeId = generateEmployeeId();

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement checkStmt = conn.prepareStatement(CHECK_EMPLOYEE);
                 PreparedStatement insertStmt = conn.prepareStatement(INSERT_EMPLOYEE)) {

                // Check for duplicate entries
                checkStmt.setString(1, employeeId);
                checkStmt.setString(2, email);
                checkStmt.setString(3, mobileNumber);

                ResultSet rs = checkStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    // Duplicate found, redirect to CreateEmployee.jsp
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Duplicate entry detected!'); location='CreateEmployee.jsp';</script>");
                    return;
                }

                // Insert employee data
                insertStmt.setString(1, employeeId);
                insertStmt.setString(2, fullName);
                insertStmt.setString(3, password);
                insertStmt.setString(4, role);
                insertStmt.setString(5, email);
                insertStmt.setString(6, dateOfBirth);
                insertStmt.setString(7, mobileNumber);

                int result = insertStmt.executeUpdate();
                if (result > 0) {
                    // Insert successful, redirect to adminDashboard.jsp
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Registration successful!'); location='adminDashboard.jsp';</script>");
                } else {
                    // Insert failed, redirect to CreateEmployee.jsp
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Registration failed! Please try again.'); location='CreateEmployee.jsp';</script>");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                response.setContentType("text/html");
                response.getWriter().write("<script>alert('SQLException: " + e.getMessage() + "'); location='CreateEmployee.jsp';</script>");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('ClassNotFoundException: " + e.getMessage() + "'); location='CreateEmployee.jsp';</script>");
        }
    }

    private String generateEmployeeId() {
        // Generate a unique employee ID using UUID and return the first 8 characters
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
    }
}
