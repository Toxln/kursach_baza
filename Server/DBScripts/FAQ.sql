SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `faq`;

CREATE TABLE IF NOT EXISTS `faq`
(
    `idfaq`        int         NOT NULL AUTO_INCREMENT,
    `question`     varchar(100) NOT NULL,
    `answer`       varchar(100) NOT NULL,
    `user_id_user` int         NOT NULL,
    PRIMARY KEY (`idfaq`),
    FOREIGN KEY (`user_id_user`)
        REFERENCES `user` (`id_user`)
        ON DELETE CASCADE
) DEFAULT CHARSET = utf8;

INSERT INTO `faq`
VALUES (1, 'где просмотреть графики?', 'вкладка производители', 1),
       (2, 'где пополнить счет?', 'вкладка профиль', 1),
       (3, 'что отображает график?', 'процент продуктов производителя от всех на рынке', 1),
       (4, 'сколько всего тем в приложении?', 'пока что 2: темная и светлая',1);
SET FOREIGN_KEY_CHECKS = 1;