DROP TABLE MEMBER;
DROP TODO MEMBER;


CREATE TABLE "MEMBER" (
	"MEMBER_NO"	NUMBER	CONSTRAINT "PK_MEMBER_NO" PRIMARY KEY,
	"MEMBER_ID"	VARCHAR2(50) CONSTRAINT "NN_MEMBER_ID" NOT NULL,
	"MEMBER_PW"	VARCHAR2(100) CONSTRAINT "NN_MEMBER_PW"	NOT NULL,
	"MEMBER_NICKNAME" VARCHAR2(30) CONSTRAINT "NN_MEMBER_NICKNAME" NOT NULL,
	"ENROLL_DATE" DATE DEFAULT SYSDATE NOT NULL,
	"MEMBER_DEL_FL"	CHAR(1) DEFAULT 'N' NOT NULL
);


COMMENT ON COLUMN "MEMBER"."MEMBER_NO" IS '회원 번호(SEQ_MEMBER_NO)';

COMMENT ON COLUMN "MEMBER"."MEMBER_ID" IS '회원 아이디';

COMMENT ON COLUMN "MEMBER"."MEMBER_PW" IS '회원 비밀번호';

COMMENT ON COLUMN "MEMBER"."MEMBER_NICKNAME" IS '회원 이름(별명)';

COMMENT ON COLUMN "MEMBER"."ENROLL_DATE" IS '회원 가입일';

COMMENT ON COLUMN "MEMBER"."MEMBER_DEL_FL" IS '탈퇴여부(N:탈퇴X, Y:탈퇴O)';


-- 탈퇴여부 CHECK 제약 조건
ALTER TABLE "MEMBER" ADD CONSTRAINT "CH_MEMBER_DEL_FL" 
CHECK("MEMBER_DEL_FL" IN ('N', 'Y'));

-- 시퀀스 생성
CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE;

-- 멤버 계정 추가
INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01', 'pass01', '유저일', DEFAULT, DEFAULT);

INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02', 'pass02', '유저이', DEFAULT, DEFAULT);

INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03', 'pass04', '유저삼', DEFAULT, DEFAULT);

INSERT INTO "MEMBER"
VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user04', 'pass04', '유저사', DEFAULT, DEFAULT);

COMMIT;

-------------------------------------------------------------------------------------
--투두 리스트 생성


CREATE TABLE "TODO" (
	"TODO_NO" NUMBER CONSTRAINT "PK_TODO_NO" PRIMARY KEY,
	"TODO_TITLE" VARCHAR2(300) CONSTRAINT "NN_TODO_TITLE" NOT NULL,
	"TODO_MEMO"	VARCHAR2(500) ,
	"TODO_DATE" DATE DEFAULT SYSDATE NOT NULL,
	"TODO_DEL_FL" CHAR(1) DEFAULT 'N' NOT NULL,
	"MEMBER_NO"	NUMBER	CONSTRAINT "NN_MEMBER_NO" NOT NULL
);

COMMENT ON COLUMN "TODO"."TODO_NO" IS '투두 번호(SEQ_TODO_NO)';

COMMENT ON COLUMN "TODO"."TODO_TITLE" IS '투두 제목';

COMMENT ON COLUMN "TODO"."TODO_MEMO" IS '투두 메모내용';

COMMENT ON COLUMN "TODO"."TODO_DATE" IS '투두 생성일';

COMMENT ON COLUMN "TODO"."TODO_DEL_FL" IS '삭제 여부(N:삭제X, Y:삭제O)';

COMMENT ON COLUMN "TODO"."MEMBER_NO" IS '작성자 회원 번호';

-- 외래키 설정
ALTER TABLE TODO ADD CONSTRAINT MEMBER_NO_FK FOREIGN KEY (MEMBER_NO)
REFERENCES MEMBER;

-- 시퀀스 생성
CREATE SEQUENCE SEQ_TODO_NO NOCACHE;

-- 투두 리스트 가져오기
INSERT INTO "TODO"
VALUES(SEQ_TODO_NO.NEXTVAL, 'TODO1', 'todo1',  DEFAULT, DEFAULT, 1);

COMMIT;


