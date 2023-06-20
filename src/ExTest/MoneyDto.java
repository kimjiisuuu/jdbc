package ExTest;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class MoneyDto {
	private int custno;
	private String custname;
	private String pcode;
	private int price;
	
	
}
