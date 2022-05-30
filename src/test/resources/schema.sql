DROP TABLE IF EXISTS url_pair;

create table url_pair
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    long_url    VARCHAR(255) NOT NULL,
    short_url_key   VARCHAR(255) NOT NULL,
    create_time DATE
);