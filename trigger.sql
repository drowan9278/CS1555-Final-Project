CREATE OR REPLACE SEQUENCE STORE_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER STORE_TRIG
	BEFORE INSERT ON Store
	FOR EACH ROW
	BEGIN 
		INSERT INTO Store
  		VALUES (STORE_SEQ.nextval, :new.Name, :new.Address, :new.Store_Type, :new.GPS_Long, :new.GPS_Lat);
	END;
/

CREATE OR REPLACE SEQUENCE COFFEE_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER COFFEE_TRIG
	BEFORE INSERT ON Coffee
	FOR EACH ROW
	BEGIN 
		INSERT INTO Coffee
  		VALUES (COFFEE_SEQ.nextval, :new.Name, :new.Description, :new.Intensity, :new.Price, :new.Reward_Points, :new.Redeem_Points);
	END;
/





