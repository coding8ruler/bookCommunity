package mvc.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//p531
//이 인터페이스는 모든 컨트롤러클래스(교재에서는 ~~Handler)에서 
//무조건 구현하게 함으로써
//모든 컨트롤러클래스에서 process()를 반드시
//오버라이딩하도록 강제화 시키는 역할을 한다
public interface CommandHandler {
	
	public abstract String process(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	//String타입의 view리턴해주는 메서드
}


/*인터페이스란?
-상수 필드 선언
:인터페이스는  상수 필드만 선언 가능-데이터 저장하지 않음 
-인터페이스에 선언된 필드는 모두 public static final:자동적으로 컴파일 과정에서 붙음
-상수명은 대문자로 작성:서로 다른 단어로 구성되어 있을 경우에는 언더 바(_)로 연결

-선언과 동시에 초기값 지정
 static { } 블록 작성 불가 - static {} 으로 초기화 불가

-추상메서드
 [public abstract] 리턴유형 메서드명(매개변수);  
 
*/


