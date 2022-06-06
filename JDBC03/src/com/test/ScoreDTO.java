/*=================
 * ScoreDTO.java
 =================*/

// DTO(Data Transfer Object)
// 주요 속성 구성 (information hiding 을 위해 private 으로 구성)
// private 으로 구성하기 때문에 메소드를 통해서만 접근 가능.
// getter setter 메소드 구성

package com.test;

public class ScoreDTO
{
	private String sid, name;
	private int kor, eng, mat, tot;
	private double avg;
	
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
	public int getKor()
	{
		return kor;
	}
	public void setKor(int kor)
	{
		this.kor = kor;
	}
	public int getEng()
	{
		return eng;
	}
	public void setEng(int eng)
	{
		this.eng = eng;
	}
	public int getMat()
	{
		return mat;
	}
	public void setMat(int mat)
	{
		this.mat = mat;
	}
	public int getTot()
	{
		return tot;
	}
	public void setTot(int kor, int eng, int mat)
	{
		this.tot = kor + eng + mat;
	}
	public double getAvg()
	{
		return avg;
	}
	public void setAvg(int kor, int eng, int mat)
	{
		this.avg = (kor + eng + mat) / 3.0;
	}
	
	

}
