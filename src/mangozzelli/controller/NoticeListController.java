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
        int page = 1;

        String field_ = request.getParameter("f");
        String query_ = request.getParameter("q");
        String page_ = request.getParameter("p");

        if(field_ != null && !field_.equals(""))
            field = field_;
        if(query_ != null && !query_.equals(""))
            query = query_;
        if(page_ != null && !page_.equals(""))
            page = Integer.parseInt(page_);

        NoticeService service = new NoticeService();
        List<Notice> list = service.getNoticeList(field, query, page);



        // paging lastNum 구하기 위해 데이터의 count 가져오기
        int count = service.getNoticeCount(field, query);

        request.setAttribute("list",list);
        request.setAttribute("count",count);


        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp")
                .forward(request,response);
    }
}
