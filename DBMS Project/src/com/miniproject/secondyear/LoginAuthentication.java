package com.miniproject.secondyear;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginAuthentication
 */
@WebServlet("/LoginAuthentication")
public class LoginAuthentication extends HttpServlet {
	
	//System.out.println("Came to Servlet");  
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAuthentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String em = request.getParameter("username");
	      String pa = request.getParameter("password");
	      String typeOfUser = request.getParameter("usertype");
	      System.out.println(em);
	      System.out.println(pa);
		dbConnection(em,pa,typeOfUser);
		response.setContentType("text/html");
		RequestDispatcher rd = null;
		if(typeOfUser.equalsIgnoreCase("admin"))
		{
			rd = request.getRequestDispatcher("O2.html");
			rd.forward(request, response);
		}
		else if(typeOfUser.equalsIgnoreCase("owner"))
		{
			 rd = request.getRequestDispatcher("Apage.html");
			 rd.forward(request, response);
		}
		else if(typeOfUser.equalsIgnoreCase("tenant") )
		{
			 rd = request.getRequestDispatcher("T.html");
			 rd.forward(request, response);
		}
		//RequestDispatcher rd = request.getRequestDispatcher("Apage.html");
		
		doGet(request, response);
	}
	@SuppressWarnings("unused")
	void dbConnection(String username, String password, String typeOfUser)
	{
		String Type = null;
		
		try
		{
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@localhost:1521:xe","project","project1234");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();  
		  
		//step4 execute query  
		ResultSet rs= null; 
		//while(rs.next())  
		//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
		
		if (username != null && password != null && Type != null) 
		{
            String sql = "Select * from loginUsers Where Username='" +username+ "' and PasswordValue='" + password + "' and Type='"+typeOfUser+"'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
            	System.out.println("Valid"); 
            } else {
            	System.out.println("InValid"); 
            }
        }
		  
		//step5 close the connection object  
		con.close();  
		  
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}  
		  
		
	}

}
