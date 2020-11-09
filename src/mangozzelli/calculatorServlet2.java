package mangozzelli;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

// application / session / cookie 객체
/*
    1. application(Server Side) 저장소 = servlet context (servlet 간의 context(문맥)을 이어가게 해주는 저장소)
        서블릿이 하나 실행되고 나면 거기서 처리된 값은 response 되면서 소멸된다.
        이 값을 가지고 다른 서블릿이 실행될때 가지고 다니고 싶다면 저장소를 사용하면 된다.
        서블릿끼리 자원을 공유하고 싶을 때 해당 application 저장소를 이용한다.

    2. session 저장소(Server Side) = 사용자마다 다른 저장소
       웹 서버가 session 을 구분하는 방식
       -> 접근하는 브라우저 프로세스별로 session id 가 WAS 에 의해서 발급되고
       -> 발급된 session id 별로 mapping 된 서버 저장소를 가지고, 접근할 수 있게 된다.
       -> 사용자가 많아지면 서버의 session 저장소도 같이 늘어나게 되는대, 주기적으로 리소스를 정리해주어야 한다.
       -> session 이 30분마다 끊어지는 것도 이러한 이유이다. (session timeout)

    3. cookie 저장소(Client Side = browser)
       클라이언트(=브라우저)에 저장된다.
 */
@WebServlet("/calculateServlet2")
public class calculatorServlet2 extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        PrintWriter writer = response.getWriter();
        String number = request.getParameter("number");
        String operator = request.getParameter("operator");
        int number2 = Integer.parseInt(number);

        //application
        ServletContext application = request.getServletContext();
/*
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
            application.setAttribute("number", number);
            application.setAttribute("operator", operator);
        }
*/

/*      //session
        HttpSession session = request.getSession();
        if (operator.equals("=")) {
            int number1 = Integer.valueOf((String)session.getAttribute("number"));
            String operator1 = (String)session.getAttribute("operator");

            if(operator1.equals("+")){
                writer.printf("result is %d", (number1 + number2));
            }
            else if(operator1.equals("*")){
                writer.println(number1 * number2);
            }

        } else {
            session.setAttribute("number", number);
            session.setAttribute("operator", operator);
        }
*/
        //cookie
        Cookie[] cookies = request.getCookies();
        if (operator.equals("=")) {
            int number1 = 0;
            String operator1 = "";

            for (Cookie c : cookies) {
                if (c.getName().equals("number")) {
                    number1 = Integer.parseInt(c.getValue());
                    break;
                }
            }

            for (Cookie c : cookies) {
                if (c.getName().equals("operator")) {
                    operator1 = c.getValue();
                    break;
                }

            }

            if (operator1.equals("+")) {
                writer.printf("result is %d", (number1 + number2));
            } else if (operator1.equals("*")) {
                writer.println(number1 * number2);
            }

        } else {
            Cookie cookieNumber = new Cookie("number", number);
            Cookie cookieOperator = new Cookie("operator", operator);
            response.addCookie(cookieNumber);
            response.addCookie(cookieOperator);
        }
    }
}
