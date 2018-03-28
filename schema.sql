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


