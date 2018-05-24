create table over (
  id UUID PRIMARY KEY,
  number INT not null,
  team_name VARCHAR(200) not null,
  game UUID not null,
  FOREIGN KEY (team_name) REFERENCES team(team_name),
  FOREIGN KEY (game) references game(id)
);

create table ball (
  id UUID PRIMARY KEY,
  over_id UUID not null,
  team_name VARCHAR(200) not null,
  batsman_id UUID,
  score INT not null,
  FOREIGN KEY (team_name) REFERENCES team(team_name),
  FOREIGN KEY (over_id) REFERENCES over(id),
  FOREIGN KEY (batsman_id) REFERENCES players(id),
);
