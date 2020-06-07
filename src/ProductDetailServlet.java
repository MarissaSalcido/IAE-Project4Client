import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;

import models.Book;

@WebServlet("/ProductDetail")
public class ProductDetailServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public ProductDetailServlet() {
		super();
	}
	private static URI getBaseURI()
    {
        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri(RestUri.URI).build();
    }
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doGet(req, res);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		HttpSession currentSession = req.getSession(true);
		
		String author = null;
		String name = null;
		String price = null;
		String type = null;
		String imgpath = null;
		String desc = null;
		
		try {		
		    
			String productID = req.getParameter("id");
			String addProduct = req.getParameter("add");
			String added = "";
			
			ClientConfig config = new ClientConfig();

	        Client client = ClientBuilder.newClient(config);

	        WebTarget target = client.target(getBaseURI());

	        ObjectMapper objectMapper = new ObjectMapper();

	        String jsonResponse =
	            target.path("tomerest").path("books").path(productID).
	                request().
	                accept(MediaType.APPLICATION_JSON).
	                get(String.class);

	        Book book = objectMapper.readValue(jsonResponse, Book.class);
		    
	    	author = book.getAuthor();
	    	name = book.getName();
	    	price = Float.toString(book.getPrice());
	    	type = book.getType();
	    	imgpath = book.getImage();
	    	desc = book.getSummary();
		    
		    
			if (addProduct != null) {
				Vector<Item> cart = new Vector<Item>();
				
				if (currentSession.getAttribute("shoppingCart") != null) {
					cart = (Vector<Item>) currentSession.getAttribute("shoppingCart");
					boolean alreadyInCart = false;
					for (int i = 0; i < cart.size(); i++) {
						if (cart.get(i).id.equals(addProduct)) {
							cart.get(i).quantity += Integer.parseInt(req.getParameter("amount"));
							alreadyInCart = true;
						}
					}
					
					if (!alreadyInCart) {
						cart.add(new Item(productID, imgpath, name, Double.parseDouble(price), Integer.parseInt(req.getParameter(("amount")))));
					}
				}
				else {
					cart.add(new Item(productID, imgpath, name, Double.parseDouble(price), Integer.parseInt(req.getParameter(("amount")))));
				}
				currentSession.setAttribute("shoppingCart", cart);	
				added = "Added "+req.getParameter("amount")+" item(s) to cart!";
			}
			else {
				// Adding item to recently viewed products history
				Vector<Item> history;
				if (currentSession.getAttribute("history") != null) {
					history = (Vector<Item>) currentSession.getAttribute("history");
				}
				else {
					history = new Vector<Item>();
				}
				history.add(new Item(productID, imgpath, name, Double.parseDouble(price)));
				currentSession.setAttribute("history", history);
			}
			
			res.setContentType("text/html");
			String html = "<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"	<meta charset=\"ISO-8859-1\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\">\r\n" + 
					"    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>\r\n" + 
					"    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/productpage.css\">\r\n" + 
					"    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n" + 
					"    <title>Product Details</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<div class = \"container\">\r\n" + 
					"        <div class=\"menu\">\r\n" + 
					"            <ul>\r\n" + 	
					"                <li class =\"logo\"> <a href=\"index.html\" style=\"text-decoration:none;\"><img src=\"images/book.png\"> </li>\r\n" + 
					"                <li class =\"title\"> Tome </li>\r\n" + 
					"                <li> <a href=\"index.html\" style=\"text-decoration:none;\"> Home </a> </li>\r\n" +
					"                <li><a href=\"html/aboutus.html\" style=\"text-decoration: none;\">About Us</a></li>\r\n" +
					"                <li class=\"active\"> <a href=\"catalog\" style=\"text-decoration:none;\"> Catalog </a> </li>\r\n" + 
					"                <li><a href=\"Cart\">Cart</a></li>\r\n" +
					"            </ul>\r\n" + 
					"        </div>\r\n" + 
					"    </div>\r\n" + 
					"	<div class=\"product_info\">\r\n" + 
					"		<div class=\"img_container\" class=\"img-magnifier-container\">\r\n" + 
					"          <img src=\""+imgpath+"\" alt=\""+name+"\" width=\"70%\" height=\"90%\">\r\n" + 
					"        </div>\r\n" + 
					"        <!-- Box for product description content -->\r\n" + 
					"        <div class=\"product_container\">\r\n" + 
					"          <div class=\"info_fix\">\r\n" + 
					"            <h3>"+name+" - "+author+"</h3>\r\n" + 
					"            <p>"+type+"</p>"+
					"            <p class=\"product_code\">Product Code: "+productID+"</p><br>\r\n" + 
					"            <h4 class=\"price\"> USD $"+price+" </h4><br>\r\n" + 
					"            <h4 class=\"aval\">Availability: In Stock</h4><br>\r\n" + 
					"            <p class=\"word_wrap\"><strong>"+desc+"</strong></p>\r\n" + 
					
					"          </div>\r\n" + 
					"        </div>\r\n" + 
					"   </div>"+
					"	<form class=\"addToCartForm\" method='post' action=\"ProductDetail?id="+productID+"&add="+productID+"\">"
					+ "<small><input class=\"formInputs\" type=\"number\" name=\"amount\" value=\"1\" min=\"1\"></small>"+
					"  <small><input class=\"formInputs\" type=\"submit\" value=\"Add to Cart\" /></small>"+ added +
		    		"</body>\r\n" + 
					"</html>";
			PrintWriter out = res.getWriter();
			out.print(html);			
		}catch(Exception e) {
			System.out.println(e);
			}
	}

}
