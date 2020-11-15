package mangozzelli.service;

import mangozzelli.entity.Notice;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeService {

    //Connect NoticeListController
    public List<Notice> getNoticeList(){
        return getNoticeList("title","",1);
    }

    public List<Notice> getNoticeList(int page){
        return getNoticeList("title","",page);
    }
    // 동일한 함수 3개중에 제일 인자가 많은거 하나만 구현하면 위처럼 return 값을 이용해서 다같이 사용 가능하다.
    public List<Notice> getNoticeList(String field, String query, int page){
    // field : TITLE, WRITE_ID    query : where title like '%A%'
        List<Notice> list = new ArrayList<>();

        String sql = " SELECT * FROM ( " +
                " SELECT ROWNUM NUM, N.* " +
                " FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N " +
                " ) " +
                " WHERE NUM BETWEEN ? AND ? ";

        // 1, 11, 21, 31 -> 1+(page-1)*10
        // 10, 20, 30, 40 -> page*10

        String url = "jdbc:oracle:thin:@localhost:1521/xe";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "C##MANGOZZELLI", "0000");
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,"%"+query+"%");
            st.setInt(2,1+(page-1)*10);
            st.setInt(3,page*10);
            ResultSet rs = st.executeQuery();

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


            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return list;
    }


    public int getNoticeCount(){
        return getNoticeCount("title", "");
    }

    public int getNoticeCount(String field, String query){

        int count = 0;

        String sql = " SELECT COUNT(ID) COUNT FROM ( " +
                " SELECT ROWNUM NUM, N.* " +
                " FROM (SELECT * FROM NOTICE WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N " +
                " ) " ;


        String url = "jdbc:oracle:thin:@localhost:1521/xe";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "C##MANGOZZELLI", "0000");
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,"%"+query+"%");
            ResultSet rs = st.executeQuery();
            count = rs.getInt("count");

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }



        return count;
    }

    //Connect NoticeDetailController
    public Notice getNotice(int id){
        Notice notice = null;
        String sql = " SELECT * FROM NOTICE WHERE ID=? ";

        String url = "jdbc:oracle:thin:@localhost:1521/xe";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "C##MANGOZZELLI", "0000");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int id2 = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                Date regdate = rs.getDate("REGDATE");
                String writer_id = rs.getString("WRITER_ID");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");

                notice = new Notice(
                        id2,
                        title,
                        content,
                        regdate,
                        writer_id,
                        hit,
                        files
                );
            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return notice;
    }

    public Notice getNextNotice(int id){
        Notice notice = null;
        String sql = " SELECT * FROM NOTICE " +
                " WHERE ID = ( " +
                " SELECT ID FROM NOTICE " +
                " WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID=?) " +
                " AND ROWNUM = 1 " +
                ")";

        String url = "jdbc:oracle:thin:@localhost:1521/xe";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "C##MANGOZZELLI", "0000");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int id2 = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                Date regdate = rs.getDate("REGDATE");
                String writer_id = rs.getString("WRITER_ID");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");

                notice = new Notice(
                        id2,
                        title,
                        content,
                        regdate,
                        writer_id,
                        hit,
                        files
                );
            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return notice;
    }

    public Notice getPrevNotice(int id){
        Notice notice = null;
        String sql = " SELECT ID FROM (SELECT * FROM NOTICE OREDER BY REGDATE DESC) " +
                " WHERE REGDATE < (SELECT REGDATE FROM WHERE ID=?) " +
                " AND ROWNUM = 1 " ;

        String url = "jdbc:oracle:thin:@localhost:1521/xe";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(url, "C##MANGOZZELLI", "0000");
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1,id);

            ResultSet rs = st.executeQuery();

            if(rs.next()){
                int id2 = rs.getInt("ID");
                String title = rs.getString("TITLE");
                String content = rs.getString("CONTENT");
                Date regdate = rs.getDate("REGDATE");
                String writer_id = rs.getString("WRITER_ID");
                String hit = rs.getString("HIT");
                String files = rs.getString("FILES");

                notice = new Notice(
                        id2,
                        title,
                        content,
                        regdate,
                        writer_id,
                        hit,
                        files
                );
            }

            rs.close();
            st.close();
            con.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return notice;
    }
}
