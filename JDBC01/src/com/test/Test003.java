/*=================================
 *         Test003.java
 *         
 *     데이터베이스 연결 실습
 *     데이터 입력 실습
 =================================*/


package com.test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import com.util.DBConn;

public class Test003
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		// 연결 객체 생성(구성)
		Connection conn = DBConn.getConnection();
		
		if (conn == null)
		{
			System.out.println("데이터베이스 연결 실패!");
			System.exit(0); // exit 메소드 : 프로그램이 종료
		}
		
		//System.out.println("데이터베이스 연결 성공~!!");
		
		try
		{
			// 작업 객체 구성(준비)
			Statement stmt = conn.createStatement(); // 오른쪽이 먼저..
			// 연결객체 conn 에 createStatement()를 매달았다. createStatement는 작업객체임.
			// 연결객채와 작업객체를 연결했음.
			
			
			// ※ 데이터 입력 쿼리 실행 과정
			// 한 번 실행하면 다시 실행하지 못하는 상황이다.
			// 기본키 제약조건이 설정되어 있으므로
			// 동일한 키 값이 중복될 수 없기 때문이다.
			
			
			// 쿼리문 구성(준비)
			String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(2, '최선하', '010-1111-1111')";
			//--  쿼리문 작성 시 주의사항
			// 1. 쿼리문 끝에 『;』 붙이지 않는다. 즉, 큰따옴표 안에는 ; 붙이지 않고 밖에다 붙인다.
			// 2. 자바에서 실행한 DML 구문은 내부적으로 자동 COMMIT 된다. (INSERT, DELETE, UPDATE 등..)
			// 3. 오라클에서 트랜잭션 처리가 끝나지 않으면 데이터 액션 처리가 이루어지지 않는다.
			//    (오라클에서 트랜잭션 처리가 끝나지 않았다는건 =▶ COMMIT 으로 확정짓지 않았다는 것임
			
			
			// 주로 사용하는 것 두개!
			//stmt.executeQuery() : SELECT 문 처럼 오라클에 전달되도 데이터가 변하지 않는다면 executeQuery() 사용
			//stmt.executeUpdate() : 오라클에 전달됐을때 오라클 데이터가 뭔가 바뀐다면 executeUpdate() 사용
			// executeUpdate() 문은 ＊적용된 행의 갯수＊를 반환한다.
			
			int result = stmt.executeUpdate(sql);
			//-- executeUpdate() : 적용된 행의 갯수를 반환함
			
			if (result > 0) 
			{
				System.out.println("데이터 입력 성공~");
			} else
			{
				System.out.println("입력 실패");
			}
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		DBConn.close();
		//-- 리소스 반납(연결 종료)
	}
}

// 실행 결과
/*
 * 데이터 입력 성공~
 */
