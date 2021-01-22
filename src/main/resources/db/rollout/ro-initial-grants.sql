/** [jooq ignore start] */
USE mysql;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON *.* TO 'user'@'localhost';
FLUSH PRIVILEGES;
/** [jooq ignore stop] */
