-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2024 at 02:27 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cobaapidb`
--

-- --------------------------------------------------------

--
-- Table structure for table `notes`
--

CREATE TABLE `notes` (
  `id_note` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `note` varchar(500) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notes`
--

INSERT INTO `notes` (`id_note`, `id_user`, `note`, `date`) VALUES
(45, 40, 'Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet!', '2024-11-25'),
(46, 42, 'Lorem ipsum dolor sit amet', '2024-11-25'),
(47, 42, 'catatan baru hari ini!', '2024-11-25'),
(48, 42, 'lahh kaga ada', '2024-11-25'),
(49, 42, 'watehell', '2024-11-25'),
(50, 42, 'can\'t?', '2024-11-25'),
(51, 42, 'idk what it is', '2024-12-04'),
(52, 42, 'cobain', '2024-11-25'),
(53, 42, 'cobaa', '2024-11-25'),
(54, 42, 'dd', '2024-12-04'),
(55, 42, 'paling baru ini', '2024-11-26'),
(62, 47, 'ini adalah catatan pertama dari ujang!', '2024-12-04'),
(63, 47, 'catatan kedua ujang', '2024-12-04'),
(64, 47, 'catatan ketiga ujang', '2024-12-04');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `email`) VALUES
(40, 'a', '$2y$10$N9li4WscDxYnzB9/PZuMGeOdEEBQIoFoaN/zvd7nIAVvTD8V56xJS', 'a'),
(41, 'a', '$2y$10$18Gb4L/pewvDMqpyj3Ki5.d0Q6UtzsebPbfG.1Nsjh6JBOj.282CK', 'a'),
(42, 'udin', '$2y$10$hP7bSm2zP9G71BznpsDqDei1OD0nda37/3K8lZIDPzYqIerthwhHa', 'udin@udin.com'),
(43, 'gordon', '$2y$10$CqvDQK5mobyjiSB4pBhncueXLf9crDGbk7o2IpXSkxup31uWxM.li', 'gordon@g.com'),
(44, 'a', '$2y$10$WgWSjdJjFSOTUG/idb6a0edw651ai7jftKYMYtoN/ftkewiSdCC1q', 'a'),
(45, 'newUser', '$2y$10$pwYsfS8Zrr.Qnj0K28Lq6u9dcizwhIXYtrZhzhOrfOdRUH2UJcML2', 'dfs'),
(46, 'a', '$2y$10$UIKI0kOShvqa.uoTwa6EUeueBO6LhHA3fwrt6dyHUZ9Ax5nwnkHt.', 'a'),
(47, 'ujang', '$2y$10$Q2VmDEjis0ycqAIxpY537O9LyCfSnW2/qt.WOcuRc2sNnPMxW/2qi', 'ujang@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id_note`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `notes`
--
ALTER TABLE `notes`
  MODIFY `id_note` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
