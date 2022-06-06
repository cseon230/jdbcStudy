/*========================
	MemberMain.java
==========================*/

/*
 ○ TBL_MEMBER 테이블을 활용하여
    이름과 전화번호를 여러 건 입력받고, 전체 출력하는 프로그램을 구현한다.
    단, 데이터베이스 연동이 이루어져야 하고,
    MemberDAO, MemberDTO 클래스를 활용해야 한다.
    
실행 예)

이름 전화번호 입력(2) : 오이삭 010-2222-2222
>> 회원 정보 입력 완료!
이름 전화번호 입력(3) : 임소민 010-3333-3333
>> 회원 정보 입력 완료!
이름 전화번호 입력(4) : .

-----------------------------------
전체 회원 수 : 3명
-----------------------------------
번호      이름      전화번호
1        이호석    010-1111-1111
2        오이삭    010-2222-2222
3        임소민    010-3333-3333
-----------------------------------

DAO : 액세스오브젝트, 액션처리하는애들만 존재(데이터입력하는기능, 전체조회하는기능, 인원수확인하는기능)
DTO : 속성만존재, 게터 세터 ( 속성 : sid, name, tel )

*/

package com.test;

import java.util.Scanner;

import com.util.DBConn;

public class MemberMain
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			MemberDAO dao = new MemberDAO();
			// MemberDAO 인스턴스 생성하면 생성자가 실행되면서 getConnection 에서 예외가 발생하므로, try~catch 문 안에 들어가있어야 한다.
			
			int count = dao.count();
			
			do
			{
				System.out.printf("이름 전화번호 입력(%d) : ", ++count);	// 오이삭 010-2222-2222
				String name = sc.next();	// sc.next로 오이삭 받아옴
				
				// 반복의 조건을 무너뜨리는 코드 구성
				if (name.equals("."))
				{
					break;
				}
				String tel = sc.next();     // sc.next로 010-2222-2222 받아옴
				
				// 여기까지의 과정을 통해 이름과 전화번호를 사용자로부터 입력받은 이유는
				// 입력받은 데이터를 데이터베이스에 입력하기 위함
				// 데이터 입력을 위해서는 DAO 의 add() 메소드 호출 필요
				// add() 메소드를 호출하기 위해서는 MemberDTO 값을 넘겨주는 과정이 필요하다.
				// MemberDTO 값을 넘겨주기 위해서는 MemberDTO 객체의 속성값 구성이 필요
				
				// 지금 넘겨받은 값으로 MemberDTO를 구성하고, add()에 MemberDTO를 넘겨줘서 값을 insert 해줘야한다.
				
				// 1. MemberDTO 객체 생성
				MemberDTO dto = new MemberDTO();
				
				// 2. 속성값 구성
				dto.setName(name);
				dto.setTel(tel);
				
				// 3. 데이터베이스에 데이터 입력하는 메소드 호출 → add()
				int result = dao.add(dto);
				if (result > 0)
				{
					System.out.println(">> 회원 정보 입력 완료!");
				}
			} while (true);
			
			System.out.println(); // ctrl + alr + 방향키 → 문장 한줄 복사해서 그대로 붙여넣어짐
			System.out.println("-----------------------------------");
			System.out.printf("전체 회원 수 : %d명\n", dao.count());
			System.out.println("-----------------------------------");
			System.out.println("번호      이름      전화번호");
			// 리스트를 가져와서 출력해줘야함
			//dao.lists()
			for (MemberDTO obj : dao.lists())
			{
				System.out.printf("%3s %7s %12s\n", obj.getSid(), obj.getName(), obj.getTel());
			}
			System.out.println("-----------------------------------");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
			// TODO: handle exception
		}
		
		
		finally
		{
			try
			{
				DBConn.close();
				System.out.println("데이터베이스 연결 닫힘");
				System.out.println("프로그램 종료됨");
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
				// TODO: handle exception
			}
		}

		sc.close();
	}
}
/*-----------------------------------
전체 회원 수 : 4명
-----------------------------------
번호      이름      전화번호
  4     이호석 010-1111-1111
  5     오이삭 010-2222-2222
  6     임소민 010-3333-3333
  7     최선하 010-2648-5587
-----------------------------------*/













