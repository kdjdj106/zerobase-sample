<%@ page import="db.History" %>
<%@ page import="db.HistoryTest" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
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
        border: 1px solid #444444;
        border-collapse: collapse;
    }

    th, td {
        border: 1px solid #444444;
        padding: 10px;
        text-align: center;

    }

    thead tr {
        background-color: #0d47a1;
        color: #ffffff;
    }

    tbody tr:nth-child(2n) {
        background-color: #bbdefb;
    }

    tbody tr:nth-child(2n+1) {
        background-color: #e3f2fd;
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


<table id="userList">
    <div style="text-align: center;">
        <thead>

        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        </thead>
    </div>
    <tbody>
    <form class="checkLocation" action="history.jsp" method="post">

        <%
            HistoryTest historyTest = new HistoryTest();
            String ID = null;
            String num = null;
            ID = request.getParameter("ID");
            num = request.getParameter("ID2");
            if (num == null || num.equals("")) {
                System.out.println("ID = " + ID);
                System.out.println("num =  " + num);
            } else {

                // 받아온 데이터(ID값(PK))가 null 이 아닐시 데이터 삭제
                historyTest.dbDelete(num);
            }
            List<History> list = historyTest.list();%>
        <input type="hidden" id="ID2" name="ID2" value=""/>

        <%
            for (History history : list) {
        %>

        <tr>

            <td style="text-align: center"><%=history.getID()%>
            </td>

            <td style="text-align: center"><%=history.getX()%>
            </td>
            <td style="text-align: center"><%= history.getY()%>
            </td>
            <td style="text-align: center"><%= history.getUsedDateTime()%>
            </td>
            <td style="text-align: center">

                <button class="btn btn-danger pull-right"
                        onclick="getUserName()" id="ID" name="ID" value="" type="submit">삭제
                </button>

            </td>

        </tr>

        <%
            }
        %>

    </form>
    </tbody>

</table>


<script>
    function getUserName() {

        let userList = document.getElementById('userList');
        console.log("클릭!");
        for (let i = 1; i < userList.rows.length; i++) {
            userList.rows[i].cells[4].onclick = function () {

                var userName = userList.rows[i].cells[0].innerText;
                document.getElementById("ID").value = userName;
                document.getElementById("ID2").value = userName;
                console.log(userName);
            }
        }
    }
</script>

</body>
</html>
