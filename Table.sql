CREATE DATABASE IF NOT EXISTS `Test`;

USE `Test`;

CREATE TABLE IF NOT EXISTS `usercredentails` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `useruid` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `loginid` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userUID_UNIQUE` (`useruid`),
  UNIQUE KEY `loginID_UNIQUE` (`loginid`)
);

INSERT INTO `test`.`usercredentails` (`id`, `useruid`, `username`, `password`, `phonenumber`, `loginid`, `address`, `firstname`, `lastname`) VALUES 
('1', 'user12345', 'Jagan', 'Test', '1234567890', 'Jaganp', 'TRT', 'Jaga', 'N'),
('2', '953145c0-e0d0-4a37-a68e-5d38e9e62698', 'Admin', 'Admin', '0123456789', 'Admin1234', 'Address', 'Admin', 'A'),
('3', '78ab4637-292a-4d4a-955b-18f3b827cf06', 'Test1', 'Test', '0123456789', 'Test1234', 'Address', 'Test', 'T');


CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `phoneNo` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `userUID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `test`.`users` (`id`, `address`, `phoneNo`, `userName`, `userUID`) VALUES 
('1', 'Tiruttani', '1234567890', 'Jagadeesh', '20241230101458'),
('2', 'Chennai', '1234567890', 'Test one', '20241230101824');

