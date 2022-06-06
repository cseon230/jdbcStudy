/*====================
 *  MemberDTO.java
 ====================*/
/*
EMP_ID   NOT NULL NUMBER(5)    
EMP_NAME          VARCHAR2(30) 
SSN               CHAR(14)     
IBSADATE          DATE         
CITY_ID           NUMBER(5)    
TEL               VARCHAR2(20) 
BUSEO_ID          NUMBER(5)    
JIKWI_ID          NUMBER(5)    
BASICPAY          NUMBER(10)   
SUDANG            NUMBER(10)
*/

package com.test;

public class MemberDTO_copy
{
	// 주요 속성 구성
	private int empId, basicPay, sudang, pay;
	private String cityName, buseoName, jikwiName, empName, ssn, ibsaDate, tel;
	
	// getter / setter 구성
	// setter : 변수에 값을 대입
	// getter : 변수값을 꺼내오기
	public int getEmpId()
	{
		return empId;
	}
	public void setEmpId(int empId)
	{
		this.empId = empId;
	}
	public int getBasicPay()
	{
		return basicPay;
	}
	public void setBasicPay(int basicPay)
	{
		this.basicPay = basicPay;
	}
	public int getSudang()
	{
		return sudang;
	}
	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}
	public int getPay()
	{
		return pay;
	}
	public void setPay(int pay)
	{
		this.pay = pay;
	}
	public String getCityName()
	{
		return cityName;
	}
	public void setCityName(String cityName)
	{
		this.cityName = cityName;
	}
	public String getBuseoName()
	{
		return buseoName;
	}
	public void setBuseoName(String buseoName)
	{
		this.buseoName = buseoName;
	}
	public String getJikwiName()
	{
		return jikwiName;
	}
	public void setJikwiName(String jikwiName)
	{
		this.jikwiName = jikwiName;
	}
	public String getEmpName()
	{
		return empName;
	}
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	public String getSsn()
	{
		return ssn;
	}
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	public String getIbsaDate()
	{
		return ibsaDate;
	}
	public void setIbsaDate(String ibsaDate)
	{
		this.ibsaDate = ibsaDate;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}

}
