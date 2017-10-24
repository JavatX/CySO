-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 27-06-2017 a las 11:46:42
-- Versión del servidor: 5.5.49-0+deb8u1
-- Versión de PHP: 5.6.20-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `cake`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE IF NOT EXISTS `administradores` (
  `Usuario_NIF` varchar(9) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`Usuario_NIF`) VALUES
('21432535H'),
('44489189J');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atiende`
--

CREATE TABLE IF NOT EXISTS `atiende` (
  `Conformidad_nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Construccion_idConstruccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `atendimiento` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conformidades`
--

CREATE TABLE IF NOT EXISTS `conformidades` (
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` longtext COLLATE utf8_spanish_ci NOT NULL,
  `tipo` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `construcciones`
--

CREATE TABLE IF NOT EXISTS `construcciones` (
  `idConstruccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `direccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `superficie` double NOT NULL,
  `descripcion` longtext COLLATE utf8_spanish_ci,
  `Constructor_Usuario_NIF` varchar(9) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `construcciones`
--

INSERT INTO `construcciones` (`idConstruccion`, `direccion`, `superficie`, `descripcion`, `Constructor_Usuario_NIF`) VALUES
('CONSTRUC01', 'Calle Falsa 123', 43, 'Se acondicionaran los terrenos de la calle Falsa para el transito exclusivo de peatones. Se sustituira el asfalto por plaqueta y se eliminaran las aceras, respetando el ajardinado ya presente. La duracion de la obra sera de 6 meses con comienzo el veintidos de julio de dos mil dieciseis.', '12345678A'),
('CONSTRUC02', 'Calle Hola 123', 42, 'No se hará nada. Simplemente estoy probando una teoría que se me acaba de ocurrir.', '12345678A'),
('CONSTRUC027', 'Calle Irreal 27', 100, 'Habrá acentos y sí, más acentos. Cuantos más, mejor. Palabras esdrújulas y un patrón, sólo uno, más no.', '23456789D'),
('CONSTRUC03', 'Calle Todos 123', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC04', 'Calle Real 234', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC05', 'Calle Falsa 2', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC06', 'Plaza Veintisiete 27', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC07', 'Calle Tres 456', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC08', 'Calle Se S/N', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC09', 'Calle Filibustero 23', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC10', 'Callejón de Atrás 773', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC11', 'Calle Mil 13', 42, 'Prueba listado.', '12345678A'),
('CONSTRUC12', 'Calle Dos 23', 56, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris a leo eget quam mollis imperdiet. Maecenas tincidunt, dolor ut porttitor tempor, eros arcu congue turpis, et semper tellus orci sit amet dui. In in pulvinar urna, eget tristique tellus. Pellentesque pellentesque pretium neque, lacinia tristique urna sollicitudin at. Donec sit amet dictum magna, et viverra dui. Nunc eget maximus nulla. Pellentesque vitae sem non tortor efficitur imperdiet. Donec semper diam magna, eget ultrices dolor rhoncus eget. Fusce id erat sem. Quisque a mauris non tortor tincidunt tincidunt.  Integer vel libero non enim iaculis volutpat. Sed mollis est nec sem placerat luctus. Donec at nunc vitae urna varius iaculis eu in erat. Aliquam erat volutpat. Cras sagittis eros vel mauris viverra, sed dictum augue interdum. Nunc nec euismod eros. Vestibulum vitae elementum odio. Maecenas nec leo erat. Vestibulum rutrum porta velit vitae luctus. Curabitur at blandit urna, ac euismod mauris. In hac habitasse platea dictumst. Curabitur nec consectetur lacus, sit amet auctor lacus. Sed posuere, purus eu condimentum elementum, tellus ante ullamcorper elit, at facilisis eros nulla eget felis. Integer venenatis pretium felis ut mollis. Donec hendrerit dui in ante pharetra gravida.', '12345678A'),
('CONSTRUC13', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC14', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC15', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC16', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC17', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC18', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC19', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC20', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC21', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC22', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC23', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC24', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC25', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC26', 'Calle Dos 23', 56, 'Gjad', '12345678A'),
('CONSTRUC27', 'Calle de la Falsedad 87', 261, 'Más falsa que las monedad de 3 euros.', '23456789D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `constructores`
--

CREATE TABLE IF NOT EXISTS `constructores` (
  `Usuario_NIF` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `idConstructor` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `constructores`
--

INSERT INTO `constructores` (`Usuario_NIF`, `idConstructor`) VALUES
('12345678A', 'CONS12345678A'),
('23456789D', 'CONS23456789D'),
('34557867G', 'CONS34557867G');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cumple`
--

CREATE TABLE IF NOT EXISTS `cumple` (
  `Construccion_idConstruccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `Exigencia_codigo` varchar(4) COLLATE utf8_spanish_ci NOT NULL,
  `notas` text COLLATE utf8_spanish_ci,
  `cumplimiento` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cumple`
--

INSERT INTO `cumple` (`Construccion_idConstruccion`, `Exigencia_codigo`, `notas`, `cumplimiento`) VALUES
('CONSTRUC01', 'HE1', 'La capa envolvente estï¿½ mal aplicada en ventanas y puertas.', 0),
('CONSTRUC01', 'HE2', '', 1),
('CONSTRUC01', 'HE3', '', 1),
('CONSTRUC01', 'HE4', '', 0),
('CONSTRUC01', 'HE5', '', 0),
('CONSTRUC01', 'HR', '', 0),
('CONSTRUC01', 'HS1', '', 0),
('CONSTRUC01', 'HS2', '', 0),
('CONSTRUC01', 'HS3', '', 0),
('CONSTRUC01', 'HS4', '', 0),
('CONSTRUC01', 'HS5', '', 0),
('CONSTRUC01', 'SE1', '', 1),
('CONSTRUC01', 'SE2', '', 1),
('CONSTRUC01', 'SI1', '', 1),
('CONSTRUC01', 'SI2', 'Carece de cortafuegos ante la posible propagaciï¿½n de incendios con respecto al edificio colindante.', 0),
('CONSTRUC01', 'SI3', '', 0),
('CONSTRUC01', 'SI4', '', 0),
('CONSTRUC01', 'SI5', '', 0),
('CONSTRUC01', 'SI6', '', 0),
('CONSTRUC01', 'SUA1', '', 0),
('CONSTRUC01', 'SUA2', '', 0),
('CONSTRUC01', 'SUA3', '', 0),
('CONSTRUC01', 'SUA4', '', 0),
('CONSTRUC01', 'SUA5', '', 0),
('CONSTRUC01', 'SUA6', '', 0),
('CONSTRUC01', 'SUA7', '', 0),
('CONSTRUC01', 'SUA8', '', 0),
('CONSTRUC01', 'SUA9', '', 0),
('CONSTRUC02', 'HS2', NULL, 1),
('CONSTRUC02', 'HS5', NULL, 1),
('CONSTRUC02', 'SI4', NULL, 1),
('CONSTRUC03', 'HE3', NULL, 1),
('CONSTRUC03', 'HS3', NULL, 1),
('CONSTRUC04', 'HE3', NULL, 1),
('CONSTRUC04', 'HR', NULL, 1),
('CONSTRUC05', 'SUA4', NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `denuncias`
--

CREATE TABLE IF NOT EXISTS `denuncias` (
  `numDenuncia` varchar(24) COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `foto` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` longtext COLLATE utf8_spanish_ci NOT NULL,
  `Inspector_Usuario_NIF` varchar(9) COLLATE utf8_spanish_ci DEFAULT NULL,
  `construcciones_idConstruccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `denuncias`
--

INSERT INTO `denuncias` (`numDenuncia`, `fecha`, `foto`, `descripcion`, `Inspector_Usuario_NIF`, `construcciones_idConstruccion`) VALUES
('010617_172117CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_172117CONSTRUC01.jpg', 'rekt', '44486394R', 'CONSTRUC01'),
('010617_172327CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_172327CONSTRUC01.jpg', '', '44486394R', 'CONSTRUC01'),
('010617_172711CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_172711CONSTRUC01.jpg', '', '44486394R', 'CONSTRUC01'),
('010617_172851CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_172851CONSTRUC01.jpg', '', '44486394R', 'CONSTRUC01'),
('010617_172931CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_172931CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('010617_181443CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_181443CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('010617_181743CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_181743CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('010617_182132CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_182132CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('010617_182905CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_182905CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('010617_183037CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_183037CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('010617_183118CONSTRUC01', '2017-06-01', '/obras/webroot/img/Denuncias/010617_183118CONSTRUC01.jpg', 'CabreiroÃ¡', NULL, 'CONSTRUC01'),
('040117_171753CONSTRUC02', '2017-01-04', '/obras/webroot/img/Denuncias/040117_171753CONSTRUC02.jpg', 'A ver como sale la fecha', NULL, 'CONSTRUC02'),
('050117_163156CONSTRUC01', '2017-01-05', '/obras/webroot/img/Denuncias/050117_163156CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('060616_141636CONSTRUC01', '2016-06-06', '/obras/webroot/img/Denuncias/060616_141636CONSTRUC01.jpg', 'Denuncia de prueba', NULL, 'CONSTRUC01'),
('070617_172509CONSTRUC12', '2017-06-07', '/obras/webroot/img/Denuncias/070617_172509CONSTRUC12.jpg', '', NULL, 'CONSTRUC12'),
('070617_172731CONSTRUC01', '2017-06-07', '/obras/webroot/img/Denuncias/070617_172731CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('070617_172824CONSTRUC01', '2017-06-07', '/obras/webroot/img/Denuncias/070617_172824CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('070617_173302CONSTRUC12', '2017-06-07', '/obras/webroot/img/Denuncias/070617_173302CONSTRUC12.jpg', 'Pues na', NULL, 'CONSTRUC12'),
('120117_182127CONSTRUC01', '2017-01-12', '/var/www/html/obras/webroot/img/Denuncias/120117_182127CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('120117_182320CONSTRUC01', '2017-01-12', '/var/www/html/obras/webroot/img/Denuncias/120117_182320CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('130617_211238CONSTRUC02', '2017-06-13', '/obras/webroot/img/Denuncias/130617_211238CONSTRUC02.jpg', 'LÃ¡mpara toloca', NULL, 'CONSTRUC02'),
('130617_214401CONSTRUC05', '2017-06-13', '/obras/webroot/img/Denuncias/130617_214401CONSTRUC05.jpg', 'Pa tu body', '44486394R', 'CONSTRUC05'),
('130617_220028CONSTRUC26', '2017-06-13', '/obras/webroot/img/Denuncias/130617_220028CONSTRUC26.jpg', 'que raro', NULL, 'CONSTRUC26'),
('130617_220313CONSTRUC16', '2017-06-13', '/obras/webroot/img/Denuncias/130617_220313CONSTRUC16.jpg', 'aquei', NULL, 'CONSTRUC16'),
('130617_220803CONSTRUC16', '2017-06-13', '/obras/webroot/img/Denuncias/130617_220803CONSTRUC16.jpg', 'holi', NULL, 'CONSTRUC16'),
('130617_224342CONSTRUC23', '2017-06-13', '/obras/webroot/img/Denuncias/130617_224342CONSTRUC23.jpg', 'bd', NULL, 'CONSTRUC23'),
('130617_232318CONSTRUC23', '2017-06-13', '/obras/webroot/img/Denuncias/130617_232318CONSTRUC23.jpg', 'ggggg', NULL, 'CONSTRUC23'),
('140617_121840CONSTRUC02', '2017-06-14', '/obras/webroot/img/Denuncias/140617_121840CONSTRUC02.jpg', 'cud', NULL, 'CONSTRUC02'),
('140617_122055CONSTRUC02', '2017-06-14', '/obras/webroot/img/Denuncias/140617_122055CONSTRUC02.jpg', 'gugu', NULL, 'CONSTRUC02'),
('140617_124218CONSTRUC04', '2017-06-14', '/obras/webroot/img/Denuncias/140617_124218CONSTRUC04.jpg', 'hg', NULL, 'CONSTRUC04'),
('140617_124943CONSTRUC06', '2017-06-14', '/obras/webroot/img/Denuncias/140617_124943CONSTRUC06.jpg', 'v', NULL, 'CONSTRUC06'),
('140617_131252CONSTRUC03', '2017-06-14', '/obras/webroot/img/Denuncias/140617_131252CONSTRUC03.jpg', 'negro', NULL, 'CONSTRUC03'),
('140617_133839CONSTRUC02', '2017-06-14', '/obras/webroot/img/Denuncias/140617_133839CONSTRUC02.jpg', 'rumd', NULL, 'CONSTRUC02'),
('140617_134722CONSTRUC03', '2017-06-14', '/obras/webroot/img/Denuncias/140617_134722CONSTRUC03.jpg', 'gg', NULL, 'CONSTRUC03'),
('140617_135709CONSTRUC26', '2017-06-14', '/obras/webroot/img/Denuncias/140617_135709CONSTRUC26.jpg', 'pene', NULL, 'CONSTRUC26'),
('140617_135715CONSTRUC03', '2017-06-14', '/obras/webroot/img/Denuncias/140617_135715CONSTRUC03.jpg', 'cucu', NULL, 'CONSTRUC03'),
('140617_140724CONSTRUC04', '2017-06-14', '/obras/webroot/img/Denuncias/140617_140724CONSTRUC04.jpg', '', NULL, 'CONSTRUC04'),
('140617_140813CONSTRUC04', '2017-06-14', '/obras/webroot/img/Denuncias/140617_140813CONSTRUC04.jpg', 'exito', NULL, 'CONSTRUC04'),
('190617_91718CONSTRUC01', '2017-06-19', '/obras/webroot/img/Denuncias/190617_91718CONSTRUC01.jpg', 'El borde del abismo', NULL, 'CONSTRUC01'),
('200617_171711CONSTRUC01', '2017-06-20', '/obras/webroot/img/Denuncias/200617_171711CONSTRUC01.jpg', 'Uno mira, otro trabaja. No pueden hacer algo con esto?', NULL, 'CONSTRUC01'),
('200617_171717CONSTRUC01', '2017-06-20', '/obras/webroot/img/Denuncias/200617_171717CONSTRUC01.jpg', 'No se toman las medidas adecuadas para la proteccion contra el ruido.', NULL, 'CONSTRUC01'),
('200617_171723CONSTRUC01', '2017-06-20', '/obras/webroot/img/Denuncias/200617_171723CONSTRUC01.jpg', 'Falta de seguridad en trabajos a gran altura. No es el primer día que ocurre esto.', NULL, 'CONSTRUC01'),
('200617_194816CONSTRUC01', '2017-06-20', '/obras/webroot/img/Denuncias/200617_194816CONSTRUC01.jpg', '', NULL, 'CONSTRUC01'),
('200617_195342CONSTRUC01', '2017-06-20', '/obras/webroot/img/Denuncias/200617_195342CONSTRUC01.jpg', 'tetete', NULL, 'CONSTRUC01'),
('270516_191600CONSTRUC02', '2194-12-05', '/obras/webroot/img/Denuncias/270516_191600CONSTRUC02.jpg', 'otra', '63623762G', 'CONSTRUC02'),
('270516_191613CONSTRUC01', '2194-12-05', '/obras/webroot/img/Denuncias/270516_191613CONSTRUC01.jpg', 'otra mas', '44171289U', 'CONSTRUC01'),
('270516_191638CONSTRUC02', '2194-12-05', '/obras/webroot/img/Denuncias/270516_191638CONSTRUC02.jpg', 'A ver si funciona ya.', '76545678L', 'CONSTRUC02'),
('280516_91656CONSTRUC03', '2195-12-05', '/obras/webroot/img/Denuncias/280516_91656CONSTRUC03.jpg', 'YHCHC', '36722389H', 'CONSTRUC03'),
('300516_111604CONSTRUC04', '2197-12-05', '/obras/webroot/img/Denuncias/300516_111604CONSTRUC04.jpg', 'El tamaño importa en estas cosas. No se por que.', '36722389H', 'CONSTRUC04'),
('300516_111609CONSTRUC04', '2165-02-01', '/obras/webroot/img/Denuncias/300516_111609CONSTRUC04.jpg', 'Prueba de fecha.', '36722389H', 'CONSTRUC04'),
('300516_111632CONSTRUC02', '2197-12-05', '/obras/webroot/img/Denuncias/300516_111632CONSTRUC02.jpg', 'MIrando a ver cuanto es el tamaño minimo', '74674832J', 'CONSTRUC02'),
('300516_111633CONSTRUC01', '2197-12-05', '/obras/webroot/img/Denuncias/300516_111633CONSTRUC01.jpg', 'a ver ahora', '45675434F', 'CONSTRUC01'),
('300516_111646CONSTRUC04', '2165-02-01', '/obras/webroot/img/Denuncias/300516_111646CONSTRUC04.jpg', 'Otra de fecha', '45675434F', 'CONSTRUC04'),
('310517_192824CONSTRUC01', '2017-05-31', '/var/www/html/obras/webroot/img/Denuncias/310517_192824CONSTRUC01.jpg', '', NULL, 'CONSTRUC01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `exigencias`
--

CREATE TABLE IF NOT EXISTS `exigencias` (
  `codigo` varchar(4) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(75) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `exigencias`
--

INSERT INTO `exigencias` (`codigo`, `nombre`, `descripcion`) VALUES
('HE1', 'Limitación de demanda energética', 'Los edificios dispondrán de una envolvente de características tales que limite adecuadamente la demandaenergética necesaria para alcanzar el bienestar térmico en función del clima de la localidad, del uso deledificio y del régimen de verano y de invierno, así como por sus características de aislamiento e inercia,permeabilidad al aire y exposición a la radiación solar, reduciendo el riesgo de aparición de humedades decondensación superficiales e intersticiales que puedan perjudicar sus características y tratando adecuadamentelos puentes térmicos para limitar las pérdidas o ganancias de calor y evitar problemas higrotérmicosen los mismos.'),
('HE2', 'Rendimiento de las instalaciones térmicas', 'Los edificios dispondrán de instalaciones térmicas apropiadas destinadas a proporcionar el bienestar térmico\nde sus ocupantes. Esta exigencia se desarrolla actualmente en el vigente Reglamento de Instalaciones\nTérmicas en los Edificios, RITE, y su aplicación quedará definida en el proyecto del edificio.'),
('HE3', 'Eficiencia energética de las instalaciones de iluminación', 'Los edificios dispondrán de instalaciones de iluminación adecuadas a las necesidades de sus usuarios y a\nla vez eficaces energéticamente disponiendo de un sistema de control que permita ajustar el encendido a\nla ocupación real de la zona, así como de un sistema de regulación que optimice el aprovechamiento de la\nluz natural, en las zonas que reúnan unas determinadas condiciones.'),
('HE4', 'Contribución solar mínima de agua caliente sanitaria', 'En los edificios con previsión de demanda de agua caliente sanitaria o de climatización de piscina cubierta,\nen los que así se establezca en este CTE, una parte de las necesidades energéticas térmicas derivadas de\nesa demanda se cubrirá mediante la incorporación en los mismos de sistemas de captación, almacenamiento\ny utilización de energía solar de baja temperatura adecuada a la radiación solar global de su emplazamiento\ny a la demanda de agua caliente del edificio o de la piscina. Los valores derivados de esta exigencia\nbásica tendrán la consideración de mínimos, sin perjuicio de valores que puedan ser establecidos\npor las administraciones competentes y que contribuyan a la sostenibilidad, atendiendo a las características\npropias de su localización y ámbito territorial.'),
('HE5', 'Contribución fotovoltaica mínima de energía eléctrica', 'En los edificios que así se establezca en este CTE se incorporarán sistemas de captación y transformación\nde energía solar en energía eléctrica por procedimientos fotovoltaicos para uso propio o suministro a la red.\nLos valores derivados de esta exigencia básica tendrán la consideración de mínimos, sin perjuicio de valores\nmás estrictos que puedan ser establecidos por las administraciones competentes y que contribuyan a\nla sostenibilidad, atendiendo a las características propias de su localización y ámbito territorial.'),
('HR', 'Protección frente al ruido', 'Para satisfacer este objetivo, los edificios se proyectarán, construirán, utilizarán y mantendrán de tal\nforma que los elementos constructivos que conforman sus recintos tengan unas características acústicas\nadecuadas para reducir la transmisión del ruido aéreo, del ruido de impactos y del ruido y vibraciones\nde las instalaciones propias del edificio, y para limitar el ruido reverberante de los recintos'),
('HS1', 'Protección frente a la humedad', 'Se limitará el riesgo previsible de presencia inadecuada de agua o humedad en el interior de los edificios y en sus cerramientos como consecuencia del agua procedente de precipitaciones atmosféricas, de escorrentías, del terreno o de condensaciones, disponiendo medios que impidan su penetración o, en su caso permitan su evacuación sin producción de daños.'),
('HS2', 'Recogida y evacuación de residuos', 'Los edificios dispondrán de espacios y medios para extraer los residuos ordinarios generados en ellos de forma acorde con el sistema público de recogida de tal manera que se facilite la adecuada separación en origen de dichos residuos, la recogida selectiva de los mismos y su posterior gestión.'),
('HS3', 'Calidad del aire interior', 'Los edificios dispondrán de medios para que sus recintos se puedan ventila adecuadamente, eliminando los contaminantes que se produzcan de forma habitual durante el uso normal de los edificios, de forma que se aporte un caudal suficiente de aire exterior y se garantice la extracción y expulsión del aire viciado por los contaminantes.'),
('HS4', 'Suministro de agua', 'Los edificios dispondrán de medios adecuados para suministrar al equipamiento higiénico previsto agua apta para el consumo de forma sostenible, aportando caudales suficientes para su funcionamiento, sin alteración de las propiedades de aptitud para el consumo e impidiendo los posibles retornos que puedan contaminar la red, incorporando medios que permitan el ahorro y el control del caudal del agua.'),
('HS5', 'Evacuación de aguas', 'Los edificios dispondrán de medios adecuados para extraer las aguas residuales generadas en ellos de forma independiente o conjunta con las precipitaciones atmosféricas y con las escorrentías.'),
('SE1', 'Resistencia y estabilidad', 'La resistencia y la estabilidad serán las adecuadas para que no se generen riesgos indebidos, de forma que se mantenga la resistencia y la estabilidad frente a las acciones e influencias previsibles durante las fases de construcción y usos previstos de los edificios, y que un evento extraordinario no produzca consecuencias desproporcionadas respecto a la causa original y se facilite el mantenimiento previsto.'),
('SE2', 'Aptitud al servicio', 'La aptitud al servicio será conforme con el uso previsto del edificio, de forma que no se produzcan deformaciones inadmisibles, se limite a un nivel aceptable la probabilidad de un comportamiento dinámico inadmisible y no se produzcan degradaciones o anomalías inadmisibles.'),
('SI1', 'Propagación interior', 'Se limitará el riesgo de propagación del incendio por el interior del edificio, tanto al mismo edificio como a otros edificios colindantes.'),
('SI2', 'Propagación exterior', 'Se limitará el riesgo de propagación del incendio por el exterior, tanto en el edificio considerado como a otros edificios'),
('SI3', 'Evacuación de ocupantes', 'El edificio dispondrá de los medios de evacuación adecuados para facilitar que los ocupantes puedan abandonarlo o alcanzar un lugar seguro dentro del mismo en condiciones de seguridad.'),
('SI4', 'Instalaciones de protección contra incendios', 'El edificio dispondrá de los equipos e instalaciones adecuados para hacer posible la detección, el control y la extinción del incendio, así como la transmisión de la alarma a los ocupantes.'),
('SI5', 'Intervención de bomberos', 'Se facilitará la intervención de los equipos de rescate y de extinción de incendios.'),
('SI6', 'Resistencia estructural al incendio', 'La estructura portante mantendrá su resistencia al fuego durante el tiempo necesario para que puedan cumplirse las anteriores exigencias básicas.'),
('SUA1', 'Seguridad frente al riesgo de caídas', 'Se limitará el riesgo de que los usuarios sufran caídas, para lo cual los suelos serán adecuados para favorecer que las personas no resbalen, tropiecen o se dificulte la movilidad. Asimismo se limitará el riesgo de caídas en huecos, en cambios de nivel y en escaleras y rampas, facilitándose la limpieza de los acristalamientos exteriores en condiciones de seguridad.'),
('SUA2', 'Seguridad frente al riesgo de impacto o de atrapamiento', 'Se limitará el riesgo de que los usuarios puedan sufrir impacto o atrapamiento con elementos fijos o móviles del edificio.'),
('SUA3', 'Seguridad frente al riesgo de aprisionamiento', 'Se limitará el riesgo de que los usuarios puedan quedar accidentalmente aprisionados en recintos.'),
('SUA4', 'Seguridad frente al riesgo causado por iluminación inadecuada', 'Se limitará el riesgo de daños a las personas como consecuencia de una iluminación inadecuada en zonas de circulación de los edificios, tanto interiores como exteriores, incluso en caso de emergencia o de fallo del alumbrado normal.'),
('SUA5', 'Seguridad frente al riesgo causado por situaciones con alta ocupación', 'Se limitará el riesgo causado por situaciones con alta ocupación facilitando la circulación de las personas y la sectorización con elementos de protección y contención en previsión del riesgo de aplastamiento.'),
('SUA6', 'Seguridad frente al riesgo de ahogamiento', 'Se limitará el riesgo de caídas que puedan derivar en ahogamiento en piscinas, depósitos, pozos y similares mediante elementos que restrinjan el acceso.'),
('SUA7', 'Seguridad frente al riesgo causado por vehículos en movimiento', 'Se limitará el riesgo causado por vehículos en movimiento atendiendo a los tipos de pavimentos y la señalización y protección de las zonas de circulación rodada y de las personas.'),
('SUA8', 'Seguridad frente al riesgo relacionado con la acción del rayo', 'Se limitará el riesgo de electrocución y de incendio causado por la acción del rayo, mediante instalaciones adecuadas de protección contra el rayo.'),
('SUA9', 'Accesibilidad', 'Se facilitará el acceso y la utilización no discriminatoria, independiente y segura de los edificios a las personas con discapacidad.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotos`
--

CREATE TABLE IF NOT EXISTS `fotos` (
  `Construccion_idConstruccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `numero` int(11) NOT NULL,
  `foto` varchar(24) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inspectores`
--

CREATE TABLE IF NOT EXISTS `inspectores` (
  `Usuario_NIF` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `numInspector` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `inspectores`
--

INSERT INTO `inspectores` (`Usuario_NIF`, `numInspector`) VALUES
('31617160D', 'INS31617160D'),
('36722389H', 'INS36722389H'),
('37328392T', 'INS37328392T'),
('44171289U', 'INS44171289U'),
('44486394R', 'INS44486394R'),
('45675434F', 'INS45675434F'),
('47848778F', 'INS47848778F'),
('63623762G', 'INS63623762G'),
('67327623G', 'INS67327623G'),
('67327643H', 'INS67327643H'),
('73267326N', 'INS73267326N'),
('73267367J', 'INS73267367J'),
('74674832J', 'INS74674832J'),
('76125281G', 'INS76125281G'),
('76325628G', 'INS76325628G'),
('76387474F', 'INS76387474F'),
('76545678L', 'INS76545678L'),
('83478578G', 'INS83478578G'),
('87347468K', 'INS87347468K'),
('87376467G', 'INS87376467G'),
('89584254H', 'INS89584254H');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `licencias`
--

CREATE TABLE IF NOT EXISTS `licencias` (
  `numLicencia` varchar(12) COLLATE utf8_spanish_ci NOT NULL,
  `Construcciones_idConstruccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `licencias`
--

INSERT INTO `licencias` (`numLicencia`, `Construcciones_idConstruccion`) VALUES
('LC0000000001', 'CONSTRUC01'),
('LC0000000002', 'CONSTRUC02'),
('LC0000000003', 'CONSTRUC02');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obras`
--

CREATE TABLE IF NOT EXISTS `obras` (
  `Licencia_numLicencia` varchar(12) COLLATE utf8_spanish_ci NOT NULL,
  `tipo` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `subtipo` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `obras`
--

INSERT INTO `obras` (`Licencia_numLicencia`, `tipo`, `subtipo`) VALUES
('LC0000000003', 'Mayor', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ocupaciones`
--

CREATE TABLE IF NOT EXISTS `ocupaciones` (
  `Licencia_numLicencia` varchar(12) COLLATE utf8_spanish_ci NOT NULL,
  `motivo` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `via` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `metros` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `horaInicio` time DEFAULT NULL,
  `horaFin` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ocupaciones`
--

INSERT INTO `ocupaciones` (`Licencia_numLicencia`, `motivo`, `via`, `metros`, `horaInicio`, `horaFin`) VALUES
('LC0000000001', 'Estacionamiento de vehículos.', 'Calle Falsa', '5', '08:00:00', '20:00:00'),
('LC0000000002', 'Contenedor de desperdicios', 'Calle Falsa', '4', '08:00:00', '20:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `NIF` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `contrasena` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `tipo` varchar(45) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`NIF`, `nombre`, `apellidos`, `contrasena`, `tipo`) VALUES
('12345678A', 'Constructor', 'Uno', '$2y$10$AkJQKjSYqYvKw2ucyxLwkuzN0RQ1u8WAB133ezLJWlLBMxCWlGa3i', 'cons'),
('21432535H', 'Prueba', 'Login', '$2y$10$eVp6lwxqFpITftFB18e23eLn.a3LJFOfGWsjKHvyL2BHnZoQ.2CTO', 'admin'),
('23456789D', 'Constructor', 'Dos', '$2y$10$jPj4FDCwJ6chMYDksvj2X.VKIxZg0MWdbV4OW78DsaGMWw88S1ETa', 'cons'),
('31617160D', 'Geralt', 'z Rivii', '$2y$10$voJyTVau427hyStlUsbXH.fP1ietx0x7m1CItxEysDXp9RanJDDNS', 'inspector'),
('34557867G', 'Constructor', 'Tres', '$2y$10$s6Une8jxnH8Y7sQwnM93ueZmhTi7mAvHLclwqjQT74PMivqnI2Nay', 'cons'),
('36722389H', 'Eustaquio', 'Habichuela Barrio', '$2y$10$71Ywg5RCQy902ksXM5YM3Obseagnoex.cWKACAhuutGPEfOyU44QC', 'inspector'),
('37328392T', 'Domingo', 'Expósito Fernández', '$2y$10$ESCEaco6NFChWrUk48pB.eR77YYhg/TzQT4kYjNv3DfO9HoRe3EWi', 'inspector'),
('44171289U', 'Óscar', 'Nóvoa González', '$2y$10$O01qwR9xAvgrrxUFQ.xB.OKT3xC4XYW/gtRahcfkF8ycvzKgJqtm.', 'inspector'),
('44486394R', 'David', 'Dopereiro Dominguez', '$2y$10$CJENVZTtMPo0w35/G4n5.OetKGWSvKUmlXUfrTWXm.Cxgbqf27h8G', 'inspector'),
('44489189J', 'Xoel', 'Nóvoa Pérez', '$2y$10$YmEi4xCC3CokObgcU5kLLeb3cnFOR8GTv62y1lgQOSWTn19moMV3S', 'admin'),
('45675434F', 'Julian Alfred Pankratz Wicehrabia', ' v Lettenhove', '$2y$10$CL2HRkO6iad920xUFVAtwu9SQv0AjzIxRPH6i0UVP.988viDKgjLu', 'inspector'),
('47848778F', 'Anacleto', 'Bermudez Carrasco', '$2y$10$iuu60c/iu7LWYWQ18IJmjujvSQl3xY9b2YVr8.GieDCAzisbi4abi', 'inspector'),
('63623762G', 'Cirilla Fiona Elen', 'Riannon', '$2y$10$EDOzN8xDsiI/ZGN7XGfyLefygpGCNjDX11TMnHNW6ZQKC9/FETvsi', 'inspector'),
('67327623G', 'Daniel', 'Sánchez Valencia', '$2y$10$XhtVXHs0KnsTBLQ/IpJsleAZ/ALeV4eHk/IjDzKXmYC1msi9ZdaK2', 'inspector'),
('67327643H', 'Rubén', 'Míguez Estévez', '$2y$10$XpqO0AffjO3eddI02meDQusmhosg4pccZtnWIJ4LW7qasiPAm93KG', 'inspector'),
('73267326N', 'Heraclio', 'Iglesias Jimenez', '$2y$10$WvoNPo4raU9XgJ67X7EBoetEHkFIhYXWarV8HAUB/e08ecuTUYORm', 'inspector'),
('73267367J', 'Sheppard', 'Commander', '$2y$10$sYKOjKb8FcgommttrN8L8OSj.tLvOFHR7I3HYeiFO4R/OJhXAGdhW', 'inspector'),
('74674832J', 'Iván', 'Jarque Kidd', '$2y$10$.pGEZHlr42uU/TnQsEC4n.iZEgPtXspIbd97Wed3rel0Ei.CpU3mi', 'inspector'),
('76125281G', 'Garrus', 'Vakarian', '$2y$10$aKxqsxxZq6oMYHm7ML2X/OHgoTPOg9YIITYHeetvguJwC/KvtHCQO', 'inspector'),
('76325628G', 'Andrew', 'Ryan', '$2y$10$PC8xIjzCeBWWSAYbDUGR0ONWJroh/Kb1NNmDX7tZ2PoA7A7l2tfCW', 'inspector'),
('76387474F', 'Belén', 'Caride Domínguez', '$2y$10$7cDyIATRAXpcUQ2SqN5.C.1W/kh1Qo3.V10gFKhx9ZDK8jRB86z4i', 'inspector'),
('76545678L', 'Carlos', 'Blanco Otero', '$2y$10$lj70WlBInRNGlZAv6Sc4ouLqOMhl8InVBrPO0SWeWqNW8xeOq/dsq', 'inspector'),
('83478578G', 'Estela', 'Fernández Gómez', '$2y$10$LVXuB1mqmFeV8dEIQ4bqYOFk/Humz6eW080NXsH90zPLAYhVsumJq', 'inspector'),
('87347468K', 'Javier', 'Ibarra Ramos', '$2y$10$t7sf6sAJEf4ViF/y9ebEc.3uzuffe9ZjkbGlHe.S6SvvO6lUtEX26', 'inspector'),
('87376467G', 'Filomena', 'Gracia Heras', '$2y$10$rVu8VC6glVrSebgsIBUbLe1CREkGux395wgbPfIGA2C5eucTF.TJK', 'inspector'),
('89584254H', 'Carmen', 'Dieguez Estevez', '$2y$10$6.I9RH96vPILQsvEFnf9be1tZhGVaAYTYtYIipE768Zh3M3WA8NOq', 'inspector');

--
-- Disparadores `usuarios`
--
DELIMITER //
CREATE TRIGGER `usuarios_AFTER_INSERT` AFTER INSERT ON `usuarios`
 FOR EACH ROW IF NEW.tipo = 'cons' THEN insert into constructores set Usuario_NIF=NEW.NIF, idConstructor=CONCAT('CONS', NEW.NIF); else IF NEW.tipo = 'admin' then insert into administradores set Usuario_NIF=NEW.NIF; else if NEW.tipo = 'inspector' then insert into inspectores set Usuario_NIF=NEW.NIF, numInspector=CONCAT('INS', NEW.NIF); end if; end if; end if
//
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradores`
--
ALTER TABLE `administradores`
 ADD PRIMARY KEY (`Usuario_NIF`);

--
-- Indices de la tabla `atiende`
--
ALTER TABLE `atiende`
 ADD PRIMARY KEY (`Conformidad_nombre`,`Construccion_idConstruccion`), ADD KEY `fk_Atiende_Construccion1_idx` (`Construccion_idConstruccion`);

--
-- Indices de la tabla `conformidades`
--
ALTER TABLE `conformidades`
 ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `construcciones`
--
ALTER TABLE `construcciones`
 ADD PRIMARY KEY (`idConstruccion`), ADD KEY `fk_Construccion_Constructor1_idx` (`Constructor_Usuario_NIF`);

--
-- Indices de la tabla `constructores`
--
ALTER TABLE `constructores`
 ADD PRIMARY KEY (`Usuario_NIF`);

--
-- Indices de la tabla `cumple`
--
ALTER TABLE `cumple`
 ADD PRIMARY KEY (`Construccion_idConstruccion`,`Exigencia_codigo`), ADD KEY `fk_Cumple_Exigencia2_idx` (`Exigencia_codigo`);

--
-- Indices de la tabla `denuncias`
--
ALTER TABLE `denuncias`
 ADD PRIMARY KEY (`numDenuncia`), ADD KEY `fk_Denuncia_Inspector1_idx` (`Inspector_Usuario_NIF`), ADD KEY `fk_denuncias_construcciones1_idx` (`construcciones_idConstruccion`);

--
-- Indices de la tabla `exigencias`
--
ALTER TABLE `exigencias`
 ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `fotos`
--
ALTER TABLE `fotos`
 ADD PRIMARY KEY (`Construccion_idConstruccion`,`numero`), ADD KEY `fk_Foto_Construccion1_idx` (`Construccion_idConstruccion`);

--
-- Indices de la tabla `inspectores`
--
ALTER TABLE `inspectores`
 ADD PRIMARY KEY (`Usuario_NIF`);

--
-- Indices de la tabla `licencias`
--
ALTER TABLE `licencias`
 ADD PRIMARY KEY (`numLicencia`), ADD KEY `fk_Licencias_Construcciones1_idx` (`Construcciones_idConstruccion`);

--
-- Indices de la tabla `obras`
--
ALTER TABLE `obras`
 ADD PRIMARY KEY (`Licencia_numLicencia`);

--
-- Indices de la tabla `ocupaciones`
--
ALTER TABLE `ocupaciones`
 ADD PRIMARY KEY (`Licencia_numLicencia`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`NIF`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administradores`
--
ALTER TABLE `administradores`
ADD CONSTRAINT `fk_Administrador_Usuario1` FOREIGN KEY (`Usuario_NIF`) REFERENCES `usuarios` (`NIF`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `atiende`
--
ALTER TABLE `atiende`
ADD CONSTRAINT `fk_Atiende_Conformidad1` FOREIGN KEY (`Conformidad_nombre`) REFERENCES `conformidades` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Atiende_Construccion1` FOREIGN KEY (`Construccion_idConstruccion`) REFERENCES `construcciones` (`idConstruccion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `construcciones`
--
ALTER TABLE `construcciones`
ADD CONSTRAINT `fk_Construccion_Constructor1` FOREIGN KEY (`Constructor_Usuario_NIF`) REFERENCES `constructores` (`Usuario_NIF`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `constructores`
--
ALTER TABLE `constructores`
ADD CONSTRAINT `fk_Constructor_Usuario1` FOREIGN KEY (`Usuario_NIF`) REFERENCES `usuarios` (`NIF`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `cumple`
--
ALTER TABLE `cumple`
ADD CONSTRAINT `fk_Cumple_Construccion1` FOREIGN KEY (`Construccion_idConstruccion`) REFERENCES `construcciones` (`idConstruccion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Cumple_Exigencia2` FOREIGN KEY (`Exigencia_codigo`) REFERENCES `exigencias` (`codigo`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `denuncias`
--
ALTER TABLE `denuncias`
ADD CONSTRAINT `fk_denuncias_construcciones1` FOREIGN KEY (`construcciones_idConstruccion`) REFERENCES `construcciones` (`idConstruccion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_Denuncia_Inspector1` FOREIGN KEY (`Inspector_Usuario_NIF`) REFERENCES `inspectores` (`Usuario_NIF`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `fotos`
--
ALTER TABLE `fotos`
ADD CONSTRAINT `fk_Foto_Construccion1` FOREIGN KEY (`Construccion_idConstruccion`) REFERENCES `construcciones` (`idConstruccion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `inspectores`
--
ALTER TABLE `inspectores`
ADD CONSTRAINT `fk_Inspector_Usuario` FOREIGN KEY (`Usuario_NIF`) REFERENCES `usuarios` (`NIF`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `licencias`
--
ALTER TABLE `licencias`
ADD CONSTRAINT `fk_licencias_construcciones1` FOREIGN KEY (`Construcciones_idConstruccion`) REFERENCES `construcciones` (`idConstruccion`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `obras`
--
ALTER TABLE `obras`
ADD CONSTRAINT `fk_Obra_Licencia1` FOREIGN KEY (`Licencia_numLicencia`) REFERENCES `licencias` (`numLicencia`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ocupaciones`
--
ALTER TABLE `ocupaciones`
ADD CONSTRAINT `ocupaciones_ibfk_1` FOREIGN KEY (`Licencia_numLicencia`) REFERENCES `licencias` (`numLicencia`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
