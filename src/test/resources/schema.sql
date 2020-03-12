-- Creating Database and Tables DDLs
CREATE SCHEMA IF NOT EXISTS cams;

CREATE TABLE IF NOT EXISTS cams.`customer` (
   `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
   `created_date_time` datetime NOT NULL,
   `updated_date_time` datetime NOT NULL,
   `date_of_birth` date DEFAULT NULL,
   `first_name` varchar(255) DEFAULT NULL,
   `gender` varchar(255) DEFAULT NULL,
   `last_name` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`customer_id`),
   UNIQUE KEY `cus_fn_ln` (`first_name`,`last_name`)
 );

CREATE TABLE  IF NOT EXISTS cams.`customer_contact_details` (
   `customer_customer_id` bigint(20) NOT NULL,
   `channel_type` varchar(255) DEFAULT NULL,
   "primary" bit(1) DEFAULT NULL,
   `tag` varchar(255) DEFAULT NULL,
   `value` varchar(255) DEFAULT NULL,
   `verified` bit(1) NOT NULL,
   CONSTRAINT `fk_cus` FOREIGN KEY (`customer_customer_id`) REFERENCES `customer` (`customer_id`)
 ) ;

CREATE TABLE  IF NOT EXISTS cams.`address` (
   `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
   `created_date_time` datetime NOT NULL,
   `updated_date_time` datetime NOT NULL,
   `address_line1` varchar(255) DEFAULT NULL,
   `address_line2` varchar(255) DEFAULT NULL,
   `city` varchar(255) DEFAULT NULL,
   `country` varchar(255) DEFAULT NULL,
   `is_active` bit(1) NOT NULL,
   `postcode` varchar(255) DEFAULT NULL,
   `customer_id` bigint(20) NOT NULL,
   PRIMARY KEY (`address_id`),
   CONSTRAINT `cus_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
 ) ;
