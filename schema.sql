drop table Store cascade constraints;
drop table Coffee cascade constraints;
drop table Promotion cascade constraints;
drop table MemberLevel cascade constraints;
drop table Customer cascade constraints;
drop table Purchase cascade constraints;
drop table OfferCoffee cascade constraints;
drop table HasPromotion cascade constraints;
drop table PromoteFor cascade constraints;
drop table BuyCoffee cascade constraints;

create table Store (
	Store_ID  int,
	Name	varchar2(20),
	Address varchar2(20),
	Store_Type varchar2(20),
	GPS_Long float,
	GPS_Lat float,
	Constraint STORE_PK primary key (Store_ID)
);

create table Coffee (
	Coffee_ID int,
	Name varchar2(20),
	Description varchar2(20),
	Intensity int,
	Price float,
	Reward_Points floats,
	Redeem_Points floats,
	Constraint COFFEE_PK primary key (Coffee_ID)
);

create table Promotion (
	Promotion_ID int,
	Name varchar2(20),
	Start date,
	End date,
	Constraint PROMOTION_PK primary key (Promotion_ID)
);

create table MemberLevel(
	MemberLevel_ID int,
	Name varchar2(20),
	Booster_Factor float,
	Constraint MEMBERLEVEL_PK primary key (MemberLevel_ID)
);

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

CREATE TABLE HasPromotion (
    Store_ID INT,
    Promotion_ID INT,
    CONSTRAINT HASPROM_pk PRIMARY KEY (Store_ID,Promotion_ID),
    CONSTRAINT storeid_fk FOREIGN KEY (Store_ID) REFERENCES Store(Store_ID),
    CONSTRAINT promid_fk FOREIGN KEY (Promotion_ID) REFERENCES Promotion(Promotion_ID)
);
CREATE TABLE PromoteFor (
    Promotion_ID INT,
    Coffee_ID INT,
    CONSTRAINT promotePK PRIMARY KEY (Promotion_ID, Coffe_ID),
    CONSTRAINT promFK FOREIGN KEY (Promotion_ID) REFERENCES Promotion(Promotion_ID),
    CONSTRAINT coffeeFK FOREIGN KEY (Coffee_ID) REFERENCES Coffee(Coffee_ID)
);
CREATE TABLE BuyCoffee(
    Purchase_ID int,
    Coffee_ID int,
    Purchase_Quantity int,
    Redeem_Quantity int,
    CONSTRAINT buycoffeePK PRIMARY KEY (Purchase_ID,Coffee_ID),
    CONSTRAINT purchaseidFK FOREIGN KEY (Purchase_ID) REFERENCES Purchase(Purchase_ID),
    CONSTRAINT coffeeidFK FOREIGN KEY (Coffee_ID) REFERENCES Coffee(Coffee_ID)
);
