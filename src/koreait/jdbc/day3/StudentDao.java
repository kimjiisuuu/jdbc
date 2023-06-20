package koreait.jdbc.day3;
//DAO : Data Access(접근 - 읽기와 쓰기)Object
//      ㄴ SQL 실행 메소드를 모아놓은 클래스.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtilty;

//DAO : Data Access(접근-읽기와 쓰기) object
//      ㄴSQL 실행 메소드를 모아늏은 클래스


//*StudentDao의 내용을 요약*

//insert,update는 sql 피라미터에 전달한 데이터의 타입을 dto
//delete는 원시형 또는 String
//delete sql의 조건절 컬럼이 여러개 일 때는 dto가 될 수 있다. map도 종종 사용한다.

//insert,update,delete 는 정수 리턴값으로 반영된 행의 갯수를 전달.
//selectOne : sql 파라미터에 전달할 데이터를 메소드 인자로 함.
//selectAll : 파라미터 없고, 여러개의 행을 저장할 객체는 List 타입
public class StudentDao {

	public int insert(StudentDto student) throws SQLException{
		
		Connection connection = OracleUtilty.getConnection();
		
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, student.getStuno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4,student.getAddress());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}
	
	public int update(StudentDto student) throws SQLException{
		Connection connection = OracleUtilty.getConnection();
		String sql = "update TBL_STUDENT set age = ? , address = ? where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, student.getAge());
		ps.setString(2, student.getAddress());
		ps.setString(3, student.getStuno());
		int result = ps.executeUpdate();
		
		ps.close();
		OracleUtilty.close(connection);
		
		return result;
	}
	
	//delete 메소드
	public int delete(String stuno) throws SQLException{
		Connection connection = OracleUtilty.getConnection();
		String sql = "delete from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1,stuno);   // 메소드 인자(매개변수) 값을 sql 쿼리에 전달
		int result = ps.executeUpdate();
		return result;
		
		
	}
	
	//select 모두조회
	public List<StudentDto>selectAll() throws SQLException{
		Connection connection = OracleUtilty.getConnection();
		String sql = "select * from TBL_STUDENT";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		List<StudentDto> results = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StudentDto dto = new StudentDto(rs.getString(1),
					                        rs.getString(2),
					                        rs.getInt(3),
					                        rs.getString(4));
	        results.add(dto);
		}
		return results;
	}
	
	//select* from TBL_STUDENT where stuno = '2023002';실행을 위한
	//selectOne메소드 정의
	public StudentDto selectOne(String stuno) throws SQLException {
		Connection connection = OracleUtilty.getConnection();
		String sql = "select * from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, stuno);
		
		ResultSet rs = ps.executeQuery();
		StudentDto result =null;
		if(rs.next()) {
			//String stuno2 = rs.getString(1);
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String address = rs.getString(4);
			result = new StudentDto(stuno,name,age,address);
			//코드 줄이면 아래와 같이 쓴다.
			//return new StudentDto(stuno,rs.getString(2),rs.getInt(3),rs.getString(4));
		}
		return result;
	}
}






























