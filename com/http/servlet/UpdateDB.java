package com.http.servlet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateDB 
{
	Connection conn = ConnectionManager.getConnection();
	int l;
	public boolean login(String username, String password) 
	{
		boolean success = false;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT username FROM userdetails WHERE password = '"
							+ password + "'");
			while (rs.next()) {
				if (rs.getString("username").equals(username)) {
					success = true;
				} else {
					success = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;

	}
	

	

	public String activities() {
		// TODO Auto-generated method stub

		String jsonString = null;
		try {
			JSONArray jsonArray = new JSONArray();		
			Statement st = conn.createStatement();
	        String query = "SELECT * FROM counsellingalldetails";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			//System.out.println("query executed");
			//System.out.println(rs.getFetchSize());
			
			while (rs.next()) 
			{
				
				JSONObject obj = new JSONObject();

				try {
                    String clgname=rs.getString("CollegeName");
					obj.put("collegename", clgname);
					
					
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
				
				catch (JSONException e1) {
					System.out.println("Error1: " +e1);
					e1.printStackTrace();
				}

				jsonString = jsonArray.toString();
				//System.out.println(jsonString);
			}
		}

		catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("Error: " +e);
		}
		return jsonString;

	
	}

	public boolean location(String latitude, String longitude) 
	{
		// TODO Auto-generated method stub
		boolean success= false;
		try
		{
			Statement s=conn.createStatement();
			
			String cc=	"INSERT INTO location values('"
						+ latitude
						+ "','"
						+ longitude
						+"')";
			s.execute(cc);

			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return success;
	}

	public void report(String fname, String lname, String sage, String sgender,
			String saddress, String sproblem) {
		// TODO Auto-generated method stub
		try {
			Statement st = conn.createStatement();
			st.execute("INSERT INTO report(firstname,lastname,age,gender,address,problem) values('"
					+ fname
					+ "','"
					+ lname
					+ "','"
					+ sage
					+ "','"
					+ sgender
					+ "','"
					+ saddress
					+ "','"
					+ sproblem +
					
					"')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	public void ack(String name, String ack, String stmt) {
		// TODO Auto-generated method stub
		try {
			Statement st = conn.createStatement();
			st.execute("INSERT INTO ack(name,ack,satements) values('"
					+ name
					+ "','"
					+ ack
					+ "','"
					+ stmt
					+"')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}