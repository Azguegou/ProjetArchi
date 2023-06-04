-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 02 juin 2023 à 12:44
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `mediatheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonne`
--

DROP TABLE IF EXISTS `abonne`;
CREATE TABLE IF NOT EXISTS `abonne` (
  `numeroAbo` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `dateNaiss` date NOT NULL,
  PRIMARY KEY (`numeroAbo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `abonne`
--

INSERT INTO `abonne` (`numeroAbo`, `nom`, `dateNaiss`) VALUES(1, 'Leonard', '2014-12-25');
INSERT INTO `abonne` (`numeroAbo`, `nom`, `dateNaiss`) VALUES(2, 'Marie', '2004-09-30');
INSERT INTO `abonne` (`numeroAbo`, `nom`, `dateNaiss`) VALUES(3, 'Bernard', '1999-05-13');
INSERT INTO `abonne` (`numeroAbo`, `nom`, `dateNaiss`) VALUES(4, 'Torvald', '1966-05-07');

-- --------------------------------------------------------

--
-- Structure de la table `dvd`
--

DROP TABLE IF EXISTS `dvd`;
CREATE TABLE IF NOT EXISTS `dvd` (
  `numeroDoc` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(100) NOT NULL,
  `adulte` tinyint(1) NOT NULL,
  PRIMARY KEY (`numeroDoc`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `dvd`
--

INSERT INTO `dvd` (`numeroDoc`, `titre`, `adulte`) VALUES(1, 'Documentaire Leonard de Vinci', 0);
INSERT INTO `dvd` (`numeroDoc`, `titre`, `adulte`) VALUES(2, 'Massacre à la tronçonneuse', 1);
INSERT INTO `dvd` (`numeroDoc`, `titre`, `adulte`) VALUES(3, 'Comment dompter votre dragon ?', 0);
INSERT INTO `dvd` (`numeroDoc`, `titre`, `adulte`) VALUES(4, 'Saw VI', 1);
INSERT INTO `dvd` (`numeroDoc`, `titre`, `adulte`) VALUES(5, 'Angry Birds', 0);

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

DROP TABLE IF EXISTS `emprunt`;
CREATE TABLE IF NOT EXISTS `emprunt` (
  `numeroAbo` int NOT NULL,
  `numeroDoc` int NOT NULL,
  PRIMARY KEY (`numeroAbo`,`numeroDoc`),
  KEY `FK_emprunt_doc` (`numeroDoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `numeroAbo` int NOT NULL,
  `numeroDoc` int NOT NULL,
  `dateRes` bigint NOT NULL,
  KEY `FK_num_abo` (`numeroAbo`),
  KEY `FK_num_doc` (`numeroDoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `emprunt`
--
ALTER TABLE `emprunt`
  ADD CONSTRAINT `emprunt_ibfk_1` FOREIGN KEY (`numeroAbo`) REFERENCES `abonne` (`numeroAbo`),
  ADD CONSTRAINT `emprunt_ibfk_2` FOREIGN KEY (`numeroDoc`) REFERENCES `dvd` (`numeroDoc`),
  ADD CONSTRAINT `emprunt_ibfk_3` FOREIGN KEY (`numeroAbo`) REFERENCES `abonne` (`numeroAbo`),
  ADD CONSTRAINT `emprunt_ibfk_4` FOREIGN KEY (`numeroAbo`) REFERENCES `abonne` (`numeroAbo`);

--
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `FK_num_abo` FOREIGN KEY (`numeroAbo`) REFERENCES `abonne` (`numeroAbo`),
  ADD CONSTRAINT `FK_num_doc` FOREIGN KEY (`numeroDoc`) REFERENCES `dvd` (`numeroDoc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
