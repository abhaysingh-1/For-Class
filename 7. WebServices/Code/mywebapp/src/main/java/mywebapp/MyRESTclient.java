package mywebapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class MyRESTclient extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, 
						 HttpServletResponse resp) 
	throws ServletException, IOException 
	{
		//resp.sendRedirect("index.html");
		
		//Get the Form Data
		String locationVal = req.getParameter("location");
		String dataType = req.getParameter("type");
		
		String meditype=null;
		String url=null;
		if(dataType.equals("txt")){
			meditype = MediaType.TEXT_PLAIN;
			url = "http://localhost:8080/googlemaps/rest/mapsapi/text/"+locationVal;
		}else if(dataType.equals("xml")){
			meditype = MediaType.APPLICATION_XML;
			url = "http://localhost:8080/googlemaps/rest/mapsapi/json/"+locationVal;
		}else if(dataType.equals("json")){
			url = "http://localhost:8080/googlemaps/rest/mapsapi/json/"+locationVal;
			meditype = MediaType.APPLICATION_JSON;
		}
		
		resp.setContentType(meditype);
		PrintWriter out = resp.getWriter();
		
		try 
		{
			//1. Build the Client
			Client client = ClientBuilder.newClient();
			//2. Set the Target
			WebTarget webTarget = client.target(url);
//			WebTarget webTarget = client.target("https://api.instagram.com/v1/media/search?lat=42.0231310&lng=-87.5236610&distance=200&access_token=4248825024.ba7bedc.dec25036d64c4e52993b531bdbe4c933");
//			WebTarget webTarget = client.target("https://maps.googleapis.com/maps/api/geocode/xml?address=chicago");
			//3. Get the Response
			Response response = webTarget.request(meditype).get();
			//4. Process the Response
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
			String output = (String) response.readEntity(String.class);
			out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}//End of try-catch block
	}
}
