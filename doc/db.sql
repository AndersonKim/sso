slave_1
CREATE DATABASE slave_1
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci
GO
user slave_1
CREATE TABLE s1user  (
	name	varchar(32) NULL,
	pass	varchar(32) NULL,
	cert	varchar(18) NULL,
	uuid      	varchar(32) NOT NULL
	);
INSERT INTO `slave_1`.`s1user`(`uuid`, `pass`, `name`,`cert`)
VALUES('1', 'ander1', 'ander1','610203199510170013')
GO


slave_2
CREATE DATABASE slave_2
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci
GO
use slave_2
CREATE TABLE s2user  (
	name	varchar(32) NULL,
	pass	varchar(32) NULL,
	cert	varchar(18) NULL,
	uuid      	varchar(32) NOT NULL
	);
INSERT INTO `slave_2`.`s2user`(`uuid`, `pass`, `name`,`cert`)
VALUES('1', 'ander2', 'ander2','610203199510170013')
GO

sso_server
CREATE DATABASE sso_server
	DEFAULT CHARACTER SET utf8
	DEFAULT COLLATE utf8_general_ci
GO
use sso_server
CREATE TABLE ssouser  (
	name	varchar(32) NULL,
	pass	varchar(32) NULL,
	cert	varchar(18) NULL,
	uuid      	varchar(32) NOT NULL
	);
INSERT INTO `ssouser`.`ssouser`(`uuid`, `pass`, `name`,`cert`)
VALUES('1', 'ander', 'ander','610203199510170013')
GO

CREATE TABLE servertoken  (
	token	varchar(32) NULL,
	userId	varchar(32) NULL,
	id      	varchar(32) NOT NULL
	);
