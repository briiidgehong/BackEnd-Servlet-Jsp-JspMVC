package mangozzelli.service;

import mangozzelli.entity.Notice;

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

        String sql = " SELECT * FROM ( " +
                " SELECT ROWNUM NUM, N.* " +
                " FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) N " +
                " ) " +
                " WHERE NUM BETWEEN 6 AND 10 ";

        return null;
    }


    public int getNoticeCount(){
        return getNoticeCount("title", "");
    }

    public int getNoticeCount(String field, String query){

        String sql = " SELECT * FROM ( " +
                " SELECT ROWNUM NUM, N.* " +
                " FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) N " +
                " ) " +
                " WHERE NUM BETWEEN 6 AND 10 ";


        return 0;
    }

    //Connect NoticeDetailController
    public Notice getNotice(int id){
        String sql = " SELECT * FROM NOTICE WHERE ID=? ";
        return null;
    }

    public Notice getNextNotice(int id){
        String sql = " SELECT * FROM NOTICE " +
                " WHERE ID = ( " +
                " SELECT ID FROM NOTICE " +
                " WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID=3) " +
                " AND ROWNUM = 1 " +
                ")";
        return null;
    }

    public Notice getPrevNotice(int id){
        String sql = " SELECT ID FROM (SELECT * FROM NOTICE OREDER BY REGDATE DESC) " +
                " WHERE REGDATE < (SELECT REGDATE FROM WHERE ID=3) " +
                " AND ROWNUM = 1 " ;
        return null;
    }
}
