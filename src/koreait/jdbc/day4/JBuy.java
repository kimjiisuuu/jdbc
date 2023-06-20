package koreait.jdbc.day4;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class JBuy {
	private int buy_seq;
	private String customId;
	private String pcode;
	private int quantity;
	private Date buyDate;
}
//필드값이 모두 같으면 equals 로 true 이 되도록 하고싶다.
//-> equals 와 hashcode를 재정의 해야 합니다.
//-> vo 입니다. vo는 테스트 케이스에서 객체를 비교할 때 사용할 수 있습니다.