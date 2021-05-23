package model;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Fund

{
	

	private static String url = "jdbc:mysql://localhost:3306/funding";
	private static String userName = "root";
	private static String password = "1234";
	private static Connection con;
	
	//A common method to connect to the DB
	
	private static Connection connect()
	
	{ 
		
		try
		{   
			Class.forName("com.mysql.jdbc.Driver"); 
			//Provide the correct details: DBServer/DBName, user, password 
			
			con = DriverManager.getConnection(url,userName,password); 
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		
		return con; 
		}
	
	public String insertItem(String name, String institue, String position, String projectname, String description , String fundingtype , String amount, String accname , String accnumber, String date  )
	
	{
		String output = "";
		try{ 
			Connection con = connect();
			if (con == null) 
			{return"Error while connecting to the database for inserting."; }
			// create a prepared statement
			
			String query = " insert into fund  (`id`,`name`,`institute`,`position`,`projectname`,`description`,`fundingtype`,`amount`,`accname`,`accnumber`,`date`)"+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
		
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, institue); 
			preparedStmt.setString(4, position); 
			preparedStmt.setString(5, projectname);
			preparedStmt.setString(6, description); 
			preparedStmt.setString(7, fundingtype);
			preparedStmt.setString(8, amount); 
			preparedStmt.setString(9,accname ); 
			preparedStmt.setString(10, accnumber);
			preparedStmt.setString(11, date);
			
			// execute the statement
			
			
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully"; 
			
		}
		catch (Exception e)
		{
			output = "Error while inserting the item."; 
			System.err.println(e.getMessage()); 
			} return output; 
			
	}	
			
			
	public String readItems() 
	{ 
		String output = ""; 
		
	try
	{ 
		Connection con = connect();
		
		if (con == null)
		{return"Error while connecting to the database for reading."; } 
		
		// Prepare the html table to be displayed
		
		output = "<table border=\"1\"><tr><th>Name</th><th>Institute</th>" +"<th>Position</th>" +  "<th>Projectname</th>"+"<th>Description</th>"+"<th>Fundingtype</th>"+"<th>Amount</th>"+"<th>Accname</th>"+"<th>Accnumber</th>"+"<th>Date</th>"+"<th>Update</th><th>Remove</th></tr>";    
		
		String query = "select * from fund";
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(query); 
		
		// iterate through the rows in the result set
		
		while (rs.next()) 
		
		{
			String id = Integer.toString(rs.getInt("id"));
			String name = rs.getString("name"); 
			String institute = rs.getString("institute"); 
			String position = rs.getString("position"); 
			String Projectname = rs.getString("projectname");
			String description = rs.getString("description");
			String fundingtype = rs.getString("fundingtype"); 
			String amount= rs.getString("amount");
			String accname = rs.getString("accname"); 
			String accnumber = rs.getString("accnumber");
			String date = rs.getString("date"); 
			
			
			// Add into the  table
			
			
			 output += "<tr><td>"
					 + name + "</td>"; 
			 output += "<td>" + institute + "</td>"; 
			 output += "<td>" + position + "</td>"; 
			 output += "<td>" + Projectname + "</td>"; 
			 output += "<td>" + description + "</td>"; 
			 output += "<td>" + fundingtype + "</td>"; 
			 output += "<td>" + amount + "</td>"; 
			 output += "<td>" + accname + "</td>"; 
			 output += "<td>" + accnumber + "</td>"; 
			 output += "<td>" + date + "</td>"; 
			 
			

			
			// buttons update and delete
			 
			 
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
					 + "class='btnUpdate btn btn-secondary' data-itemid='" + id + "'></td>"
					 + "<td><input name='btnRemove' type='button' value='Remove' "
					 + "class='btnRemove btn btn-danger' data-itemid='" + id + "'></td></tr>";  
			
			
			
		} 
		con.close();
		
		// Complete the ht table
		
		output += "</table>";
		
	} 
	catch (Exception e)
	{
		output = "Error while reading the items."; 
		System.err.println(e.getMessage()); 
		}
	return output; 
	
	
	}
	
	
	
	public String updateItem(String id , String name, String institue, String position, String projectname, String description , String fundingtype , String amount, String accname , String accnumber, String date)
	
	{
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			
			if (con == null)
			{return"Error while connecting to the database for updating."; }
			
			// create a prepared statement
			
			String query = "UPDATE fund SET name=?,institute=?,position=?,projectname=?,description=?,fundingtype=?,amount=?,accname=?,accnumber=?,date=?  WHERE id=?";
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, institue); 
			preparedStmt.setString(3, position); 
			preparedStmt.setString(4, projectname);
			preparedStmt.setString(5, description); 
			preparedStmt.setString(6, fundingtype);
			preparedStmt.setString(7, amount); 
			preparedStmt.setString(8,accname ); 
			preparedStmt.setString(9, accnumber);
			preparedStmt.setString(10, date);
			
			
			// execute the statement
			
			preparedStmt.execute(); 
			con.close();
			
			String newItems = readItems(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
			 } 
			 catch (Exception e) 
			 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
	
	
	public String deleteItem(String id)
	
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			
			{return"Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			
			String query = "delete from fund where id=?"; 
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			
			preparedStmt.setInt(1, Integer.parseInt(id));
			
			// execute the statement
			
			preparedStmt.execute();
			con.close();
			
			 String newItems = readItems(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
			 
			 } 
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
			 System.err.println(e.getMessage()); 
			 } 
			 return output; 
			 } 
		
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


