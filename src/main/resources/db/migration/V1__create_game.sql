create table team (
  team_name VARCHAR(200) PRIMARY KEY,
);

create table players (
  id UUID PRIMARY KEY,
  name VARCHAR(200),
  team_name VARCHAR(200),
  FOREIGN KEY (team_name) REFERENCES team(team_name)
);

create table game (
  id UUID PRIMARY KEY,
  team1 VARCHAR(200),
  team2 VARCHAR(200),
  game_start_time DATETIME,
  game_type char(10),
  FOREIGN KEY (team1) REFERENCES team(team_name),
  FOREIGN KEY (team2) REFERENCES team(team_name)
);

