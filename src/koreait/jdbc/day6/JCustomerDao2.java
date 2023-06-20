package koreait.jdbc.day6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtilty;
import koreait.jdbc.day4.JCustomer;

public class JCustomerDao2 {
	private static JCustomerDao2 dao = new JCustomerDao2();
	private JCustomerDao2() {}
	public static JCustomerDao2 getJCustomerDao2() {
		return dao;
	}

	public JCustomer login(String id, String password) throws SQLException {
		Connection conn = OracleUtilty.getConnection();
		String sql = "select * from j_custom where custom_id = ? and password = ?";
		JCustomer result = null;
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			result = JCustomer.builder()
					          .customId(rs.getString(1))
					          .name(rs.getString(2))
					          .build();		  
		}
		
		
		return result;     //result 가 null 이 아니면 로그인 성공
		
	}
}
//LoginMain에서 사용자에게 아이디 >>>, 패스워드 >>>  '로그인 성공했습니다.' 또는 '입력한 계정 정보가 바르지 않습니다.'
//로 출력하기