-- phpMyAdmin SQL Dump
-- version 4.0.4.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 16, 2023 at 01:58 PM
-- Server version: 5.6.13
-- PHP Version: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `geeproject`
--
CREATE DATABASE IF NOT EXISTS `geeproject` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `geeproject`;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` varchar(20) NOT NULL,
  `customer_name` varchar(40) NOT NULL,
  `contact` varchar(30) NOT NULL,
  `customer_password` varchar(20) NOT NULL,
  `accountbal` double NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `customer_name`, `contact`, `customer_password`, `accountbal`) VALUES
('061', 'John Barber', '876-865', 'jesus123', 100),
('1000', 'Mikhail', 'mmcdowell@gmail.com', 'null', 0),
('2116', 'Mikhail Luke', '876-865', 'j234', 0),
('4545', 'Mike Milwood', '876554321', 'jeremy123', 10000),
('777777', 'LA', 'lEWis', '7boss', 0);

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE IF NOT EXISTS `equipment` (
  `equipment_id` varchar(7) NOT NULL,
  `equipment_name` varchar(20) NOT NULL,
  `equipment_category` varchar(20) NOT NULL,
  `availability_status` varchar(20) NOT NULL,
  `equipment_cost_per_day` double NOT NULL,
  PRIMARY KEY (`equipment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`equipment_id`, `equipment_name`, `equipment_category`, `availability_status`, `equipment_cost_per_day`) VALUES
('36631', 'Jmba', 'Sound', 'Available', 10000),
('4535', 'Bazel', 'Staging', '0', 10000),
('7367', 'Jmb', 'Sound', 'Available', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `invoice_id` int(7) NOT NULL,
  `customer_id` varchar(7) NOT NULL,
  `date` date NOT NULL,
  `equipment_id` varchar(7) NOT NULL,
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE IF NOT EXISTS `messages` (
  `message_id` varchar(7) NOT NULL,
  `customer_id` varchar(7) NOT NULL,
  `message` varchar(100) NOT NULL,
  `date` varchar(20) NOT NULL,
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`message_id`, `customer_id`, `message`, `date`) VALUES
('0127', '45453', 'I am new here ppl.', '11/89/2020'),
('1237', '4537', 'More money more problems.', '12/03/1962'),
('56398', '2111649', 'I want to rent equipment', '12/04/2023'),
('6835', '97868', 'Meeting example.', '11/11/2023');

-- --------------------------------------------------------

--
-- Table structure for table `rentalrequests`
--

CREATE TABLE IF NOT EXISTS `rentalrequests` (
  `request_id` varchar(20) NOT NULL,
  `customer_id` varchar(20) NOT NULL,
  `equipment_id` varchar(20) NOT NULL,
  `date` varchar(20) NOT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `scheduledequipment`
--

CREATE TABLE IF NOT EXISTS `scheduledequipment` (
  `schedule_id` varchar(7) NOT NULL,
  `date` varchar(20) NOT NULL,
  `equipment_name` varchar(40) NOT NULL,
  `customer_id` varchar(20) NOT NULL,
  `equipment_id` varchar(20) NOT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `scheduledequipment`
--

INSERT INTO `scheduledequipment` (`schedule_id`, `date`, `equipment_name`, `customer_id`, `equipment_id`) VALUES
('2111649', '2023/11/12', 'Hacksaw', '4545', '36631');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `employee_id` varchar(20) NOT NULL,
  `employee_name` varchar(40) NOT NULL,
  `contact` varchar(30) NOT NULL,
  `employee_password` varchar(20) NOT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`employee_id`, `employee_name`, `contact`, `employee_password`) VALUES
('061', 'John Barber', '876-865', 'jesus123'),
('2346', 'Mikhail Mcdowell', '8653429', 'j123');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `transaction_id` varchar(20) NOT NULL,
  `customer_id` varchar(20) NOT NULL,
  `amountPaid` double NOT NULL,
  `equipment_name` varchar(50) NOT NULL,
  `paymentDate` varchar(20) NOT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transaction_id`, `customer_id`, `amountPaid`, `equipment_name`, `paymentDate`) VALUES
('496868', '756768', 867.87, 'Speaker', '12/04/2020'),
('76567', '52535', 167.87, 'Boombox', '12/04/2019'),
('996868', '756768', 567.87, 'Speaker', '12/04/2020');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
