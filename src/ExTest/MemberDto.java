package ExTest;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class MemberDto {

	
	
	private String custon;
	private String custname;
	private String  phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
	
	
	
}
