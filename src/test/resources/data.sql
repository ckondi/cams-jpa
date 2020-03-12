
-- Creating data DMLs

insert into cams.customer (created_date_time, updated_date_time, date_of_birth, first_name, gender, last_name, customer_id) values (now(), now(), '1990-02-09', 'FN1', 'M', 'LN1', 101);
insert into cams.customer (created_date_time, updated_date_time, date_of_birth, first_name, gender, last_name, customer_id) values (now(), now(), '1979-09-11', 'FN2', 'F', 'LN2', 123);

insert into cams.customer_contact_details (customer_customer_id, channel_type, "primary", tag, value, verified) values (101, 'MOBILE', 1, 'cell', '+4475654764892', 0);
insert into cams.customer_contact_details (customer_customer_id, channel_type, "primary", tag, value, verified) values (101, 'SOCIAL', 1, 'insta', 'starsz.wander', 0);

insert into cams.address (address_id, created_date_time, updated_date_time, address_line1, address_line2, city, country, is_active, postcode, customer_id) values (201, now(), now(), 'A1Line1', 'A1Line2', 'C1', 'GB', 1, 'YUYTRT', 123);
insert into cams.address (address_id, created_date_time, updated_date_time, address_line1, address_line2, city, country, is_active, postcode, customer_id) values (202, now(), now(), 'A2Line1', 'A2Line2', 'C2', 'US', 1, 'YUYTRT', 123);
insert into cams.address (address_id, created_date_time, updated_date_time, address_line1, address_line2, city, country, is_active, postcode, customer_id) values (203, now(), now(), 'A3Line1', 'A3Line2', 'C3', 'IN', 1, 'IOU89K', 123);
insert into cams.address (address_id, created_date_time, updated_date_time, address_line1, address_line2, city, country, is_active, postcode, customer_id) values (204, now(), now(), 'A4Line1', 'A4Line2', 'C4', 'GB', 1, 'ASVCFD', 101);