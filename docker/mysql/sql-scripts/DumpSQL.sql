-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  Dim 12 jan. 2020 à 14:33
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gpm`
--
CREATE DATABASE if not exists gpm;
use gpm;
-- --------------------------------------------------------

--
-- Structure de la table `entreprise`
--

DROP TABLE IF EXISTS `entreprise`;
CREATE TABLE IF NOT EXISTS `entreprise` (
  `id` int(10) NOT NULL auto_increment,
  `tel` varchar(10) DEFAULT NULL,
  `siren` varchar(24) DEFAULT NULL,
  `mail` varchar(24) DEFAULT NULL,
  `raisonSociale` varchar(24) DEFAULT NULL,
  `mdp` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `entreprise`
--

INSERT INTO `entreprise` (`tel`, `siren`, `mail`, `raisonSociale`, `mdp`) VALUES
('023456789', '123456789', 'arezki@gmail.com', NULL, '0000'),
('213123332', '342342323', 'are@gmail.com', NULL, '1111');

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE IF NOT EXISTS `etudiant` (
  `id` int(10) NOT NULL auto_increment,
  `numetu` varchar(24) NOT NULL,
  `idProjet` int(10) DEFAULT NULL,
  `annee` int(10) DEFAULT NULL,
  `nom` varchar(24) DEFAULT NULL,
  `prenom` varchar(24) DEFAULT NULL,
  `mail` varchar(24) DEFAULT NULL,
  `mdp` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`numetu`, `idProjet`, `annee`, `nom`, `prenom`, `mail`, `mdp`) VALUES
('2154376', 1, NULL, NULL, NULL,'bbb@gmail.com', '3333'),
('2255333', 2, NULL, NULL, NULL,'aaa@gmail.com', '4444');

-- --------------------------------------------------------

--
-- Structure de la table `prof`
--

DROP TABLE IF EXISTS `prof`;
CREATE TABLE IF NOT EXISTS `prof` (
  `id` int(10) NOT NULL auto_increment,
  `mail` varchar(24) DEFAULT NULL,
  `mdp` varchar(24) DEFAULT NULL,
  `nom` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `prof`
--

INSERT INTO `prof` (`mail`, `mdp`, `nom`) VALUES
(NULL, NULL, NULL),
(NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `tuteur`
--

DROP TABLE IF EXISTS `tuteur`;
CREATE TABLE IF NOT EXISTS `tuteur` (
  `id` int(10) NOT NULL auto_increment,
  `tel` varchar(10) DEFAULT NULL,
  `entreprise` int(10) NOT NULL,
  `nom` varchar(24) DEFAULT NULL,
  `mail` varchar(24) DEFAULT NULL,
  `mdp` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Entreprise` (`entreprise`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `tuteur`
--

INSERT INTO `tuteur` (`tel`, `entreprise`, `nom`, `mail`, `mdp`) VALUES
('45353443', 1, NULL, NULL, NULL),
('34534543', 2, NULL, NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;