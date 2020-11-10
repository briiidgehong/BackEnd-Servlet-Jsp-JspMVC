package mangozzelli;

import jdk.swing.interop.SwingInterOpUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculateServlet3")
public class calculatorServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method control using doGet function");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get method control using doPost function");
    }

    /* 서비스 함수의 오버라이드 원형 -> doGet / doPost 를 호출하는 super.service 코드를 가지고 있다.
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
    */
    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        // <form action="calculateServlet3" method="get or post">
        // 위와 같이 method 로 get 과 post 가 올때 각각 처리하는 방법에는 두가지가 존재
        // 1. 아래와 같이 service 함수 내에서 getMethod 로 구분하여 코드 작성
        if(request.getMethod().equals("GET"))
            System.out.println("get method control using service function");
        if(request.getMethod().equals("POST"))
            System.out.println("post method using service function");


        // 2. httpServlet 객체의 doGet , doPost 함수 오버라이딩 하여 사용
        // doGet, doPost -> 겟, 포스트 방식으로 데이터가 넘어오면 다음과 같은 코드를 실행해라.
        // 오버라이딩 한후, super.service(request, response);
        // get / post 에 따라 -> doGet / doPost 메소드 실행
        super.service(request, response);

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
        }else if(operator != null && operator.equals("C")){
            outputStr = "";

        }else { // number / operator / dot 중 하나의 값만 들어온다.
                // 들어온 값을 output 값에 문자열 누적한다.
            outputStr += (number == null)?"":number;
            outputStr += (operator == null)?"":operator;
            outputStr += (dot == null)?"":dot;
        }

        // 쿠키에 저장을 하고
        Cookie outputCookie = new Cookie("output", outputStr);

        if(operator != null && operator.equals("C")) // c인 경우 cookie 를 아예 지운다.
            outputCookie.setMaxAge(0);

        response.addCookie(outputCookie);
        // servlet page 로 redirection 한다.
        // 경로가 같으므로 / 안붙여줘도 된다.
        response.sendRedirect("servletPage");
    }
}
