package mangozzelli;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/helloAnnotation")
public class ServletAnnotation extends HttpServlet {
    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        //우리는 리스폰스 텍스트의 인코딩 형식을 UTF-8 로 사용할꺼야
        response.setCharacterEncoding("UTF-8");
        //브라우저에게 명시 : 우리가 보내는 텍스트는 html 이고, 인코딩 형식은 UTF-8이야
        response.setContentType("text/html; charset=UTF-8");

        System.out.println("hello Servlet !!! using console");

        PrintWriter writer = response.getWriter();

        for(int i=0;i<10;i++)
        {
            writer.println(i + " 안녕 servlet<br>" );
        }


    }
}
