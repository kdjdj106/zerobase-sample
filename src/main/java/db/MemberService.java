package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberService {

    public List<Member> list(){

        List<Member> memberList = new ArrayList<>();
        String url=  "jdbc:mariadb://211.250.172.54:3306/db2";
        String dbUserId= "testuser4";
        String dbPassword= "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection =null;
        PreparedStatement preparedStatement= null;
        ResultSet rs = null;
        String memberTypeValue = "email";
        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);



            String sql = " select m2.member_type , m2.user_id , m2.password , m2.name "+
                    " from `member` m2 "+
                    " where member_type = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, memberTypeValue);

            rs = preparedStatement.executeQuery();

            while (rs.next()){
                String member_type =rs.getString("m2.member_type");
                String user_id =rs.getString("m2.user_id");
                String password =rs.getString("m2.password");
                String name =rs.getString("m2.name");

                Member member = new Member();
                member.setMemberType(memberTypeValue);
                member.setUserId(user_id);
                member.setPassword(password);
                member.setName(name);

                memberList.add(member);

                System.out.println(member_type+", "+user_id+", "+password+", "+name);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return memberList;
    }

    public Member detail(String memberType, String userId){

        Member member = null;
        String url=  "jdbc:mariadb://211.250.172.54:3306/db2";
        String dbUserId= "testuser4";
        String dbPassword= "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection =null;
        PreparedStatement preparedStatement= null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);



            String sql = " select m2.member_type , m2.user_id , m2.password , m2.name " +
                    ", md.mobile_no, md.marketing_yn, md.register_date " +
                    " from `member` m2 " +
                    " left join member_detail md on md.member_type = m2.member_type and md.user_id = m2.user_id "+
                    " where m2.user_id = ? and m2.member_type = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, memberType);

            rs = preparedStatement.executeQuery();

            if (rs.next()){


                member = new Member();

                member.setMemberType(rs.getString("m2.member_type"));
                member.setUserId(rs.getString("m2.user_id"));
                member.setPassword(rs.getString("m2.password"));
                member.setName(rs.getString("m2.name"));
                member.setMobileNo(rs.getString("mobile_no"));
                member.setMarketingYn(rs.getBoolean("marketing_yn"));
                member.setRegisterDate(rs.getDate("register_date"));
            }




            CallableStatement callableStatement = null;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return member;
    }

    public void register(Member member){
        String url=  "jdbc:mariadb://211.250.172.54:3306/db2";
        String dbUserId= "testuser4";
        String dbPassword= "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection =null;
        PreparedStatement preparedStatement= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);



            String sql = " insert into `member` (member_type, user_id, password, name) "+
            " values (?, ?, ?, ?); ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberType());
            preparedStatement.setString(2, member.getUserId());
            preparedStatement.setString(3, member.getPassword());
            preparedStatement.setString(4, member.getName());

            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("저장 성공");
            }else{
                System.out.println("저장 실패");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public void dbUpdate(){
        String url=  "jdbc:mariadb://211.250.172.54:3306/db2";
        String dbUserId= "testuser4";
        String dbPassword= "zerobase";


        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection =null;
        PreparedStatement preparedStatement= null;
        ResultSet rs = null;

        String memberTypeValue = "email";
        String userIdvalue = "zerobase2@naver.com";
        String passwordValue = "9999";

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);



            String sql = " update `member` "+
            " set "+
                    " password = ? "+
            " where member_type = ? and user_id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, passwordValue);
            preparedStatement.setString(2, memberTypeValue);
            preparedStatement.setString(3, userIdvalue);


            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("저장 성공");
            }else{
                System.out.println("저장 실패");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public void withdraw(Member member){
        String url=  "jdbc:mariadb://211.250.172.54:3306/db2";
        String dbUserId= "testuser4";
        String dbPassword= "zerobase";




        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection =null;
        PreparedStatement preparedStatement= null;
        ResultSet rs = null;


        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);



            String sql = " delete from `member` "+
            " where member_type = ? and user_id = ? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getMemberType());
            preparedStatement.setString(2, member.getUserId());



            int affected = preparedStatement.executeUpdate();

            if (affected > 0){
                System.out.println("삭제 성공");
            }else{
                System.out.println("삭제 실패");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }



}
