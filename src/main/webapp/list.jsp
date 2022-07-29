
<%@ page import="static sun.misc.Version.print" %>
<%@ page import="java.util.List" %>
<%@ page import="db.Data" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="db.GetApi" %>
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

    GetApi getApi = new GetApi();
    String str2="";
   int a = getApi.getCount();
    System.out.println(a);
    ArrayList<Data> resultList = getApi.getData(a);

%>

<h1>와이파이 정보 구하기</h1>
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
//    String str1 = resultList.get(0).getX_SWIFI_MGR_NO();
        {
            %>
    <tr>
        <td>

        </td>
        <td>

        </td>
        <td>


        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
        <td>

        </td>
    </tr>

    <%
        }
    %>
    </tr>


    </tbody>
</table>


<br/>
</body>
</html>