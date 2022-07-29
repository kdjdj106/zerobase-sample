package db;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.stream.JsonReader;
import okhttp3.*;

import java.util.ArrayList;


public class GetApi {
    static String start = String.valueOf(1);
    static String end = String.valueOf(1000);
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Data> resultList = new ArrayList<>();



    public  ArrayList<Data> getData(int count) {
        ArrayList<Data> list = new ArrayList<>();
        while (Integer.parseInt(end) <= count) {
            try {
                sb = new StringBuilder("");
                StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
                urlBuilder.append("/" + URLEncoder.encode("50527861446b646a3130386d565a5670", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
                urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
                urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
                urlBuilder.append("/" + URLEncoder.encode(start, "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
                urlBuilder.append("/" + URLEncoder.encode(end, "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
                // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

                // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
                urlBuilder.append("/" + URLEncoder.encode("20220301", "UTF-8")); /* 서비스별 추가 요청인자들*/

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/

                BufferedReader rd;

                // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
                }

                String line = "";
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }

                rd.close();
                conn.disconnect();

                System.out.println(sb.toString());
                JsonObject jsonObject = (JsonObject) JsonParser.parseString(sb.toString());
                Gson gson = new Gson();


//                int a1= Integer.parseInt(end);
//                if (Integer.parseInt(end) == 1001){
//                    System.out.println(1001);
//                }
                //관리번호 저장
                // start가 1001 이고 end가 1001 일시에 0이되서 포문을 돌리지 못한다.
                // 맨처음 start = 1 end = 1000
                // 두번째 start = 1000 +1 (end + 1) end = 1000 + 1000 (end + 1000)
                for (int i = 0; i < Integer.parseInt(end)-Integer.parseInt(start)+1; i++) {
                    Data data = new Data();

                    //1
                    String X_SWIFI_MGR_NO = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
                    data.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);

                    //2
                    String X_SWIFI_WRDOFC = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString();
                    data.setX_SWIFI_WRDOFC(X_SWIFI_WRDOFC);

                    //3
                    String X_SWIFI_MAIN_NM = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString();
                    data.setX_SWIFI_MAIN_NM(X_SWIFI_MAIN_NM);

                    //4
                    String X_SWIFI_ADRES1 = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_ADRES1").getAsString();
                    data.setX_SWIFI_ADRES1(X_SWIFI_ADRES1);

                    //5
                    String X_SWIFI_ADRES2 = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_ADRES2").getAsString();
                    data.setX_SWIFI_ADRES2(X_SWIFI_ADRES2);

                    //6
                    String X_SWIFI_INSTL_FLOOR = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString();
                    data.setX_SWIFI_INSTL_FLOOR(X_SWIFI_INSTL_FLOOR);

                    //7
                    String X_SWIFI_INSTL_TY = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString();
                    data.setX_SWIFI_INSTL_TY(X_SWIFI_INSTL_TY);

                    //8
                    String X_SWIFI_INSTL_MBY = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString();
                    data.setX_SWIFI_INSTL_MBY(X_SWIFI_INSTL_MBY);

                    //9
                    String X_SWIFI_SVC_SE = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString();
                    data.setX_SWIFI_SVC_SE(X_SWIFI_SVC_SE);

                    //10
                    String X_SWIFI_CMCWR = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_CMCWR").getAsString();
                    data.setX_SWIFI_CMCWR(X_SWIFI_CMCWR);

                    //11
                    String X_SWIFI_CNSTC_YEAR = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString();
                    data.setX_SWIFI_CNSTC_YEAR(X_SWIFI_CNSTC_YEAR);

                    //12
                    String X_SWIFI_INOUT_DOOR = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString();
                    data.setX_SWIFI_INOUT_DOOR(X_SWIFI_INOUT_DOOR);

                    //13
                    String X_SWIFI_REMARS3 = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("X_SWIFI_REMARS3").getAsString();
                    data.setX_SWIFI_REMARS3(X_SWIFI_REMARS3);

                    //14
                    String LAT = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("LAT").getAsString();
                    data.setLAT(LAT);

                    //15
                    String LNT = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("LNT").getAsString();
                    data.setLNT(LNT);

                    //16
                    String WORK_DTTM = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                            .get("row").getAsJsonArray().get(i).getAsJsonObject().get("WORK_DTTM").getAsString();
                    data.setWORK_DTTM(WORK_DTTM);



                    list.add(data);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

//            int a2= Integer.parseInt(end);
            if (Integer.parseInt(end) == count)
                break;
            start = Integer.toString(Integer.parseInt(end)+1);
            end = Integer.toString(Integer.parseInt(end) + 1000);
            if (Integer.parseInt(end) >= count) {
                end = Integer.toString(count);
            }

            System.out.println(end);

        }
        return list;
    }
    public  int getCount() {
        int count = 0;
        String getCountStart = Integer.toString(1);
        String getCountEnd = Integer.toString(1);
        try {
            StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088"); /*URL*/
            urlBuilder.append("/" + URLEncoder.encode("50527861446b646a3130386d565a5670", "UTF-8")); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
            urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
            urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
            urlBuilder.append("/" + URLEncoder.encode(getCountStart, "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
            urlBuilder.append("/" + URLEncoder.encode(getCountEnd, "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
            // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

            // 서비스별 추가 요청 인자이며 자세한 내용은 각 서비스별 '요청인자'부분에 자세히 나와 있습니다.
            urlBuilder.append("/" + URLEncoder.encode("20220301", "UTF-8")); /* 서비스별 추가 요청인자들*/

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/

            BufferedReader rd;

            // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }

            String line="";
            while ((line = rd.readLine()) != null) {
                sb.append(line);

            }
            Gson gson = new Gson();

            JsonObject jsonObject1 = (JsonObject) JsonParser.parseString(sb.toString());
            rd.close();
            conn.disconnect();


            count = jsonObject1.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                    .get("list_total_count").getAsInt();
            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }
        int result =count;
        return result;
    }

    public static void main(String[] args) {
        GetApi getApi = new GetApi();
        System.out.println(getApi.getCount());
        resultList = getApi.getData(getApi.getCount());
        System.out.println(resultList.get(0).getX_SWIFI_MGR_NO());
    }
}
