SELECT USER
FROM DUAL;
--==>> SCOTT

DROP SEQUENCE MEMBERSEQ;

DROP TABLE TBL_MEMBER PURGE;
--==>> Table TBL_MEMBER��(��) �����Ǿ����ϴ�.


--�� �ǽ� ���̺� ����
CREATE TABLE TBL_MEMBER
( SID  NUMBER
, NAME VARCHAR2(30)
, TEL  VARCHAR2(60)
, CONSTRAINT MEMBER_SID_PK PRIMARY KEY(SID)
);
--==>> Table TBL_MEMBER��(��) �����Ǿ����ϴ�.


--�� ���� ������ �Է�
INSERT INTO TBL_MEMBER(SID, NAME, TEL) VALUES(3, '�Ӽҹ�', '010-3333-3333');
--==>> 1 �� ��(��) ���ԵǾ����ϴ�.

--�� Ȯ��
SELECT *
FROM TBL_MEMBER;
--==>> 1	ȫ�浿	010-1111-1111

COMMIT;
--==>> Ŀ�� �Ϸ�.


--�� �ڹٿ��� Test003.java ���� �� �ٽ� Ȯ��
SELECT *
FROM TBL_MEMBER;
/*
1	ȫ�浿	010-1111-1111
2	�ּ���	010-1111-1111
*/

--�� �ڹٿ��� Test004.java ���� �� �ٽ� Ȯ��
SELECT *
FROM TBL_MEMBER;
/*
3	������	010-3333-3333
1	ȫ�浿	010-1111-1111
2	�ּ���	010-1111-1111
4	���̻�	010-4444-4444
5	������	010-5555-5555
*/
--==>> 3, 4, 5 ������ �Է� �Ϸ�~!!




--�� ��ȸ ������ �غ�
SELECT SID, NAME, TEL
FROM TBL_MEMBER
ORDER BY SID;
/*
1	ȫ�浿	010-1111-1111
2	�ּ���	010-1111-1111
3	������	010-3333-3333
4	���̻�	010-4444-4444
5	������	010-5555-5555
*/

-->  �� �� ����
SELECT SID, NAME, TEL FROM TBL_MEMBER ORDER BY SID
;
-- ������ �� �ٷ� �����ϰ�, �����ݷ��� �Ʒ��� ������ ����������.






















































