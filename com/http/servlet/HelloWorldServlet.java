package com.http.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UpdateDB updb = new UpdateDB();
	Connection con = ConnectionManager.getConnection();
	
	public HelloWorldServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	
	FileOutputStream fos;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();

		String flag = request.getParameter("flag").toString();
		System.out.println(flag);

		int flag_value = Integer.parseInt(flag);
		
		
		if(flag.equals("15"))
		{
			
				
				System.out.println("15.Getting All Details Try:::");
				
			
				   System.out.println("1five");
			      UpdateDB db=new UpdateDB();
					
					String data=db.activities();
					
					System.out.println("the json data is :"+data);
					response.getWriter().write(data);	
			
		}
		
		//flag_value
		if (flag_value == 8) 
		{
			System.out.println("Welcome to Location sending !!!");
			
			System.out.println("latitude is"+request.getParameter("latitude").toString());
			String latitude = request.getParameter("latitude").toString();
		System.out.println("longitude is "+request.getParameter("longitude").toString());
			String longitude = request.getParameter("longitude").toString();
//			System.out.println("dept is "+request.getParameter("dept").toString());
//			String dept = request.getParameter("dept").toString();
//			System.out.println(request.getParameter("caste").toString());
//			String caste = request.getParameter("caste").toString();
//			System.out.println(request.getParameter("collegename").toString());
//			String collegename = request.getParameter("collegename").toString();
			updb.location(latitude,longitude);
			
		}
		
		if (flag_value == 5) 
		{
			System.out.println("Welcome to Ack sending !!!");
			
			System.out.println("name is"+request.getParameter("name").toString());
			String name = request.getParameter("name").toString();
		System.out.println("ack is "+request.getParameter("ack").toString());
			String ack = request.getParameter("ack").toString();
			System.out.println("stmt is "+request.getParameter("stmt").toString());
			String stmt = request.getParameter("stmt").toString();
			updb.ack(name,ack,stmt);
			
		}
		if (flag_value == 7)
		{
			try
			{
				
				
				System.out.println("7.Query Connecting Try:::");
				System.out.println(request.getParameter("query").toString());
				String query = request.getParameter("query").toString();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM counsellingalldetails where collegename='"+query+"';");
				
				JSONArray jsonArray = new JSONArray();
				JSONObject obj = null ;
				while (rs.next()) 
				{
					 obj = new JSONObject();
					obj.put("collegename", rs.getString("collegename"));
					obj.put("cutoffmark", rs.getString("cutoffmark"));
					obj.put("totalseats", rs.getString("totalseats"));
					obj.put("cse", rs.getString("cse"));
					obj.put("it", rs.getString("it"));
					obj.put("eee", rs.getString("eee"));
					obj.put("ece", rs.getString("ece"));
					
					obj.put("othersdept", rs.getString("othersdept"));
					obj.put("bc", rs.getString("bc"));
					obj.put("oc", rs.getString("oc"));
					obj.put("mbc", rs.getString("mbc"));
					obj.put("otherscaste", rs.getString("otherscaste"));
					
					obj.put("vacancyseats", rs.getString("vacancyseats"));
					obj.put("fillingseats", rs.getString("fillingseats"));
					jsonArray.put(obj);
			} 
				System.out.println("Saravana effect"+jsonArray);
				String jsonString = jsonArray.toString();
				System.out.println(jsonString);

				response.setContentType("application/json");
				response.getWriter().write(jsonString.toString());
				
			}
			catch (Exception jse) 
			{
				jse.printStackTrace();
			}
		}
		if (flag_value == 4)
		{
			System.out.println("Welcome to Report sending !!!");

			System.out.println(request.getParameter("fname").toString());
			String fname = request.getParameter("fname").toString();
			System.out.println(request.getParameter("lname").toString());
			String lname = request.getParameter("lname").toString();
			System.out.println(request.getParameter("sage").toString());
			String sage= request.getParameter("sage").toString();
			
			System.out.println(request.getParameter("sgender").toString());
			String sgender= request.getParameter("sgender").toString();
			System.out.println(request.getParameter("saddress").toString());
			
			String saddress= request.getParameter("saddress").toString();
			
			System.out.println(request.getParameter("sproblem").toString());
			
			String sproblem= request.getParameter("sproblem").toString();
			
             updb.report(fname,lname,sage,sgender,saddress,sproblem);
             
		}
		if (flag_value == 6)
		{
			try
			{
				
				System.out.println("6.Location Retriving Latitude:::");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT latitude FROM location ORDER BY latitude DESC LIMIT 1");

				JSONArray arr = new JSONArray();

				while (rs.next()) 
				{
					arr.put(rs.getString("latitude"));
				}
				System.out.println("Json Result "+arr);
				JSONObject a=new JSONObject();
				a.put("receive",arr);
				response.setContentType("application/json");
				response.getWriter().write(a.toString());
				
			} 
			catch (Exception jse) 
			{
				jse.printStackTrace();
			}
		}
	
		if (flag_value == 16)
		{
			try
			{
				
				System.out.println("16.Location Retriving Longtitude:::");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT longitude FROM location ORDER BY longitude DESC LIMIT 1");

				
				JSONArray arr = new JSONArray();

				while (rs.next()) 
				{
					arr.put(rs.getString("longitude"));
				}
				System.out.println("Json Result "+arr);
				JSONObject a=new JSONObject();
				a.put("receive",arr);
				response.setContentType("application/json");
				response.getWriter().write(a.toString());		
				
			} 
			catch (Exception jse) 
			{
				jse.printStackTrace();
			}
		}
	
	
	}}
				
		
	
