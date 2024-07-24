import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AssociateLogin")
public class AssociateLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database connection settings
    private static final String URL = "jdbc:mysql://localhost:3306/employ";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "bankingsystem@12";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            try (Connection con = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD)) {
                // Prepare SQL query to check if the user exists with the given username and password
                String query = "SELECT * FROM associate WHERE username = ? AND password = ?";
                try (PreparedStatement pst = con.prepareStatement(query)) {
                    pst.setString(1, username);
                    pst.setString(2, password);

                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            // User exists, create a session and redirect to associateHome.jsp
                            HttpSession session = request.getSession();
                            session.setAttribute("username", username);
                            response.sendRedirect("associateHome.jsp");
                        } else {
                            // User does not exist, display error message
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Invalid username or password');");
                            out.println("location='associateLogin.jsp';");
                            out.println("</script>");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
