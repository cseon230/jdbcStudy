/*==================
 * 	DBConn.java
 ==================*/
// ※ 싱글톤(Singleton) 디자인 패턴을 이용한 Database 연결 객체 생성 전용 클래스
// → DB 연결과정이 가장 부하가 크기 때문에
//	  한번 연결된 객체를 계속 사용하는 것이 좋지 않을까.....

// 디자인(=설계)패턴 : 계층 구조를 어떻게 짜느냐

// ★ 한 개의 클래스(java.sql.DriverManager)와
// ★ 두 개의 인터페이스(java.sql.Driver 와 java.sql.Connection)를 사용한다.

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn
{
	// 변수선언
	// Conn 입력 + ctrl + space  ==▶ import java.sql.Connection; 자동으로 삽입됨.
	private static Connection dbConn;
	// static을 쓴 이유는 dbConn 객체가 공유되어야 하기 때문
	// 여기에서 static은 탄생시점보단 "공유"의 개념으로 사용
	// 현재 dbConn은 추상적인 상태. 정의가 안되어있음.
	
	
	// 메소드 정의 ▶ 데이터베이스와 연결하는 메소드 정의
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		// 한 번 연결된 객체를 계속해서 사용할 것임.
		// 즉, 연결되지 않은 경우에만 연결을 시도하겠다는 의미
		// dbConn이 null일 때만 if문 실행
		// != null 이면 Connection이 되어있다는 거니까 dbConn을 return 해줘서 연결시켜야함.
		// == null 이면 연결이 안되어 있는 상태
		// → 싱글톤(디자인 패턴)
		if (dbConn == null)
		{
			String url = "jdbc:oracle:thin:@localhost:1521:xe";  // ★꼭 외우기!
			// localhost는 오라클 서버의 ip 주소를 기재하는 부분
			// 1521 은 오라클 리스너 포트
			// xe는 오라클 SID (Express Edition의 SID는 xe)
			
			String user = "scott"; // 오라클 사용자 계정 이름
			
			String pwd = "tiger"; // 오라클 사용자 계정 암호
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			dbConn = DriverManager.getConnection(url, user, pwd);
			// DriverManager import 문에 추가해줘야함. import java.sql.DriverManager;
			// 오라클 서버 실제 연결
			// 갖고 있는 인자값(매개변수)은 오라클주소, 계정명, 패스워드
		}
		
		return dbConn; 
		// Connection 타입의 결과를 반환해야 한다.
		// 클래스 변수로 선언한 Connection 타입의 dbConn; 을 return 값으로 해야한다.
	}
	
	// getConnection() 메소드의 오버로딩 → 연결
	public static Connection getConnection(String url, String user, String pwd) throws ClassNotFoundException, SQLException 
	{
		if (dbConn == null)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			dbConn = DriverManager.getConnection(url, user, pwd);
		}
		return dbConn;
	}
	
	// 연결 종료 메소드 정의
	public static void close() throws SQLException
	{
		// dbConn 이 null 이 아닐때 즉, 연결이 된 상태일때 종료하겠다.
		if (dbConn != null)
		{
			// 연결 객체 dbConn을 isClosed() 메소드를 통해 연결 상태 확인
			// 연결이 닫혀있는 경우는 true를 반환
			// 연결이 닫혀있지 않은 경우 false 를 반환
			if (!dbConn.isClosed())  // dbConn이 닫혀있지 "않다" → 연결이 된 상태 true면 if문 실행
			{
				dbConn.close();
				// 연결 객체의 close() 메소드 호출을 통해 연결 종료
			}
		}
		
		// forName , getConnection 메소드들 (글꼴이 기울어져 있는 메소드) ==▶ static 메소드들
		// 인스턴스 생성 안하고 바로 사용 OK
		dbConn = null; // 연결 객체 초기화
	}
}











































