def generate():
	f = open("script.txt","w+")
	for i in range(3):
		f.write("insert into Store values(1,'StoreName_Test','StoreAddressTest','TestType'," + str(i) + ',' +  str(i) + ');' + "\n")


	for i in range(20):
		f.write("insert into Customer values(1,'First Name','LastName','Email',3,'0');" + "\n")

	for i in range(50):
		f.write("insert into Purchase values(1," + str(i+1) + "," +  str(1) + ",to_date('24-FEB-2015 7:00:00','DD-MON-YYYY HH24:MI:SS'));" + "\n")
	f.close()
generate()

