package com.test;

import java.util.ArrayList;
import java.util.Scanner;

public class ScoreProcess
{
	private ScoreDAO dao;
	
	public ScoreProcess()
	{
		dao = new ScoreDAO();
	}
	
	
	// 성적 입력 기능
	public void sungjukInsert()
	{
		try
		{
			// 데이터베이스 연결
			dao.connection();
			
			// 레코드 수 확인
			int count = dao.count();
			
			Scanner sc = new Scanner(System.in);
			
			do
			{
				System.out.println();
				System.out.printf("%d번 학생 성적 입력(이름 국어 영어 수학) : ", (++count));
				String name = sc.next();
				
				if (name.equals("."))
				{
					break;
				}
				
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// 입력받은 값을 토대로 ScoreDTO 에 넘겨주기
				ScoreDTO dto = new ScoreDTO();
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				int result = dao.add(dto); // add 메소드는 적용된 행의 갯수를 int타입으로 반환함
				
				if (result > 0)
				{
					System.out.println("성적 입력이 완료되었습니다.");
				}
			} while (true);
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	
	
	
	// 성적 전체 출력 메소드
	public void sungjukSelectAll()
	{
		try
		{
			dao.connection();
			
			int count = dao.count();
			
			System.out.println();
			System.out.printf("전체 인원 : %d명\n", count);
			System.out.println("번호   이름   국어   영어   수학   총점   평균   석차");
			
			for (ScoreDTO dto : dao.lists())
			{
				System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %5d\n"
								, dto.getSid(), dto.getName(), dto.getKor(), dto.getEng(), dto.getMat()
								, dto.getTot(), dto.getAvg(), dto.getRank() );
			}
			
			dao.close();
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	
	
	
	// 이름 검색 출력
	public void sungjukSearchName()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			
			System.out.print("검색할 이름 입력 : ");
			String name = sc.next();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arraList = dao.lists(name);
			
			if (arraList.size() > 0)
			{
				System.out.println("번호   이름   국어   영어   수학   총점   평균   석차");
				
				for (ScoreDTO dto : arraList)
				{
					System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %d\n"
							, dto.getSid(), dto.getName(), dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank() );
				}
			}
			else
			{
				System.out.println("검색 결과가 존재하지 않습니다.");
			}
			
			dao.close();
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	
	
	// 성적 수정
	public void sungjukUpdate()
	{
		try
		{
			// 수정할 대상의 번호 입력받기
			Scanner sc = new Scanner(System.in);
			System.out.print("수정할 대상의 번호를 입력하세요 : ");
			int sid = sc.nextInt();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				System.out.println("번호   이름   국어   영어   수학   총점   평균   석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank() );
				}
				
				System.out.println();
				System.out.print("수정 데이터 입력(이름 국어 영어 수학) : ");
				String name = sc.next();
				int kor = sc.nextInt();
				int eng = sc.nextInt();
				int mat = sc.nextInt();
				
				// dao.modify로 수정된 값을 넣어줘야 하는데 dto를 넘겨줘야하는데 아직 dto가 없음
				// sid로 넘겨줘야함. 왜냐면 modify 메소드에서 sid까지 꺼내 쓰고 있기 때문에
				ScoreDTO dto = new ScoreDTO();
				dto.setSid(String.valueOf(sid));
				dto.setName(name);
				dto.setKor(kor);
				dto.setEng(eng);
				dto.setMat(mat);
				
				
				// 구성된 dto를 넘겨주며 dao의 modify() 메소드 호출
				int result = dao.modify(dto);
				if (result > 0)
				{
					System.out.println("수정이 완료되었습니다.");
				}
				else
				{
					System.out.println("수정 대상이 존재하지 않습니다.");
				}
				
			}
			
			dao.close();
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
	
	
	
	
	// 성적 삭제 기능
	public void sungjukDelete()
	{
		try
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("삭제할 대상을 입력하세요 : ");
			int sid = sc.nextInt();
			
			dao.connection();
			
			ArrayList<ScoreDTO> arrayList = dao.lists(sid);
			
			if (arrayList.size() > 0)
			{
				// 수신된 내용 출력 
				System.out.println("번호   이름   국어   영어   수학   총점   평균   석차");
				
				for (ScoreDTO dto : arrayList)
				{
					System.out.printf("%3s  %4s  %4d  %5d  %5d  %5d  %5.1f  %d\n"
							, dto.getSid(), dto.getName()
							, dto.getKor(), dto.getEng(), dto.getMat()
							, dto.getTot(), dto.getAvg(), dto.getRank() );
				}
				
				System.out.print(">> 정말 삭제하시겠습니까?(Y/N)");
				String response = sc.next();
				
				if (response.equals("Y") || response.equals("y"))
				{
					int result = dao.remove(sid);
					if (result > 0)
					{
						System.out.println("삭제가 완료되었습니다.");
					}
				}
				else
				{
					System.out.println("취소되었습니다.");
				}
			}
			else
			{
				System.out.println("삭제할 대상이 존재하지 않습니다. 다시 확인해주세요.");
			}
			
			dao.close();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	
	
}








































