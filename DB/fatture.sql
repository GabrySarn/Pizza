-- --------------------------------------------------------
-- Host:                         localhost
-- Versione server:              10.4.21-MariaDB-log - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            12.4.0.6659
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dump della struttura di tabella pizzadb.fatture
DROP TABLE IF EXISTS `fatture`;
CREATE TABLE IF NOT EXISTS `fatture` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Richiesta` varchar(50) DEFAULT NULL,
  `Prezzo` float DEFAULT NULL,
  `Mancia` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella pizzadb.fatture: ~10 rows (circa)
DELETE FROM `fatture`;
INSERT INTO `fatture` (`Id`, `Richiesta`, `Prezzo`, `Mancia`) VALUES
	(122, '"Vorrei una focaccia con sopra il salame"', 7, 1.31),
	(123, '"Una salsiccia senza mozza perfavore"', 7, 4.48),
	(124, '"Una pizza coi funghi senza mozzarella"', 6, 0.53),
	(125, '"Pizza margherita senza salsa, che non mi piace"', 5, 0.56),
	(126, '"Una pizza con il salame grazie!"', 0, 0),
	(127, '"Vorrei solo una focaccia"', 4, 3.45),
	(128, '"Una pizza coi funghi senza mozzarella"', 6, 4.09),
	(129, '"Vorrei una focaccia con sopra la salsiccia"', 0, 0),
	(130, '"Pizza con il salame bianca perfavore!"', 6, 1.84),
	(131, '"Vorrei una marinara, grazie!"', 5, 1.01);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
