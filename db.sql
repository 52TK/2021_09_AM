#DB 삭제/생성/사용
DROP DATABASE IF EXISTS dy;
CREATE DATABASE dy;
USE dy;

# 게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(255) NOT NULL,
    `body` LONGTEXT NOT NULL
);

# 게시물 데이터 추가 
INSERT INTO article 
SET regDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article 
SET regDate = NOW(),
title = '제목3',
`body` = '내용3';

INSERT INTO article 
SET regDate = NOW(), 
title = CONCAT('제목__',RAND()), 
`body` = CONCAT('내용__',RAND());

#글 갯수 2배
INSERT INTO article (regDate,title,`body`)
SELECT NOW(), CONCAT('제목_',RAND()), CONCAT('내용_',RAND())
FROM article;

SELECT * FROM article;

SELECT * FROM article ORDER BY id DESC LIMIT 0, 20

SELECT COUNT(*) FROM article;

DELETE FROM article
WHERE id > 200;

DELETE FROM article
WHERE id = 434 OR id = 433;
#위와 동일
DELETE FROM article
WHERE id IN (434, 433);

 # articleRows.get(1).get("body")
