/*=================================
 * ScoreDTO.java
 * - 데이터 보관 및 전송 전용 객체
==================================*/

package com.test;

public class ScoreDTO
{
	// 주요 속성 구성
	private String sid, name;   // 번호, 이름 sid를 int가 아니라 string으로 한 이유는, 연산을 할 것 아니기 때문에 string으로 하는게 더 편하다.
	private int kor, eng, mat;  // 국어 영어 수학 점수
	private int tot, rank;      // 총점, 석차
	private double avg;         // 평균
	
	// getter / setter 구성 
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
	public void setTot(int tot)
	{
		this.tot = tot;
	}
	public int getRank()
	{
		return rank;
	}
	public void setRank(int rank)
	{
		this.rank = rank;
	}
	public double getAvg()
	{
		return avg;
	}
	public void setAvg(double avg)
	{
		this.avg = avg;
	}
	
	
}
