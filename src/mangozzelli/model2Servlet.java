package mangozzelli;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/model2Servlet")
public class model2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Model 2를 구현하기 위해 Controller 부분을 jsp 에서 Servlet 으로 분기
        // controller : servlet -> model -> view : jsp

        //입력과 제어 Controller
        int intNum = 0;
        String strNum = "0";
        strNum = request.getParameter("n");
        if(strNum != null && !strNum.equals(""))
            intNum = Integer.parseInt(strNum);

        String model ="";
        if(intNum%2 !=0){
            model = "홀수";
        }else {
            model = "짝수";
        }

        // 출력 데이터 Model
        // 데이터 전달을 위해 forwarding(운송) 사용
        // -> dispatcher(지상에서 근무하는 항공기 운항 관리자) 사용
        // 서블릿간(servlet - servlet(=jsp 또한 포함)) 데이터 공유는 requst 객체를 사용한다.
        request.setAttribute("model",model);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("spagettiJSP.jsp");

        // list, map 의 전달
        String[] list = {"kkiri", "desic"};
        request.setAttribute("list", list);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("title", "good");
        request.setAttribute("map", map);


        // 여기 서블릿에서 쓰고있는 request 와 response 객체를 그대로 jsp 페이지로 보낸다.
        // 여기 서블릿의 requst, response 객체와 jsp page의 request response 가 같아져서 데이터를 공유하는 효과
        requestDispatcher.forward(request,response);

        // 이렇게 이어가는 것이 아니라 새로 요청 할때는 redirect 쓰면 된다.



    }
}
