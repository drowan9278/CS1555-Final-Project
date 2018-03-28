CREATE TABLE Customer(
	Customer_ID int,
	First_Name varchar2(20),
	Last_Name varchar2(20),
	Email varchar2(20),
	MemberLevel_ID int,
	Total_Points float,
	CONSTRAINT CUSTOMER_PK PRIMARY KEY (Customer_ID),
	CONSTRAINT CUSTOMER_FK FOREIGN KEY (MemberLevel_ID) REFERENCES MemberLevel(MemberLevel_ID)
);

CREATE TABLE Purchase(
	Purchase_ID int,
	Customer_ID int,
	Store_ID,
	Purchase_Time date
	CONSTRAINT PURCHASE_PK PRIMARY KEY (Purchase_ID),
	CONSTRAINT PURCHASE_CUSTOMER_FK FOREIGN KEY (Customer_ID) REFERENCES Customer(Customer_ID),
	CONSTRAINT PURCHASE_STORE_FK FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID)
);

CREATE TABLE OfferCoffee(
	Store_ID int,
	Coffee_ID int,
	CONSTRAINT OFFERCOFFEE_PK PRIMARY KEY (Store_ID, Coffee_ID),
	CONSTRAINT OFFERCOFFEE_STORE_FK FOREIGN KEY (Store_ID) REFERENCES Store (Store_ID),
	CONSTRAINT OFFERCOFFEE_COFFEE_FK FOREIGN KEY (Coffee_ID) REFERENCES Coffee (Coffee_ID)
);