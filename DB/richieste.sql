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

-- Dump della struttura di tabella pizzadb.richieste
DROP TABLE IF EXISTS `richieste`;
CREATE TABLE IF NOT EXISTS `richieste` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Richiesta` varchar(50) DEFAULT NULL,
  `Pizza` varchar(50) DEFAULT NULL,
  `Prezzo` double DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- Dump dei dati della tabella pizzadb.richieste: ~32 rows (circa)
DELETE FROM `richieste`;
INSERT INTO `richieste` (`Id`, `Richiesta`, `Pizza`, `Prezzo`) VALUES
	(97, '"Vorrei solo una focaccia"', 'Pizza Normale', 4),
	(98, '"Vorrei una marinara, grazie!"', 'Pizza Normale Salsa', 5),
	(99, '"Pizza margherita senza salsa, che non mi piace"', 'Pizza Normale Mozzarella', 5),
	(100, '"La più classica delle margherite!" ', 'Pizza Normale Salsa Mozzarella', 6),
	(101, '"Vorrei una focaccia con sopra la salsiccia"', 'Pizza Normale Salsiccia', 5),
	(102, '"Una salsiccia senza mozza perfavore"', 'Pizza Normale Salsa Salsiccia', 6),
	(103, '"Salsiccia senza salsa, con mozzarella"', 'Pizza Normale Mozzarella Salsiccia', 6),
	(104, '"Vorrei una pizza con la salsiccia"', 'Pizza Normale Salsa Mozzarella Salsiccia', 8),
	(105, '"Vorrei una focaccia con sopra il salame"', 'Pizza Normale Salame', 5),
	(106, '"Una salame, la mozzarella non è invitata"', 'Pizza Normale Salsa Salame', 6),
	(107, '"Pizza con il salame bianca perfavore!"', 'Pizza Normale Mozzarella Salame', 6),
	(108, '"Una pizza con il salame grazie!"', 'Pizza Normale Salsa Mozzarella Salame', 8),
	(109, '"Vorrei una focaccia con sopra i funghi" ', 'Pizza Normale Funghi', 5),
	(110, '"Una pizza coi funghi senza mozzarella"', 'Pizza Normale Salsa Funghi', 6),
	(111, '"Pizza con i funghi bianca perfavore!"', 'Pizza Normale Mozzarella Funghi', 6),
	(112, '"Fammi una pizza ai funghi"', 'Pizza Normale Salsa Mozzarella Funghi', 8),
	(113, '"Vorrei una focaccia con sopra i peperoni"', 'Pizza Normale Peperoni', 5),
	(114, '"Una peperoni senza mozzarella, veloce!"', 'Pizza Normale Salsa Peperoni', 6),
	(115, '"Peperoni senza salsa, con mozzarella"', 'Pizza Normale Mozzarella Peperoni', 6),
	(116, '"Vorrei una pizza con i peperoni"', 'Pizza Normale Salsa Mozzarella Peperoni', 8),
	(117, '"Vorrei una focaccia con sopra le olive"', 'Pizza Normale Olive', 5),
	(118, '"Una Olive, non mi piace la mozzarella"', 'Pizza Normale Salsa Olive', 6),
	(119, '"Olive bianca grazie!"', 'Pizza Normale Mozzarella Olive', 6),
	(120, '"Pizza alle olive per me"', 'Pizza Normale Salsa Mozzarella Olive', 8),
	(121, '"Vorrei una focaccia con sopra le cipolle"', 'Pizza Normale Cipolle', 5),
	(122, '"Una cipolle, leva la mozzarella"', 'Pizza Normale Salsa Cipolle', 6),
	(123, '"Togli la salsa e fammene una con le cipolle"', 'Pizza Normale Mozzarella Cipolle', 6),
	(124, '"Una bella pizza alle cipolle"', 'Pizza Normale Salsa Mozzarella Cipolle', 8),
	(125, '"Vorrei una focaccia con sopra le melanzane"', 'Pizza Normale Melanzane', 5),
	(126, '"Una pizza bianca con le melanzane"', 'Pizza Normale Salsa Melanzane', 6),
	(127, '"Una melanzane senza mozzarella, sbrigati."', 'Pizza Normale Mozzarella Melanzane', 6),
	(128, '"Una bellissima pizza con le melanzane"', 'Pizza Normale Salsa Mozzarella Melanzane', 8);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
