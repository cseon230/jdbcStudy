/*==================
 * DBConn.java
 * -try ~ catch
===================*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn_copy
{
	private static Connection dbConn; // DB연결 변수 생성
	
	public static Connection getConnection() // DB연결 메소드. Conncetion 타입을 반환해줘야 한다.
	{
		
		if (dbConn == null) 
		{
			try
			{
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd); // dbConn 변수에 DriverManger를 연결하고 url user pwd 를 전달해서 데이터베이스에 접속시킴 
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		return dbConn; // 
	}
	
	public static Connection getConnection(String url, String user, String pwd) // 메소드 오버로딩
	{
		if (dbConn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
				
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		return dbConn;
	}
	
	
	public static void close() // 데이터베이스 연결 종료 메소드
	{
		if (dbConn != null)
		{
			try
			{
				if (!dbConn.isClosed())
				{
					dbConn.close();
				}
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		dbConn = null;
	}
}







































