-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Dec 02, 2024 at 01:44 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `hashedPassword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_user`, `name`, `email`, `hashedPassword`) VALUES
(1, 'khalifa', 'khalifa@gmail.com', '$2a$12$CNA5/zjvVRBWwbDnF9sR3eyludUbfk3e2Z22r9qUqsyeDgX3jCoOG'),
(5, 'ayoub', 'ayoub@gmail.com', '$2a$12$VJ.Z9MtVGBbBZfKBZSzaTO2GeHcZJVFPy2GO9CrjrdkgaI/ojczOG'),
(6, 'said', 'said@gmail.com', '$2a$12$OE7IN.S5slCmdsfPc5x.ReHnnwYV4fO80wCLVOrI/5eXEDPXop6c2'),
(7, 'adil', 'adil@gmail.com', '$2a$12$elj6tQ0.i53MIzezPHgwju8U1mn3GGbw2wAwtXQIynPRMGHSQS2lK'),
(8, 'rachid', 'rachid@gmail.com', '$2a$12$akY62WZQGKRga/uZ5usXLe2nc6YUhuKoQYajYzkxzvJVnF7VVUxTm'),
(9, 'najat', 'najat@gmail.com', '$2a$12$1JfITxVCF4okW4gdLCgSZOl56eEefSSj.UXThjYkAkIe00kUUaToi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
