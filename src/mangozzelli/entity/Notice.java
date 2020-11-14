package mangozzelli.entity;

import java.util.Date;

public class Notice {

    //private -> getter / setter
    private int id;
    private String title;
    private String content;
    private Date regdate;
    private String writer_id;
    private String hit;
    private String files;

    //기본 생성자
    public Notice(){

    }

    // 풀 생성자
    public Notice(int id, String title, String content, Date regdate, String writer_id, String hit, String files) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.writer_id = writer_id;
        this.hit = hit;
        this.files = files;
    }

    // 객체를 출력해서 확인하기 쉬움
    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", regdate=" + regdate +
                ", writer_id='" + writer_id + '\'' +
                ", hit='" + hit + '\'' +
                ", files='" + files + '\'' +
                '}';
    }

    //getter setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public String getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(String writer_id) {
        this.writer_id = writer_id;
    }

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
