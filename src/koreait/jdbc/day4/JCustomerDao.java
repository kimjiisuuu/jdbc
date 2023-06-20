package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtilty;

public class JCustomerDao{
public  JCustomer selectById(String customid) throws SQLException{
Connection conn = OracleUtilty.getConnection();
String sql ="select * from j_custom where custom_id=?";
PreparedStatement ps = conn.prepareStatement(sql);
ps.setString(1, customid);

ResultSet rs = ps.executeQuery();
JCustomer temp = null;
if(rs.next()) {
	temp = new JCustomer(rs.getString(1),
			    rs.getString(2),
			    rs.getString(3),
			    rs.getInt(4),
			    rs.getDate(5));
}
ps.close();
conn.close();

return temp;
}
}