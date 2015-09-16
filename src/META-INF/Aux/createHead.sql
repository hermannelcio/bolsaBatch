CREATE TABLE `head` (
  `idhead` int(11) NOT NULL AUTO_INCREMENT,
  `tipreg` smallint(6) DEFAULT NULL,
  `nomarq` varchar(13) DEFAULT NULL,
  `codori` varchar(8) DEFAULT NULL,
  `datger` timestamp NULL DEFAULT NULL,
  `totreg` decimal(10,0) DEFAULT NULL,
  `codorihead` varchar(255) DEFAULT NULL,
  `datgerhead` datetime DEFAULT NULL,
  `nomarqhead` varchar(255) DEFAULT NULL,
  `tipreghead` bigint(20) DEFAULT NULL,
  `totregtrailer` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idhead`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
