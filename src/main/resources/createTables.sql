CREATE TABLE team
(
    id SERIAL PRIMARY KEY,
    name      varchar(25) NOT NULL,
    city      varchar(25) NOT NULL,
    country   varchar(25) NOT NULL,
    account   FLOAT,
    commission_transfer FLOAT NOT NULL
);


CREATE TABLE players
(
    id SERIAL PRIMARY KEY,
    name                 varchar(25) NOT NULL,
    last_name            varchar(35) NOT NULL,
    age                  int         NOT NULL,
    career_start_date    DATE        NOT NULL,
    team_id              int         NOT NULL,
    FOREIGN KEY (team_id) REFERENCES team (id)
);