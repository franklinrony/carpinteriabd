-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-05-2014 a las 06:00:05
-- Versión del servidor: 5.5.36
-- Versión de PHP: 5.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `carpinteria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo_usuario`
--

CREATE TABLE IF NOT EXISTS `grupo_usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `play_evolutions`
--

CREATE TABLE IF NOT EXISTS `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_script` text COLLATE utf8_spanish_ci,
  `revert_script` text COLLATE utf8_spanish_ci,
  `state` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `last_problem` text COLLATE utf8_spanish_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `play_evolutions`
--

INSERT INTO `play_evolutions` (`id`, `hash`, `applied_at`, `apply_script`, `revert_script`, `state`, `last_problem`) VALUES
(1, '22ba9794eeac200f36bbc73f77bbca9b4c9eff5b', '2014-05-08 01:42:50', 'create table grupo_usuario (\nid                        bigint auto_increment not null,\ndescripcion               varchar(255),\nconstraint pk_grupo_usuario primary key (id))\n;\n\ncreate table usuario (\nid                        bigint auto_increment not null,\nnombres                   varchar(255),\napellidos                 varchar(255),\nedad                      integer,\ncorreo                    varchar(255),\nusername                  varchar(255),\npassword                  varchar(255),\ngrupo                     integer default 1,\nconstraint pk_usuario primary key (id))\n;', 'SET FOREIGN_KEY_CHECKS=0;\n\ndrop table grupo_usuario;\n\ndrop table usuario;\n\nSET FOREIGN_KEY_CHECKS=1;', 'applied', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellidos` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `correo` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `grupo` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombres`, `apellidos`, `edad`, `correo`, `username`, `password`, `grupo`) VALUES
(1, 'Franklin ', 'Cortez Barrera', 28, 'franklinrony2@gmail.com', 'admin', '$2a$10$Ymyw2ZFGgvfgm0B5PtpR.OXfYY.xNAdYxCXTUexBuvcajSeYV.Zlm', 1),
(2, 'Franklin ', 'Cortez Barrera', 28, 'franklinrony@gmail.com', 'franklinrony', '$2a$10$CYyJSmYf7D/IbpcMZnf1IO9X8hUOqHtriYwBSKlpe5BywJfbZLp.2', 2);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
