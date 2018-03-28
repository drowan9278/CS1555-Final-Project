<<<<<<< HEAD
<<<<<<< HEAD
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
=======
=======
>>>>>>> 22db012f1eef74d9af168d3dddbab8ecbd9384fb
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


<<<<<<< HEAD
>>>>>>> 22db012f1eef74d9af168d3dddbab8ecbd9384fb
=======
>>>>>>> 22db012f1eef74d9af168d3dddbab8ecbd9384fb
