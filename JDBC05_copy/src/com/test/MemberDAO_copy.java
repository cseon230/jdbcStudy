/*=====================================
 * MemberDAO.java
 * - 데이터베이스 액션 처리 전용 클래스
 =====================================*/
// Data Access Object

package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.util.DBConn_copy;

public class MemberDAO_copy
{
	// DB연결 메소드
	private Connection conn; // Conneciton 인터페이스의 conn 변수 선언. Connection 인터페이스는 특정 데이터베이스와의 연결(세션)이다
	
	
	public Connection connection() // Conneciton 인터페이스 구현
	{
		conn = DBConn_copy.getConnection();
		return conn;
	}
	
	// DB연결 종료 메소드
	public void close()
	{
		DBConn_copy.close();
	}
	
	
	// 직원 정보 입력 메소드 INSERT => executeUpdate => insert한 행의 "갯수"를 반환 => 반환값은 int 타입 
	// 직원 정보를 입력하려면 setter.. 따라서, DTO를 매개변수로 받아야 한다.
	// 이름, 주민번호, 입사일, 지역, 전화번호, 부서명, 직위명, 기본급, 수당
	public int add(MemberDTO_copy dto) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO TBL_EMP(EMP_ID, EMP_NAME, SSN, IBSADATE"
							   + ", CITY_ID, TEL, BUSEO_ID, JIKWI_ID, BASICPAY, SUDANG)"
							   + " VALUES(EMPSEQ.NEXTVAL, '%s', '%s'"
							   + ", TO_DATE('%s', 'YYYY-MM-DD')"
							   + ", (SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME='%s')"
							   + ", '%s'"
							   + ", (SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME='%s')"
							   + ", (SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME='%s')"
							   + ", %d, %d)"
							   , dto.getEmpName(), dto.getSsn(), dto.getIbsaDate(), dto.getCityName()
							   , dto.getTel(), dto.getBuseoName(), dto.getJikwiName(), dto.getBasicPay()
							   , dto.getSudang());
		result = stmt.executeUpdate(sql); // executeUpdate는 insert,delete,update 한 레코드의 "갯수"를 반환하므로 int 형 변수에 담아야한다. 
		
		return result;
	}
	
	// 직원 전체 인원 수 => count(*) => select => executeQuery => ResultSet => while 반복문.. 
	public int memberCount() throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_EMP";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		return result;
	}
	
	
	// 검색 결과 직원 인원 수
	// EMP_ID = 1001;        => key:EMP_ID , value:1001
	// EMP_NAME = '이호석'   => key:EMP_NAME , value:'이호석'
	// BUSEO_NAME = '개발부' => key:BUSEO_NAME , value:'개발부'
	// JIKWI_NAME = '대리'   => key:JIKWI_NAME , value:'대리'
	public int memberCount(String key, String value) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = "";
		
		if (key.equals("EMP_ID")) // key가 EMP_ID면 value가 int형이지만 나머지는 전부 string타입
		{
			sql = String.format("SELECT COUNT(*) AS COUNT FROM EMPVIEW WHERE '%s' = %s", key, value); // sql에 넘겨줄땐 문자열형이 아닌 숫자타입으로 들어가야 하므로 '%s'가 아닌 %s만 쓴다.
		} 
		else
		{
			sql = String.format("SELECT COUNT(*) AS COUNT FROM EMPVIEW WHERE '%s' = '%s'", key, value); 
		}
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		return result;
	}
	
	
	// 직원 데이터 전체 조회 (사번/이름/부서/직위/급여내림차순)
	public ArrayList<MemberDTO_copy> lists(String key) throws SQLException
	{
		ArrayList<MemberDTO_copy> result = new ArrayList<MemberDTO_copy>();
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE"
							   + ", CITY_NAME, TEL, BUSEO_NAME, JIKWI_NAME"
							   + ", BASICPAY, SUDANG, PAY"
							   + " FROM EMPVIEW ORDER BY %s", key);
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO_copy dto = new MemberDTO_copy();
			
			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoName(rs.getString("BUSEO_NAME"));
			dto.setJikwiName(rs.getString("JIKWI_NAME"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			
			result.add(dto);
			// ArrayList의 add() 메소드는 인자로 전달된 객체를 리스트에 추가합니다.
		}
		rs.close();
		stmt.close();
		
		return result;
		
	}
	
	
	
	// 직원 데이터 검색 조회(사번/이름/부서/직위) 
	public ArrayList<MemberDTO_copy> searchLists(String key, String value) throws SQLException
	{
		ArrayList<MemberDTO_copy> result = new ArrayList<MemberDTO_copy>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "";
		
		if (key.equals("EMP_ID"))
		{
			sql = String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE"
					   + ", CITY_NAME, NVL(TEL, '번호없음') AS TEL"
					   + ", BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG, PAY"
					   + " FROM EMPVIEW WHERE %s = %s", key, value); // 사번 value는 int 타입이므로 sql에 넘겨줄때 문자열형식으로 넘겨주면 안된다. 따라서, %s
		} else
		{
			String.format("SELECT EMP_ID, EMP_NAME, SSN, IBSADATE"
					   + ", CITY_NAME, NVL(TEL, '번호없음') AS TEL"
					   + ", BUSEO_NAME, JIKWI_NAME, BASICPAY, SUDANG, PAY"
					   + " FROM EMPVIEW WHERE %s = '%s'", key, value); // 사번 외의 value는 String 타입이므로 문자형 형식으로 넘겨준다. 
		}
		
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			MemberDTO_copy dto = new MemberDTO_copy();
			
			dto.setEmpId(rs.getInt("EMP_ID"));
			dto.setEmpName(rs.getString("EMP_NAME"));
			dto.setSsn(rs.getString("SSN"));
			dto.setIbsaDate(rs.getString("IBSADATE"));
			dto.setCityName(rs.getString("CITY_NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setBuseoName(rs.getString("BUSEO_NAME"));
			dto.setJikwiName(rs.getString("JIKWI_NAME"));
			dto.setBasicPay(rs.getInt("BASICPAY"));
			dto.setSudang(rs.getInt("SUDANG"));
			dto.setPay(rs.getInt("PAY"));
			
			result.add(dto);
		}
		rs.close();
		stmt.close();
		
		return result;
				
	}
	
	
	// 지역 리스트 조회 (지역"들" 이므로 ArrayList 
	public ArrayList<String> searchCity() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();
		Statement stmt = conn.createStatement();
		String sql = "SELECT CITY_NAME FROM TBL_CITY";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			result.add(rs.getString("CITY_NAME"));
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 부서 리스트 조회
	public ArrayList<String> searchBuseo() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT BUSEO_NAME FROM TBL_BUSEO";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result.add(rs.getString("BUSEO_NAME"));
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	// 직위 리스트 조회
	public ArrayList<String> searchjikwi() throws SQLException
	{
		ArrayList<String> result = new ArrayList<String>();
		
		Statement stmt = conn.createStatement();
		
		String sql = "SELECT JIKWI_NAME FROM TBL_JIKWI";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result.add(rs.getString("JIKWI_NAME"));
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직위에 따른 최소 기본급 검색
	public int searchBasicPay(String jikwi) throws SQLException // 직위를 넘겨주면 최소 기본급을 반환
	{
		int result = 0;
		
		Statement stmt = conn.createStatement();
		
		String sql = String.format("SELECT MIN_BASICPAY FROM TBL_JIKWI WHERE JIKWI_NAME ='%s'", jikwi);
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			result = rs.getInt("MIN_BASICPAY");
		}
		rs.close();
		stmt.close();
		
		return result;
	}
	
	
	// 직원 데이터 수정 => Update => executeUpdate => 변경한 행의 "갯수" 반환 => int 
	public int modify(MemberDTO_copy dto) throws SQLException
	{
		
		int result = 0;
		Statement stmt = conn.createStatement();
		
		String sql = String.format("UPDATE TBL_EMP SET EMP_NAME='%s', SSN='%s'"
							   + ", IBSADATE=TO_DATE('%s', 'YYYY-MM-DD')"
							   + ", CITY_ID=(SELECT CITY_ID FROM TBL_CITY WHERE CITY_NAME='%s')"
							   + ", TEL = '%s'"
							   + ", BUSEO_ID=(SELECT BUSEO_ID FROM TBL_BUSEO WHERE BUSEO_NAME='%s')"
							   + ", JIKWI_ID=(SELECT JIKWI_ID FROM TBL_JIKWI WHERE JIKWI_NAME='%s')"
							   + ", BASICPAY=%d"
							   + ", SUDANG=%d"
							   + " WHERE EMP_ID = %d"
							   , dto.getEmpName(), dto.getSsn(), dto.getIbsaDate(), dto.getCityName(), dto.getTel()
							   , dto.getBuseoName(), dto.getJikwiName(), dto.getBasicPay(), dto.getSudang(), dto.getEmpId());
		result = stmt.executeUpdate(sql); // update한 행의 갯수 반환.
		
		stmt.close();
		
		return result;
	}
	
	
	
	// 직원 데이터 삭제
	public int remove(int empId) throws SQLException
	{
		int result = 0;
		Statement stmt = conn.createStatement();
		String sql = String.format("DELETE FROM TBL_EMP WHERE EMP_ID = %d", empId);
		result = stmt.executeUpdate(sql);
		stmt.close();
		
		return result;
	}
}



























































































