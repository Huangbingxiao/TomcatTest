import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatabaseAccess extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://120.79.89.4:3306/music";
    
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "guest";
    static final String PASS = "Rao614614614@"; 

    public DatabaseAccess() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        String title = "Servlet Mysql 测试 - 菜鸟教程";
//        String docType = "<!DOCTYPE html>\n";
//        out.println(docType +
//        "<html>\n" +
//        "<head><title>" + title + "</title></head>\n" +
//        "<body bgcolor=\"#f0f0f0\">\n" +
//        "<h1 align=\"center\">" + title + "</h1>\n");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(conn);
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT email FROM user_info";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            while(rs.next()){
   
                String email = rs.getString("email");
            	System.out.println(rs.getString("email"));
           
                out.println("ID: " + email);

            }
//            out.println("</body></html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
          
            se.printStackTrace();
        } catch(Exception e) {
         
            e.printStackTrace();
        }finally{
       
            try{
                if(stmt!=null)
                stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
       
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        doGet(request, response);
    }
}