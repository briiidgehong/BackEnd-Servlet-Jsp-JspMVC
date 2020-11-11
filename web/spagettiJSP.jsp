<%@ page contentType="text/html;charset=UTF-8" language="java"
    pageEncoding="UTF-8" %>

<!-- 출력 담당 View -->
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%=request.getAttribute("model")%> 입니다.
    <!-- EL : expression Language -->
    ${model} 입니다.

    ${list[0]}
    ${list[1]}

    ${map.id}
    ${map.title}

    ${map.id}
    ${title}

    <!--
    // 저장소의 우선순위는 1.page 객체 -> 2.request 객체 -> 3.sessopm 객체 -> 4.application 객체
    // 변수값이 중복될 경우, 해당 우선순위에 따라 출력하게 된다.
    // jsp 에서 어떤 저장소에서 해당 값을 받을 것인지 선택 가능함 : scope 설정
    -->
    ${requestScope.map.id} <br/>
    ${requestScope.map.title} <br/>

    <!-- param, header 등의 내장객체도 EL 로 사용 가능함 -->
    ${param.n} <br/>
    ${header.host}

    <!--
    EL 의 연산자
    [] .
    ()
    not ! empty
    * / div % mod
    + -
    < > <= => it gt le ge
    == != eq ne
    && and
    || or
    ?:
    -->


</body>
</html>
