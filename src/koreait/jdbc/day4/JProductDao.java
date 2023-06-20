package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtilty;

public class JProductDao {
public static List<JProduct> selectAll() throws SQLException{
	Connection conn = OracleUtilty.getConnection();
	String sql = "select * from j_product";
	PreparedStatement ps = conn.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	
	List<JProduct> list = new ArrayList<>();
	while(rs.next()) {
		list.add(new JProduct(rs.getString(1),
				              rs.getString(2),
				              rs.getString(3),
				              rs.getInt(4))); 
	}
	ps.close();
	conn.close();
	return list;
}

//상품명으로 검색
public static List<JProduct>selectByPname(String pname)throws SQLException{
	//pname은 사용자가 입력한 검색어
	Connection conn = OracleUtilty.getConnection();
	String sql = "select * from j_product where pname like '%' || ? ||'%'";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, pname);
	ResultSet rs = ps.executeQuery();
	
	List<JProduct> list = new ArrayList<>();
	while(rs.next()) {
		list.add(new JProduct(rs.getString(1),
				              rs.getString(2),
				              rs.getString(3),
				              rs.getInt(4)));
	}
	return list;
}
}
