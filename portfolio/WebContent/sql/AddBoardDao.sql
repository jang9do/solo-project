CREATE SEQUENCE ADDBOARD_SEQ;
SELECT * FROM ADDBOARD WHERE BNUM = 30 ORDER BY ABDATE DESC;
INSERT INTO ADDBOARD VALUES(ADDBOARD_SEQ.NEXTVAL, 1, 'jang', '��Ʈ���', SYSDATE);
COMMIT;

SELECT * FROM ADDBOARD WHERE BNUM = 1;