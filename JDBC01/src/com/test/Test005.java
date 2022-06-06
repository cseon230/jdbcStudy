/*====================================
   Test005.java
   - 테이블 내의 데이터 읽어오기
======================================*/


package com.test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class Test005
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		// 연결 객체 생성
		Connection conn = DBConn.getConnection();
		
		if (conn != null)
		{
			System.out.println("데이터베이스 연결 성공");
			
			try
			{
				//작업 객체 생성
				Statement stmt = conn.createStatement();
				
				// 쿼리문 준비(select)
				String sql = "SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID";
				/*
				String sql = "SELECT SID, NAME, TEL" 
							+ " FROM TBL_MEMBER" 
							+ " ORDER BY SID";
				*/
				// ※ 쿼리문을 구성하는 과정에서 공백이나 개행 처리 check~!!!
				
				// ※ executeQuery() 메소드를 사용하면
				// 질의 결과는 ResultSet 객체로 가져올 수 있다.
				// 하지만, ResultSet 객체가 질의에 대한 결과물 모두를
				// 한꺼번에 갖고있는 구조는 아니다.
				// 단지, 데이터베이스로부터 획득한 질의 결과물에 대한
				// 관리가 가능한 상태가 되는 것이다.
				// 이 때문에 데이터베이스와의 연결을 끊게 되면
				// ResultSet 객체는 더 이상 질의 결과를 관리할 수 없게 된다.
				
				
				// 쿼리문 실행
				ResultSet rs = stmt.executeQuery(sql);
				// ※ executeQuery 문은 ResultSet 을 반환함.
				// ResultSet은 "결과집합" 인터페이스
				// ResultSet에 실제로 값이 담겨있는건 아니라 관리가 가능한 상태임
				
				// ResultSet에 대한 처리 (반복문 구성)
				while (rs.next()) // 다음값이 있는지 없는지 반환하는 next 메소드. 있으면 true.
				{
					String sid = rs.getString("SID"); // 문자열로 select해서 받아오니까 getString 메소드 사용.
					String name = rs.getString("NAME");
					String tel = rs.getString("TEL"); // 만약 실수값이면.. getDouble 메소드 사용. 
					
					String str = String.format("%3s %8s %12s", sid, name, tel);
								 // 이런 형태의(format) "문자열로" 반환하겠다.
					             // System.out.printf("%3d %8s %12s", sid, name, tel);
					
					System.out.println(str);
				}
				
				// ResultSet 리소스 반납
				rs.close();
				
				// Statement 리소스 반납
				stmt.close();
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
			
			
		}
		else
		{
			System.out.println("데이터베이스 연결 실패");
		}
		
		DBConn.close();
		
		System.out.println("데이터베이스 연결 닫힘");
		System.out.println("프로그램 종료됨");
	}
}

// 실행 결과
/*
데이터베이스 연결 성공
  1      홍길동 010-1111-1111
  2      최선하 010-1111-1111
  3      김정용 010-3333-3333
  4      오이삭 010-4444-4444
  5      김태형 010-5555-5555
데이터베이스 연결 닫힘
프로그램 종료됨
*/






































