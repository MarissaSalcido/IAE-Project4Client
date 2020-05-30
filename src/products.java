import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products")
public class products extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public products() {super();}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	try {
    		response.setContentType("text/html;charset=UTF-8");
    		/* REPLACING DB CALL WITH RESTFUL CALL */
		    //dispatch to jsp
    		RequestDispatcher rd1 = request.getRequestDispatcher("SessionTracking");
    		rd1.include(request, response);
		
    	} catch(Exception e) {
    		System.out.println(e);
    	}
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
	}

}
