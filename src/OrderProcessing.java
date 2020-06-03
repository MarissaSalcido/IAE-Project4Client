/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
 
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference; 
import org.glassfish.jersey.client.ClientConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import models.Book;
import models.Order;
import models.OrderItem;


/**
 * Servlet implementation class OrderProcessing
 */
@WebServlet("/OrderProcessing")
public class OrderProcessing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderProcessing() {
        super();
    }


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {	
	
		ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI()).path("tomerest").path("orders");
        
        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library	
			
		HttpSession currentSession = req.getSession(true);
		Order order = new Order();
        
        order.setFirstName(req.getParameter("firstname"));
        order.setLastName(req.getParameter("lastname"));
        order.setPhoneNum(req.getParameter("phone"));
        order.setAddress1(req.getParameter("address1"));
        order.setAddress2(req.getParameter("address2"));
        order.setCity(req.getParameter("city"));
        order.setState(req.getParameter("state"));
        order.setZipcode(req.getParameter("zip"));
        order.setShippingMethod((String) currentSession.getAttribute("shippingMethod"));
        order.setCardType(req.getParameter("cardtype"));
        order.setCardNumber(req.getParameter("cardnumber"));
        order.setExpMonth(req.getParameter("expmonth"));
        order.setExpYear(req.getParameter("expyear"));
        order.setCvv(req.getParameter("cvv"));
                
		Double sessionSubtotal = (Double) currentSession.getAttribute("subtotal");
		String subtotal = String.valueOf(sessionSubtotal.doubleValue());
		order.setSubtotal(subtotal);
        
        order.setTax((String) currentSession.getAttribute("tax"));
        order.setShippingCost((String) currentSession.getAttribute("shippingCost"));
        order.setTotal((String) currentSession.getAttribute("total"));
        
        @SuppressWarnings("unchecked")
		Vector<Item> sessionCart = (Vector<Item>) currentSession.getAttribute("shoppingCart");
        List<OrderItem> shoppingCart = new ArrayList<OrderItem>();
        OrderItem orderItem;
        
        for (int i = 0; i < sessionCart.size(); ++i) {
        	orderItem = new OrderItem();
        	orderItem.setProductId(sessionCart.elementAt(i).id);
        	orderItem.setImageSrc(sessionCart.elementAt(i).imageSrc);
        	orderItem.setItemName(sessionCart.elementAt(i).item);
        	orderItem.setPrice(Double.toString(sessionCart.elementAt(i).price));
        	orderItem.setQuantity(Integer.toString(sessionCart.elementAt(i).quantity));
        	shoppingCart.add(orderItem);
        }
        
        order.setOrderItems(shoppingCart);
					       
        String jsonStr = objectMapper.writeValueAsString(order);
        System.out.println("jsonStr: " + jsonStr);
        Entity<String> jsonEntity = Entity.json(jsonStr);
        
        String jsonResponse = target.request(). //send a request
				        		accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                				method("POST", jsonEntity, String.class);        
        
        System.out.println("jsonResponse: " + jsonResponse);
                       
        req.setAttribute("jsonResponse", jsonResponse);
        
		RequestDispatcher rd = req.getRequestDispatcher("OrderConf");
		rd.forward(req, res);       
	}

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8080/TomeRestService").build();
    }


}
