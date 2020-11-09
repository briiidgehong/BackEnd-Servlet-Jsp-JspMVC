package mangozzelli;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FirstServlet extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException{

        System.out.println("hello Servlet !!! using console");

        PrintWriter writer = response.getWriter();
        writer.println("hello servlet !!! using printwriter");

        //ServletOutputStream outputStream = response.getOutputStream();
        //outputStream.println("hello Servlet !!! using servletoutputstream");

    }
}

