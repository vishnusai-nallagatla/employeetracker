import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectId = request.getParameter("projectId");
        String projectName = request.getParameter("projectName");
        String employeeId = request.getParameter("employeeId");
        String description = request.getParameter("description");
        String deadline = request.getParameter("deadline");
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        HttpSession session = request.getSession(); // Get the session object
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employ", "root", "bankingsystem@12");
            
            String sql = "INSERT INTO project_assignments (project_id, project_name, employee_id, description, deadline) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, projectId);
            pstmt.setString(2, projectName);
            pstmt.setString(3, employeeId);
            pstmt.setString(4, description);
            pstmt.setDate(5, Date.valueOf(deadline));
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Set success message in session
                session.setAttribute("message", "Project assignment successful!");
                response.sendRedirect("associateHome.jsp");
            } else {
                // Set error message in session
                session.setAttribute("message", "Project assignment failed. Please try again.");
                response.sendRedirect("assignProject.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Set error message in session
            session.setAttribute("message", "An error occurred: " + e.getMessage());
            response.sendRedirect("assignProject.jsp");
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
