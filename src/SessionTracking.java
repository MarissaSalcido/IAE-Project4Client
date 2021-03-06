import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SessionTracking")
public class SessionTracking extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SessionTracking() {super();}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {   	
    	response.setContentType("text/html"); 
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        
        Vector<Item> history = (Vector<Item>) session.getAttribute("history");
        
		out.println("<h2 class='history'>Recently Viewed Products</h2>");
        
		// If history does not exist or is empty, display message for empty cart 
		if ((history == null) || history.isEmpty()) {
			out.println("<h3 class='history'>None</h3></body></html>");
		}
		else {		
			out.println("          <table class='history'>\r\n" + 
			"                    <tr>");
			
			// Removes item at beginning of vector if history is bigger than 5
			while (history.size() > 5) {
				history.remove(0);
			}
			
			// Displays the item from most recent viewed to least recent viewed
			for (int i = history.size() - 1; i >= 0; --i) {
				out.println("       <td><img src=\"" + history.elementAt(i).imageSrc + "\" alt=\"" + history.elementAt(i).item + "\" width=\"80\" height=\"100\"></td>\r\n"); 
			}
			out.println("			</tr>");
			out.println("</table></body></html>");
		}
		
		out.close();		
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		processRequest(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
