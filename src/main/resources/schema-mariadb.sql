CREATE TABLE IF NOT EXISTS `PGI_USERS`(
	`USER_ID`	VARCHAR(45)				NOT NULL,
	`USERNAME`	VARCHAR(45)				NOT NULL,
	`PASSWORD`	VARCHAR(255)			NOT NULL,
	`EMAIL`		VARCHAR(255)			NULL,
	`ENABLED`	INT 					DEFAULT 1	NOT NULL,
	`ROLE_TYPE`	VARCHAR(20)				DEFAULT 'USER' NOT NULL,
	`EMAIL_VERIFIED_YN`	INT				DEFAULT 1	NOT NULL,
	`PROVIDER_TYPE`	VARCHAR(20)			NOT NULL,
	`PROFILE_IMAGE_URL`	VARCHAR(512)	NOT NULL,
	`INS_ID`	VARCHAR(45)				NOT NULL,
	`INS_DT`	TIMESTAMP				NOT NULL,
	`UPD_ID`	VARCHAR(45)				NOT NULL,
	`UPD_DT`	TIMESTAMP				NOT NULL,
	PRIMARY KEY(`USERNAME`)
);

CREATE TABLE IF NOT EXISTS `PGI_USER_REFRESH_TOKEN`(
	`USER_ID`	VARCHAR(45)				NOT NULL,
	`REFRESH_TOKEN`		VARCHAR(1000)	NOT NULL,
	`INS_ID`	VARCHAR(45)				NOT NULL,
	`INS_DT`	TIMESTAMP				NOT NULL,
	`UPD_ID`	VARCHAR(45)				NOT NULL,
	`UPD_DT`	TIMESTAMP				NOT NULL,
	PRIMARY KEY(`USER_ID`)
);

CREATE TABLE IF NOT EXISTS `PGI_AUTHORITIES`(
	`USERNAME`	VARCHAR(45)	NOT NULL,
	`AUTHORITY`	VARCHAR(45) NOT NULL,
	`INS_ID`	VARCHAR(45)		NOT NULL,
	`INS_DT`	TIMESTAMP		NOT NULL,
	`UPD_ID`	VARCHAR(45)		NOT NULL,
	`UPD_DT`	TIMESTAMP		NOT NULL,
	PRIMARY KEY(`USERNAME`)
);

CREATE TABLE IF NOT EXISTS `PGI_MENU` (
  `MENU_CD` 		VARCHAR(50) NOT NULL,
  `UP_MENU_CD` 		VARCHAR(50) NULL,
  `MENU_NAME` 		VARCHAR(50) NOT NULL,
  `URL` 			VARCHAR(100) NULL,
  `SORT_SEQ` 		INT,
  `USE_YN` 			VARCHAR(3) DEFAULT 'Y',
  `INS_ID`			VARCHAR(45)		NOT NULL,
  `INS_DT`			TIMESTAMP		NOT NULL,
  `UPD_ID`			VARCHAR(45)		NOT NULL,
  `UPD_DT`			TIMESTAMP		NOT NULL,
  PRIMARY KEY(MENU_CD)
);

CREATE TABLE IF NOT EXISTS `PGI_PROJECT` (
  `PROJECT_CODE` 		VARCHAR(50)		NOT NULL,
  `PROJECT_NAME` 		VARCHAR(50)		NOT NULL,
  `PROJECT_COMMENT` 	VARCHAR(200),
  `SORT_SEQ` 			INT,
  `INS_ID`				VARCHAR(45)		NOT NULL,
  `INS_DT`				TIMESTAMP		NOT NULL,
  `UPD_ID`				VARCHAR(45)		NOT NULL,
  `UPD_DT`				TIMESTAMP		NOT NULL,
  PRIMARY KEY(PROJECT_CODE)
);

CREATE TABLE IF NOT EXISTS `PGI_PACKAGE` (
  `PROJECT_CODE` 		VARCHAR(50)		NOT NULL,
  `PACKAGE_NAME` 		VARCHAR(200)	NOT NULL,
  `PACKAGE_COMMENT` 	VARCHAR(200),
  `SORT_SEQ` 			INT,
  `INS_ID`				VARCHAR(45)		NOT NULL,
  `INS_DT`				TIMESTAMP		NOT NULL,
  `UPD_ID`				VARCHAR(45)		NOT NULL,
  `UPD_DT`				TIMESTAMP		NOT NULL,
  PRIMARY KEY(PROJECT_CODE, PACKAGE_NAME)
);

CREATE TABLE IF NOT EXISTS `PGI_CLASS` (
  `PROJECT_CODE` 		VARCHAR(50)		NOT NULL,
  `PACKAGE_NAME` 		VARCHAR(200)	NOT NULL,
  `CLASS_NAME` 			VARCHAR(50)		NOT NULL,
  `CLASS_TYPE` 			VARCHAR(50)		NOT NULL,
  `CLASS_COMMENT` 		VARCHAR(200),
  `SORT_SEQ` 			INT,
  `INS_ID`				VARCHAR(45)		NOT NULL,
  `INS_DT`				TIMESTAMP		NOT NULL,
  `UPD_ID`				VARCHAR(45)		NOT NULL,
  `UPD_DT`				TIMESTAMP		NOT NULL,
  PRIMARY KEY(PROJECT_CODE, PACKAGE_NAME, CLASS_NAME)
);

CREATE TABLE IF NOT EXISTS `PGI_CLASS_VAR` (
  `PROJECT_CODE` 				VARCHAR(50)		NOT NULL,
  `PACKAGE_NAME` 				VARCHAR(200)	NOT NULL,
  `CLASS_NAME` 					VARCHAR(50)		NOT NULL,
  `CLASS_VAR` 					VARCHAR(50)		NOT NULL,
  `CLASS_VAR_COMMENT` 			VARCHAR(200),
  `CLASS_VAR_ACCESS_MODIFIER` 	INT				NOT NULL,
  `SORT_SEQ` 					INT,
  `INS_ID`						VARCHAR(45)		NOT NULL,
  `INS_DT`						TIMESTAMP		NOT NULL,
  `UPD_ID`						VARCHAR(45)		NOT NULL,
  `UPD_DT`						TIMESTAMP		NOT NULL,
  PRIMARY KEY(PROJECT_CODE, PACKAGE_NAME, CLASS_NAME, CLASS_VAR)
);

CREATE TABLE IF NOT EXISTS `PGI_METHOD` (
  `PROJECT_CODE` 				VARCHAR(50)		NOT NULL,
  `PACKAGE_NAME` 				VARCHAR(200)	NOT NULL,
  `CLASS_NAME` 					VARCHAR(50)		NOT NULL,
  `METHOD` 						VARCHAR(50)		NOT NULL,
  `METHOD_COMMENT` 				VARCHAR(200),
  `METHOD_ACCESS_MODIFIER` 		VARCHAR(200)	NOT NULL,
  `RETURN_TYPE` 				VARCHAR(200)	NOT NULL,
  `SORT_SEQ` 					INT,
  `INS_ID`						VARCHAR(45)		NOT NULL,
  `INS_DT`						TIMESTAMP		NOT NULL,
  `UPD_ID`						VARCHAR(45)		NOT NULL,
  `UPD_DT`						TIMESTAMP		NOT NULL,
  PRIMARY KEY(PROJECT_CODE, PACKAGE_NAME, CLASS_NAME, METHOD)
);

CREATE TABLE IF NOT EXISTS `PGI_METHOD_VAR` (
  `PROJECT_CODE` 				VARCHAR(50)		NOT NULL,
  `PACKAGE_NAME` 				VARCHAR(200)	NOT NULL,
  `CLASS_NAME` 					VARCHAR(50)		NOT NULL,
  `METHOD` 						VARCHAR(50)		NOT NULL,
  `METHOD_VAR` 					VARCHAR(200)	NOT NULL,
  `VAR_TYPE` 					VARCHAR(200)	NOT NULL,
  `METHOD_VAR_COMMENT` 			VARCHAR(200),
  `SORT_SEQ` 					INT,
  `INS_ID`						VARCHAR(45)		NOT NULL,
  `INS_DT`						TIMESTAMP		NOT NULL,
  `UPD_ID`						VARCHAR(45)		NOT NULL,
  `UPD_DT`						TIMESTAMP		NOT NULL,
  PRIMARY KEY(PROJECT_CODE, PACKAGE_NAME, CLASS_NAME, METHOD, METHOD_VAR)
);

CREATE TABLE IF NOT EXISTS `PGI_RELATION_SHIP` (
  `PROJECT_CODE` 				VARCHAR(50)		NOT NULL,
  `LEFT_PACKAGE` 				VARCHAR(200)	NOT NULL,
  `LEFT_CLASS` 					VARCHAR(50)		NOT NULL,
  `LEFT_NUMBER` 				VARCHAR(50)		NOT NULL,
  `RIGHT_PACKAGE` 				VARCHAR(200)	NOT NULL,
  `RIGHT_CLASS` 				VARCHAR(50)		NOT NULL,
  `RIGHT_NUMBER` 				VARCHAR(50)		NOT NULL,
  `RELATION` 					VARCHAR(200)	NOT NULL,
  `RELATION_SHIP_COMMENT` 		VARCHAR(200),
  `SORT_SEQ` 					INT,
  `INS_ID`						VARCHAR(45)		NOT NULL,
  `INS_DT`						TIMESTAMP		NOT NULL,
  `UPD_ID`						VARCHAR(45)		NOT NULL,
  `UPD_DT`						TIMESTAMP		NOT NULL,
  PRIMARY KEY(PROJECT_CODE, LEFT_PACKAGE, LEFT_CLASS, RIGHT_PACKAGE, RIGHT_CLASS, RELATION)
);

CREATE TABLE IF NOT EXISTS PGI_COMMON_CODE (
	`CODE`						VARCHAR(10) 	NOT NULL,
	`CODE_NAME`					VARCHAR(20) 	NOT NULL,
	`CODE_DESC`					VARCHAR(100)	NULL,
	`SORT_SEQ` 					INT,
	`INS_ID`					VARCHAR(45)		NOT NULL,
  	`INS_DT`					TIMESTAMP		NOT NULL,
  	`UPD_ID`					VARCHAR(45)		NOT NULL,
  	`UPD_DT`					TIMESTAMP		NOT NULL,
	PRIMARY KEY(CODE)
);

CREATE TABLE IF NOT EXISTS PGI_COMMON_CODE_DETAIL (
	`CODE`						VARCHAR(10)	 	NOT NULL,
	`CODE_DETAIL_CD`			VARCHAR(10) 	NOT NULL,
	`CODE_DETAIL_NAME`			VARCHAR(20) 	NOT NULL,
	`CODE_DESC`					VARCHAR(100) 	NULL,
	`SORT_SEQ` 					INT,
	`INS_ID`					VARCHAR(45)		NOT NULL,
  	`INS_DT`					TIMESTAMP		NOT NULL,
  	`UPD_ID`					VARCHAR(45)		NOT NULL,
  	`UPD_DT`					TIMESTAMP		NOT NULL,
	PRIMARY KEY(CODE, CODE_DETAIL_CD)
);

INSERT INTO PGI_COMMON_CODE(CODE, CODE_NAME, CODE_DESC, SORT_SEQ, INS_ID, INS_DT, UPD_ID, UPD_DT) VALUES('CD001', 'Yes/No', 'Yes/No', 1, 'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO PGI_COMMON_CODE_DETAIL(CODE, CODE_DETAIL_CD, CODE_DETAIL_NAME, CODE_DESC, SORT_SEQ, INS_ID, INS_DT, UPD_ID, UPD_DT) VALUES('CD001', 'Y', 'Yes', 'Yes', 1,'SYSTEM', now(), 'SYSTEM', now());
INSERT INTO PGI_COMMON_CODE_DETAIL(CODE, CODE_DETAIL_CD, CODE_DETAIL_NAME, CODE_DESC, SORT_SEQ, INS_ID, INS_DT, UPD_ID, UPD_DT) VALUES('CD001', 'N', 'No', 'No', 2,'SYSTEM', now(), 'SYSTEM', now());