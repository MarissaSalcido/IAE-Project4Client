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

    @Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}
	
    @Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		processRequest(req, res);
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession currentSession = req.getSession(false);
		double shippingCost;
				
		String shippingMethod = req.getParameter("shippingmethod");
		currentSession.setAttribute("shippingMethod", shippingMethod);
				
		FormErrors errorMessages;
		if (currentSession.getAttribute("errorMessages") == null) {
			currentSession.setAttribute("errorMessages", new FormErrors());
			System.out.println("errorMessages is null");
		}
		else {
			System.out.println("errorMessages is not null");
		}
		
		errorMessages = (FormErrors) currentSession.getAttribute("errorMessages");
		
		res.setContentType("text/html; charset='UTF-8'");
		PrintWriter out = res.getWriter();
		out.println("<!DOCTYPE html>"); 
		out.println("<html lang=\"en\">"); 
		out.println("	<head>"); 
		out.println("       <title>Check Out</title>"); 
		out.println("       <meta name=\"description\" content=\"Check Out\">"); 
		out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">"); 
		out.println("       <link rel=\"stylesheet\" type=\"text/css\" href=\"css/ordering.css\">"); 
		out.println("		<link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>"); 
		out.println("   </head>"); 
		out.println("	<body>"); 
		out.println("		<div class = \"container\">"); 
		out.println("       	<div class=\"menu noTextDecoration\">"); 
		out.println("           	<ul>"); 	
		out.println("               	<li class =\"logo\"> <a href=\"../index.html\"> <img src=\"images/book.png\"> </a> </li>"); 
		out.println("                	<li class =\"title\"> <a href=\"../index.html\"> Tome </a> </li>"); 
		out.println("               	<li> <a href=\"../index.html\"> Home </a> </li>");
		out.println("               	<li> <a href=\"html/aboutus.html\"> About Us </a> </li>");
		out.println("               	<li> <a href=\"catalog\"> Catalog </a> </li>"); 
		out.println("               	<li> <a href=\"Cart\"> Cart </a> </li>");
		out.println("				</ul>");
		out.println("			</div>"); 
		out.println("		</div>");
		out.println("       <div class=\"center\">"); 
		out.println("			<div class=\"overall\">");
		out.println("				<h1>Check Out</h1>");
		out.println("				<form action=\"OrderProcessing\" novalidate class=\"overall\" name=\"orderform\" id=\"orderform\" onSubmit=\"return validateForm()\" method=\"post\">");
		out.println("               	<p id=\"formnote\">All fields required, except \"Address 2\" field</p>"); 
		out.println("					<h2>Personal</h2>"); 

		// If form was submitted, then there are errors
		if (errorMessages.formSubmitted) {
		
			out.println("<script type=\"text/javascript\">");
			out.println("alert('" + ((String) req.getAttribute("errorMessagesString")) + "');");
			out.println("</script>");
			
			// First name
			out.println("					<label for=\"firstname\">First Name: </label>");
			out.println("                	<input name=\"firstname\" id=\"firstname\" type=\"text\" required value=\"" + req.getParameter("firstName") + "\">"); 
			out.println("					<div for=\"firstname\" id=\"firstnameerror\" class=\"errormessage\">" + errorMessages.firstName + "</div>"); 
			out.println("					<br>"); 
	
			// Last name
			out.println("					<label for=\"lastname\">Last Name:</label>"); 
			out.println("					<input name=\"lastname\" id=\"lastname\" type=\"text\" required>"); 
			out.println("					<div for=\"lastname\" id=\"lastnameerror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>");
			
			// Phone number
			out.println("         	        <label for=\"phone\">Phone Number:</label>"); 
			out.println("              		<input name=\"phone\" id=\"phone\" type=\"text\" pattern=\"[1-9]{1}\\d{9}\" required>"); 
			out.println("                	<span class=\"helptext\">10 digit U.S. phone number; numbers only</span>"); 
			out.println("              		<div for=\"phone\" id=\"phoneerror\" class=\"errormessage\"></div>");
			
			
			out.println("                	<h2>Shipping</h2>"); 
			
			// Address 1
			out.println("                	<label for=\"address1\">Address 1:</label>"); 
			out.println("                   <input name=\"address1\" id=\"address1\" type=\"text\" required>"); 
			out.println("                   <div for=\"address1\" id=\"address1error\" class=\"errormessage\"></div>"); 
			out.println("                	<br>"); 
			
			// Address 2
			out.println("                	<label for=\"address2\">Address 2:</label>"); 
			out.println("                   <input name=\"address2\" id=\"address2\" type=\"text\">"); 
			out.println("                   <span class=\"helptext\">(Optional) Apartment number, floor, etc</span>"); 
			out.println("                   <div for=\"address2\" id=\"address2error\" class=\"errormessage\"></div>"); 
			out.println("                   <br>");
	
			// Zip
			out.println("                   <label for=\"zip\">Zip:</label>"); 
			out.println("                	<input name=\"zip\" id=\"zip\" onblur=\"getZipCode(this.value);getCityState(this.value)\" type=\"text\" pattern=\"[0-9]{5}\" required>"); 
			out.println("                   <span class=\"helptext\">Numbers only</span>"); 
			out.println("                   <div for=\"zip\" id=\"ziperror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>"); 
	
			// City
			out.println("               	<label for=\"city\">City:</label>"); 
			out.println("                   <input name=\"city\" id=\"city\" type=\"text\" required>"); 
			out.println("                   <div for=\"city\" id=\"cityerror\" class=\"errormessage\"></div>"); 
			out.println("         		    <br>"); 
	
			// State
			out.println("                	<label for=\"state\">State:</label>"); 
			out.println("                	<input name=\"state\" id=\"state\" type=\"text\" required>"); 
			out.println("                	<div for=\"state\" id=\"stateerror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>");
			
			// Displays shipping method
			shippingCost = 0;
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
			
			
			
			out.println("                	<h2>Billing</h2>"); 
		
			// Card type
			out.println("    				<label for=\"cardtype\">Card Type:</label>"); 
			out.println("                	<select name=\"cardtype\" id=\"cardtype\" required>"); 
			out.println("                   	<option value=\"\" selected=\"selected\"></option>"); 
			out.println("                   	<option value=\"Discover\">Discover</option>"); 
			out.println("                   	<option value=\"Mastercard\">Mastercard</option>"); 
			out.println("                   	<option value=\"Visa\">Visa</option>"); 
			out.println("         	        </select>"); 
			out.println("                	<div for=\"cardtype\" id=\"cardtypeerror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>"); 

			// Credit card number
			out.println("                	<label for=\"cardnumber\">Card Number:</label>"); 
			out.println("                	<input name=\"cardnumber\" id=\"cardnumber\" type=\"text\" pattern=\"[0-9]{16}\" required>"); 
			out.println("             		<span class=\"helptext\">Numbers only</span>"); 
			out.println("        	        <div for=\"cardnumber\" id=\"cardnumbererror\" class=\"errormessage\"></div>"); 
			out.println("          		    <br>"); 

			// Expiration month and year
			out.println("	                <label id=\"expiration\">Expiration:</label>"); 
			out.println("                	<select name=\"expmonth\" id=\"expmonth\" required>"); 
			out.println("                   	<option value=\"\" selected=\"selected\"></option>"); 
			out.println("                    	<option value=\"1\">January</option>"); 
			out.println("               	    <option value=\"2\">February</option>"); 
			out.println("                    	<option value=\"3\">March</option>"); 
			out.println("                  		<option value=\"4\">April</option>"); 
			out.println("                    	<option value=\"5\">May</option>"); 
			out.println("                    	<option value=\"6\">June</option>"); 
			out.println("            	        <option value=\"7\">July</option>"); 
			out.println("               	    <option value=\"8\">August</option>"); 
			out.println("                  		<option value=\"9\">September</option>"); 
			out.println("             		    <option value=\"10\">October</option>"); 
			out.println("               	    <option value=\"11\">November</option>"); 
			out.println("                       <option value=\"12\">December</option>"); 
			out.println("                	</select>"); 
			out.println("                	<select name=\"expyear\" id=\"expyear\" required>"); 
			out.println("                   	<option value=\"\" selected=\"selected\"></option>"); 
			out.println("                    	<option value=\"2020\">2020</option>"); 
			out.println("                    	<option value=\"2021\">2021</option>"); 
			out.println("	                    <option value=\"2022\">2022</option>"); 
			out.println("               	    <option value=\"2023\">2023</option>"); 
			out.println("           	        <option value=\"2024\">2024</option>"); 
			out.println("                    	<option value=\"2025\">2025</option>"); 
			out.println("           	        <option value=\"2026\">2026</option>"); 
			out.println("     	                <option value=\"2027\">2027</option>"); 
			out.println("          		        <option value=\"2028\">2028</option>"); 
			out.println("                    	<option value=\"2029\">2029</option>"); 
			out.println("                    	<option value=\"2030\">2030</option>"); 
			out.println("                   	<option value=\"2031\">2031</option>"); 
			out.println("                	</select>"); 
			out.println("                	<br>"); 
			out.println("                	<div for=\"expiration\" id=\"experror\" class=\"errormessage\"></div>"); 

			// CVV
			out.println("	                <label>CVV:</label>"); 
			out.println("                	<input name=\"cvv\" id=\"cvv\" pattern=\"[0-9]{3}\" required>"); 
			out.println("                	<br>"); 
			out.println("               	<div for=\"cvv\" id=\"cvverror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>");
		}
		else {
			
			// First name
			out.println("					<label for=\"firstname\">First Name: </label>");
			out.println("                	<input name=\"firstname\" id=\"firstname\" type=\"text\" required>"); 
			out.println("					<div for=\"firstname\" id=\"firstnameerror\" class=\"errormessage\">" + errorMessages.firstName + "</div>"); 
			out.println("					<br>"); 
	
			// Last name
			out.println("					<label for=\"lastname\">Last Name:</label>"); 
			out.println("					<input name=\"lastname\" id=\"lastname\" type=\"text\" required>"); 
			out.println("					<div for=\"lastname\" id=\"lastnameerror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>");
			
			// Phone number
			out.println("         	        <label for=\"phone\">Phone Number:</label>"); 
			out.println("              		<input name=\"phone\" id=\"phone\" type=\"text\" pattern=\"[1-9]{1}\\d{9}\" required>"); 
			out.println("                	<span class=\"helptext\">10 digit U.S. phone number; numbers only</span>"); 
			out.println("              		<div for=\"phone\" id=\"phoneerror\" class=\"errormessage\"></div>");
			
			
			out.println("                	<h2>Shipping</h2>"); 
			
			// Address 1
			out.println("                	<label for=\"address1\">Address 1:</label>"); 
			out.println("                   <input name=\"address1\" id=\"address1\" type=\"text\" required>"); 
			out.println("                   <div for=\"address1\" id=\"address1error\" class=\"errormessage\"></div>"); 
			out.println("                	<br>"); 
			
			// Address 2
			out.println("                	<label for=\"address2\">Address 2:</label>"); 
			out.println("                   <input name=\"address2\" id=\"address2\" type=\"text\">"); 
			out.println("                   <span class=\"helptext\">(Optional) Apartment number, floor, etc</span>"); 
			out.println("                   <div for=\"address2\" id=\"address2error\" class=\"errormessage\"></div>"); 
			out.println("                   <br>");
	
			// Zip
			out.println("                   <label for=\"zip\">Zip:</label>"); 
			out.println("                	<input name=\"zip\" id=\"zip\" onblur=\"getZipCode(this.value);getCityState(this.value)\" type=\"text\" pattern=\"[0-9]{5}\" required>"); 
			out.println("                   <span class=\"helptext\">Numbers only</span>"); 
			out.println("                   <div for=\"zip\" id=\"ziperror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>"); 
	
			// City
			out.println("               	<label for=\"city\">City:</label>"); 
			out.println("                   <input name=\"city\" id=\"city\" type=\"text\" required>"); 
			out.println("                   <div for=\"city\" id=\"cityerror\" class=\"errormessage\"></div>"); 
			out.println("         		    <br>"); 
	
			// State
			out.println("                	<label for=\"state\">State:</label>"); 
			out.println("                	<input name=\"state\" id=\"state\" type=\"text\" required>"); 
			out.println("                	<div for=\"state\" id=\"stateerror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>");
			
			// Displays shipping method
			shippingCost = 0;
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
			
			
			
			out.println("                	<h2>Billing</h2>"); 
		
			// Card type
			out.println("    				<label for=\"cardtype\">Card Type:</label>"); 
			out.println("                	<select name=\"cardtype\" id=\"cardtype\" required>"); 
			out.println("                   	<option value=\"\" selected=\"selected\"></option>"); 
			out.println("                   	<option value=\"Discover\">Discover</option>"); 
			out.println("                   	<option value=\"Mastercard\">Mastercard</option>"); 
			out.println("                   	<option value=\"Visa\">Visa</option>"); 
			out.println("         	        </select>"); 
			out.println("                	<div for=\"cardtype\" id=\"cardtypeerror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>"); 

			// Credit card number
			out.println("                	<label for=\"cardnumber\">Card Number:</label>"); 
			out.println("                	<input name=\"cardnumber\" id=\"cardnumber\" type=\"text\" pattern=\"[0-9]{16}\" required>"); 
			out.println("             		<span class=\"helptext\">Numbers only</span>"); 
			out.println("        	        <div for=\"cardnumber\" id=\"cardnumbererror\" class=\"errormessage\"></div>"); 
			out.println("          		    <br>"); 

			// Expiration month and year
			out.println("	                <label id=\"expiration\">Expiration:</label>"); 
			out.println("                	<select name=\"expmonth\" id=\"expmonth\" required>"); 
			out.println("                   	<option value=\"\" selected=\"selected\"></option>"); 
			out.println("                    	<option value=\"1\">January</option>"); 
			out.println("               	    <option value=\"2\">February</option>"); 
			out.println("                    	<option value=\"3\">March</option>"); 
			out.println("                  		<option value=\"4\">April</option>"); 
			out.println("                    	<option value=\"5\">May</option>"); 
			out.println("                    	<option value=\"6\">June</option>"); 
			out.println("            	        <option value=\"7\">July</option>"); 
			out.println("               	    <option value=\"8\">August</option>"); 
			out.println("                  		<option value=\"9\">September</option>"); 
			out.println("             		    <option value=\"10\">October</option>"); 
			out.println("               	    <option value=\"11\">November</option>"); 
			out.println("                       <option value=\"12\">December</option>"); 
			out.println("                	</select>"); 
			out.println("                	<select name=\"expyear\" id=\"expyear\" required>"); 
			out.println("                   	<option value=\"\" selected=\"selected\"></option>"); 
			out.println("                    	<option value=\"2020\">2020</option>"); 
			out.println("                    	<option value=\"2021\">2021</option>"); 
			out.println("	                    <option value=\"2022\">2022</option>"); 
			out.println("               	    <option value=\"2023\">2023</option>"); 
			out.println("           	        <option value=\"2024\">2024</option>"); 
			out.println("                    	<option value=\"2025\">2025</option>"); 
			out.println("           	        <option value=\"2026\">2026</option>"); 
			out.println("     	                <option value=\"2027\">2027</option>"); 
			out.println("          		        <option value=\"2028\">2028</option>"); 
			out.println("                    	<option value=\"2029\">2029</option>"); 
			out.println("                    	<option value=\"2030\">2030</option>"); 
			out.println("                   	<option value=\"2031\">2031</option>"); 
			out.println("                	</select>"); 
			out.println("                	<br>"); 
			out.println("                	<div for=\"expiration\" id=\"experror\" class=\"errormessage\"></div>"); 

			// CVV
			out.println("	                <label>CVV:</label>"); 
			out.println("                	<input name=\"cvv\" id=\"cvv\" pattern=\"[0-9]{3}\" required>"); 
			out.println("                	<br>"); 
			out.println("               	<div for=\"cvv\" id=\"cvverror\" class=\"errormessage\"></div>"); 
			out.println("                	<br>");
		}
		
			
		
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
