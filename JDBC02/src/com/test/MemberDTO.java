/*=========================
	  MemberDTO.java
===========================*/

// getter 값을 얻는 것
// setter 값을 채워넣는 것

// 별도의 연산이 필요한 프로그래밍이 아니므로
// sid를 String으로 구성해도 괜찮다.

package com.test;

public class MemberDTO
{
	// DTO(Data Transfer Object)
	
	// 주요 속성 구성 (information hiding을 위해 private으로 구성)
	// private으로 구성했기때문에 get set을 구성해주었다.
	// get set 이건 반드시 만들어야하는 약속이다!!!
		
	private String sid, name, tel;

	// private String sid, name, tel 을 입력 한 다음 getter setter를 만들어야 하는데,
	// 이클립스에서 자동으로 만드는걸 지원해주는데,
	// 오른쪽버튼 -> Source -> Generate Getter and Setter -> select All 누르고 OK 하면
	// 이클립스가 전부 알아서 만들어준다..... 와우..................
	
	public String getSid()
	{
		return sid;
	}

	public void setSid(String sid)
	{
		this.sid = sid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
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
