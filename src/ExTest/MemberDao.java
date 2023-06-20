package ExTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtilty;
import koreait.jdbc.day4.JProduct;



//DAO에는 입력과 출력은 포함시키지않습니다. 오직 어떤 형식의 데이터를 받아서
// 어떤 SQL을 실행하여, 어떤 값을 리턴할 것인가를 중점을 두고 정의하면 됩니다.
//DTO는 데이터를 저장하는 목적의 클래스, DAO는 어떤 동작을 할 것인지를 정의한 메소드만 있다.
public class MemberDao {
//insert,select,update
   // 싱글톤으로 만들어 보기
   
   private static MemberDao dao= new MemberDao();
   private MemberDao() {}
   public static MemberDao getExTestDao() {//메소드 getInstance외에
//    내용을 알 수 있는 이름으로 정하기
      
      
      return dao;
   }
   
   
   public int insert(MemberDto member) throws SQLException {
         Connection connection = OracleUtilty.getConnection();
         String sql = "insert into j_member values(seq_nextval,?,?,?,?,?,sysdate)";
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setString(1, member.getCustname());
         ps.setString(2, member.getPhone());
         ps.setString(3, member.getAddress());
         ps.setString(4, member.getGrade());
         ps.setString(5, member.getCity());
         
         int result = ps.executeUpdate();

         ps.close();
         connection.close();
         return result;
         
      }
         public MemberDto selectOne(String custno) throws SQLException {//수정할 데이터 가져오기
               Connection conn = OracleUtilty.getConnection();
               String sql = "select * from j_member where custno = ?";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setString(1,custno);
               MemberDto result = null;
               ResultSet rs = ps.executeQuery();
               if(rs.next()) {
                  String custname = rs.getString(2);
                  String phone = rs.getString(3);
                  String address = rs.getString(4);
                  Date joindate = rs.getDate(5);
                  String grade = rs.getString(6);
                  String city = rs.getString(7);
                  result = new MemberDto(custno, custname, phone, address, joindate, grade, city);
               }
               ps.close();
               conn.close();
               return result;
            }
         
            public List<MemberDto> selectAll() throws SQLException{
            	Connection conn = OracleUtilty.getConnection();
            	String sql = "select * from j_member";
            	PreparedStatement ps = conn.prepareStatement(sql);
            
            	ResultSet rs = ps.executeQuery();
            	List<MemberDto> list = new ArrayList<>();
            	while(rs.next()) {
            		list.add(new MemberDto(rs.getString(1),
            				              rs.getString(2),
            				              rs.getString(3),
            				              rs.getString(4),
            				              rs.getDate(5),
            				             rs.getString(6),
            				             rs.getString(7))); 
            }
            	ps.close();
            	conn.close();
            	return list;
            }
            public int update(MemberDto md) throws SQLException {
               Connection conn = OracleUtilty.getConnection();
               //수정 가능한 항목(컬럼)은 모두 set에 포함시키기
               String sql = "update j_member set custname = ?,phone = ?,address =?,city =? \n"
                     + "where custno=?";
               PreparedStatement ps = conn.prepareStatement(sql);
               ps.setString(1, md.getCustname());
               ps.setString(2, md.getPhone());
               ps.setString(3, md.getAddress());
               ps.setString(4, md.getCity());
               int result = ps.executeUpdate();
               
               ps.close();
               conn.close();
               return result;
            }
}