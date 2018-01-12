-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 12 jan. 2018 à 17:49
-- Version du serveur :  10.1.29-MariaDB
-- Version de PHP :  7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet_android`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

CREATE TABLE `article` (
  `idArticle` int(11) NOT NULL,
  `nomArticle` varchar(50) NOT NULL,
  `descriptifArticle` varchar(100) DEFAULT NULL,
  `prixArticle` double NOT NULL,
  `etatArticle` tinyint(1) NOT NULL,
  `idLocalite` int(11) NOT NULL,
  `livraisonArticle` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`idArticle`, `nomArticle`, `descriptifArticle`, `prixArticle`, `etatArticle`, `idLocalite`, `livraisonArticle`) VALUES
(1, 'Pc Gamer', 'PC tres performant', 2500, 1, 1, 1),
(16, 'Statue', 'Belle statue', 1250, 1, 2, 1),
(17, 'Short', 'En 8 exemplaires', 80, 0, 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `localite`
--

CREATE TABLE `localite` (
  `idLocalite` int(11) NOT NULL,
  `nomLocalite` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `localite`
--

INSERT INTO `localite` (`idLocalite`, `nomLocalite`) VALUES
(1, 'Charleroi'),
(2, 'Bruxelles'),
(3, 'Mons'),
(4, 'Tournai'),
(5, 'Namur'),
(6, 'Liege'),
(7, 'Louvain'),
(8, 'Luxembourg'),
(9, 'Anvers'),
(10, 'Bruges');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`idArticle`);

--
-- Index pour la table `localite`
--
ALTER TABLE `localite`
  ADD PRIMARY KEY (`idLocalite`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `article`
--
ALTER TABLE `article`
  MODIFY `idArticle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `localite`
--
ALTER TABLE `localite`
  MODIFY `idLocalite` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
