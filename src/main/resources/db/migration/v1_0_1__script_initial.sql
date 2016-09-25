CREATE TABLE users (
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	admin CHAR(3) NOT NULL,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	username VARCHAR(255) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE projects (
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

# insert into users (id, username, password, first_name, last_name, admin) values (1, 'mickey', '$2a$10$ronSixcN1QXpDUN25kRceeCQ4UvVJTdheGxW8knjYbNceemicYaIq', 'Mickey', 'Mouse', 'no');
# insert into users (id, username, password, first_name, last_name, admin) values (2, 'minnie', '$2a$10$SRe96GYN5/22ol/HDH1t/e2yLyVJKQ0v4qZcHvL28rKEyeCL2Tl2m', 'Minnie', 'Mouse', 'no');
# insert into users (id, username, password, first_name, last_name, admin) values (3, 'donald', '$2a$10$ttzq2eNwEqTM0dkng0uHn.r6qd9U89bAFQbZd96p1GOMAzexhFZCS', 'Donald', 'Duck', 'no');
# insert into users (id, username, password, first_name, last_name, admin) values (4, 'daisy', '$2a$10$oU9GHs7f3Y.HHqc4vClKkeCECGuxK8tb1VPK0MlB4Q1GeUhulw2ne', 'Daisy', 'Duck', 'no');
# insert into users (id, username, password, first_name, last_name, admin) values (5, 'clarabelle', '$2a$10$Hp5PRX45WR8fLsdk6JAeMuKCTHpTm4znWwZOcCcbArspRXjeVmdkq', 'Clarabelle', 'Cow', 'no');
# insert into users (id, username, password, first_name, last_name, admin) values (6, 'admin', '$2a$10$1vuvFMbw3zUkb.5mjDuXiuN7H.zNaiir/wWxjbdlC4XM7Xu.iR/ba', 'Super', 'Admin', 'yes');
