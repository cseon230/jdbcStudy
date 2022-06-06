/*=======================================
 * ScoreDAO.java
 * - 데이터베이스 액션 처리 전용 클래스
 ========================================*/

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class ScoreDAO
{
	// 주요 속성 구성
	private Connection conn;
	
	// 데이터베이스 연결 담당 메소드
	public Connection connection() throws ClassNotFoundException, SQLException
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	
	// 데이터 입력 담당 메소드
	public int add(ScoreDTO dto) throws SQLException
	{
		// 결과값을 반환할 변수 선언
		int result = 0;
		
		// 작업 객체
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("INSERT INTO TBL_SCORE(SID, NAME, KOR, ENG, MAT)"
							    + " VALUES(SCORESEQ.NEXTVAL, '%s', %d, %d, %d)"
								  , dto.getName(), dto.getKor(), dto.getEng(), dto.getMat());
		
		// 작업객체를 활용해 쿼리문을 전달(실행)
		result = stmt.executeUpdate(sql); 
		
		// 리소스 반납
		stmt.close();
		
		return result;
	}
	
	
	// 전체 리스트 출력 담당 메소드
	public ArrayList<ScoreDTO> lists() throws SQLException
	{
		// 결과값을 반환할 변수 선언
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT SID, NAME, KOR, ENG, MAT"
					+ ", (KOR + ENG + MAT) AS TOT"
					+ ", (KOR + ENG + MAT)/3 AS AVG"
					+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
					+ " FROM TBL_SCORE"
					+ " ORDER BY SID ASC";
		
		// 작업객체를 통해 쿼리문 실행(전달)
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
			// ArrayList의 add() 메소드는 인자로 전달된 객체를 리스트에 추가합니다.
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 이름 검색 담당 메소드
	public ArrayList<ScoreDTO> lists(String name) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE)"
				+ " WHERE NAME = '%s'", name);
		
		// 작업 객체 활용해 쿼리문 전달
		ResultSet rs = stmt.executeQuery(sql);
		
		// 반복문
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();

		return result;
	}
	
	
	// 번호 검색 담당 메소드 
	// string 이 아니라 int형으로 넘겨주는 이유는 이미 오버로딩된 lists(string name)이 있기때문이다
	public ArrayList<ScoreDTO> lists(int sid) throws SQLException
	{
		ArrayList<ScoreDTO> result = new ArrayList<ScoreDTO>();
		
		// 작업 객체 생성
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("SELECT SID, NAME, KOR, ENG, MAT, TOT, AVG, RANK"
				+ " FROM"
				+ " (SELECT SID, NAME, KOR, ENG, MAT, (KOR + ENG + MAT) AS TOT"
				+ ", (KOR + ENG + MAT)/3 AS AVG"
				+ ", RANK() OVER(ORDER BY (KOR+ENG+MAT) DESC) AS RANK"
				+ " FROM TBL_SCORE)"
				+ " WHERE SID = %d", sid);
		
		// 작업 객체 활용해 쿼리문 전달
		ResultSet rs = stmt.executeQuery(sql);
		
		// 반복문
		while (rs.next())
		{
			ScoreDTO dto = new ScoreDTO();
			
			dto.setSid(rs.getString("SID"));
			dto.setName(rs.getString("NAME"));
			dto.setKor(rs.getInt("KOR"));
			dto.setEng(rs.getInt("ENG"));
			dto.setMat(rs.getInt("MAT"));
			dto.setTot(rs.getInt("TOT"));
			dto.setAvg(rs.getDouble("AVG"));
			dto.setRank(rs.getInt("RANK"));
			
			result.add(dto);
		}
		
		rs.close();
		stmt.close();

		return result;
	}
	
	
	
	// 인원 수 확인 담당 메소드
	public int count() throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_SCORE";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next()) 					// 단일 값 이므로 if (rs.next())이렇게 해도 상관없음 
			result = rs.getInt("COUNT");    // result = rs.getInt(1);
		
		rs.close();
		stmt.close();
		
		return result;
		
	}
	
	
	// 데이터 수정 담당 메소드 → 매개변수의 유형 check!!! (찾는 메소드와 수정하는 메소드는 따로따로다.. 주의해야함!!!!!)
	public int modify(ScoreDTO dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("UPDATE TBL_SCORE SET NAME='%s', KOR=%d, ENG=%d, MAT=%d WHERE SID = %s"
								, dto.getName(), dto.getKor(), dto.getEng(), dto.getMat(), dto.getSid());
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		
		return result;
	}
	
	
	// 데이터 삭제 담당 메소드 
	public int remove(int sid) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_SCORE WHERE SID=%d", sid);
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		
		return result;
	}
	
	
	// 데이터베이스 연결 종료 담당 메소드
	public void close() throws SQLException
	{
		DBConn.close();
	}
	
	
	
	
}














































































