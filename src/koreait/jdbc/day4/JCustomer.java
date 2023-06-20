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
public class JCustomer {
	
private String customId;
private String name;
private String email;
private int age;
private Date regDate;
}
