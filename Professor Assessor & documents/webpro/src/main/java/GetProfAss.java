import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Fetch data
/**
 * Servlet implementation class demo3
 */
@WebServlet("/GetProfAss")
public class GetProfAss extends HttpServlet {
    private static final long serialVersionUID = 1 ;

    String dns = "ec2-3-141-165-46.us-east-2.compute.amazonaws.com";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfAss() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    	System.out.println(request.toString() +"\n"+ response.toString());
        String sql;
        Connection connection = null;
        //Statement statement = null;
        PreparedStatement statement1 = null;
        ResultSet rs = null;
        //PreparedStatement preparedStatement = null;
        String keyword = request.getParameter("keyword");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String title = "Fetch Professor Reviews";
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor = \"##CCCCFF\">\n" + //
            "<h1 align = \"center\">" + title + "</h1>\n");


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return;
        }

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + dns + ":3306/myDB", "root", "sqlAdmin123!@#");
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed!:\n" + e2.getMessage());
        }
        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
        System.out.println("Creating statement...");

        sql = "SELECT * FROM profAss WHERE professor=?";
        try {

            statement1 = connection.prepareStatement(sql);
            String theUserName = keyword;
            statement1.setString(1, theUserName);
     
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        try {

            rs = statement1.executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        out.println("<table border=1 width=80% height=30%, align=\"center\">");
        out.println("<tr><th>Professor</th><th>School</th><th>Course</th><th>Rating</th><th>Comment</th>");
        try {
            while (rs.next()) {
                //Retrieve by column name
                String professor = rs.getString("professor");
                String school = rs.getString("school");
                String course = rs.getString("course");
                String rating = rs.getString("rating");
                String comment = rs.getString("comment");
                out.println("<tr><td>" + professor + "</td><td>" + school + "</td><td>" + course + "</td><td>" + rating + "</td><td>" + comment + "</td></tr>");
            }
            out.println("<form action=\"index.html\"><input type=\"submit\" value=\"Return to home\" /></form>");
            out.println("</body></html>");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}