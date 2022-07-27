package db;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import okhttp3.*;

import java.util.ArrayList;


public class GetApi {
    static String start = String.valueOf(1);
    static String end = String.valueOf(1);
    static StringBuilder sb = new StringBuilder();

    public static int getCount() {
        int count = 0;
        try {
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

            count = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                    .get("list_total_count").getAsInt();

            String str = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                    .get("row").getAsJsonArray().get(0).getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();

            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int result =count;
        return result;
    }

    public static ArrayList<Data> getData(int count) {
        ArrayList<Data> list = new ArrayList<>();
        Data data = new Data();
        System.out.println(1);
        while (Integer.parseInt(end) < count) {
            try {

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

                count = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                        .get("list_total_count").getAsInt();
                //관리번호 저장
                String X_SWIFI_MGR_NO = jsonObject.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject()
                        .get("row").getAsJsonArray().get(0).getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
                data.setX_SWIFI_MGR_NO(X_SWIFI_MGR_NO);
                System.out.println(X_SWIFI_MGR_NO);


            } catch (Exception e) {
                e.printStackTrace();
            }
            list.add(data);
            start = end;
            end = Integer.toString(Integer.parseInt(end) + 1000);
            if (Integer.parseInt(end) >= count) {
                end = Integer.toString(count);
            }
            System.out.println(1);
        }


        return list;
    }

    public static void main(String[] args) throws IOException {

        int a = getCount();
        System.out.println(getData(a));

    }
}
