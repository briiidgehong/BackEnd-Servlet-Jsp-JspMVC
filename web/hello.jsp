<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" %>
<%
    String strCnt = request.getParameter("cnt");
    int cnt = 100;
    if(strCnt!=null && !strCnt.equals(""))
        cnt = Integer.parseInt(strCnt);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        for(int i=0;i<cnt;i++){
            out.write("안녕 servlet");
            out.write("<br>");
        }
    %>

    <%for(int i=0;i<cnt;i++){%>
            안녕2 servlet <br>
    <%}%>

</body>
</html>
