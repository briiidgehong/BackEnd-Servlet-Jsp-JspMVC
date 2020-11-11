<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" %>
<!-- MVC Model 1 -->
<!-- 스파게티처럼 꼬이지 않게 제어단과 뷰단을 나누자 -->

<!-- 입력과 제어 Controller-->
<%
    int intNum = 0;
    String strNum = "0";
    strNum = request.getParameter("n");
    if(strNum != null && !strNum.equals(""))
        intNum = Integer.parseInt(strNum);

    String model ="";
    if(intNum%2 !=0){
        model = "홀수";
    }else {
        model = "짝수";
    }
%>
<!-- 출력 데이터 Model-->

<!-- 출력 담당 View -->
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%=model%> 입니다.
</body>
</html>
