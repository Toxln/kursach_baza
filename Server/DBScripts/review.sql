SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review`
(
    `idreview`    int         NOT NULL AUTO_INCREMENT,
    `id_user`     int         NOT NULL,
    `idstudent`   int         NOT NULL,
    `review_text` varchar(45) NOT NULL,
    PRIMARY KEY (`idreview`),
    FOREIGN KEY (`id_user`)
        REFERENCES `user` (`id_user`)
        ON DELETE CASCADE,
    FOREIGN KEY (`idstudent`)
        REFERENCES `student` (`idstudent`)
        ON DELETE CASCADE
) DEFAULT CHARSET = utf8;

INSERT INTO `review`
VALUES (1, 3, 6, 'хороший, мощный процессор'),
       (2, 2, 6, 'не стоит своих денег'),
       (3, 4, 6, 'через месяц вышел из строя'),
       (4, 1, 6, 'прекрасно работает');

SET FOREIGN_KEY_CHECKS = 1;
