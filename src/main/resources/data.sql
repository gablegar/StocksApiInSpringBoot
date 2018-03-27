/**
 * CREATE Script for init of DB
 */

-- Create Users

insert into users (id, password, username, role) values (1, 'Stock@p1GeneralUserStore', 'userstore', 'ROLE_STORE');

insert into users (id, password, username, role) values (2, 'Stock@p1GeneralAdmin', 'admin', 'ROLE_ADMIN');

insert into users (id, password, username, role) values (3, 'Stock@p1General', 'generaluser', 'ROLE_USER');