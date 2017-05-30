CREATE DATABASE IF NOT EXISTS me;
USE me;

DROP TABLE IF EXISTS `Preference`;
DROP TABLE IF EXISTS `Device`;
DROP TABLE IF EXISTS `User`;
DROP TABLE IF EXISTS `Hardware`;
DROP TABLE IF EXISTS `ExceptionRecord`;

CREATE TABLE `Hardware` (
  `hw_no`           INT(10) UNSIGNED NOT NULL,
  `hw_type`         VARCHAR(50)               DEFAULT NULL,
  `hw_name`         VARCHAR(100)              DEFAULT NULL,
  `hw_madein`       VARCHAR(100)              DEFAULT NULL,
  `hw_manufacturer` VARCHAR(100)              DEFAULT NULL,
  `hw_date`         TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hw_part_no`      VARCHAR(30)               DEFAULT NULL,
  PRIMARY KEY (`hw_no`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `Device` (
  `dv_addr`     INT(10) UNSIGNED NOT NULL,
  `dv_hw_no`    INT(10) UNSIGNED NOT NULL,
  `dv_state`    VARCHAR(100)     NOT NULL DEFAULT 'OFF',
  `dv_position` VARCHAR(100)              DEFAULT NULL,
  PRIMARY KEY (`dv_addr`),
  KEY `fk_device_hardware` (`dv_hw_no`),
  CONSTRAINT `fk_device_hardware` FOREIGN KEY (`dv_hw_no`) REFERENCES `hardware` (`hw_no`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `User` (
  `u_id`     INT(10)  NOT NULL,
  `u_name`   VARCHAR(50)      NOT NULL,
  `u_gender` ENUM ('M', 'F')  NOT NULL,
  `u_phone`  VARCHAR(50)      NOT NULL,
  PRIMARY KEY (`u_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `Preference` (
  `pref_id`      INT(10) UNSIGNED NOT NULL,
  `pref_u_id`    INT(10) UNSIGNED NOT NULL,
  `pref_dv_addr` INT(10) UNSIGNED NOT NULL,
  `pref_date`    TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pref_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
ALTER TABLE `Preference`
  ADD CONSTRAINT `fk_pref_user` FOREIGN KEY (`pref_u_id`) REFERENCES `User` (`u_id`);
ALTER TABLE `Preference`
  ADD CONSTRAINT `fk_pref_device` FOREIGN KEY (`pref_dv_addr`) REFERENCES `Device` (`dv_addr`);


CREATE TABLE `ExceptionRecord` (
  `er_username` VARCHAR(100) NOT NULL,
  `er_device`   VARCHAR(100) NOT NULL,
  `er_cause`    VARCHAR(100) NOT NULL,
  `er_date`     VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `ExceptionRecord` VALUES
  ("张三(1234567890)", "-", "室内温度过高", "2017-05-01 08:00"),
  ("张三(1234567890)", "-", "室内温度过高", "2017-05-01 18:10"),
  ("张三(1234567890)", "-", "室内温度过低", "2017-05-01 18:20"),
  ("张三(1234567890)", "-", "室内温度过低", "2017-05-02 12:10"),
  ("-", "-", "有非户主进行刷卡操作", "2017-05-02 15:09"),
  ("-", "-", "有非户主进行刷卡操作", "2017-05-02 15:13"),
  ("-", "-", "有非户主进行刷卡操作", "2017-05-07 20:09"),
  ("-", "-", "有非户主进行刷卡操作", "2017-05-07 20:10"),
  ("-", "-", "有非户主进行刷卡操作", "2017-05-07 20:11"),
  ("-", "-", "有非户主进行刷卡操作", "2017-05-07 20:21"),
  ("-", "-", "有非户主进行刷卡操作", "2017-05-07 20:22");
