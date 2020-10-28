-- Drop Log table statement
Drop TABLE LogTable;

-- Create Log table statement
Create TABLE LogTable (
	  Log_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	  Timestamp   TIMESTAMP,
	  UserName VARCHAR(255) NOT NULL,
	  TableName  VARCHAR(255) NOT NULL,
      ActionTaken VARCHAR(225),
	  PrimaryKeyRecord  VARCHAR(255) NOT NULL
);

-- Three statements that drop the three triggers (referred to below as Trigger1, Trigger2, and Trigger3)
drop Trigger TriggerAfterInsertStation;
drop Trigger TriggerBeforeUpdateStation;
drop Trigger TriggerDeleteStation;

-- A trigger that fires on an insert (referred to later as Trigger1) 
DELIMITER $$

CREATE TRIGGER TriggerAfterInsertStation
AFTER INSERT
ON Station
FOR EACH ROW
BEGIN

SELECT concat('station_id = ', New.station_id) INTO @PrimaryKeyRecord;
	
	Insert into LogTable 
	(timestamp, UserName, TableName, ActionTaken, PrimaryKeyRecord)
	values (now(),user_name,"Station","Insert", @PrimaryKeyRecord);

  END$$

DELIMITER ;

-- A trigger that fires on an update (referred to later as Trigger2)
DELIMITER $$

CREATE TRIGGER TriggerBeforeUpdateStation
BEFORE UPDATE
ON Station
  FOR EACH ROW 
  BEGIN
  
 IF NEW.freq < 0 THEN
	  SET NEW.freq = 0;
    END IF;
    
    SELECT concat('station_id = ', New.station_id) INTO @PrimaryKeyRecord;
	Insert into LogTable 
	(timestamp, UserName, TableName, ActionTaken, PrimaryKeyRecord)
	values (now(),user_name,"Station","Update", @PrimaryKeyRecord);

END$$

DELIMITER ;

-- A trigger that fires on a delete (referred to later as Trigger3)
DELIMITER $$

CREATE TRIGGER TriggerDeleteStation
After Delete
ON Station
  FOR EACH ROW 
  BEGIN	

	SELECT concat('station_id = ', Old.station_id) INTO @PrimaryKeyRecord;
    
	Insert into LogTable 
	(timestamp, UserName, TableName, ActionTaken, PrimaryKeyRecord)
	values (now(),user_name,"Station","Delete", @PrimaryKeyRecord);
END$$

DELIMITER ;
