import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EmployeeServlet")
public class EmployeeDisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String SELECT_QUERY = "SELECT * FROM employees";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String searchTerm = request.getParameter("searchTerm");
        StringBuilder htmlBuilder = new StringBuilder();

        htmlBuilder.append("<table style='width:100%; border-collapse: collapse;'>");
        htmlBuilder.append("<tr style='background-color: #f2f2f2;'>");
        htmlBuilder.append("<th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>ID</th>");
        htmlBuilder.append("<th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Employee ID</th>");
        htmlBuilder.append("<th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Full Name</th>");
        htmlBuilder.append("<th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Role</th>");
        htmlBuilder.append("<th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Email</th>");
        htmlBuilder.append("<th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Date of Birth</th>");
        htmlBuilder.append("<th style='padding: 12px; text-align: left; border: 1px solid #ddd;'>Mobile Number</th>");
        htmlBuilder.append("</tr>");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employ", "root", "bankingsystem@12")) {
            String sql = SELECT_QUERY;
            if (searchTerm != null && !searchTerm.isEmpty()) {
                sql += " WHERE employee_id LIKE ? OR full_name LIKE ? OR email LIKE ? OR mobile_number LIKE ?";
            }

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                if (searchTerm != null && !searchTerm.isEmpty()) {
                    String likeSearchTerm = "%" + searchTerm + "%";
                    ps.setString(1, likeSearchTerm);
                    ps.setString(2, likeSearchTerm);
                    ps.setString(3, likeSearchTerm);
                    ps.setString(4, likeSearchTerm);
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        htmlBuilder.append("<tr>");
                        htmlBuilder.append("<td style='padding: 12px; text-align: left; border: 1px solid #ddd;'>").append(rs.getInt("id")).append("</td>");
                        htmlBuilder.append("<td style='padding: 12px; text-align: left; border: 1px solid #ddd;'>").append(rs.getString("employee_id")).append("</td>");
                        htmlBuilder.append("<td style='padding: 12px; text-align: left; border: 1px solid #ddd;'>").append(rs.getString("full_name")).append("</td>");
                        htmlBuilder.append("<td style='padding: 12px; text-align: left; border: 1px solid #ddd;'>").append(rs.getString("role")).append("</td>");
                        htmlBuilder.append("<td style='padding: 12px; text-align: left; border: 1px solid #ddd;'>").append(rs.getString("email")).append("</td>");
                        htmlBuilder.append("<td style='padding: 12px; text-align: left; border: 1px solid #ddd;'>").append(rs.getDate("date_of_birth")).append("</td>");
                        htmlBuilder.append("<td style='padding: 12px; text-align: left; border: 1px solid #ddd;'>").append(rs.getString("mobile_number")).append("</td>");
                        htmlBuilder.append("</tr>");
                    }
                }
            }
        } catch (SQLException se) {
            htmlBuilder.append("<tr><td colspan='7'>").append(se.getMessage()).append("</td></tr>");
            se.printStackTrace();
        } catch (Exception e) {
            htmlBuilder.append("<tr><td colspan='7'>").append(e.getMessage()).append("</td></tr>");
            e.printStackTrace();
        }

        htmlBuilder.append("</table>");

        request.setAttribute("employeeData", htmlBuilder.toString());
        request.getRequestDispatcher("Display.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}