SELECT * FROM BOARD WHERE BANUM!=1 ORDER BY BGROUP DESC, BSTEP;
SELECT * FROM (SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD WHERE BANUM!=1 ORDER BY BGROUP DESC, BSTEP;) A) WHERE RN BETWEEN 1 AND 10;
SELECT * FROM BOARD WHERE BANUM=1 ORDER BY BNUM DESC;
SELECT * FROM BOARDA;

DROP SEQUENCE BOARD_SEQ;
CREATE SEQUENCE BOARD_SEQ;

UPDATE BOARD SET BNAME='���', BDATE=SYSDATE, BTITLE='�����', BCONTENT='�����', BFILE='nonono.jpg' WHERE BNUM='1';
UPDATE BOARD SET BSTEP = BSTEP+1 WHERE BGROUP=1 AND BSTEP>0;
UPDATE BOARD SET BHIT = BHIT+1 WHERE BNUM=1;

SELECT * FROM BOARD WHERE BNUM=1;
SELECT COUNT(*) COUNT FROM BOARD;

SELECT * FROM BOARD WHERE BANUM!=1 AND (BTITLE LIKE '%'||''||'%' OR BCONTENT LIKE '%'||''||'%') ORDER BY BGROUP DESC, BSTEP;

INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, 3, 'jang', '�����', '192.168.10.1', '16/01/01', 0, '����', '�����̴�', BOARD_SEQ.NEXTVAL, 0, 0, 'noImage.jpg');

INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, 1, 'jang', '�����', '192.168.10.1', '16/01/01', 0, '��������������������������������������������������������', '�����̴�', BOARD_SEQ.NEXTVAL, 0, 0, 'noImage.jpg');
INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, 2, 'jang', '�����', '192.168.10.1', '16/01/01', 0, 'bebest', '�����̴�', BOARD_SEQ.NEXTVAL, 0, 0, 'noImage.jpg');

DELETE FROM BOARD;

COMMIT;