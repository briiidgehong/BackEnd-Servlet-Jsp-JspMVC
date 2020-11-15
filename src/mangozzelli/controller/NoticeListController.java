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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //검색 요청 받기
        // jsp page -> /notice/list?f=title?q=blablabla

        String field = "title";
        String query = "";

        String field_ = request.getParameter("f");
        String query_ = request.getParameter("q");

        if(field_ != null)
            field = field_;
        if(query_ != null)
            query = query_;

        NoticeService service = new NoticeService();
        List<Notice> list = service.getNoticeList(field, query,1);



        request.setAttribute("list",list);

        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
                .forward(request,response);
    }
}
