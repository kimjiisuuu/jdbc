package ExTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtilty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Builder
@ToString

public class MoneyDao {
private static MoneyDao dao = new MoneyDao();
public static MoneyDao getMoneydao() {
	return dao;
}

      public List<MoneyDto> money() throws SQLException {
            Connection conn = OracleUtilty.getConnection();
            String sql = "select met.custno, custname,\r\n"
            		+ "   decode(met.grade, 'A', 'VIP', 'B', '일반', 'C', '직원') as grade,\r\n"
            		+ "   asum\r\n"
            		+ "   from member_tbl_02 met join\r\n"
            		+ "   (select custno, sum(price) asum from money_tbl_02 mot \r\n"
            		+ "   group by custno\r\n"
            		+ "   order by asum desc) sale\r\n"
            		+ "   on met.custno = sale.custno ORDER BY asum desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            List<MoneyDto> result = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
         while(rs.next()) {
               result.add(new MoneyDto(rs.getInt(1), 
                                    rs.getString(2), 
                                    rs.getString(3), 
                                    rs.getInt(4))
                                    ); 
                                  
              
            }
            return result;
         }

   }


