package mangozzelli.controller;

import mangozzelli.entity.Notice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Notice> list = new ArrayList<>();

        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String sql = "SELECT * FROM NOTICE";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "C##MANGOZZELLI", "0000");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("ID");
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

                list.add(notice);
            }

            request.setAttribute("list",list);

            request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
                    .forward(request,response);

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ODBC CLASS LOAD 실패");
            e.printStackTrace();
        }
    }
}
