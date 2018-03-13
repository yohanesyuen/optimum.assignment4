DROP TABLE IF EXISTS `USERS`;

CREATE TABLE IF NOT EXISTS `USERS` (
  `userID` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) DEFAULT NULL,
  `nric` VARCHAR(9) DEFAULT NULL UNIQUE,
  `email` VARCHAR(255) DEFAULT NULL UNIQUE,
  `dob` DATE DEFAULT NULL,
  `mobile` VARCHAR(8) DEFAULT NULL,
  `password` VARCHAR(255) DEFAULT NULL,
  `role` VARCHAR(6) DEFAULT 'User',
  `security_question` VARCHAR(255) DEFAULT NULL,
  `security_answer` VARCHAR(255) DEFAULT NULL,
  `first_login` BOOLEAN DEFAULT true,
  `status` VARCHAR(10) DEFAULT 'active',
  `login_attempts` INT(10) DEFAULT 0,
  PRIMARY KEY (`userID`)
) AUTO_INCREMENT=1;

INSERT INTO `USERS` (`name`, `email`, `password`, `role`) VALUES
('Admin', 'admin', 'admin', 'Admin');
