package com.miniproject.secondyear;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComplaintBox
 */
@WebServlet("/ComplaintBox")
public class ComplaintBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComplaintBox() {
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
		doGet(request, response);
		String name = request.getParameter("name");
	    String number = request.getParameter("number");
	    String mobile = request.getParameter("mobile");
	    String message = request.getParameter("message");
	    
		dbConnection(name,number,mobile,message);
		RequestDispatcher rd = request.getRequestDispatcher("T.html?submitted");
		rd.forward(request, response);
		
		
	}
	@SuppressWarnings("unused")
	void dbConnection(String name, String number, String mobile,String message)
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
		//Statement stmt=con.createStatement();  
		  
		///step4 execute query  
		//ResultSet rs= null; 
		//while(rs.next())  
		//System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)); 
		String sql = "INSERT INTO ComplaintBox (username, villaNumber, mobile,message)" +
		        "VALUES (?, ?, ?, ?)";
		//if (username != null && password != null && Type != null) 
		//{
			
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, number);
			preparedStatement.setString(3, mobile);
			preparedStatement.setString(4, message);
			preparedStatement.executeUpdate(); 
           
        //}
		
		  
		//step5 close the connection object  
		con.close();  
		  
		}
		catch(Exception e)
		{
			System.out.println(e);
			
		}  
		  
		
	}

}
