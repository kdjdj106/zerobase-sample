
<%@ page import="static sun.misc.Version.print" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Data" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.GetApi" %>
<%@ page import="java.util.PriorityQueue" %>
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
//    GetApi getApi = new GetApi();

//    int a = getApi.getCount();
//    System.out.println(a);
//    ArrayList<Data> resultList = getApi.getData(a);
    ArrayList<Data> resultList = new ArrayList<>();

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
    <p>LAT :</p>
    <input type="text" id="latitude" value="">
    <p>LNT :</p>
    <input type="text" id="longitude" value="">
    <button onclick="getMyLocation()">내 위치 가져오기</button>
    <button>주변 와이파이 가져오기</button>
</div>


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
    for(int i = 0; i < resultList.size(); i++) {

            %>
    <tr>
        <td>
            <%=  i+" 번째"%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_MGR_NO()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_WRDOFC()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_MAIN_NM()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_ADRES1()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_ADRES2()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_INSTL_FLOOR()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_INSTL_TY()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_INSTL_MBY()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_SVC_SE()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_CMCWR()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_CNSTC_YEAR()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_INOUT_DOOR()%>
        </td>
        <td>
            <%= resultList.get(i).getX_SWIFI_REMARS3()%>
        </td>
        <td>
            <%= resultList.get(i).getLAT()%>
        </td>
        <td>
            <%= resultList.get(i).getLNT()%>
        </td>
        <td>
            <%= resultList.get(i).getWORK_DTTM()%>


        </td>

    </tr>

    <%
        }
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
            alert("현재 위치는 : " + latitude + ", "+ longitude);
        });
    }

</script>
</body>
</html>