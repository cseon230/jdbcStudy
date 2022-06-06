/*==================
 * MemberDTO.java
 ==================*/


package com.test;

public class MemberDTO
{
	
	
	// 주요 속성 구성
	// 어떤게 필요할까..
	/*이름 : 김정용
주민등록번호(yymmdd-nnnnnn) : 960608-2234567
입사일(yyyy-mm-dd) : 2019-06-08
지역(강원/경기/경남/경북/부산/서울/인천/전남/전북/제주/충남/충북/) : 경기
전화번호 : 010-2731-3153
부서(개발부/기획부/영업부/인사부/자재부/총무부/홍보부/) : 개발부
직위 (사장/전무/상무/이사/부장/차장/과장/대리/사원/) : 대리
기본급(최소 1800000 이상) : 5000000
수당 : 2000000

전체 인원 : xx명
사번   이름   주민번호   입사일   지역   전화번호   부서   직위    기본급   수당   급여

*/
	
	private String name, ssn, ibsadate, city, tel, jikwi, buseo;
	private int empid, basicpay, sudang, sal;
	
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getSsn()
	{
		return ssn;
	}
	public void setSsn(String ssn)
	{
		this.ssn = ssn;
	}
	public String getIbsadate()
	{
		return ibsadate;
	}
	public void setIbsadate(String ibsadate)
	{
		this.ibsadate = ibsadate;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		switch (city)
		{
		case "강원":
			this.city = "1";
			break;
		case "경기":
			this.city = "2";
			break;
		case "경남":
			this.city = "3";
			break;
		case "경북":
			this.city = "4";
			break;
		case "부산":
			this.city = "5";
			break;
		case "서울":
			this.city = "6";
			break;
		case "인천":
			this.city = "7";
			break;
		case "전남":
			this.city = "8";
			break;
		case "전북":
			this.city = "9";
			break;
		case "제주":
			this.city = "10";
			break;
		case "충남":
			this.city = "11";
			break;
		case "충북":
			this.city = "12";
			break;
		default:
			break;
		}
		//this.city = city;
	}
	public String getTel()
	{
		return tel;
	}
	public void setTel(String tel)
	{
		this.tel = tel;
	}
	public String getBuseo()
	{
		return buseo;
	}
	public void setBuseo(String buseo)
	{
		switch (buseo)
		{
		case "개발부":
			this.buseo = "1";
			break;
		case "기획부":
			this.buseo = "2";
			break;
		case "영업부":
			this.buseo = "3";
			break;
		case "인사부":
			this.buseo = "4";
			break;
		case "자재부":
			this.buseo = "5";
			break;
		case "총무부":
			this.buseo = "6";
			break;
		case "홍보부":
			this.buseo = "7";
			break;
		default:
			break;
		}
		//this.buseo = buseo;
	}
	public String getJikwi()
	{
		return jikwi;
	}
	public void setJikwi(String jikwi)
	{
		switch (jikwi)
		{
		case "사장":
			this.jikwi = "1";
			break;
		case "전무":
			this.jikwi = "2";
			break;
		case "상무":
			this.jikwi = "3";
			break;
		case "이사":
			this.jikwi = "4";
			break;
		case "부장":
			this.jikwi = "5";
			break;
		case "차장":
			this.jikwi = "6";
			break;
		case "과장":
			this.jikwi = "7";
			break;
		case "대리":
			this.jikwi = "8";
			break;
		case "사원":
			this.jikwi = "9";
			break;
		default:
			break;
		}
		//this.jikwi = jikwi;
	}
	public int getEmpid()
	{
		return empid;
	}
	public void setEmpid(int empid)
	{
		this.empid = empid;
	}
	public int getBasicpay()
	{
		return basicpay;
	}
	public void setBasicpay(int basicpay)
	{
		this.basicpay = basicpay;
	}
	public int getSudang()
	{
		return sudang;
	}
	public void setSudang(int sudang)
	{
		this.sudang = sudang;
	}
	public int getSal()
	{
		return sal;
	}
	public void setSal(int basicpay, int sudang)
	{
		this.sal = basicpay + sudang;
	}
	
	
}












