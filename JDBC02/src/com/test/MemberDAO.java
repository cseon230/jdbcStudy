/*=========================
	  MemberDAO.java
===========================*/

// DAO(Data Access Object)

// 0. 데이터베이스에 액세스 하는 기능
//   → DBConn 활용(java-db연결 전담 계층)

// 1. 데이터를 입력하는 기능 → insert 쿼리문

// 2. 인원 수 확인하는 기능
//    즉, 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능 → select 쿼리문

// 3. 전체 리스트를 조회하는 기능
//    즉, 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능 → select 쿼리문

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	
	// 주요 속성 구성 → DB 연결 객체
	private Connection conn;
	
	// 생성자 정의(사용자 정의 생성자)
	public MemberDAO()
	{
		conn = DBConn.getConnection();
	}
	
	
	// 1. 데이터를 입력하는 기능 → insert 쿼리문
	public int add(MemberDTO dto) throws SQLException
	{
		// 반환할 결과값을 담아낼 변수(적용된 행의 갯수)
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement(); // createStatement가 예외를 던지고 있으므로, 예외처리를 해줘야한다.
		
		// 작업 객체에 넘겨줄 쿼리문 준비(insert)
		String sql = String.format("INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
								+ " VALUES(MEMBERSEQ.NEXTVAL, '%s', '%s')", dto.getName(), dto.getTel());
		
		// 작업 객체를 활용하여 오라클에 쿼리문 실행(전달)
		result = stmt.executeUpdate(sql); // executeUpdate는 적용된 행의 갯수를 반환한다.
		
		// 사용한 리소스 반납
		stmt.close();
		
		// 최종 결과값 반환
		return result;
	} // end add()
	
	
	// 2. 인원 수 확인하는 기능
	//  즉, 대상 테이블(TBL_MEMBER)의 레코드 카운팅 기능 → select 쿼리문
	public int count() throws SQLException
	{
		// 결과값으로 반환하게 될 변수 선언 및 초기화
		int result = 0;
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement(); // createStatement 예외처리 주의
		
		// 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBER";
		
		//★ 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() 메소드 사용
		// → ResultSet을 반환받음 → 일반적으로 반복문 구성 ★
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 반복문 구성 → 결과값 수신
		while (rs.next())   // 단 한번만 수행하기 때문에 while 문이 아니라 if 문으로 처리해도 상관없다 if(rs.next())
		{
			result = rs.getInt("COUNT"); // "COUNT" 는 우리가 쓰는 컬럼명을 입력한것. 
			                             // rs.getInt(1); 이렇게 1을 쓰면 컬럼의 인덱스값을 넘긴다
										 // 우리가 select ~~~.. 한 컬럼의 인덱스다.
			                             // 오라클의 인덱스를 기준으로 하므로 1부터 시작함
									     // 하지만 컬럼명을 쓰는게 더 바람직하다. 코드를 공유할 때 더 직관적이다
			
		}// 반복문은 딱 한번 실행된다.
		
		
		// 리소스 반납
		rs.close();
		stmt.close();
		// 리소스 반납 순서 : a → b → c 순으로 오픈했으면 c → b → a 순으로 클로즈
		// html의 태그 열고 닫는 순서와 같다.

		
		// 최종 결과값
		return result;
		
	}// end count()
	
	
	
	// 3. 전체 리스트를 조회하는 기능
	//  즉, 대상 테이블(TBL_MEMBER)의 데이터를 조회하는 기능 → select 쿼리문
	public ArrayList<MemberDTO> lists() throws SQLException  // "MemberDTO들" 을 반환할거다.
	{
		// 결과값으로 반환할 변수 선언 및 초기화
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 → select
		String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
		
		// 생성된 작업 객체를 활용하여 쿼리문 실행 → select → executeQuery() → ResultSet 반환 → 일반적으로 반복문 처리
		ResultSet rs = stmt.executeQuery(sql);
		
		// ResultSet 처리 → 일반적으로 반복문 활용
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			
			result.add(dto);
					
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 최종 결과값 반환
		return result;
		
		
	}// end lists()
	
	public void close()
	{
		// 주의
		// conn.close(); 아님.. check~!
		
		DBConn.close();
	}
	
	
}// end class MemberDAO






























































