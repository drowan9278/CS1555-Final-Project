CREATE OR REPLACE TRIGGER trig_BuyCoffee
	AFTER INSERT ON BuyCoffee
	FOR EACH ROW
	VALUE1 INTEGER,
	VALUE2 INTEGER,
	VALUE3 INTEGER,
	VALUE4 INTEGER
	BEGIN 
	    --Grab Customer id
		VALUE1 = (SELECT Customer_ID
		            FROM PURCHASE
		            WHERE :new.Purchase_id = Purchase.id);
		VALUE2 = (SELECT Rewards_Points
		            FROM COFFEE
		            WHERE :new.coffee_id = coffee_id);
		VALUE3 = (SELECT Booster_Factor
		            FROM Customer JOIN MEMBERLEVEL 
		                on CUSTOMER.MemberLevel_ID = MEMBERLEVEL.MemberLevel_ID
		            WHERE value1 = customer.customer_ID);
	    IF(:new.COFFEE_ID IN (SELECT COFFEE_ID FROM PROMOTEFOR)
	        VALUE4 = 2;
	    ELSE
	        VALUE4 = 1;
	    END IF;
		UPDATE CUSTOMER SET TOTAL_POINTS = (value2 * :new.Product_Quantity * value3 * value4)
		WHERE value1 = CUSTOMER_ID;
		commit;
	END;
CREATE OR REPLACE SEQUENCE STORE_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER STORE_TRIG
	BEFORE INSERT ON Store
	FOR EACH ROW
	BEGIN 
		:new.Store_ID = STORE_SEQ.nextval;
	END;
/



CREATE OR REPLACE SEQUENCE COFFEE_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER COFFEE_TRIG
	BEFORE INSERT ON Coffee
	FOR EACH ROW
	BEGIN 
		:new.Coffee_ID = COFFEE_SEQ.nextval;
	END;
/



CREATE OR REPLACE SEQUENCE PROMOTION_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER PROMOTION_TRIG
	BEFORE INSERT ON Promotion
	FOR EACH ROW
	BEGIN 
		:new.Promotion_ID = PROMOTION_SEQ.nextval;
	END;
/



CREATE OR REPLACE SEQUENCE MEMBERLEVEL_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER MEMBERLEVEL_TRIG
	BEFORE INSERT ON MemberLevel
	FOR EACH ROW
	BEGIN 
		:new.MemberLevel_ID = MEMBERLEVEL_SEQ.nextval;
	END;
/

CREATE OR REPLACE SEQUENCE CUSTOMER_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER CUSTOMER_TRIG
	BEFORE INSERT ON Customer
	FOR EACH ROW
	BEGIN 
		:new.Customer_ID = CUSTOMER_SEQ.nextval;
	END;
/

CREATE OR REPLACE SEQUENCE PURCHASE_SEQ
	START WITH     1
	INCREMENT BY   1;

CREATE OR REPLACE TRIGGER Purchase_TRIG
	BEFORE INSERT ON Purchase
	FOR EACH ROW
	BEGIN 
		:new.Purchase_ID = PURCHASE_SEQ.nextval;
	END;
/










