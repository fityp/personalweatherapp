use pwsdb;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Station;
DROP TABLE IF EXISTS StationData;
DROP TABLE IF EXISTS UserStationMapping;
DROP TABLE IF EXISTS UserCity;
DROP TABLE IF EXISTS City;

CREATE TABLE  IF NOT EXISTS User
(
user_id INTEGER,
user_name VARCHAR (255),
added_on DATE,
email VARCHAR (255),
password VARCHAR (255),
CONSTRAINT User_pkey PRIMARY KEY (user_id)
);

CREATE TABLE  IF NOT EXISTS StationData
(
station_id INTEGER,
gethered_date DATE,
wh65batt FLOAT,
tempf FLOAT,
humidity FLOAT,
winddir FLOAT,
windspeedmph FLOAT,
windgustmph FLOAT,
maxdailygust FLOAT,
solarradiation FLOAT,
uv FLOAT,
rainratein FLOAT,
eventrainin FLOAT,
hourlyrainin FLOAT,
dailyrainin FLOAT,
weeklyrainin FLOAT,
monthlyrainin FLOAT,
yearlyrainin FLOAT,
totalrainin FLOAT,
tempinf FLOAT,
humidityin FLOAT,
baromrelin FLOAT,
baromabsin FLOAT,
CONSTRAINT StationData_pkey PRIMARY KEY (station_id,gethered_date)
);

CREATE TABLE  IF NOT EXISTS Station
(
station_id INTEGER,
stationtype VARCHAR (255),
model VARCHAR (255),
freq VARCHAR (255),
PASSKEY VARCHAR (255),
ZonedDateTime DATE,
date_created DATE,
CONSTRAINT Station_pkey PRIMARY KEY (station_id)
);

CREATE TABLE  IF NOT EXISTS UserStationMapping
(
station_id INTEGER,
user_id INTEGER,
CONSTRAINT UserStationMapping_pkey PRIMARY KEY (station_id,user_id)
);

CREATE TABLE  IF NOT EXISTS City
(
city_id INTEGER,
city_name VARCHAR (255),
zip INTEGER,
country VARCHAR (255),
CONSTRAINT City_pkey PRIMARY KEY (city_id)
);

CREATE TABLE  IF NOT EXISTS UserCity
(
user_id INTEGER,
city_id INTEGER,
CONSTRAINT UserCity_pkey PRIMARY KEY (user_id,city_id)
);

ALTER TABLE StationData ADD FOREIGN KEY (station_id) REFERENCES Station (station_id);

ALTER TABLE UserCity ADD FOREIGN KEY (user_id) REFERENCES User (user_id);

ALTER TABLE UserStationMapping ADD FOREIGN KEY (user_id) REFERENCES User (user_id);

ALTER TABLE UserStationMapping ADD FOREIGN KEY (station_id) REFERENCES Station (station_id);

ALTER TABLE UserCity ADD FOREIGN KEY (city_id) REFERENCES City (city_id);
