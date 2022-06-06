select user
from dual;
--==>> SCOTT


TRUNCATE TABLE TBL_MEMBER;
--==>> Table TBL_MEMBER이(가) 잘렸습니다.

DROP SEQUENCE MEMBERSEQ;

CREATE SEQUENCE MEMBERSEQ
NOCACHE;
--==>> Sequence MEMBERSEQ이(가) 생성되었습니다.


--○ 데이터 입력 쿼리문 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL)
VALUES(MEMBERSEQ.NEXTVAL, '이호석', '010-1111-1111');

--> 한 줄 구성
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(MEMBERSEQ.NEXTVAL, '김상기', '010-4444-4444')
;
--==>> --==>> 1 행 이(가) 삽입되었습니다.


--○ 인원 수 확인 쿼리문 구성
SELECT COUNT(*) AS COUNT
FROM TBL_MEMBER;
--> 한 줄 구성
SELECT COUNT(*) AS COUNT FROM TBL_MEMBER
;
--==>> 1


--○ 전체 리스트 조회 쿼리문 구성
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
--> 한 줄 구성
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;
--==>> 1	이호석	010-1111-1111

delete from tbl_member;

--○ 트랜잭션 처리. = 커밋
COMMIT;
--==>> 커밋 완료.
























































