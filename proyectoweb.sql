-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-08-2021 a las 11:34:18
-- Versión del servidor: 10.4.19-MariaDB
-- Versión de PHP: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectoweb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administracion`
--

CREATE TABLE `administracion` (
  `id_admin` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `ap` varchar(50) NOT NULL,
  `am` varchar(50) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `rol` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `administracion`
--

INSERT INTO `administracion` (`id_admin`, `email`, `contrasena`, `ap`, `am`, `nombre`, `rol`) VALUES
(1, 'Rober@gmail.com', 'rol', 'Lundez', 'Marcial', 'Roberto', 'Admin'),
(2, 'colin@gmail.com', 'colin', 'Colin', 'Garcia', 'Guadalupe', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `id_compra` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `total` double NOT NULL,
  `fecha_compra` varchar(15) NOT NULL,
  `fm` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id_compra`, `cantidad`, `total`, `fecha_compra`, `fm`) VALUES
(1, 25, 3750, '30/07/2021', 1),
(2, 30, 4500, '30/07/2021', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `funko_pop`
--

CREATE TABLE `funko_pop` (
  `fm` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `img` varchar(200) NOT NULL,
  `edicion` varchar(50) NOT NULL,
  `precio` double NOT NULL,
  `fecha_elaboracion` varchar(15) NOT NULL,
  `categoria` varchar(60) NOT NULL,
  `tipo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `funko_pop`
--

INSERT INTO `funko_pop` (`fm`, `nombre`, `descripcion`, `img`, `edicion`, `precio`, `fecha_elaboracion`, `categoria`, `tipo`) VALUES
(1, 'Harley Quinn', 'Funko pop de Harley quinn', 'https://cdn11.bigcommerce.com/s-0kvv9/images/stencil/1280x1280/products/132872/1', 'Master', 150, '27/07/2020', 'Peliculas', 'Vinil'),
(3, 'Luke', 'Funko de Luke de Star Wars', 'https://hotstuff4geeks.com/wp-content/uploads/2020/04/Luke-Skywalker-Gold-1024x1024.jpg', 'Gold', 3000, '12/05/2012', 'Star Wars', 'Vinil');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movimiento`
--

CREATE TABLE `movimiento` (
  `id_movimiento` int(11) NOT NULL,
  `fecha_actual` varchar(13) NOT NULL,
  `movimiento` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `movimiento`
--

INSERT INTO `movimiento` (`id_movimiento`, `fecha_actual`, `movimiento`) VALUES
(1, '30/07/2021', 'Alta en productos'),
(2, '30/07/2021', 'Alta en productos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL,
  `nombre_empresa` varchar(50) NOT NULL,
  `rfc` varchar(50) NOT NULL,
  `cp` int(11) NOT NULL,
  `calle` varchar(60) NOT NULL,
  `colonia` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_proveedor`, `nombre_empresa`, `rfc`, `cp`, `calle`, `colonia`) VALUES
(1, 'Hasbro', 'HASUSA14814H', 6000, 'United Publish', 'Calendera'),
(2, 'Matel', 'MATUSA154', 58000, 'La Luxiana', 'Calendera');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sumistracion`
--

CREATE TABLE `sumistracion` (
  `id_sum` int(11) NOT NULL,
  `num_alta_funkos` int(11) NOT NULL,
  `fecha_alta` varchar(13) NOT NULL,
  `precio_unidad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sumistracion`
--

INSERT INTO `sumistracion` (`id_sum`, `num_alta_funkos`, `fecha_alta`, `precio_unidad`) VALUES
(1, 25, '30/07/2021', 250),
(2, 30, '31/07/2021', 400);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `email` varchar(60) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `cp` int(11) NOT NULL,
  `calle` varchar(50) NOT NULL,
  `colonia` varchar(50) NOT NULL,
  `ap` varchar(60) NOT NULL,
  `am` varchar(60) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `email`, `contrasena`, `cp`, `calle`, `colonia`, `ap`, `am`, `nombre`) VALUES
(1, 'Maria@gmail.com', '', 58000, 'Lomitas del villon', 'Colonia Calamidad', 'Mendez', 'Lopez', 'Maria'),
(2, 'Colin@gmail.com', '', 5800, 'La Luxiana', 'Villa Lopez', 'Colin ', 'Garcia', 'Guadalupe');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administracion`
--
ALTER TABLE `administracion`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`id_compra`);

--
-- Indices de la tabla `funko_pop`
--
ALTER TABLE `funko_pop`
  ADD PRIMARY KEY (`fm`);

--
-- Indices de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  ADD PRIMARY KEY (`id_movimiento`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `sumistracion`
--
ALTER TABLE `sumistracion`
  ADD PRIMARY KEY (`id_sum`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administracion`
--
ALTER TABLE `administracion`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `id_compra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `funko_pop`
--
ALTER TABLE `funko_pop`
  MODIFY `fm` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `movimiento`
--
ALTER TABLE `movimiento`
  MODIFY `id_movimiento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `sumistracion`
--
ALTER TABLE `sumistracion`
  MODIFY `id_sum` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
