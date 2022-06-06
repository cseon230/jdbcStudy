/*==========================
 	  DBConnBackup.java
 ==========================*/

package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnBackup
{
	private static Connection dbConn;
	
	public static Connection getConnection()
	{
		if (dbConn == null)
		{
			try
			{
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				String user = "scott";
				String pwd = "tiger";
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e)
			{
				System.out.println(e.toString()); // e.toString() : 에러의 Exception 내용과 원인을 출력한다
			}
		}
		
		return dbConn;
	}
	public static Connection getConnection(String url, String user, String pwd)
	{
		if (dbConn == null)
		{
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				dbConn = DriverManager.getConnection(url, user, pwd);
			} catch (Exception e)
			{
				System.out.println(e.toString()); // e.toString() : 에러의 Exception 내용과 원인을 출력한다	 
				
				//-- 오라클 연결 실패 시 오류 메세지 출력 부분 반드시 작성..!!
			}
		}
		return dbConn;
	}
	
	public static void close()
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
