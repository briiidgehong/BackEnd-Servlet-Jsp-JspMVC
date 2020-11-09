package mangozzelli;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// application / session / cookie 객체
/*
    1. application 저장소 = servlet context (servlet 간의 문맥을 이어가게 해주는 저장소)
        서블릿이 하나 실행되고 나면 거기서 처리된 값은 response 되면서 소멸된다.
        이 값을 가지고 다른 서블릿이 실행될때 가지고 다니고 싶다면 저장소를 사용하면 된다.
 */
@WebServlet("/calculateServlet2")
public class calculatorServlet2 extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        ServletContext servletContext = request.getServletContext();
        PrintWriter writer = response.getWriter();
        String number = request.getParameter("number");
        String operator = request.getParameter("operator");
        int number2 = Integer.parseInt(number);

        if (operator.equals("=")) {
            int number1 = Integer.valueOf((String)getServletContext().getAttribute("number"));
            String operator1 = (String)getServletContext().getAttribute("operator");

            if(operator1.equals("+")){
                writer.printf("result is %d", (number1 + number2));
            }
            else if(operator1.equals("*")){
                writer.println(number1 * number2);
            }

        } else {
            servletContext.setAttribute("number", number);
            servletContext.setAttribute("operator", operator);
        }
    }
}
