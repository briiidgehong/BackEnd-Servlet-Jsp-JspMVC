package mangozzelli;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculateServlet3")
public class calculatorServlet3 extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        // 사용자가 입력한 값을 받고
        String number = request.getParameter("number");
        String operator = request.getParameter("operator");
        String dot = request.getParameter("dot");


        //기존 쿠키를 가지고와서 새로운 입력에 맞게 가공
        String outputStr = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("output")) {
                    outputStr = c.getValue();
                    break;
                }
            }
        }

        if(operator != null && operator.equals("=")){
            // 정식적으로 구현 안하고, 스크립트 엔진을 쓰겠다.
            // 덧셈 뺄셈 식 자체를 계산해주는 스크립트 라이브러리
            ScriptEngine engine = new ScriptEngineManager().
                    getEngineByName("nashorn");
            try {
                outputStr = String.valueOf(engine.eval(outputStr));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }else if(operator.equals("C")){
            outputStr = "";

        }else { // number / operator / dot 중 하나의 값만 들어온다.
                // 들어온 값을 output 값에 문자열 누적한다.
            outputStr += (number == null)?"":number;
            outputStr += (operator == null)?"":operator;
            outputStr += (dot == null)?"":dot;
        }

        // 쿠키에 저장을 하고
        Cookie outputCookie = new Cookie("output", outputStr);

        if(operator.equals("C")) // c인 경우 cookie 를 아예 지운다.
            outputCookie.setMaxAge(0);

        response.addCookie(outputCookie);
        // servlet page 로 redirection 한다.
        // 경로가 같으므로 / 안붙여줘도 된다.
        response.sendRedirect("servletPage");
    }
}
