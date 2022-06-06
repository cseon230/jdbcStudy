/*==========================================
 * Process.java
 * - 콘솔 기반 서브 메뉴 입출력 전용 클래스
 ==========================================*/


package com.test;

import java.util.ArrayList;
import java.util.Scanner;


public class MemberProcess_copy
{
	// 주요 속성 구성.
	// Process 는 DAO 를 바탕으로 실질적인 콘솔 처리가 이루어지는 부분
	private MemberDAO_copy dao;
	
	// 생성자 정의
	public MemberProcess_copy()
	{
		dao = new MemberDAO_copy();
	}
	
	
	// 직원 정보 입력 메소드 정의
	public void memberInsert()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			dao.connection(); // dao는 memberDAO 인스턴스 객체임 .connection으로 접근해서 데이터베이스 연결함
			
			// 지역 리스트 구성
			ArrayList<String> citys = dao.searchCity();
			StringBuilder citysStr = new StringBuilder();
			// StringBuilder 는 String과 문자열을 더할 때 새로운 객체를 생성하는 것이 아니라 기존의 데이터에 더하는 방식이기 때문에 속도가 빠르다
			for (String city : citys)
			{
				citysStr.append(city + "/");
			}
			
			// 부서 리스트 구성
			ArrayList<String> buseos = dao.searchBuseo();
			StringBuilder buseoStr = new StringBuilder();
			for (String buseo : buseos)
			{
				buseoStr.append(buseo + "/");
			}
			
			// 직위 리스트 구성
			ArrayList<String> jikwis = dao.searchjikwi();
			StringBuilder jikwiStr = new StringBuilder();
			for (String jikwi : jikwis)
			{
				jikwiStr.append(jikwi + "/");
			}
			
			System.out.println();
			System.out.println("직원 정보 입력 ----------------------------------------------------------");
			
			System.out.print("이름 : ");
			String empName = sc.next();
			
			System.out.print("주민등록번호(yymmdd-nnnnnn) : ");
			String ssn = sc.next();
			
			System.out.print("입사일(yyyy-mm-dd) : ");
			String ibsaDate = sc.next();
			
			System.out.printf("지역(%s) : ", citysStr.toString()); // toString 메소드는 객체가 가지고 있는 정보나 값들을 문자열로 만들어 리턴하는 메소드
			String cityName = sc.next();
			
			System.out.print("전화번호 : ");
			String tel = sc.next();
			
			System.out.printf("부서(%s) : ", buseoStr.toString());
			String buseoName = sc.next();
			
			System.out.printf("직위 (%s) : ", jikwiStr.toString());
			String jikwiName = sc.next();
			
			System.out.printf("기본급(최소 %d원 이상) : ", dao.searchBasicPay(jikwiName)); // 사용자가 입력한 jikwiName을 searchBasicPay의 매개변수로 보내 기본급을 찾는다.
			int basicPay = sc.nextInt();
			
			System.out.print("수당 : ");
			int sudang = sc.nextInt();
			
			System.out.println();
			
			System.out.println("직원 정보 입력 완료                                                        ");
			System.out.println("---------------------------------------------------------- 직원 정보 입력");
			
			// 직원 정보 입력이니까 DTO에 정보를 입력해야함. 정보입력은 setter
			MemberDTO_copy dto = new MemberDTO_copy();
			dto.setEmpName(empName);
			dto.setSsn(ssn);
			dto.setIbsaDate(ibsaDate);
			dto.setCityName(cityName);
			dto.setTel(tel);
			dto.setBuseoName(buseoName);
			dto.setJikwiName(jikwiName);
			dto.setBasicPay(basicPay);
			dto.setSudang(sudang);
			
			// 직원 정보 입력이 잘 됐는지 확인
			int result = dao.add(dto);
			if (result > 0)
			{
				System.out.println("직원 정보 입력 완료");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally // 예외여부와 상관없이 실행되는 로직
		{
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	
	}
	
	
	// 직원 전체 출력 메소드 정의
	public void memberLists() // 반환하는 값 없음 .. DAO에서 이미 다 정의했음.
	{
		Scanner sc = new Scanner(System.in);
		
		// 서브 메뉴 출력
		System.out.println("1. 사번 정렬");
		System.out.println("2. 이름 정렬");
		System.out.println("3. 부서 정렬");
		System.out.println("4. 직위 정렬");
		System.out.println("5. 급여 내림차순 정렬");
		System.out.print(">> 항목 선택(1~5, -1:종료) : ");
		
		String menuStr = sc.next();
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			
			if (menu == -1)
			{
				return;
			}
			
			String key = "";
			
			switch (menu)
			{
				case 1:
					key = "EMP_ID";
					break;
				case 2:
					key = "EMP_NAME";
					break;
				case 3:
					key = "BUSEO_NAME";
					break;
				case 4:
					key = "JIKWI_NAME";
					break;
				case 5:
					key = "PAY DESC";
				default:
					break;
			}
			
			dao.connection(); // 데이터베이스 연결
			
			System.out.printf("전체 인원 : %d명\n", dao.memberCount());
			System.out.println("사번   이름   주민번호   입사일   지역   전화번호   부서   직위    기본급   수당   급여");
			ArrayList<MemberDTO_copy> Lists = dao.lists(key);
			for (MemberDTO_copy dto : Lists)
			{
				System.out.printf("%5d %4s %14s %10s %4s %12s %4s %3s %8d %8d %8d\n"
				          , dto.getEmpId(), dto.getEmpName(), dto.getSsn(), dto.getIbsaDate(), dto.getCityName(), dto.getTel(), dto.getBuseoName()
				          , dto.getJikwiName(), dto.getBasicPay(), dto.getSudang(), dto.getPay());
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
	
	
	// 직원 검색 출력 메소드 정의
	public void memberSearch()
	{
		dao.connection();
		Scanner sc = new Scanner(System.in);
		
		// 서브메뉴구성
		System.out.println("1. 사번 검색");
		System.out.println("2. 이름 검색");
		System.out.println("3. 부서 검색");
		System.out.println("4. 직위 검색");
		System.out.print(">> 항목 선택(1~4, -1:종료) : ");
		
		String menuStr = sc.next();
		
		
		try
		{
			int menu = Integer.parseInt(menuStr);
			
			if (menu == -1)
			{
				return;
			}
			
			String key = "";
			String value = "";
			
			switch (menu)
			{
				case 1:
					key = "EMP_ID";
					System.out.print("검색할 사번 입력 : ");
					value = sc.next();
					break;
				case 2:
					key = "EMP_NAME";
					System.out.print("검색할 이름 입력 : ");
					value = sc.next();
					break;
				case 3:
					key = "BUSEO_NAME";
					System.out.print("검색할 부서 입력 : ");
					value = sc.next();
					break;
				case 4: 
					key = "JIKWI_NAME";
					System.out.print("검색할 직위 입력 : ");
					value = sc.next();
					break;
				default:
					break;
			}
			
			dao.connection();
			
			System.out.println();
			System.out.printf("검색 인원 : ", dao.memberCount(key, value));
			System.out.println(" 사번   이름     주민번호      입사일     지역   전화번호     부서    직위  기본급    수당     급여");
			ArrayList<MemberDTO_copy> memList = dao.searchLists(key, value);
			for (MemberDTO_copy dto : memList)
			{
				System.out.printf("%5d %4s %14s %10s %4s %12s %4s %3s %8d %8d %8d\n"
				          , dto.getEmpId(), dto.getEmpName(), dto.getSsn(), dto.getIbsaDate(), dto.getCityName(), dto.getTel(), dto.getBuseoName()
				          , dto.getJikwiName(), dto.getBasicPay(), dto.getSudang(), dto.getPay());
			}
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally
		{
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
	}
	
	
	
	// 직원 정보 수정 메소드 정의
	public void memberUpdate()
	{
		Scanner sc = new Scanner(System.in);
		
		try
		{
			System.out.println("수정할 대상의 사원번호 입력 : ");
			String value = sc.next();
			
			dao.connection();
			
			ArrayList<MemberDTO_copy> memList = dao.searchLists("EMP_ID", value);
			
			if (memList.size() > 0)
			{
				// 지역 리스트 구성
				ArrayList<String> citys = dao.searchCity();
				// 부서 리스트 구성
				
				// 직위 리스트 구성
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}

}





































