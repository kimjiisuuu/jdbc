package jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import koreait.jdbc.day4.JBuy;
import koreait.jdbc.day4.JBuyDao;
//테스트 케이스 입니다.
//테스트 할 메소드 앞에는 @Test 애노테이션 작성하기.
//테스트 결과는 성공 또는 실패 입니다. 테스트 메소드에는 대부분의 경우 리턴이 없습니다.
class BuyDaoTest {

	private JBuyDao dao = new JBuyDao();
	@DisplayName("buy 테이블에 insert 성공하면 리턴값은 1(기대값)이 되어야 합니다.")
	@Test
	void insertTest() {
		JBuy buy = JBuy.builder()
				.customId("hongGD")
			    .pcode("")
				.quantity(5).build();
		int i = dao.insert(buy);
		
		//성공 또는 실패 결과를 확인하는 테스트 메소드를 확인하기
		assertEquals(i,1);   //기대값, 실제값
	}

	@DisplayName("buy테이블에서 buy_seq 컬럼으로 조회하면 null이 아니고 dto 가 리턴된다.")
	@Test
	void selectOneTest() throws SQLException {
		JBuy buy = dao.selectOne(1001);
		print();
		assertNotNull(buy);
	}
	@Disabled
	@Test
	void test() {
		fail("테스트를 비활성화 하는 연습");
		
	}
	//테스트 메소드 아닌 것도 정의하여 호출할 수 있다.
	void print() {
		System.out.println("테스트 중입니다.");
	}
}
