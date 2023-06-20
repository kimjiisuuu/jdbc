package koreait.jdbc.day6;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Scanner;

import com.google.common.hash.Hashing;

import koreait.jdbc.day4.JCustomer;

public class LoginMain {

	public static void main(String[] args) {

		System.out.println("로그인을 구현합니다. - 해시값으로 저장된 패스워드 테스트");
		Scanner sc = new Scanner(System.in);
		boolean isLogin = false;         //로그인 성공 여부
		String id = null;
		String password = null;
		String name = null;
		
		JCustomer user = null;
		JCustomerDao2 dao = JCustomerDao2.getJCustomerDao2();
		while(!isLogin) {
			System.out.println("사용자 아이디 입력");
			id = sc.nextLine();
			
			System.out.println("사용자 비밀번호 입력");
			//password = sc.nextLine();
			password = Hashing.sha256()                                  
             .hashString(sc.nextLine(), StandardCharsets.UTF_8) 
             .toString();
			
			
			try {
				user = dao.login(id, password);
				if(user != null) {
			    	System.out.println("로그인 성공 !! "+ user.getName());
			    	isLogin = true;
			    }else {
			    System.out.println("사용자 계정 정보가 일치하지 않습니다.");
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		    
		    }
			
		}
	}


