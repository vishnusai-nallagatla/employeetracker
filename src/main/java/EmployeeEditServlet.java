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

@WebServlet("/EmployeeEditServlet")
public class EmployeeEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPDATE_EMPLOYEE = "UPDATE employees SET full_name = ?, password = ?, role = ?, email = ?, date_of_birth = ?, mobile_number = ? WHERE employee_id = ?";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employ";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bankingsystem@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String employeeId = request.getParameter("employeeId");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String mobileNumber = request.getParameter("mobileNumber");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement updateStmt = conn.prepareStatement(UPDATE_EMPLOYEE)) {

                // Set parameters for the SQL query
                updateStmt.setString(1, name);
                updateStmt.setString(2, password);
                updateStmt.setString(3, role);
                updateStmt.setString(4, email);
                updateStmt.setString(5, dateOfBirth);
                updateStmt.setString(6, mobileNumber);
                updateStmt.setString(7, employeeId);

                int result = updateStmt.executeUpdate();
                if (result > 0) {
                    // Update successful, redirect to confirmation page
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Account updated successfully!'); location='adminDashboard.jsp';</script>");
                } else {
                    // Update failed, redirect to error page
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Account update failed! Please check the provided information and try again.'); location='edit.jsp';</script>");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                response.setContentType("text/html");
                response.getWriter().write("<script>alert('SQLException: " + e.getMessage() + "'); location='edit.jsp';</script>");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('ClassNotFoundException: " + e.getMessage() + "'); location='edit.jsp';</script>");
        }
    }
}
