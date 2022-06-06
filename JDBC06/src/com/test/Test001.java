/*========================
   * Test001.java
   * - 쿼리문 전송 실습
 ========================*/

package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import com.util.DBConn;

public class Test001
{
	public static void main(String[] args)
	{
		try
		{
			Connection conn = DBConn.getConnection();
			
			if (conn != null)
			{
				System.out.println("데이터베이스 연결 성공!");
				
				try
				{
					/*
					Statement stmt = conn.createStatement();
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
							  + " VALUES(MEMBERSEQ.NEXTVAL, '한충희', '010-5555-5555')";
					int result = stmt.executeUpdate(sql);
					
					if (result > 0)
						System.out.println("데이터 입력 성공!");
					
					stmt.close();
					DBConn.close();
					*/
					
					String sql = "INSERT INTO TBL_MEMBER(SID, NAME, TEL)"
					//		  + " VALUES(MEMBERSEQ.NEXTVAL, '?', '?')";
							  + " VALUES(MEMBERSEQ.NEXTVAL, ?, ?)";   // preparedStatement로 넘겨줄땐 따옴표가 없어야한다.
					                                                  // 따옴표가 들어가냐 아니냐는 setString이냐 setInt냐로
					                                                  // 결정된다
					
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					
					// IN 매개변수 넘겨주기
					pstmt.setString(1, "박현수");
					pstmt.setString(2, "010-6666-6666");
					
					int result = pstmt.executeUpdate(); // executeUpdate는 적용된 행의 '갯수' 반환..
					if (result > 0)
					{
						System.out.println("데이터 입력 성공!");
					}
					pstmt.close();
					DBConn.close();
					
				} catch (Exception e)
				{
					System.out.println(e.toString());
					// TODO: handle exception
				}
			}
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}


























































