-- 데이터베이스 생성
-- create database 데이터베이스명  default  character set utf8;
create database board  default  character set utf8;

-- 계정삭제
-- drop user 계정명;

-- 계정생성 및 권한부여
-- create user '계정명'@'localhost'  identified by 비번;
-- grant all privileges on  데이터베이스명.*  to '계정명'@'localhost';
-- grant all privileges on  데이터베이스명.*  to '계정명'@'%';

create user 'gangnam'@'localhost'  identified by 'asdf111';
create user 'gangnam'@'%'  		   identified by 'asdf111';
grant all privileges on  board.*  to 'gangnam'@'localhost';
grant all privileges on  board.*  to 'gangnam'@'%';

DROP TABLE member;

CREATE TABLE member(
	memberno	int	auto_increment   NOT NULL primary key,
	memberid	varchar(50)	NOT NULL,
	meberpwd	varchar(50)	NOT NULL,
	mebername	varchar(50)	NOT NULL,
	email		varchar(50)	NOT NULL,
	regdate		datetime	NOT NULL,
	grade		int	  default 1	NOT NULL 
)engine=InnoDB default character set=utf8;

/* ALTER TABLE 테이블명  
	ADD CONSTRAINT   제약조건명 제약조건 (컬럼명); */
-- unique제약조건추가
ALTER TABLE member ADD CONSTRAINT   member_memberid_uk     unique(memberid);

-- check제약조건추가
ALTER TABLE member ADD CONSTRAINT  member_grade_ck     	
check (grade in (1,2,3,4,999));

-- 컬럼에 주석문추가
-- ALTER TABLE 테이블명 MODIFY 컬럼명 타입 comment '내용'
ALTER TABLE  member  MODIFY grade int COMMENT '회원등급.기본1. 1(정상),2(강퇴),3(탈퇴),4(휴면),999(관리자)';


-- sample데이터입력
insert into member(memberid,meberpwd,mebername,email,regdate,grade)
values('adminid','1234','관리자','admin@test.com','2022-01-01 00:00:00',999);

insert into member(memberid,meberpwd,mebername,email,regdate,grade)
values('kimid','1234','김구','kim9@naver.com','2022-07-01 00:00:00',1);
insert into member(memberid,meberpwd,mebername,email,regdate,grade)
values('leeid','1234','이순신','leesh@naver.com','2022-07-10 00:00:00',1);
insert into member(memberid,meberpwd,mebername,email,regdate,grade)
values('hongid','1234','홍길동','honggd@nate.com','2022-07-29 00:00:00',1);

commit;

select memberno,memberid,meberpwd,mebername,email,regdate,grade
from   member;

