SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`(
    `id_user` int NOT NULL AUTO_INCREMENT,
    `login` varchar(45) NOT NULL,
    `password` varchar(45) NOT NULL,
    `role` int DEFAULT NULL,
    `first_name` varchar(45) NOT NULL,
    `last_name` varchar(45) NOT NULL,
    `phone` varchar(45) NOT NULL,
    PRIMARY KEY (`id_user`)
) DEFAULT CHARSET=utf8;

INSERT INTO `user` VALUES
    (1,'admin','1242sda23',1,'Maxim','Anischenko','+3757140232'),
    (2,'pushka','4tdssdf',0,'Саша', 'Белый','+375442352434'),
    (3,'lolik','634ccve',0,'Леша', 'Черный','+375442634746'),
    (4,'ghost','rewvvsd3',0,'Толя', 'Вайлд','+375442343253'),
    (5,'sekiro', '132ds34', 0,'Джон', 'Силвер','+375452324466');

SET FOREIGN_KEY_CHECKS = 1;
