package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ScoreSelectWithSubject2 {
   public static void main(String[] args) {
      Connection conn = OracleUtilty.getConnection();
      System.out.println("::::::::::::::과목명으로 점수조회:::::::::::::");
      ScoreSelectWithsubject(conn);
      
      OracleUtilty.close(conn);
   }

   private static void selectCount(Connection conn , String subject) {
String sql = "select count(*) from tbl_score where subject = ? ";   
// count 와 같은 함수 결과는 행 1개 , 컬럼 1개
try (PreparedStatement ps = conn.prepareStatement(sql);) {
   ps.setString(1, subject);
   ResultSet rs = ps.executeQuery();
   int count = 0;
   if(rs.next()) {                 //1.다른 조회문과 다르게 if문 안 써도 됨. rs.next()만 단독으로
      count = rs.getInt(1); 
      //참고 : 입력한 과목의 건(행) 수를 조회할 수 있다.
   } System.out.println("과목 " + subject + "," + count + "건이 조회되었습니다.");
   }
       catch (SQLException e) {
         System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용  " + e.getMessage());
      }}

   private static void ScoreSelectWithsubject(Connection conn) {
      Scanner sc = new Scanner(System.in);

      String sql = "select * from TBL_SCORE where subject = ?";
      //count 와 같은 함수 결과는 항상 행 1개, 컬럼 1개 
      System.out.println("조회할 과목명 입력");
      //2.조건절에 사용한는 컬럼이 기본키와 유니크 일 때는 0 또는 1개 행이 조회되고 -> rs.next() 를 if에 사용
      //                 기본키가 유니크 아닐 때는 0~n 개 행이 조회됩니다. -> rs.next() 를 while에 사용
      String subject = sc.nextLine();
      try (PreparedStatement ps = conn.prepareStatement(sql);) {
         ps.setString(1, subject);
         ResultSet rs = ps.executeQuery();
         while (rs.next()) {
            System.out.println(" 학번 " + rs.getString(1));
            System.out.println(" 과목 " + rs.getString(2));
            System.out.println(" 점수 " + rs.getInt(3));
            System.out.println(" 선생님 " + rs.getString(4));
            System.out.println(" 학기 " + rs.getString(5));
            System.out.println();
         }
         selectCount(conn, subject);
         //sql = "select count(*) from tbl_score where subject = ? ";
            // 참고 : 입력한 과목의 건(행) 수를 조회할 수 있습니다. 
      } catch (SQLException e) {
         System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용  " + e.getMessage());
      }
      sc.close();

   }

}