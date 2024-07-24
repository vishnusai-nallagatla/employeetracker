import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeDeleteServlet")
public class EmployeeDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employee_id = ? AND full_name = ?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employ";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bankingsystem@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("employeeId");
        String fullname = request.getParameter("fullname");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement deleteStmt = conn.prepareStatement(DELETE_EMPLOYEE)) {

                // Set parameters for the SQL query
                deleteStmt.setString(1, employeeId);
                deleteStmt.setString(2, fullname);

                int result = deleteStmt.executeUpdate();
                if (result > 0) {
                    // Deletion successful, redirect to confirmation page
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Account deleted successfully!'); location='adminDashboard.jsp';</script>");
                } else {
                    // Deletion failed, redirect to error page
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Account deletion failed! Please check the provided information and try again.'); location='CreateEmployee.jsp';</script>");
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
}
