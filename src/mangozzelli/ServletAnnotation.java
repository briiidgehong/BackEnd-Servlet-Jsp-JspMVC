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

        //GET 요청과 Query String
        /*
        기본적으로 사용자의 요청은 문서를 달라는 식의 요청 (GET 방식의 경우)
        http://localhost/hello
        그 요청에 다음과 같이 쿼리 스트링을 붙여서 요청할 수 있다.
        http://localhost/hello?repeatCnt=3

        http://localhost/hello?repeatCnt=3     -> "3"
        http://localhost/hello?repeatCnt=      -> ""
        http://localhost/hello?                -> null
        http://localhost/hello                 -> null

         */
        int repeatCnt = 1;
        String strCnt = request.getParameter("repeatCnt");

        if(strCnt!=null && !strCnt.equals(""))
        {
            repeatCnt = Integer.parseInt(strCnt);
        }

        for(int i=0;i<repeatCnt;i++)
        {
            writer.println(i + "query string servlet<br>" );
        }


    }
}
