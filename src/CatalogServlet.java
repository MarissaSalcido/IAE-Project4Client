	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */ 
	 
	import org.codehaus.jackson.map.ObjectMapper;
	import org.codehaus.jackson.type.TypeReference; 
	import org.glassfish.jersey.client.ClientConfig;

import models.Book;

import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.ws.rs.client.Client;
	import javax.ws.rs.client.ClientBuilder;
	import javax.ws.rs.client.WebTarget;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.UriBuilder;
	import java.io.IOException;
	import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/catalog")
public class CatalogServlet extends HttpServlet{

	    @Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	        ClientConfig config = new ClientConfig();

	        Client client = ClientBuilder.newClient(config);

	        WebTarget target = client.target(getBaseURI());
	        
	        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library
	        
            String jsonResponse =
                    target.path("tomerest").path("books").
                            request(). //send a request
                            accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                            get(String.class); // use the get method and return the response as a string

            System.out.println(jsonResponse);
            
            List<Book> bookList = objectMapper.readValue(jsonResponse, new TypeReference<List<Book>>(){});
            Map<String,Book> bookMap = new HashMap<String,Book>();  
            
            for (int i = 0; i < bookList.size(); i++) {
            	
            	bookMap.put(bookList.get(i).getId(), bookList.get(i));  
            	
            }
            
	        request.setAttribute("bookMap", bookMap);
	        
	        getServletConfig().getServletContext().getRequestDispatcher("/jsp/catalog.jsp").forward(request, response);
	    }

	    private static URI getBaseURI() {

	        //Change the URL here to make the client point to your service.
	        return UriBuilder.fromUri("http://localhost:8081/TomeRestService").build();
	    }

}
