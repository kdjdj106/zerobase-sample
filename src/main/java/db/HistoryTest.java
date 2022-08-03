package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryTest {

    //db에 제일큰 ID값(PK)을 리턴하는 함수
    public int dbCount() {
        int count = 0;
        History history = new History();
        HistoryService historyService = new HistoryService();

        boolean result = historyService.recodeLocation(history);

        boolean result2 = historyService.deleteLocation(history);

        List<History> locationList = historyService.getList();

        String url = "jdbc:mariadb://211.250.172.54:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " select ID, XLocation, YLocation, UseDateTime " +
                    " from history ";


            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String XLocation = rs.getString("XLocation");
                String YLocation = rs.getString("YLocation");
                int ID = rs.getInt("ID");
                count = Math.max(ID, count);

                System.out.println(XLocation + " " + " || " + YLocation + " || " + ID);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return count;
    }

    // db에 있는 데이터를 리스트 형태로 반환하는 함수
    public List<History> list() {

        List<History> historyList = new ArrayList<>();


        HistoryService historyService = new HistoryService();

        String url = "jdbc:mariadb://211.250.172.54:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " select ID, XLocation, YLocation, UseDateTime " +
                    " from history ";


            preparedStatement = connection.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                double XLocation = rs.getDouble("XLocation");
                double YLocation = rs.getDouble("YLocation");
                String UseDateTime = rs.getString("UseDateTime");
                int ID = rs.getInt("ID");

               // System.out.println(XLocation + " " + " || " + YLocation + " || " + ID);

                History history = new History();
                history.setX(XLocation);
                history.setY(YLocation);
                history.setUsedDateTime(UseDateTime);
                history.setID(ID);

                historyList.add(history);

            }


//
//            CallableStatement callableStatement = null;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return historyList;
    }

    //db에 데이터를 삽입하는 함수 단 dbCount()을 사용하여 받아온 값 +1을 ID(PK)로 사용하여 PK중복 방지
    public void dbInsert(double x, double y) {

        History history = new History();
        history.setX(x);
        history.setY(y);
        //db에 아무데이터도 없을경우 ID를 자동으로 1로 설정
        if (dbCount() == 0) {
            history.setID(1);
        } else {

            history.setID(dbCount() + 1);
        }
        HistoryService historyService = new HistoryService();

        boolean result = historyService.recodeLocation(history);

        boolean result2 = historyService.deleteLocation(history);

        List<History> locationList = historyService.getList();

        String url = "jdbc:mariadb://211.250.172.54:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        double x1 = x;
        double y1 = y;


        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " insert into history (XLocation, YLocation, UseDateTime, ID) " +
                    " value (?, ?, date_format(now(), '%Y-%m-%d %H:%i:%s'), ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, history.getX());
            preparedStatement.setDouble(2, history.getY());
            preparedStatement.setInt(3, history.getID());


            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //db에 데이터를 ID값을 기반으로 지우는 함수
    public void dbDelete(String str) {

        History history = new History();
        HistoryService historyService = new HistoryService();

        boolean result = historyService.recodeLocation(history);

        boolean result2 = historyService.deleteLocation(history);

        List<History> locationList = historyService.getList();

        String url = "jdbc:mariadb://211.250.172.54:3306/testdb3";
        String dbUserId = "testuser3";
        String dbPassword = "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        String testDatetime = "2022-08-02 01:04:12";
        String datetime = str;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);


            String sql = " DELETE from history " +
                    " where ID = ?; ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(str));


            int affected = preparedStatement.executeUpdate();

            if (affected > 0) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("삭제 실패");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void _main(String[] args) {
        HistoryTest historyTest = new HistoryTest();

//        historyTest.dbInsert(1.1, 2.2 );
//        historyTest.dbSelect();
//        historyTest.dbDelete("2022-08-02 01:09:45");
        System.out.println(historyTest.getClass().getName());
        System.out.println(historyTest.getClass().getSimpleName());
    }
}
