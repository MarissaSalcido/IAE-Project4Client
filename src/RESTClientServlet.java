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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author 
 */
@WebServlet("/restcli")
public class RESTClientServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());


        String jsonResponse =
                target.path("tomerest").path("books").
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class); // use the get method and return the response as a string

        System.out.println(jsonResponse);

        ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

        List<Book> bookList = objectMapper.readValue(jsonResponse, new TypeReference<List<Book>>(){});

        PrintWriter out = response.getWriter();

        out.print("<html><head>" +
                "<title>REST Client</title>" +
                "</head>" +
                "<body>" +
                "<h2>REST response for GET call - /tomerest/books</h2>" +
                "<ul>"
        );

        for(Book book : bookList) {
            out.print("<li>");
            out.print(book.getSummary() + " - " + book.getDescription());
            out.print("</li>");
        }
        out.print("</ul>");

        out.print(
                "<h2>REST response for GET call - /tomerest/books/1</h2>" +
                "<ul>"
        );

        String jsonResponse2 =
                target.path("tomerest").path("books").path(request.getParameter("1")).
                        request(). //send a request
                        accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                        get(String.class);

        Book book = objectMapper.readValue(jsonResponse2, Book.class);

        out.print("<li>");
        out.print(book.getSummary() + " - " + book.getDescription());
        out.print("</li>");

        out.print("</ul></body></html>");


    }

    private static URI getBaseURI() {

        //Change the URL here to make the client point to your service.
        return UriBuilder.fromUri("http://localhost:8081/TomeRestService").build();
    }


}