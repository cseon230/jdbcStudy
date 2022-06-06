/*==================================================
 * Test001.java
 * - CallableStatement 를 활용한 SQL 구문 전송 실습
 ==================================================*/


package com.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			Connection conn = DBConn.getConnection();
			
			do
			{
				System.out.print("이름 입력(-1 종료) : ");
				String name = sc.next();
				
				if (name.equals("-1"))
					break;

				System.out.print("전화번호 입력 : ");
				String tel = sc.next();
				
				if (conn != null)
				{
					System.out.println("데이터베이스 연결 성공");
					
					try
					{
						// preparedStatement 처럼 쿼리문이 먼저 준비되어야 작업객체를 생성할 수 있다. check!
						String sql = "{call PRC_MEMBERINSERT(?, ?)}"; // 넘겨주어야하는 매개변수의 갯수만큼 『?』를 써줌
						
						// CallableStatement 작업 객체 생성 check!
						CallableStatement cstmt = conn.prepareCall(sql);
						
						// 매개변수 전달
						cstmt.setString(1, name);
						cstmt.setString(2, tel);
						
						int result = cstmt.executeUpdate(); // 이 프로시저는 INSERT가 발생하는 프로시저 이므로 executeUpdate => 적용된 행의 갯수 int 반환
						if (result > 0)
							System.out.println("프로시저 호출 및 데이터 입력 완료!!!");
							
						
					} catch (Exception e)
					{
						System.out.println(e.toString());
					}
				}
				
			} while (true);
			
			DBConn.close();
			System.out.println("\n데이터베이스 연결 종료");
			System.out.println("프로그램 종료됨");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}














































