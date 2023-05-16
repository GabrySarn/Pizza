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
  `Totale` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella pizzadb.fatture: ~6 rows (circa)
DELETE FROM `fatture`;
INSERT INTO `fatture` (`Id`, `Richiesta`, `Prezzo`, `Mancia`, `Totale`) VALUES
	(7, 'Pizza Normale Salsa', 20, 20, 40),
	(8, 'Pizza Normale Salsa Mozzarella', 15, 5, 20),
	(9, 'Pizza Normale Salsa Mozzarella Funghi', 15, 5, 20),
	(10, 'Pizza Normale', 15, 5, 20),
	(11, 'Pizza Normale Salsa', 15, 5, 20),
	(12, '"Una pizza coi funghi per me grazie"', 15, 1.60221, 16.6022),
	(13, '"Vorrei solo una focaccia"', 15, 2.43494, 17.4349);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
