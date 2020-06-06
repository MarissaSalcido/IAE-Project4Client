import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private double subtotal;   

    public Cart() {
        super();
    }
 


    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession currentSession = req.getSession(true);
		Vector<Item> tempCart = new Vector<Item>();
		tempCart.add(new Item("INF5678", "images/1945.jpg", "COUNTDOWN 1945", 15.30, 1));
		tempCart.add(new Item("INF1234", "images/a_stroke_of_malice.jpg", "A STROKE OF MALICE", 15.30, 2));
		
		currentSession.setAttribute("shoppingCart", tempCart);
				
		res.setContentType("text/html; charset='UTF-8'");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"    <head>\r\n" + 
				"        <title>Cart</title>\r\n" + 
				"        <meta name=\"description\" content=\"Cart\">\r\n" + 
				"        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n" + 
				"        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/ordering.css\">\r\n" + 
				"        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>\r\n" + 
				"        <script type = \"text/javascript\" src=\"js/orderingAjax.js\"></script>\r\n" + 
				"		 <script type=\"text/javascript\" src=\"js/orderFormValidation.js\"></script>" +
				"    </head>\r\n" + 
				"    <body>\r\n" + 
					"	<div class = \"container\">\r\n" + 
					"        <div class=\"menu noTextDecoration\">\r\n" + 
					"            <ul>\r\n" + 	
				"                    <li class =\"logo\"> <a style=\"text-decoration:none;\" href=\"index.html\"> <img src=\"images/book.png\"> </a> </li>\r\n" + 
				"                    <li class =\"title\"><a style=\"text-decoration:none;\" href=\"index.html\">Tome</a></li>\r\n" + 
					"                <li> <a href=\"../index.html\" style=\"text-decoration:none;\"> Home </a> </li>\r\n" +
					"                <li><a style=\"text-decoration:none;\" href=\"html/aboutus.html\">About Us</a></li>\r\n" +
					"                <li> <a style=\"text-decoration:none;\" href=\"catalog\"> Catalog </a> </li>\r\n" + 
				"                    <li><a href=\"Cart\">Cart</a></li>" +
					"            </ul>\r\n" + 
					"        </div>\r\n" + 
					"    </div>\r\n" + 
				"        <div class=\"center\">\r\n" + 
				"            <div class=\"overall\">\r\n" + 
				"                <h1>Shopping Cart</h1>\r\n"); 

		
		@SuppressWarnings("unchecked")
		Vector<Item> cart = (Vector<Item>) currentSession.getAttribute("shoppingCart");
		
		// If cart does not exist or is empty, display message for empty cart 
		if ((cart == null) || cart.isEmpty()) {
			out.println("<h2>Your shopping cart is empty.</h2>");
		}
		else {
			this.subtotal = 0;
			NumberFormat moneyFormat = new DecimalFormat("#0.00");
			
			// Print out table for shopping cart
			out.println(
			"                <table>\r\n" + 
			"                    <tr>\r\n" + 
			"                        <th class=\"leftedge\">Product Id</th>\r\n" + 
			"                        <th></th>\r\n" + 
			"                        <th>Item</th>\r\n" + 
			"                        <th>Price</th>\r\n" + 
			"                        <th class=\"rightedge\">Quantity</th>\r\n" + 
			"                    </tr>\r\n");
			
			// Print each item stored in cart
			for (int i = 0; i < cart.size(); ++i) {	
				out.println("	<tr>");
				out.println("		<td class=\"leftedge\">" + cart.elementAt(i).id + "</td>"); 
				out.println("       <td><img src=\"" + cart.elementAt(i).imageSrc + "\" alt=\"This book name\" width=\"80\" height=\"100\"></td>\r\n"); 
				out.println("   	<td>" + cart.elementAt(i).item + "</td>"); 
				out.println("   	<td>$" + moneyFormat.format(cart.elementAt(i).price) + "</td>"); 
				out.println("   	<td class=\"rightedge\">" + cart.elementAt(i).quantity + "</td>"); 
				out.println("	</tr>");
				
				this.subtotal += cart.elementAt(i).price * cart.elementAt(i).quantity;
			}
			
			currentSession.setAttribute("subtotal", this.subtotal);
			
			out.println("</table>");
			
			
			out.println("<div class=\"lefttotal\">");
			out.println("	<h6 class=\"totalline\">Subtotal:</h6>"); 
			out.println("   <p class=\"totalline\" id=\"subtotal\">$" + moneyFormat.format(this.subtotal) + "</p></div>"); 


			out.println("               <br>" +
					"				<form action=\"CheckOut\" method=\"post\">" +
					"					<div class=\"lefttotal\">" +
							"                <label for=\"shippingmethod\">Shipping Method:</label>" + 
							"                <select name=\"shippingmethod\" id=\"shippingmethod\" onchange=\"shippingCost()\" required>\r\n" + 
							"                    <option value=\"ground\">$0.00 6-Days Ground</option>\r\n" + 
							"                    <option value=\"expedited\">$10.99 2-Days Expedited   </option>\r\n" + 
							"                    <option value=\"overnight\">$17.50 Overnight</option>\r\n" + 
							"                </select>" + 
					"                	</div>");
			
			out.println(  
					"           <!-- Checkout button -->\r\n" + 
					"				<div class=\"center\">" +
		    		"       			<input type=\"submit\" class=\"button\" value=\"Check Out\">" +
					"				</div></form>");
		}
		

		
		
		out.println(
				"            </div>\r\n" + 
				"        </div>\r\n" + 
				"    </body>\r\n" + 
				"</html>");
		
		out.close();
	}
	
	

}
