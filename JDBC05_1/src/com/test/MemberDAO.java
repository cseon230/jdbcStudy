/*==================
 * MemberDAO.java
 ==================*/


package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn;

public class MemberDAO
{
	
	// 주요 속성 구성
	private Connection conn;
	
	// 데이터베이스 연결 담당 메소드
	public Connection connection()
	{
		conn = DBConn.getConnection();
		return conn;
	}
	
	// 데이터 입력 담당 메소드
	public int add(MemberDTO dto) throws SQLException
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("INSERT INTO TBL_EMP (EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_ID, TEL, BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG, SAL)"
								+ " VALUES (EMPSEQ.NEXTVAL, '%s', '%s', '%s', '%s', '%s', '%s', '%s', %d, %d, %d)"
								, dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getSal() );
		
		result = stmt.executeUpdate(sql);
		
		stmt.close();
		
		return result;
	}
	
	
	// 직원 전체 출력(사번 정렬)
	public ArrayList<MemberDTO> list_empid() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
				  + "FROM  TBL_EMP E JOIN TBL_CITY C"
				  + "ON E.CITY_ID = C.CITY_ID"
				  + "JOIN TBL_BUSEO B"
				  + "ON E.BUSEO_ID = B.BUSEO_ID"
				  + "JOIN TBL_JIKWI J"
				  + "ON E.JIKWI_ID = J.JIKWI_ID"
				  + "ORDER BY EMP_ID";
				  
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo(rs.getString("BUSEO"));
			dto.setJikwi(rs.getString("JIKWI"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직원 전체 출력(이름 정렬)
	public ArrayList<MemberDTO> list_name() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
				  + "FROM  TBL_EMP E JOIN TBL_CITY C"
				  + "ON E.CITY_ID = C.CITY_ID"
				  + "JOIN TBL_BUSEO B"
				  + "ON E.BUSEO_ID = B.BUSEO_ID"
				  + "JOIN TBL_JIKWI J"
				  + "ON E.JIKWI_ID = J.JIKWI_ID"
				  + "ORDER BY EMP_NAME";
				  
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo(rs.getString("BUSEO"));
			dto.setJikwi(rs.getString("JIKWI"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직원 전체 출력 (부서 정렬)
	public ArrayList<MemberDTO> list_buseo() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
				  + "FROM  TBL_EMP E JOIN TBL_CITY C"
				  + "ON E.CITY_ID = C.CITY_ID"
				  + "JOIN TBL_BUSEO B"
				  + "ON E.BUSEO_ID = B.BUSEO_ID"
				  + "JOIN TBL_JIKWI J"
				  + "ON E.JIKWI_ID = J.JIKWI_ID"
				  + "ORDER BY BUSEO";
				  
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo(rs.getString("BUSEO"));
			dto.setJikwi(rs.getString("JIKWI"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직원 전체 출력 (직위 정렬)
	public ArrayList<MemberDTO> list_jikwi() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
				  + "FROM  TBL_EMP E JOIN TBL_CITY C"
				  + "ON E.CITY_ID = C.CITY_ID"
				  + "JOIN TBL_BUSEO B"
				  + "ON E.BUSEO_ID = B.BUSEO_ID"
				  + "JOIN TBL_JIKWI J"
				  + "ON E.JIKWI_ID = J.JIKWI_ID"
				  + "ORDER BY JIKWI";
				  
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo(rs.getString("BUSEO"));
			dto.setJikwi(rs.getString("JIKWI"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직원 전체 출력 (급여 내림차순 정렬)
	public ArrayList<MemberDTO> list_sal() throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = "SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
				  + "FROM  TBL_EMP E JOIN TBL_CITY C"
				  + "ON E.CITY_ID = C.CITY_ID"
				  + "JOIN TBL_BUSEO B"
				  + "ON E.BUSEO_ID = B.BUSEO_ID"
				  + "JOIN TBL_JIKWI J"
				  + "ON E.JIKWI_ID = J.JIKWI_ID"
				  + "ORDER BY SAL DESC";
				  
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo(rs.getString("BUSEO"));
			dto.setJikwi(rs.getString("JIKWI"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
			
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
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		rs.close();
		stmt.close();
		
		return result; 
	}
	
	
	// 직원 검색 출력(사번 검색)
	public ArrayList<MemberDTO> list_empid(int empid) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, J.JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
				  + "FROM  TBL_EMP E JOIN TBL_CITY C"
				  + "ON E.CITY_ID = C.CITY_ID"
				  + "JOIN TBL_BUSEO B"
				  + "ON E.BUSEO_ID = B.BUSEO_ID"
				  + "JOIN TBL_JIKWI J"
				  + "ON E.JIKWI_ID = J.JIKWI_ID"
				  + "WHERE EMP_ID = %D", empid );
				  
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo(rs.getString("BUSEO"));
			dto.setJikwi(rs.getString("JIKWI"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직원 검색 출력 (이름 검색)
	public ArrayList<MemberDTO> list_name(String name) throws SQLException
	{
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		Statement stmt = conn.createStatement();
		
		// 쿼리문 준비
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
				  + "FROM  TBL_EMP E JOIN TBL_CITY C"
				  + "ON E.CITY_ID = C.CITY_ID"
				  + "JOIN TBL_BUSEO B"
				  + "ON E.BUSEO_ID = B.BUSEO_ID"
				  + "JOIN TBL_JIKWI J"
				  + "ON E.JIKWI_ID = J.JIKWI_ID"
				  + "WHERE EMP_NAME = '%s'", name );
				  
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setEmpid(rs.getInt("EMP_ID"));
			dto.setName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsadate(rs.getString("IBSADATE"));
			dto.setCity(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseo(rs.getString("BUSEO"));
			dto.setJikwi(rs.getString("JIKWI"));
			dto.setBasicpay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
			
			result.add(dto);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직원 검색 출력 (부서 검색)
		public ArrayList<MemberDTO> list_buseo(String buseo) throws SQLException
		{
			ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
			
			Statement stmt = conn.createStatement();
			
			// 쿼리문 준비
			String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
					  + "FROM  TBL_EMP E JOIN TBL_CITY C"
					  + "ON E.CITY_ID = C.CITY_ID"
					  + "JOIN TBL_BUSEO B"
					  + "ON E.BUSEO_ID = B.BUSEO_ID"
					  + "JOIN TBL_JIKWI J"
					  + "ON E.JIKWI_ID = J.JIKWI_ID"
					  + "WHERE BUSEO = '%s'", buseo );
					  
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				MemberDTO dto = new MemberDTO();
				
				dto.setEmpid(rs.getInt("EMP_ID"));
				dto.setName(rs.getString("EMP_NAME"));
				dto.setSsn(rs.getString("SSN"));
				dto.setIbsadate(rs.getString("IBSADATE"));
				dto.setCity(rs.getString("CITY_NAME"));
				dto.setTel(rs.getString("TEL"));
				dto.setBuseo(rs.getString("BUSEO"));
				dto.setJikwi(rs.getString("JIKWI"));
				dto.setBasicpay(rs.getInt("BASICPAY"));
				dto.setSudang(rs.getInt("SUDANG"));
				dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
				
				result.add(dto);
				
			}
			
			rs.close();
			stmt.close();
			
			return result;
		}
		
		
		// 직원 검색 출력 (직위 검색)
		public ArrayList<MemberDTO> list_jikwi(String jikwi) throws SQLException
		{
			ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
			
			Statement stmt = conn.createStatement();
			
			// 쿼리문 준비
			String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE, CITY_NAME, TEL, BUSEO_NAME AS BUSEO, JIKWI_NAME AS JIKWI, BASICPAY, SUDANG, BASICPAY+SUDANG AS SAL"
					  + "FROM  TBL_EMP E JOIN TBL_CITY C"
					  + "ON E.CITY_ID = C.CITY_ID"
					  + "JOIN TBL_BUSEO B"
					  + "ON E.BUSEO_ID = B.BUSEO_ID"
					  + "JOIN TBL_JIKWI J"
					  + "ON E.JIKWI_ID = J.JIKWI_ID"
					  + "WHERE JIKWI = '%s'", jikwi );
					  
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				MemberDTO dto = new MemberDTO();
				
				dto.setEmpid(rs.getInt("EMP_ID"));
				dto.setName(rs.getString("EMP_NAME"));
				dto.setSsn(rs.getString("SSN"));
				dto.setIbsadate(rs.getString("IBSADATE"));
				dto.setCity(rs.getString("CITY_NAME"));
				dto.setTel(rs.getString("TEL"));
				dto.setBuseo(rs.getString("BUSEO"));
				dto.setJikwi(rs.getString("JIKWI"));
				dto.setBasicpay(rs.getInt("BASICPAY"));
				dto.setSudang(rs.getInt("SUDANG"));
				dto.setSal(rs.getInt("BAISCPAY"), rs.getInt("SUDANG"));
				
				result.add(dto);
				
			}
			
			rs.close();
			stmt.close();
			
			return result;
		}
		
		
		
		// 데이터 수정 담당 메소드 → 매개변수의 유형 check!!! (찾는 메소드와 수정하는 메소드는 따로따로다.. 주의해야함!!!!!)
		public int modify(MemberDTO dto) throws SQLException
		{
			int result = 0;
			Statement stmt = conn.createStatement();
			String sql = String.format("UPDATE TBL_EMP SET EMP_NAME = '%s', SSN = '%s', IBSADATE = '%s', CITY_NAME = '%s', TEL = '%s', BUSEO = '%s', JIKWI = '%s', BASICPAY = %d, SUDANG = %d, SAL = %d WEHRE EMP_ID = %d"
								, dto.getName(), dto.getSsn(), dto.getIbsadate(), dto.getCity(), dto.getTel(), dto.getBuseo(), dto.getJikwi(), dto.getBasicpay(), dto.getSudang(), dto.getSal(), dto.getEmpid());
			result = stmt.executeUpdate(sql);
			stmt.close();
			
			
			return result;
		}
		
		
		
		// 데이터 삭제 담당 메소드
		public int remove(int empid) throws SQLException
		{
			int result = 0;
			
			Statement stmt = conn.createStatement();
			String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", empid);
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








































































































































































































