package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenuComplete {

	public static void main(String[] args) {
		Connection conn = OracleUtilty.getConnection();
		System.out.println("::::::::::::::::: 학생을 학번으로 조회하는 메뉴 :::::::::::::::");
		selectOneStudent(conn);

		OracleUtilty.close(conn);
	}                                                                                 

	private static void selectOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql = "select * from TBL_STUDENT where stuno =?";
		System.out.println("조회할 학번 입력 >>>");
		String stuno = sc.nextLine();
		try(
		    PreparedStatement ps = conn.prepareCall(sql);
		){
			ps.setString(1, stuno);
			//sql 실행하고 select는 조회 데이터를 결과로 받아 자바 변수에 저장해야 한다.
			//                             ->ResultSet 인터페이스 객체로 저장하기.
			ResultSet rs = ps.executeQuery(); //select 실행하기
			System.out.println("rs 객체의 구현 클래스는 "+ rs.getClass().getName());
			// ㄴ oracle.jdbc.driver.OracleResultSetImpl 클래스 객체로 만들어진다.
			
			//rs.next()데이터를 가져올 커서(위치)를 다음 행으로 이동한다.
			//조회 결과 유무를 알려면 '제일 먼저 실행헤야 할 메소드-조회결과 첫 번쨰 행으로 이동' 이다.
			
			//조회된 rs에서 특정 컬럼값을 가져오기 할 때, 컬럼의 데이터 타입을 확인하고
			//getXXXX 메소드 정하기. getXXXX 메소드의 인자는 컬럼의 인덱스 또는 컬럼 이름이다.
			if(rs.next()) { //주의 : 테이블 컬럼의 구조를 알아야 인덱스를 정할 수 있다.
				//getXXXX메소드는 rs가 가리키는 현재 행의 컬럼값을 가져오기 한다.
			System.out.println("학번 : "+ rs.getString(1));
			System.out.println("학번 : "+ rs.getString("stuno"));      //인덱스 대신 컬럼명으로 함.
			System.out.println("이름: "+ rs.getString(2));
			System.out.println("이름: "+ rs.getString("name"));
			System.out.println("나이 : "+ rs.getInt(3));
			System.out.println("나이 : "+ rs.getInt("age"));
			System.out.println("주소 : "+ rs.getString(4));
			System.out.println("주소 : "+ rs.getString("address"));
			
			}else {
				System.out.println("<<조회 결과가 없습니다>>");
			}
			
		}catch(SQLException e) {
			System.out.println("데이터 조회에 문제가 생겼습니다. 상세내용 : "+e.getMessage());
			//결과 집합읋 모두 소모했음 -> 조회 결과가 없는데 rs.getXXXX 메소드 실행할 때의 예외 메세지.
		}
		sc.close();
	}

}
//모든학생 조회하는 StudentSelectAllMenu 클래스 : 한 줄에 한 개 행을 출력하기
//과목명을 입력하면 해당 과목 조회하는 ScoreSelectWithSubject 클래스





































