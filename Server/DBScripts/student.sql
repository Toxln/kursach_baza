SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `student`;

CREATE TABLE IF NOT EXISTS `student`
(
    `idstudent` int NOT NULL AUTO_INCREMENT,
    `first_name` varchar(45) NOT NULL,
    `last_name` varchar(45) NOT NULL,
    `average_grade` float NOT NULL,
    `idmark` int NOT NULL,
    `idscholarship` int NOT NULL,
    `user_id_user` int NOT NULL,
    PRIMARY KEY (`idstudent`),
    FOREIGN KEY (`user_id_user`)
        REFERENCES `user` (`id_user`)
        ON DELETE CASCADE,
    FOREIGN KEY (`idmark`)
        REFERENCES `mark` (`idmark`)
        ON DELETE CASCADE,
    FOREIGN KEY (`idscholarship`)
        REFERENCES `scholarship` (`idscholarship`)
        ON DELETE CASCADE
) DEFAULT CHARSET = utf8;

INSERT INTO `student`
VALUES (1, 'Иванов', 'Михаил', 8.4, 1, 1, 1),
       (2, 'Петров', 'Геннадий', 9.5, 2, 2, 2),
       (3, 'Альсицкая', 'Вероника', 7.1, 3, 3, 3),
       (4, 'Савеноков', 'Виктор', 7.0, 4, 4, 4);

SET FOREIGN_KEY_CHECKS = 1;
