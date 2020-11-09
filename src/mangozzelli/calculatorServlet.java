package mangozzelli;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculateServlet")
public class calculatorServlet extends HttpServlet {

    public int plusOperator(String[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += Integer.parseInt(numbers[i]);
        }
        return sum;
    }

    private int multipleOperator(String[] numbers) {
        int sum = 1;
        for (int i = 0; i < numbers.length; i++) {
            sum *= Integer.parseInt(numbers[i]);
        }
        return sum;
    }

    public void service(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        PrintWriter writer = response.getWriter();
        String[] numbers = request.getParameterValues("number");
        String operator = request.getParameter("operator");

        int sum = 0;
        if (operator.equals("+"))
            sum = plusOperator(numbers);
        else if (operator.equals("*"))
            sum = multipleOperator(numbers);

        writer.println(sum);
    }
}
