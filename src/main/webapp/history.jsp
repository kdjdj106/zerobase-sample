<%@ page import="db.History" %>
<%@ page import="db.HistoryTest" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: kdjdj
  Date: 2022-07-30
  Time: 오전 4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>

    table {
        width: 100%;
    }

    th, td {
        border: solid 1px #000;
    }

</style>
<body>
<h1> 위치 히스토리 목록 </h1>
<div style="display: flex">

    <a href="http://localhost:8080/zerobase_study_sample_war_exploded/home.jsp"> 홈 </a>
    <div>&nbsp; | &nbsp;</div>
    <a href="http://localhost:8080/zerobase_study_sample_war_exploded/history.jsp"> 위치 히스토리 목록 </a>
    <div>&nbsp; | &nbsp;</div>
    <a href="http://localhost:8080/zerobase_study_sample_war_exploded/list.jsp"> Open API 와이파이 정보 가져오기</a>
</div>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <% HistoryTest historyTest = new HistoryTest();
        List<History> list= historyTest.list();
        for (History history : list){ %>
        <tr>
        <td><%=history.getID()%></td>
        <td><%=history.getX()%></td>
        <td><%= history.getY()%></td>
        <td><%= history.getUsedDateTime()%></td>
        <td>삭제버튼 예정</td>
        </tr>

        <%
            }
        %>
    </tr>
    </tbody>
</table>


</body>
</html>
