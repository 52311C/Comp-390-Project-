/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PropertyServlet")
public class PropertyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Connect to your database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rpms", "root", "1234");
            
            // Retrieve category ID from the request parameter
           String categoryID = request.getParameter("category");

            // Query to fetch available properties
            String query = "SELECT propertyname, img, price, location FROM property WHERE status = ? AND categoryID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, "Available");
            stmt.setString(2, categoryID);
            rs = stmt.executeQuery();

            // Set content type for response
            response.setContentType("text/html");
            OutputStream out = response.getOutputStream();

            // Generate HTML for displaying properties
            while (rs.next()) {
                String propertyName = rs.getString("propertyname");
                byte[] imgData = rs.getBytes("img");
                String price = rs.getString("price");
                String location = rs.getString("location");
                 
                // Display property details in HTML cards with basic styling
                out.write("<!DOCTYPE html>".getBytes());
                out.write("<html>".getBytes());
                out.write("<head>".getBytes());
                out.write("<meta charset=\"UTF-8\">".getBytes());
                out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">".getBytes());
                out.write("<title>Property Listings</title>".getBytes());
                out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"category.css\">".getBytes()); // Change "styles.css" to your actual stylesheet file
                out.write("</head>".getBytes());
                out.write("<body>".getBytes());
                out.write("<div class=\"card\" style=\"border: 1px solid #ccc; border-radius: 5px; padding: 10px; margin-bottom: 20px;width: calc(30.33% - 20px);margin: 20px 10px;float: left;text-align: center;display: inline-block; border: 1px solid #ccc; border-radius: 5px;overflow: hidden; margin-right: 10px;\">".getBytes());
                out.write("<div class=\"img\" style=\" width: 100%; height: 500%; border-top-right-radius:5px ; border-top-left-radius:5px;aspect-ratio: 1/1;object-fit: cover;  \">".getBytes());
                out.write("<img src=\"data:image/jpg;base64,".getBytes());
                out.write(org.apache.commons.codec.binary.Base64.encodeBase64(imgData));
                out.write("\" alt=\"Property Image\" style=\"width:100%; border-radius: 5px;\">".getBytes());
                out.write("</div>".getBytes());
                out.write(("<h2 style=\"margin-top: 10px;\">" + propertyName + "</h2>").getBytes());
                out.write(("<p style=\"margin-bottom: 5px;\">Price: " + price + "</p>").getBytes());
                out.write(("<p style=\"margin-bottom: 5px;\">Location: " + location + "</p>").getBytes());
                
                out.write("</div>".getBytes());
                out.write("</body>".getBytes());
                out.write("</html>".getBytes());

            }
            out.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
