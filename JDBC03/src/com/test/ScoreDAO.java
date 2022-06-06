/*================
 * ScoreDAO.java
 ================*/

/*
1. 데이터베이스에 연결하는 기능
	(DBConn 활용)
	
2. 데이터를 입력하는 기능 → insert


3. 전체 리스트 조회하는 기능
*/


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 1. 데이터베이스에 연결하는 기능
	private Connection conn;
	
	public ScoreDAO()
	{
		conn = DBConn.getConnection();
	}
	
	
	// 2. 데이터를 입력하는 기능 → insert 쿼리문
	public int add(ScoreDTO dto) throws SQLException
	{
		// 반환할 결과값을 담아낼 변수 선언
		int result = 0;
		
		// 작업 객체 생성. 연결 객체에 작업 객체 달아주기.
		Statement stmt = conn.createStatement();
		
		// 작업 객체에 넘겨줄 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_SCORE (SID, NAME, KOR, ENG, MAT)"
								+ " VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)", dto.getName() ,dto.getKor(), dto.getEng(), dto.getMat() );		
		// String.format은 비단 소수점이 아니라 정수형, 실수형, 주소 등 다양한 형태를 출력할 수 있다. 
		
		// 작업 객체를 활용하여 오라클에 쿼리문 전달(실행)
		result = stmt.executeUpdate(sql); // executeUpdate 메소드는 적용된 행의 갯수를 반환한다.
		
		// 사용한 리소스 반납
		stmt.close();
		
		return result;
	}
	
	
	// 3. 인원 수 확인하는 기능 → select
	public int count() throws SQLException
	{
		// 반환할 결과값을 담아낼 변수 선언
		int result = 0;
		
		// 작업 객체 생성, 연결 객체에 작업 객체 달아주기
		Statement stmt = conn.createStatement();
		
		// 작업 객체에 넘겨줄 쿼리문 준비
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		// 생성한 작업 객체를 활용하여 쿼리문 실행 → SELECT → executeQuery → ResultSet 으로 반환받음 → 반복문 구성
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 결과값 반환
		return result;
	}
	
	
	
	// 4. 전체 리스트 조회하는 기능 → select
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		// 결과값으로 변환할 변수 선언 및 초기화
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비 (select)
		String sql = "SELECT SID, NAME, KOR, ENG, MAT, KOR+ENG+MAT AS TOT, KOR+ENG+MAT/3 AS AVG"
				  + " FROM TBL_SCORE"
				  + " ORDER BY SID";
		
		// 생성한 작업객체를 활용하여 쿼리문 전달 (select = executeQuery() → ResultSet 반환 → 일반적으로 반복문 처리 
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("KOR"), rs.getInt("ENG"), rs.getInt("MAT"));
			dto.setAvg(rs.getInt("KOR"), rs.getInt("ENG"), rs.getInt("MAT"));
			
			result.add(dto);
		}
		
		// 리소스 반납
		rs.close();
		stmt.close();
		
		// 결과값 반환
		return result;
	}
	
	
}

























