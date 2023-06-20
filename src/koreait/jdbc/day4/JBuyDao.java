package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtilty;

public class JBuyDao {//구매와 관련된 CRUD 실행 SQL DAO : JCustomerDao, JProductDao,
	//메소드 이름은 insert, update, delete, select, selectByPname 등등으로 이름을 작성하기
public int insertMany(List<JBuy> carts) {
	Connection conn = OracleUtilty.getConnection();
	String sql = "insert into j_buy \n " + "values(jbuy_seq.nextval,?,?,?,sysdate)";
	int count = 0;
	PreparedStatement ps = null;
	try {
		conn.setAutoCommit(false);       //auto commit 설정 - false
		 ps = conn.prepareStatement(sql);
		 for(JBuy b : carts) {
			 ps.setString(1, b.getCustomId());
			 ps.setString(2, b.getPcode());
			 ps.setInt(3, b.getQuantity());
			 count+= ps.executeUpdate();
		 }
		 conn.commit();    //커밋
	} catch (SQLException e) {
		System.out.println("장바구니 상품 구매하기 예외 : "+e.getMessage());
		System.out.println("장바구니 상품 구매를 취소했습니다.");
		try {
			conn.rollback();  //롤백
		} catch (SQLException e1) {
		}
		
	}
	return count;}
	
public List<MyPageBuy> mypageBuy(String customid) throws SQLException{
	Connection conn = OracleUtilty.getConnection();
	String sql = "select * from mypage_buy where customid = ?";
	PreparedStatement ps = conn.prepareStatement(sql);
	ps.setString(1, customid);
	ResultSet rs = ps.executeQuery();
	
	List<MyPageBuy> list = new ArrayList<>();
    while(rs.next()) {
    	list.add(new MyPageBuy(rs.getDate(1),
    			               rs.getString(2),
    			               rs.getString(3),
    			               rs.getString(4),
    			               rs.getInt(5),
    			               rs.getInt(6),
    			               rs.getLong(7)));
    }
	return list;
	
	
}
	public long myMoney(String customid) throws SQLException {
		Connection conn = OracleUtilty.getConnection();
		String sql = "select sum(total) from mypage_buy where customid = ? ";
		PreparedStatement ps =conn.prepareStatement(sql);
		ps.setString(1, customid);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		long sum = rs.getLong(1);
	return sum;
	
	

}

	public JBuy selectOne(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(JBuy buy) {
		// TODO Auto-generated method stub
		return 0;
	}
}













