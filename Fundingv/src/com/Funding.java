package com;
import model.Fund;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Fund")
public class Funding 

{
	Fund itemObj = new Fund();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() 
	{ 
		return itemObj.readItems(); 
	} 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("name") String name,
			
			
	
	@FormParam("institue")String institue, 
	@FormParam("position")String position,
	@FormParam("projectname") String projectname,
	@FormParam("description") String description,
	@FormParam("fundingtype") String fundingtype,
	@FormParam("amount") String amount,
	@FormParam("accname") String accname ,
	@FormParam("accnumber")String accnumber,
	@FormParam("date")String date)
	
	{ 
		String output = itemObj.insertItem( name,  institue,  position, projectname,  description , fundingtype , amount, accname , accnumber, date ); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem (String itemData)
	{
		//Convert the input string to a JSON object 
		
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON object
		
		String id = itemObject.get("id").getAsString();
		String name = itemObject.get("name").getAsString(); 
		String institue = itemObject.get("institue").getAsString(); 
		String position = itemObject.get("position").getAsString();
		String projectname = itemObject.get("projectname").getAsString();  
		String  description = itemObject.get(" description").getAsString(); 
		String fundingtype = itemObject.get("fundingtype").getAsString(); 
		String amount = itemObject.get("amount").getAsString(); 
		String accname = itemObject.get("accname").getAsString();
		String accnumber = itemObject.get("accnumber").getAsString();
		String date = itemObject.get("date").getAsString();  
		
		String output = itemObj.updateItem(id, name, institue, position, projectname, description, fundingtype, amount, accname, accnumber, date); 
		
		return output; 
		}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
		//Convert the input string to an XML document
		
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		
		String id = doc.select("id").text(); 
		String output = itemObj.deleteItem(id);
		
		return output;
		}
	}
	
	

