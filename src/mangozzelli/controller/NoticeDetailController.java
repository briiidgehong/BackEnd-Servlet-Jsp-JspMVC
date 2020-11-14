package mangozzelli.controller;

import mangozzelli.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Date;


@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CONTROLLER
        int id = Integer.parseInt(request.getParameter("id"));
        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String sql = "SELECT * FROM NOTICE WHERE ID=?";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(url, "C##MANGOZZELLI", "0000");
            PreparedStatement st = con.prepareStatement(sql);

            // 위의 ? 와 연계
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();

            //resultSet이 가지고 있는 공간에 record 하나가 적재된다.
            rs.next();


            //MODEL VARIABLE
            // 이 데이터들을 "공지사항" 이라는 엔티티 로 묶을수 있다.
            // 엔티티 = 개체 = 개념화된 데이터 = 구조적인 데이터
            String title = rs.getString("TITLE");
            String content = rs.getString("CONTENT");
            Date regdate = rs.getDate("REGDATE");
            String writer_id = rs.getString("WRITER_ID");
            String hit = rs.getString("HIT");
            String files = rs.getString("FILES");

            Notice notice = new Notice(
                    id,
                    title,
                    content,
                    regdate,
                    writer_id,
                    hit,
                    files
            );


            rs.close();
            st.close();
            con.close();


            /*
            request.setAttribute("title", title);
            request.setAttribute("content", content);
            request.setAttribute("regdate", regdate);
            request.setAttribute("writer_id", writer_id);
            request.setAttribute("hit", hit);
            request.setAttribute("files", files);
            */

            request.setAttribute("notice", notice);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // detail.jsp 페이지로 흐름(데이터)을 흘려줘야 한다.
        // servlet 에서 servlet 으로 전이하는 방법은 두가지
        // 1. redirect  2. forward
        request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp")
                .forward(request,response);

    }
}
