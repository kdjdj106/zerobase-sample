
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

    int a = getApi.getCount();
    System.out.println(a);
    ArrayList<Data> resultList = getApi.getData(a);

%>

<h1><%= resultList.size() + "개의 리스트를 저장하였습니다."%></h1> 가운데 정렬
<a href="http://localhost:8080/zerobase_study_sample_war_exploded/home.jsp">홈으로 돌아가기 </a>

<table>
    <thead>

    </thead>
    <tbody>

    </tbody>
</table>


<br/>
<script language="JavaScript">
let test = "<%= resultList %>" ;
console.log(test)
</script>
</body>
</html>