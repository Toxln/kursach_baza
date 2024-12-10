SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `scholarship`;

CREATE TABLE IF NOT EXISTS `scholarship`
(
    `idscholarship` int   NOT NULL AUTO_INCREMENT,
    `scholarship`   int   NOT NULL,
    `coafficient`   float NOT NULL,
    PRIMARY KEY (`idscholarship`)
) DEFAULT CHARSET = utf8;

INSERT INTO `scholarship`
VALUES (1, 140, 1),
       (2, 160, 2),
       (3, 120, 3),
       (4, 120, 4);

SET FOREIGN_KEY_CHECKS = 1;
