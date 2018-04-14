insert into MemberLevel values(1,'A',1);
insert into MemberLevel values(2,'B',2);
insert into MemberLevel values(3,'C',3);
insert into Coffee values(1,'Promoted','Description',1, 10, 1, 1);
insert into Coffee values(1,'NonPromoted','Description',1, 20, 1, 1);

insert into Store values(1,'StoreName_Test','StoreAddressTest','TestType',0,0);
insert into Store values(1,'StoreName_Test','StoreAddressTest','TestType',1,1);
insert into Store values(1,'StoreName_Test','StoreAddressTest','TestType',2,2);

insert into Promotion values(1,'Test_Promotion',to_date('24-FEB-2015 7:00:00','DD-MON-YYYY HH24:MI:SS'), to_date('28-FEB-2015 7:00:00','DD-MON-YYYY HH24:MI:SS') );

insert into HasPromotion values(1,1);
insert into PromoteFor values(1,1);

insert into Customer values(1,'First Name','LastName','Email',3,'0');
insert into Customer values(1,'First Name','LastName','Email',2,'0');
insert into Customer values(1,'First Name','LastName','Email',3,'0');
insert into Customer values(1,'First Name','LastName','Email',3,'0');

--Purchase (Purchase ID, Customer ID, Store ID, Purchase Time)
insert into Purchase values(1,1,1,to_date('24-MAR-2015 7:00:00','DD-MON-YYYY HH24:MI:SS'));
insert into Purchase values(2,1,1,to_date('25-JAN-2015 7:00:00','DD-MON-YYYY HH24:MI:SS'));
insert into Purchase values(3,2,2,to_date('25-JAN-2015 7:00:00','DD-MON-YYYY HH24:MI:SS'));

--BuyCoffee (Purchase ID, Coffee ID, Purchase Quantity, Redeem Quantity)
insert into BuyCoffee values(1,2,30,0);
insert into BUYCOFFEE values(2,2,3,0);
insert into BUYCOFFEE values(2,1,5,0);
