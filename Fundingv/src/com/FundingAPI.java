package com;

import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FundingAPI
 */
@WebServlet("/FundingAPI")
public class FundingAPI extends HttpServlet {
	Funding itemObj = new Funding();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FundingAPI() {
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
		String output = itemObj.insertItem(request.getParameter("name"), 
				request.getParameter("institue"), 
				request.getParameter("position"), 
				request.getParameter("projectname"), 
				request.getParameter("description"), 
				request.getParameter("fundingtype"), 
				request.getParameter("amount"), 
				request.getParameter("accname"), 
				request.getParameter("accnumber"), 
				request.getParameter("date"));
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	
	{
		Map paras = getParasMap(request); 
		
		 String output = itemObj.updateItem(paras.get("hidItemIDSave").toString(),
		 paras.get("name").toString(), 
		 paras.get("institue").toString(), 
		 paras.get("position").toString(), 
		 paras.get("projectname").toString(),
		 paras.get("description").toString(), 
		 paras.get("fundingtype").toString(), 
		 paras.get("amount").toString(), 
		 paras.get("accname").toString(),
		 paras.get("accnumber").toString(), 
		 paras.get("date").toString());
		 
				 
		 response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request); 
		String output = itemObj.deleteItem(paras.get("id").toString()); 
		response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) 
		{ 
		 Map<String, String> map = new HashMap<String, String>(); 
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
		 String queryString = scanner.hasNext() ? 
		 scanner.useDelimiter("\\A").next() : ""; 
		 scanner.close(); 
		 String[] params = queryString.split("&"); 
		 for (String param : params) 
		 { 
		String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}
}
