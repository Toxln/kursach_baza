SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `coefficient`;
CREATE TABLE IF NOT EXISTS `coefficient`
(
    `idcoefficient` int NOT NULL AUTO_INCREMENT,
    `id_user`       int NOT NULL,
    `coefficient`   float NOT NULL,
    `idmark`        int NOT NULL,
    PRIMARY KEY (`idcoefficient`),
    KEY `mark` (`idmark`),
    KEY `id_user` (`id_user`),
    FOREIGN KEY (`idmark`)
        REFERENCES `mark` (`idmark`)
        ON DELETE CASCADE,
    FOREIGN KEY (`id_user`)
        REFERENCES `user` (`id_user`)
        ON DELETE CASCADE
) DEFAULT CHARSET = utf8;

INSERT INTO `coefficient`
VALUES (1, 1, 1.4, 1),
       (2, 1, 1.6, 2),
       (3, 1, 1.2, 3),
       (4, 1, 1.2, 4);

SET FOREIGN_KEY_CHECKS = 1;
