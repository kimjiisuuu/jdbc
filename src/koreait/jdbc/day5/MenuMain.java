package koreait.jdbc.day5;

import ExTest.MemberDao;

public class MenuMain {

	//우리가 만든 dao를 정상적으로 동작하는지 확인하려면 데이터를 직접 주고
	//메소드를 호출해서 실행하는 main을 만들어야 한다.
	//main메소드는 '응용프로그램'을 만들기 위해서 작성하는 것으로 테스트용은 아니다.
	
	//특정 메소드가 잘 실행되는지 검증(테스트)하는 것을 단위테스트(unit test)라고 한다.
	//자바에서 기본적으로 사용하는 단위테스트 라이브러리로 JUnit 이 있다.
	//테스트케이스는 테스트메소드 또는 이것을 포함하는 클래스이다. 테스트케이스는 test 폴더에 작성
	//           빌드에 포함시키지 않는다. src 폴더 : 프로덕션 코드.(빌드에 포함되는 코드)     
	//우리는 하나의 dao를 완성하기 위해 main까지 만들지 않고도 검증하는 목적으로 사용할 수 있다.
	
	//싱글톤 dao 사용하기
	MemberDao dao = MemberDao.getMemberDao();
	
}
