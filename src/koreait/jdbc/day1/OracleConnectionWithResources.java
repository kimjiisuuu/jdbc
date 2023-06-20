package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
public class OracleConnectionWithResources {
   public static void main(String[] args) {
         
   //      Connection conn=null;
         
         String url="jdbc:oracle:thin:@localhost:1521:xe";
         String driver="oracle.jdbc.driver.OracleDriver";
         String user="iclass";
         String password="0419";
         
         //try with resources 형식: try에 자원객체 선언하기
         try (//자원 해제 close가 필요한 객체생성과 변수 선언하기
         Connection conn=DriverManager.getConnection(url,user,password);
          // 2개 이상의 구문을 작성할 수 있다.
         ){
            
            // 현재 버전에서는 DriverManager가 실행시키므로 생략이 가능하다
         //   Class.forName(driver);                  
            System.out.println("연결 상태="+conn);
            if(conn!=null)
               System.out.println("오라클 데이터베이스 연결 성공 !!");
            else
               System.out.println("오라클 데이터베이스 연결 실패 !!");
            
         } catch (Exception e) {// ClassNotFoundException, SQLException 처리가 필요
             System.out.println("ClassNotFoundException = 드라이버 경로가 잘못되었습니다.");
             System.out.println("SQLException = url 또는 user 또는 password가 잘못되었습니다." );
             System.out.println("오류 메세지="+e.getMessage());
             e.printStackTrace(); //Exception 발생의 모든 원인을 cascade 형식으로 출력
         
               
            }
         }
               // conn.close()를 명시적으로 실행할 필요가 없습니다,
      }
   