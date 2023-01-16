# Java 개발정리 - Servlet -> JSP -> JSP MVC -> 완료

Java 개발의 흐름 : Servlet -> JSP -> JSP MVC -> SPRING 

각각의 구현과 함께 바뀌어가는 과정을 한 cycle 정리할 예정

Servlet-Jsp-JspMVC 의 개발환경은 다음과 같습니다. 
* IDE : IntelliJ IDEA
* Git Tools : GitHub Desktop
* OS : Window 10
* Java Enterprise Edition 
* Java 11 JDK
* Tomcat 9

## 주요 commit 내역☑️ (아래 링크를 클릭하면 해당 commit으로 이동합니다.)
### 1. jsp with jasper 의 등장배경:   
[calcExample : CalcSevletPage(servlet include html code) + CalcServlet](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/fbdd2370ebd2588b3611ed44d82ae37d63b15004)  
[jasper automatly convert jsp to servlet](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/50a283c3c70f8c5c41078c144cf090a9920eebfd)  
  
### 2. service/doGet/doPost 함수간의 관계 
[doGet doPost 특성을 이용한 sevletPage(receive Get request)와 calculate3(receive Post request) 의 merge](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/2155e707fbe631ddb729f3f4725fb0e5aff9ae79)  
  
### 3. MVC MODEL  
[spagetti To MVC Model 1](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/75d45d550c75c0e4e300e5f68b6f5ed8164553b9)  
[MVC Model 2](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/8ed3ffbd69bba9789f0044ab42dc5b98ae71374f)  
[noticeDetail.jsp to MVC Model 2 (jsp(MVC) -> jsp(V)-M-servlet(C))](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/9ebdc9c75c93384a32583bbd7b73c6588d6e13bc)  
[noticeList.jsp to MVC MODEL 2 (jsp -> jsp + servlet(controller))](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/18c05ea41cc68c87049d172f6e9e2ed08716732c)  

### 4. JDBC/EL/JSTL  
[jdbc connect & run test source](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/40af8aba876bddb3013cacb93e0692f0c6ae4108)  
[EL: Expression Language for JSP](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/75eaf994a6adcbc8634f64a4f999d9d211173c5c)  
[use jstl(jsp standard tag library)](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/a84bf0ccf85142e37cf2e31fa3379ffdf4463fae)  
  
### 5. Seperate Controller to Controller + Service  
[noticeListController 에서 getNoticeList()을 service로 분리](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/75786760510d1fc809e08969f50ad178efa6b4e5)  
[noticeDetailController 에서 getNotice(id)를 service로 분리](https://github.com/mangozzelli/BackEnd-Servlet-Jsp-JspMVC/commit/df5ec2fb4a64e85c9a7250c3f303ef24315f32d8)  
  
##### NEXT - SPRING1-DI-AOP 🌱
