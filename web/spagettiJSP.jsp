<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" %>
<%
    int intNum = 0;
    String strNum = "0";
    strNum = request.getParameter("n");
    if(strNum != null && !strNum.equals(""))
        intNum = Integer.parseInt(strNum);

%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%if(intNum%2 !=0){%>
    홀수입니다.
    <%}
    else
    {%>
    짝수입니다.
    <%}%>
</body>
</html>
