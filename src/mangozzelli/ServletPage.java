package mangozzelli;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servletPage")
public class ServletPage extends HttpServlet {

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {
        // 이 페이지에서 html 을 만들어서 화면에 뿌리고
        // 해당 html은 form action 으로 calculateServlet3 에 post 형식으로 데이터를 날린다.
        // + - * / 이 눌릴 경우에는 calculateServlet3에서 값을 계산해서 쿠키에 저장시킨다.
        // 이 페이지에서는 쿠키에서 값을 꺼내와서 output을 출력한다.

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
        writer.write("<form action=\"calculateServlet3\" method=\"post\">");
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
}
