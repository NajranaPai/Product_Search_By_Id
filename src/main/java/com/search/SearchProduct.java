package com.search;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;

/**
 * Servlet implementation class SearchProduct
 */
public class SearchProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out=response.getWriter();
		 response.setContentType("text/html");
		 
		 InputStream in=request.getServletContext().getResourceAsStream("/WEB-INF/config.properties");
		 Properties p=new Properties();
		 p.load(in);
		 try {
			DBConnection con=new DBConnection(p.getProperty("url"),p.getProperty("user"),p.getProperty("pwd"));
			con.getConnection();
			int pid=Integer.parseInt(request.getParameter("pid"));
		    out.println("<html><head><style>.center{background-color:lightblue;text-align: center;text-align:center;margin:auto;width: 50%; border: 4px outset red;}</style><title>Product Table</title></head><body><div class=center>");
			
			
			Statement st=con.getConnection().createStatement();
			String query="select * from product where pid='"+request.getParameter("pid") +"'";	
				
			ResultSet rs=st.executeQuery(query);
			 int n=1;
			 while(rs.next())
			    {
				   if(n==1)
				   {
					   out.println("<Font size=4>Product details of ID :" +request.getParameter("pid")+"<br>");
					   out.println(" <table border=2 align=center>");
					   out.println("<tr><td width=150><font size=4> ProductId: </font></td><td width=150><font size=4>Product Name</font></td><td width=150><font size=4>Product Price</font></td></tr>");
				   }
			     out.println("<tr><td width=150><font size=4>" +  rs.getInt("pid")  + "</font></td><td width=150><font size=4>" + rs.getString("pname") + "</font></td><td width=150><font size=4>" + rs.getString("price") + "</font></td></tr>");
			     n++;
			    }
			 if(n==1)
				 out.println("Record not found...");
			 out.println("<br></table><a href=SearchProduct.html><font size=4>Home Page</font></a></div></body>");
			con.CloseConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
