

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckOut
 */
@WebServlet("/CheckOut")
public class CheckOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public CheckOut() {
        super();
    }


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String shippingMethod = req.getParameter("shippingmethod");
		HttpSession currentSession = req.getSession(false);
		
		currentSession.setAttribute("shippingMethod", shippingMethod);
		
		res.setContentType("text/html; charset='UTF-8'");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"    <head>\r\n" + 
				"        <title>Check Out</title>\r\n" + 
				"        <meta name=\"description\" content=\"Check Out\">\r\n" + 
				"        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n" + 
				"        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/ordering.css\">\r\n" + 
				"        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>\r\n" + 
				"        <script type = \"text/javascript\" src=\"js/orderingAjax.js\"></script>\r\n" + 
				"        <script type=\"text/javascript\" src=\"js/orderFormValidation.js\"></script>\r\n" + 
				"    </head>\r\n"); 
		out.println("    <body>\r\n" + 
				"\r\n" + 
				"	<div class = \"container\">\r\n" + 
				"        <div class=\"menu noTextDecoration\">\r\n" + 
				"            <ul>\r\n" + 	
				"                <li class =\"logo\"> <a href=\"../index.html\"> <img src=\"images/book.png\"> </a> </li>\r\n" + 
				"                <li class =\"title\"> <a href=\"../index.html\">Tome</a> </li>\r\n" + 
				"                <li> <a href=\"../index.html\"> Home </a> </li>\r\n" +
				"                <li><a href=\"html/aboutus.html\">About Us</a></li>\r\n" +
				"                <li> <a href=\"catalog\"> Catalog </a> </li>\r\n" + 
				"                <li><a href=\"Cart\">Cart</a></li>" + 
				"            </ul>\r\n" + 
				"        </div>\r\n" + 
				"    </div>\r\n" +
				
				
				"        <div class=\"center\">\r\n" + 
				"			<div class=\"overall\">" +
				"                <h1>Check Out</h1>\r\n");


			out.println(
			"            <form action=\"OrderProcessing\" novalidate class=\"overall\" name=\"orderform\" id=\"orderform\" onSubmit=\"return validateForm()\" method=\"post\">\r\n" +
			"                <p id=\"formnote\">All fields required, except \"Address 2\" field</p>\r\n" + 
							"\r\n" +
			"                <h2>Personal</h2>\r\n" + 
			"                <!-- First name -->\r\n" + 
			"                <label for=\"firstname\">First Name: </label>\r\n" + 
			"                <input name=\"firstname\" id=\"firstname\" type=\"text\" required>\r\n" + 
			"                <div for=\"firstname\" id=\"firstnameerror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <!-- Last name -->\r\n" + 
			"                <label for=\"lastname\">Last Name:</label>\r\n" + 
			"                <input name=\"lastname\" id=\"lastname\" type=\"text\" required>\r\n" + 
			"                <div for=\"lastname\" id=\"lastnameerror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <!-- Phone -->\r\n" + 
			"                <label for=\"phone\">Phone Number:</label>\r\n" + 
			"                <input name=\"phone\" id=\"phone\" type=\"text\" pattern=\"[1-9]{1}\\d{9}\" required>\r\n" + 
			"                <span class=\"helptext\">10 digit U.S. phone number; numbers only</span>\r\n" + 
			"                <div for=\"phone\" id=\"phoneerror\" class=\"errormessage\"></div>\r\n" + 
			"\r\n" + 
			"                <h2>Shipping</h2>\r\n" + 
			"                <!-- Street address -->\r\n" + 
			"                <label for=\"address1\">Address 1:</label>\r\n" + 
			"                <input name=\"address1\" id=\"address1\" type=\"text\" required>\r\n" + 
			"                <div for=\"address1\" id=\"address1error\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <label for=\"address2\">Address 2:</label>\r\n" + 
			"                <input name=\"address2\" id=\"address2\" type=\"text\">\r\n" + 
			"                <span class=\"helptext\">(Optional) Apartment number, floor, etc</span>\r\n" + 
			"                <div for=\"address2\" id=\"address2error\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <!-- Zip -->\r\n" + 
			"                <label for=\"zip\">Zip:</label>\r\n" + 
			"                <input name=\"zip\" id=\"zip\" onblur=\"getZipCode(this.value);getCityState(this.value)\"\r\n" + 
			"                type=\"text\" pattern=\"[0-9]{5}\" required>\r\n" + 
			"                <span class=\"helptext\">Numbers only</span>\r\n" + 
			"                <div for=\"zip\" id=\"ziperror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <!-- City -->\r\n" + 
			"                <label for=\"city\">City:</label>\r\n" + 
			"                <input name=\"city\" id=\"city\" type=\"text\" required>\r\n" + 
			"                <div for=\"city\" id=\"cityerror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <!-- State -->\r\n" + 
			"                <label for=\"state\">State:</label>\r\n" + 
			"                <input name=\"state\" id=\"state\" type=\"text\" required>\r\n" + 
			"                <div for=\"state\" id=\"stateerror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>");
			
			double shippingCost = 0;
			out.println("<label class=\"orderconflabel\">Shipping Method: </label>");
			if (shippingMethod.compareTo("ground") == 0) {
				out.println("<span>6-Days Ground   $0.00</span>");
				shippingCost = 0;
			}
			else if (shippingMethod.compareTo("expedited") == 0) {
				out.println("<span>2-Days Expedited   $10.99</span>");
				shippingCost = 10.99;
			}
			else if (shippingMethod.compareTo("overnight") == 0) {
				out.println("<span>Overnight   $17.50</span>");
				shippingCost = 17.50;
			}
			
			out.println("                <h2>Billing</h2>\r\n" + 
			"                <!-- Credit card type -->\r\n" + 
			"                <label for=\"cardtype\">Card Type:</label>\r\n" + 
			"                <select name=\"cardtype\" id=\"cardtype\" required>\r\n" + 
			"                    <option value=\"\" selected=\"selected\"></option>\r\n" + 
			"                    <option value=\"Discover\">Discover</option>\r\n" + 
			"                    <option value=\"Mastercard\">Mastercard</option>\r\n" + 
			"                    <option value=\"Visa\">Visa</option>\r\n" + 
			"                </select>\r\n" + 
			"                <div for=\"cardtype\" id=\"cardtypeerror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <!-- Credit card number -->\r\n" + 
			"                <label for=\"cardnumber\">Card Number:</label>\r\n" + 
			"                <input name=\"cardnumber\" id=\"cardnumber\" type=\"text\" pattern=\"[0-9]{16}\" required>\r\n" + 
			"                <span class=\"helptext\">Numbers only</span>\r\n" + 
			"                <div for=\"cardnumber\" id=\"cardnumbererror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>\r\n" + 
			"\r\n" + 
			"                <!-- Expiration month and year -->\r\n" + 
			"                <label id=\"expiration\">Expiration:</label>\r\n" + 
			"                <select name=\"expmonth\" id=\"expmonth\" required>\r\n" + 
			"                    <option value=\"\" selected=\"selected\"></option>\r\n" + 
			"                    <option value=\"1\">January</option>\r\n" + 
			"                    <option value=\"2\">February</option>\r\n" + 
			"                    <option value=\"3\">March</option>\r\n" + 
			"                    <option value=\"4\">April</option>\r\n" + 
			"                    <option value=\"5\">May</option>\r\n" + 
			"                    <option value=\"6\">June</option>\r\n" + 
			"                    <option value=\"7\">July</option>\r\n" + 
			"                    <option value=\"8\">August</option>\r\n" + 
			"                    <option value=\"9\">September</option>\r\n" + 
			"                    <option value=\"10\">October</option>\r\n" + 
			"                    <option value=\"11\">November</option>\r\n" + 
			"                    <option value=\"12\">December</option>\r\n" + 
			"                </select>\r\n" + 
			"                <select name=\"expyear\" id=\"expyear\" required>\r\n" + 
			"                    <option value=\"\" selected=\"selected\"></option>\r\n" + 
			"                    <option value=\"2020\">2020</option>\r\n" + 
			"                    <option value=\"2021\">2021</option>\r\n" + 
			"                    <option value=\"2022\">2022</option>\r\n" + 
			"                    <option value=\"2023\">2023</option>\r\n" + 
			"                    <option value=\"2024\">2024</option>\r\n" + 
			"                    <option value=\"2025\">2025</option>\r\n" + 
			"                    <option value=\"2026\">2026</option>\r\n" + 
			"                    <option value=\"2027\">2027</option>\r\n" + 
			"                    <option value=\"2028\">2028</option>\r\n" + 
			"                    <option value=\"2029\">2029</option>\r\n" + 
			"                    <option value=\"2030\">2030</option>\r\n" + 
			"                    <option value=\"2031\">2031</option>\r\n" + 
			"                </select>\r\n" + 
			"                <br>\r\n" + 
			"                <div for=\"expiration\" id=\"experror\" class=\"errormessage\"></div>\r\n" + 
			"\r\n" + 
			"                <!-- CVV -->\r\n" + 
			"                <label>CVV:</label>\r\n" + 
			"                <input name=\"cvv\" id=\"cvv\" pattern=\"[0-9]{3}\" required>\r\n" + 
			"                <br>\r\n" + 
			"                <div for=\"cvv\" id=\"cvverror\" class=\"errormessage\"></div>\r\n" + 
			"                <br>");
			
		
			
		
			DecimalFormat moneyFormat = new DecimalFormat("#0.00");
			
			@SuppressWarnings("unchecked")
			Vector<Item> cart = (Vector<Item>) currentSession.getAttribute("shoppingCart");
			Double sessionSubtotal = (Double) currentSession.getAttribute("subtotal");
			double subtotal = sessionSubtotal.doubleValue();
			
			double tax = Math.floor(subtotal * 0.08 * 100) / 100;
			double total = subtotal + tax + shippingCost;
			currentSession.setAttribute("subtotal", subtotal);
			currentSession.setAttribute("tax", tax);
			currentSession.setAttribute("shippingCost", shippingCost);
			currentSession.setAttribute("total", total);
			
			
			// Print out table for shopping cart
			out.println(
			" <br>" +		
			" 				 <h2>Order</h2>" +
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
			}
			
			
			out.println("</table>");
			
			
			out.println("<div class=\"total\">");
			out.println("	<h6 class=\"totalline\">Subtotal:</h6>"); 
			out.println("   <p class=\"totalline\" id=\"subtotal\">$" + moneyFormat.format(subtotal) + "</p>\r\n" + 
					"	</div>" + 
					"	<br>");
			
			out.println("<div class=\"total\">");
			out.println("	<h6 class=\"totalline\">Tax:</h6>"); 
			out.println("   <p class=\"totalline\" id=\"tax\">$" + moneyFormat.format(tax) + "</p>\r\n" + 
					"	</div>" + 
					"	<br>");
			
			
			
			
			out.println("<div class=\"total\">");
			out.println("	<h6 class=\"totalline\">Shipping:</h6>"); 
			out.println("   <p class=\"totalline\" id=\"shipping\">$" + moneyFormat.format(shippingCost) + "</p>\r\n" + 
					"	</div>" + 
					"	<br>");
			
			out.println("<div class=\"total\">");
			out.println("	<h6 class=\"totalline\">Total:</h6>"); 
			out.println("   <p class=\"totalline\" id=\"Total\">$" + moneyFormat.format(total) + "</p>\r\n" + 
					"	</div>" + 
					"	<br>");
						
			out.println( 
			"                <div class=\"center\">\r\n" + 
			"                    <input class=\"button\" id=\"button\" type=\"submit\" name=\"submit\" value=\"Submit Order\">\r\n" + 
			"                </div>\r\n" + 
			"            </form>\r\n" + 
			"        </div>\r\n" +
			" <br> " +		
			"    </body>\r\n" + 
			"</html>\r\n");
		
			out.close();
	}

}
