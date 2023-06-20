package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentSelectAllMenu {

   public static void main(String[] args) {
      Connection conn = OracleUtilty.getConnection();
      System.out.println("모든 학생을 조회합니다.");
      selectAllStudent(conn);
      
      OracleUtilty.close(conn);
      
   }private static void selectAllStudent(Connection conn) {
         String sql = "select * from TBL_STUDENT";
         String stuno;
      
         
          
            
         try(PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery(); 
            System.out.println("rs 객체의 구현  클래스는 " + rs.getClass().getName());
            while(rs.next()) 
            System.out.println("학번 :" + rs.getString(1) + " 이름 : " + rs.getString(2) +" 나이 : " + rs.getInt(3) + " 주소 : " + rs.getString(4));
            
            //System.out.println("다음 조회 결과 행이 또 있을까요? " + rs.next());
           
         } catch (SQLException e) {
            System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용  " + e.getMessage());
           
         }
         
   }
}

