package mangozzelli.controller;

import mangozzelli.entity.Notice;
import mangozzelli.service.NoticeService;

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

        NoticeService service = new NoticeService();
        Notice notice = service.getNotice(id);
        request.setAttribute("notice", notice);

        // detail.jsp 페이지로 흐름(데이터)을 흘려줘야 한다.
        // servlet 에서 servlet 으로 전이하는 방법은 두가지
        // 1. redirect  2. forward
        request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp")
                .forward(request,response);

    }
}
