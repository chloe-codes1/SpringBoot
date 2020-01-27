create table board(
    board_num number(15) unique,
    board_parent number(15) default 0,
    board_userid varchar2(50),
    board_title varchar2(100),
    board_content varchar2(100),
    board_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    board_cnt number default 0,
    board_img varchar2(100) default null
);    




create table Board2(
    userid VARCHAR2(20),
    boardno NUMBER(4) PRIMARY key,
    boardtitle VARCHAR2(70),
    regdate DATE,
    boardpw VARCHAR2(100),
    imagesrc VARCHAR2(1000),
    countp NUMBER(4),
    contentp VARCHAR2(2000)
);

create table BoardList1(
    userid VARCHAR2(20),
    boardno NUMBER(4) PRIMARY key,
    boardtitle VARCHAR2(70),
    regdate DATE,
    boardpw VARCHAR2(100),
    imagesrc VARCHAR2(1000),
    filesrc VARCHAR2(1000),
    countp NUMBER(4),
    contentp VARCHAR2(2000),
    originno NUMBER(4),
    groupord NUMBER(4),
    grouplayer NUMBER(4)
);

create table BoardList(
    userid VARCHAR2(20),
    boardno NUMBER(4) PRIMARY key,
    boardtitle VARCHAR2(70),
    regdate DATE,
    boardpw VARCHAR2(100),
    imagesrc VARCHAR2(1000),
    filesrc VARCHAR2(1000),
    countp NUMBER(4),
    contentp VARCHAR2(2000)
);
