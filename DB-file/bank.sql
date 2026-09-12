-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2025 at 03:17 PM
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
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customerId` varchar(5) NOT NULL,
  `customerName` varchar(15) NOT NULL,
  `balance` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customerId`, `customerName`, `balance`) VALUES
('150', 'Mehedi Dipu', 40000),
('153', 'James Anderson', 10000),
('155', 'Aryan Rovin', 80000),
('160', 'Prateek', 50000),
('162', 'Wei Zhang', 43000),
('166', 'Araf Chowdhury', 20000),
('168', 'Rimon Deb', 24900),
('170', 'Yuki Nakamura', 1000),
('175', 'Mei Liu', 120000),
('180', 'Elizabeth', 46000),
('182', 'Marco Rossi', 1500),
('185', 'Isabella Romano', 2800);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `empId` varchar(4) NOT NULL,
  `employeeName` varchar(15) NOT NULL,
  `designation` varchar(10) NOT NULL,
  `salary` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`empId`, `employeeName`, `designation`, `salary`) VALUES
('100', 'Raja Abbas', 'Manager', 1000000),
('101', 'Iftekhar Amin', 'Accountant', 800000),
('105', 'Shafi Ahmed', 'Cashier', 800000),
('109', 'Toufique Ahmed', 'Cashier', 800000),
('110', 'Yuxuan Song', 'Accountant', 800000),
('115', 'Wenjing Liang', 'Cashier', 800000);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userId` varchar(5) NOT NULL,
  `password` int(8) NOT NULL,
  `status` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userId`, `password`, `status`) VALUES
('100', 11111, 0),
('101', 121212, 1),
('105', 50505, 1),
('109', 718, 1),
('110', 628, 1),
('115', 595, 1),
('150', 106, 2),
('153', 352, 2),
('155', 830, 2),
('160', 704, 2),
('162', 1024, 2),
('166', 488, 2),
('168', 815, 2),
('170', 309, 2),
('175', 1048, 2),
('180', 984, 2),
('182', 975, 2),
('185', 462, 2);

-- --------------------------------------------------------

--
-- Table structure for table `relation`
--

CREATE TABLE `relation` (
  `ecId` varchar(5) NOT NULL,
  `empId` varchar(4) NOT NULL,
  `customerId` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customerId`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`empId`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `relation`
--
ALTER TABLE `relation`
  ADD PRIMARY KEY (`ecId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
