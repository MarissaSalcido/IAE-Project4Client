
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import models.Book;
import models.Order;;

/**
 * Servlet implementation class OrderConf
 */
@WebServlet("/OrderConf")
public class OrderConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderConf() {
        super();
    }


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession currentSession = req.getSession(true);
		
		String orderJSON = (String) currentSession.getAttribute("shoppingCart");
	
		ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library	        Order orderConf = objectMapper.readValue(orderJSON, new TypeReference<Order>(){});

		
		res.setContentType("text/html; charset='UTF-8'");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"    <head>\r\n" + 
				"        <title>Order Confirmation</title>\r\n" + 
				"        <meta name=\"description\" content=\"Order Confirmation\">\r\n" + 
				"        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n" + 
				"        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/ordering.css\">\r\n" + 
				"        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>\r\n" + 
				"    </head>\r\n" + 
				"    <body>\r\n" + 
				"	<div class = \"container\">\r\n" + 
				"        <div class=\"menu\">\r\n" + 
				"            <ul>\r\n" + 	
				"                <li class =\"logo\"> <a href=\"index.html\" style=\"text-decoration:none;\"><img src=\"images/book.png\"> </li>\r\n" + 
				"                <li class =\"title\"> Tome </li>\r\n" + 
				"                <li> <a href=\"index.html\" style=\"text-decoration:none;\"> Home </a> </li>\r\n" +
				"                <li><a href=\"html/aboutus.html\" style=\"text-decoration: none;\">About Us</a></li>\r\n" +
				"                <li> <a href=\"products\" style=\"text-decoration:none;\"> Catalog </a> </li>\r\n" + 
				"                <li>\r\n" + 
	    		"                	<form action=\"Cart\" method=\"post\">\r\n" + 
	    		"                    	<small><input class=\"formInputs cartNav\" type=\"submit\" value=\"Cart\"></small>\r\n" + 
	    		"                  	</form>\r\n" + 
	    		"                </li>\r\n" +
				"            </ul>\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" +
				"        <div class=\"center\">\r\n" + 
				"            <div class=\"overall\">\r\n" + 
				"                <h1>Thank you for your order!</h1>");			
		
		// Display order confirmation information 
		out.println("<label class='orderconflabel'>Order Number:</label>");
		out.println("<span>" + orderConf.getOrderId() + "</span>"); 
			
			
			
			out.println("<h2>Personal</h2>");
			
			out.println("<label class=\"orderconflabel\">First Name: </label>");
			out.println("<span>" +orderConf.getFirstName() + "</span>");
			out.println("<br>");
			
			out.println("<label class=\"orderconflabel\">Last Name: </label>");
			out.println("<span>" +orderConf.getLastName() + "</span>");
			out.println("<br>");

			out.println("<label class=\"orderconflabel\">Phone: </label>");
			out.println("<span>" +orderConf.getPhoneNum() + "</span>");
			out.println("<br>");
			
			out.println("<h2>Shipping</h2>");
			
			out.println("<label class=\"orderconflabel\">Address 1: </label>");
			out.println("<span>" +orderConf.getAddress1() + "</span>");
			out.println("<br>");
			
			out.println("<label class=\"orderconflabel\">Address 2: </label>");
			out.println("<span>" +orderConf.getAddress2() + "</span>");
			out.println("<br>");

			out.println("<label class=\"orderconflabel\">Zip: </label>");
			out.println("<span>" +orderConf.getZipcode() + "</span>");
			out.println("<br>");
			
			out.println("<label class=\"orderconflabel\">City: </label>");
			out.println("<span>" +orderConf.getCity() + "</span>");
			out.println("<br>");
			
			out.println("<label class=\"orderconflabel\">State: </label>");
			out.println("<span>" +orderConf.getState() + "</span>");
			out.println("<br>");

			String method = orderConf.getShippingMethod();	
			out.println("<label class=\"orderconflabel\">Shipping Method: </label>");
			if (method.compareTo("ground") == 0) {
				out.println("<span>6-Days Ground   $0.00</span>");
			}
			else if (method.compareTo("expedited") == 0) {
				out.println("<span>2-Days Expedited   $10.99</span>");
			}
			else if (method.compareTo("overnight") == 0) {
				out.println("<span>Overnight   $17.50</span>");
			}
			out.println("<br>");
					
			
			out.println("<h2>Billing</h2>");
			
			out.println("<label class=\"orderconflabel\">Card Type: </label>");
			out.println("<span>" + orderConf.getCardType() + "</span>");
			out.println("<br>");
			
			out.println("<label class=\"orderconflabel\">Credit card number: </label>");
			out.println("<span>ending with " + orderConf.getCardNumber().substring(12) + "</span>");
			out.println("<br>");

			out.println("<label class=\"orderconflabel\">Expiration: </label>");
			out.println("<span>" + orderConf.getExpMonth() + "/" + orderConf.getExpYear() + "</span>");
			out.println("<br><br>");				
		
			
			// Print out table for items ordered
			out.println("<h2>Items</h2>");
			out.println(
			"                <table>\r\n" + 
			"                    <tr>\r\n" + 
			"                        <th class=\"leftedge\">Product Id</th>\r\n" + 
			"                        <th></th>\r\n" + 
			"                        <th>Item</th>\r\n" + 
			"                        <th>Price</th>\r\n" + 
			"                        <th class=\"rightedge\">Quantity</th>\r\n" + 
			"                    </tr>\r\n");
			
			String product_id;
			String image_src;
			String item;
			String price;
			int quantity;

			
			// Loop through each arraylist/list item and assign to variable
			while (result.next()) {
				product_id = result.getString("product_id");
				image_src = result.getString("image_src");
				item = result.getString("item");
				price = result.getString("price");
				quantity = result.getInt("quantity");
					
				
				out.println("	<tr>");
				out.println("		<td class=\"leftedge\">" + product_id + "</td>"); 
				out.println("       <td><img src=\"" + image_src + "\" alt=\"This book name\" width=\"80\" height=\"100\"></td>\r\n"); 
				out.println("   	<td>" + item + "</td>"); 
				out.println("   	<td>$" + price + "</td>"); 
				out.println("   	<td class=\"rightedge\">" + quantity + "</td>"); 
				out.println("	</tr>");
			}
				
			out.println("</table>");
			
			
			result = statement.executeQuery("SELECT subtotal, tax, shipping, total FROM billing WHERE order_id='" + orderId + "'");
			if (result.next()) {
				String subtotal = result.getString("subtotal");
				String tax = result.getString("tax");
				String shipping = result.getString("shipping");
				String total = result.getString("total");
			
			
				out.println("<div class=\"total\">");
				out.println("	<h6 class=\"totalline\">Subtotal:</h6>"); 
				out.println("   <p class=\"totalline\" id=\"subtotal\">$" + subtotal + "</p>\r\n" + 
						"	</div>" + 
						"	<br>");
				
				out.println("<div class=\"total\">");
				out.println("	<h6 class=\"totalline\">Tax:</h6>"); 
				out.println("   <p class=\"totalline\" id=\"tax\">$" + tax + "</p>\r\n" + 
						"	</div>" + 
						"	<br>");
				
				out.println("<div class=\"total\">");
				out.println("	<h6 class=\"totalline\">Shipping:</h6>"); 
				out.println("   <p class=\"totalline\" id=\"shipping\">$" + shipping + "</p>\r\n" + 
						"	</div>" + 
						"	<br>");
				
				out.println("<div class=\"total\">");
				out.println("	<h6 class=\"totalline\">Total:</h6>"); 
				out.println("   <p class=\"totalline\" id=\"Total\">$" + total + "</p>\r\n" + 
						"	</div>" + 
						"	<br>");
			}
			
			result.close();
		    statement.close();
		    con.close();
		    currentSession.removeAttribute("shoppingCart");
		    currentSession.removeAttribute("subtotal");
		    currentSession.removeAttribute("shippingMethod");
		    currentSession.removeAttribute("shippingCost");
		    currentSession.removeAttribute("tax");
		    currentSession.removeAttribute("total");
		} 
        catch(Exception e) {
			System.out.println("Error in Order Conf: " + e);
		}

	
		out.println("</div></div></body></html>");
		out.close();
		
		
	}

}
