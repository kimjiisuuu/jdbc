package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//select 부터는 지금 만든 유틸리티 클래스로 Connection 만들어주기
public class OracleUtilty {  // Connection 생성하여 제공해주고, 리소스 해제 메소드 작성
	
	public static Connection getConnection() {
	 Connection conn=null;
     
     String url="jdbc:oracle:thin:@localhost:1521:xe";
     //1. 아래 4개의 필수 연결 정보를 설정합니다.
     //접속하고자 하는 서버의 주소(포탈접속 https://www.naver.com 과 비슷한 개념)
     String driver="oracle.jdbc.driver.OracleDriver";
     //oracle.jdbc.driver는 ojdbc.jar에 포함된 패키지 이름
     //OracleDriver 는 오라클드라이버 클래스 이름
     String user="iclass";
     String password="0419";
     
     try {
     //   Class.forName(driver);
        conn=DriverManager.getConnection(url,user,password);
  
     } catch (Exception e) {// ClassNotFoundException, SQLException 처리가 필요
         System.out.println("데이터베이스 연결 및 사용에 문제가 생겼습니다. "+e.getMessage());
     }
      return conn;      //생성된 Connection 객체 리턴
      

  }
public static void close (Connection conn) {
	try {
		if(conn !=null) {
			conn.close();   //conn이 null 객체라면 close에서 오류 -> 조건식 추가
			System.out.println("연결 종료");
		} else {
			System.out.println("Connection 이 null 입니다");
		}
		
   }catch(SQLException e) {
		System.out.println("데이터베이스 연결 종료 오류 : "+ e.getMessage());
	} 
}
}
