package mangozzelli;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 사용자가 전달한 값을 읽어들일때 UTF-8 로 읽어들이겠다.
        // 이게 귀찮으면, tomcat server.xml 에서 URIEndoding="UTF-8" 설정
        request.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        String strTitle = request.getParameter("title");
        String strContent = request.getParameter("content");

        writer.println(strTitle);
        writer.println(strContent);


    }
}
