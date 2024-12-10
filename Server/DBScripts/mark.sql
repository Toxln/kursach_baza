SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `mark`;

CREATE TABLE IF NOT EXISTS `mark`
(
    `idmark`      int NOT NULL,
    `maths`       int NOT NULL,
    `programming` int NOT NULL,
    `unity`       int NOT NULL,
    `course_work` int NOT NULL,
    PRIMARY KEY (`idmark`)
) DEFAULT CHARSET = utf8;

INSERT INTO `mark`
VALUES (1, 9, 8, 8, 8),
       (2, 9, 10, 10, 9),
       (3, 8, 7, 7, 7),
       (4, 6, 8, 6, 9);
SET FOREIGN_KEY_CHECKS = 1;

