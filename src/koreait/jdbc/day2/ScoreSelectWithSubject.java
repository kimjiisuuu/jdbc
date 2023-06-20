package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ScoreSelectWithSubject {

   public static void main(String[] args) {
      Connection conn = OracleUtilty.getConnection();
      Scanner sc = new Scanner(System.in);
		
      selectManyScore(conn);
      
      OracleUtilty.close(conn);
      
   }private static void selectManyScore(Connection conn) {
        
         Scanner sc = new Scanner(System.in);
 		String sql = "select * from TBL_SCORE where subject = ?";
 		System.out.println("조회할 과목명 입력 >>>");
 		String subject = sc.nextLine();
         
         
            
         try(PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, subject);
        	 ResultSet rs = ps.executeQuery(); 
            System.out.println("rs 객체의 구현  클래스는 " + rs.getClass().getName());
            while(rs.next()) 
            System.out.println("학번 :" + rs.getString(1) + " 과목명 : " + rs.getString(2) +" 점수 : " + rs.getInt(3) + " 이름 : " + rs.getString(4)+" 연도 : "+rs.getString(5));
           
         } catch (SQLException e) {
            System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용  " + e.getMessage());
           
         }
         
   }
}