<%@ page import="db.MemberService" %>
<%@ page import="static sun.misc.Version.print" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Member" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        table {
            width: 100%;
        }

        th, td {
            border: solid 1px #000;
        }
    </style>
</head>
<body>
<%
    String memberType  = request.getParameter("memberType");
    String userId = request.getParameter("userId");
    MemberService memberService = new MemberService();
    Member member = memberService.detail(memberType, userId);

%>

<h1>회원상세정보</h1>
<table>
    <colgroup>
        <col style="width: 20%;"/>
        <col style="width: 80%;"/>
    </colgroup>
         <tbody>
         <tr>
             <th>회원타입입니다.</th>
                <td>
                    <%=member.getMemberType()%>
                </td>
         </tr>
         <tr>
             <th>아이디</th>
             <td>
                 <%=member.getUserId()%>
             </td>
         </tr>
         <tr>
             <th>비밀번호</th>
             <td>
                 <%=member.getPassword()%>
             </td>
         </tr>
         <tr>
             <th>이름</th>
             <td>
                 <%=member.getName()%>
             </td>
         </tr>
         <tr>
             <th>전화번호</th>
             <td>
                 <%=member.getMobileNo()%>
             </td>
         </tr>
         <tr>
             <th>마케팅 수신여부</th>
             <td>
                 <%=member.isMarketingYn()%>
             </td>
         </tr>
         <tr>
             <th>가입일</th>
             <td>
                 <%=member.getRegisterDate()%>
             </td>
         </tr>

         </tbody>
</table>
<div>
    <a href="list.jsp">목록으로 이동</a>
</div>

<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>