package jdbc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import koreait.jdbc.day6.SingletonClass;

class SingletonTest {

	@Test
	void singletonTtest() {
		SingletonClass s1 = SingletonClass.getInstance();
		SingletonClass s2 = SingletonClass.getInstance();
		
		assertEquals(s1,s2);
		//s1, s2가 참조하는 객체는 동일하다
	}

}
