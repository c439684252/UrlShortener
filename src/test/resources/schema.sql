DROP TABLE IF EXISTS url_pair;

create table url_pair
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    long_url    VARCHAR(255) NOT NULL,
    short_url_key   VARCHAR(255) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    access_cnt INT NOT NULL DEFAULT 0,
    last_access_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);