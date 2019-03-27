slave_1
CREATE DATABASE slave_1
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci
GO
user slave_1
CREATE TABLE user  (
	username	varchar(32) NULL,
	password	varchar(32) NULL,
	id      	varchar(32) NOT NULL
	);
INSERT INTO `slave_1`.`user`(`id`, `password`, `username`)
VALUES('1', 'ander1', 'ander1')
GO


slave_2
CREATE DATABASE slave_2
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci
GO
use slave_2
CREATE TABLE dbuser  (
	username	varchar(32) NULL,
	password	varchar(32) NULL,
	id      	varchar(32) NOT NULL
	);
INSERT INTO `slave_2`.`dbuser`(`id`, `password`, `username`)
VALUES('1', 'ander2', 'ander2')
GO

sso_server
CREATE DATABASE sso_server
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci
GO
use sso_server
CREATE TABLE serveruser  (
	username	varchar(32) NULL,
	password	varchar(32) NULL,
	id      	varchar(32) NOT NULL
	);
INSERT INTO `sso_server`.`serveruser`(`id`, `password`, `username`)
VALUES('1', 'ander3', 'ander3')
GO