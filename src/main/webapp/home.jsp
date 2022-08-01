
<%@ page import="static sun.misc.Version.print" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Data" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.GetApi" %>
<%@ page import="java.util.PriorityQueue" %>
<%@ page import="db.CompareDistance" %>
<%@ page import="db.HistoryTest" %>
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

    CompareDistance cd = new CompareDistance();

    ArrayList<Data> resultList = (ArrayList<Data>) session.getAttribute("list");
    PriorityQueue<Data> pq = new PriorityQueue<>();
    boolean flag = false;

%>


<h1>와이파이 정보 구하기</h1>
<div style="display: flex">

<a href="http://localhost:8080/zerobase_study_sample_war_exploded/home.jsp"> 홈 </a>
<div>&nbsp; | &nbsp;</div>
<a href="http://localhost:8080/zerobase_study_sample_war_exploded/history.jsp"> 위치 히스토리 목록 </a>
<div>&nbsp; | &nbsp;</div>
<a href="http://localhost:8080/zerobase_study_sample_war_exploded/list.jsp"> Open API 와이파이 정보 가져오기</a>
</div>
<div style="display: flex">
    <form class="checkLocation" action="home.jsp" method="post">
        <%--    <p>LAT :</p>--%>
        <input type="text"  placeholder="LAT" id="latitude" name="latitude" value="" required>
        <%--    <p>LNT :</p>--%>
        <input type="text"  placeholder="LNT" id="longitude" name="longitude" value="" required>
        <button class="btn" onclick="getMyLocation()">내 위치 가져오기</button>

        <button  type="submit">주변 와이파이 가져오기</button>
    </form>
</div>
<%
    String x = request.getParameter("latitude");
    String y= request.getParameter("longitude");
    HistoryTest historyTest = new HistoryTest();
    if (x == null || x.equals("")){
        System.out.println("x, y 값이 없습니다.");
    }

    else {
        if (resultList.size() ==0 || resultList == null){
            System.out.println("뛰어넘기");
            %>
        <script type="text/javascript">
            alert("먼저 와이파이 정보를 받아야 합니다.");
        </script>

<%
        }

        else {
            pq = cd.compareDistance(resultList, x, y);
            historyTest.dbInsert(Double.parseDouble(x), Double.parseDouble(y));
        }
    }
    if (pq == null || pq.size() ==0 || resultList.size() ==0 || resultList == null){
        System.out.println("위치정보를 입력한 후에 조회해 주세요");
        flag =false;
    }else {
        flag = true;
    }

    System.out.println(x);
    System.out.println(y);

%>

<table>
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>

    </tr>
    </thead>
    <tbody>

    <tr><%
    if (flag)
    {
for(int i = 0; i < 20; i++) {
        Data db = pq.poll();
        %>
<tr>
        <div style="text-align: center;">
    <td>
        <%= db.getDistance()%>
    </td>
    <td>
        <%= db.getX_SWIFI_MGR_NO()%>
    </td>
    <td>
        <%= db.getX_SWIFI_WRDOFC()%>
    </td>
    <td>
        <%= db.getX_SWIFI_MAIN_NM()%>
    </td>
    <td>
        <%= db.getX_SWIFI_ADRES1()%>
    </td>
    <td>
        <%= db.getX_SWIFI_ADRES2()%>
    </td>
    <td>
        <%= db.getX_SWIFI_INSTL_FLOOR()%>
    </td>
    <td>
        <%= db.getX_SWIFI_INSTL_TY()%>
    </td>
    <td>
        <%= db.getX_SWIFI_INSTL_MBY()%>
    </td>
    <td>
        <%= db.getX_SWIFI_SVC_SE()%>
    </td>
    <td>
        <%= db.getX_SWIFI_CMCWR()%>
    </td>
    <td>
        <%= db.getX_SWIFI_CNSTC_YEAR()%>
    </td>
    <td>
        <%= db.getX_SWIFI_INOUT_DOOR()%>
    </td>
    <td>
        <%= db.getX_SWIFI_REMARS3()%>
    </td>
    <td>
        <%= db.getLAT()%>
    </td>
    <td>
        <%= db.getLNT()%>
    </td>
    <td>
        <%= db.getWORK_DTTM()%>


    </td>
        </div>
</tr>


<%
    }
        }else {%>
       <td colspan="17">
           <div style="text-align: center;">
           <%="위치정보를 입력한 후에 조회해 주세요"%>
           </div>
       </td>

    <%}
    %>
    </tr>


    </tbody>
</table>


<br/>
<script>
    function getMyLocation(){
        navigator.geolocation.getCurrentPosition(function(pos) {
            console.log(pos);
            var latitude = pos.coords.latitude;
            var longitude = pos.coords.longitude;
            document.getElementById("latitude").value= latitude
            document.getElementById("longitude").value= longitude

        });
    }

</script>


</body>

</html>