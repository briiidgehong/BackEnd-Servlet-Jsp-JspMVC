package mangozzelli;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// ServletPage 는 get 요청만 받아들이고
// calculatorServlet은 post 요청만 받아들이므로
// 다음과 같은 하나의 서블릿에 각각 나누어서 merge 할 수 있다.

@WebServlet("/mergegetpost")
public class MergeGetPost extends HttpServlet {

    /* service 의 오버라이드 원형 : super.service(req,resp) -> 요청에 따라 자동으로 doGet / doPost 호출
    // 오버라이드 안했으므로, 해당 코드대로 흘러가서 doGet / doPost가 자동으로 호출된다고 보면된다.
    // 아하... 그럼 지금까지 우리가 service 함수를 오버라이드 해서 그 안에 원하는 것을 넣고 했던게
    // client request -> servlet 생성 안되있으면 -> init() -> service() 순으로 가기때문에 서비스 함수에다 코드 작성했구나

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }
    */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String output = "0";
        if(cookies != null){
            for (Cookie c : cookies) {
                if (c.getName().equals("output")) {
                    output = c.getValue();
                    break;
                }
            }
        }


        PrintWriter writer = response.getWriter();

        //write는 문자열 출력 전용 메소드
        writer.write("<!DOCTYPE html>");
        writer.write("<!DOCTYPE html>");
        writer.write("<html langen\">");
        writer.write("<head>");
        writer.write("<meta charsetUTF-8\">");
        writer.write("<title>myCalculator</title>");
        writer.write("<style>");
        writer.write("input{");
        writer.write("width:50px;");
        writer.write("height:50px;");
        writer.write("}");
        writer.write(".output{");
        writer.write("height: 50;");
        writer.write("font-size: 24;");
        writer.write("font-weight: bold;");
        writer.write("text-align: right;");
        writer.write("background-color: darkgray;");
        writer.write("padding: 0px 5px;");

        writer.write("}");
        writer.write("</style>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<div>");

        //calcutateServlet3 으로 보내는 form action
        writer.write("<form action=\"mergegetpost\" method=\"post\">");
        writer.write("<table>");
        writer.write("<tr>");
        writer.printf("<td class=\"output\" colspan=\"4\">%s</td>", output);
        writer.write("</tr>");
        writer.write("<tr>");
        writer.write("<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"operator\" value=\"BS\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"operator\" value=\"/\"/></td>");
        writer.write("</tr>");
        writer.write("<tr>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"7\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"8\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"9\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"operator\" value=\"*\"/></td>");
        writer.write("</tr>");
        writer.write("<tr>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"4\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"5\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"6\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"operator\" value=\"-\"/></td>");
        writer.write("</tr>");
        writer.write("<tr>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"1\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"2\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"3\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"operator\" value=\"+\"/></td>");
        writer.write("</tr>");
        writer.write("<tr>");
        writer.write("<td><input type=\"submit\" name=\"number\"\" value=\"0\"/></td>");
        writer.write("<td><input type=\"submit\" name=\"dot\" value=\".\"/></td>");
        writer.write("<td colspan=\"2\"><input type=\"submit\" name=\"operator\" value=\"=\"/></td>");
        writer.write("</tr>");
        writer.write("</table>");
        writer.write("</form>");
        writer.write("</div>");

        writer.write("</body>");
        writer.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        response.sendRedirect("mergegetpost");
    }


}
