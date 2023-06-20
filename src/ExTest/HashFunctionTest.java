package ExTest;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashFunctionTest {
public static void main(String[] args) {
	//해시함수 : 문자열을 전달받아서 해시코드(평문->암호문)로 변환. 해시코드를 평문으로 변환하지는 못함.
    //         패스워드를 저장할 때 해시값으로 저장.해시값은 해시함수에 따라 고정된 길이로 생성
    //      대표적인 해시함수로 sha256, sha512 함수등은 각각 256비트 또는 512 비트 길이로 해시값 만듭니다.
    //      외부라이브리 guava 를 사용하면 sha256 메소드가 있습니다.
	
	//암호를 '1234'로 정하면 db에는 해시코드로 변환해서 저장합니다.
	//      해시함수는 평문이 같으면 해시코드값도 항상 같습니다. 
	String hashval = Hashing.sha256()                                   //적용할 해시함수 실행
			                .hashString("1234", StandardCharsets.UTF_8) //평문, 인코딩 형식
			                .toString();
	
	System.out.println("'1234' 평문의 해시코드 값은? "+hashval);
	//03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
	// 256비트 나누기 4 하면(16진수 한 자리가 4비트) 64개의 16진수 문자열로 만들어집니다.

	//해시값으로 평문을 예측할 수 없어야 하므로 해시함수는 수학적 알고리즘이 검증된 것을 사용해야 합니다.
}
}
