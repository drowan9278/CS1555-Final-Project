import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.xml.transform.Result;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 * This was created for CS1555 Final Project By Arie, Bin, and David
 * Team Number: P25
 */
public class BoutiqueCoffee {

	private static Connection dbconn;
	private static String username = "dar172";//username
	private static String pass = "4149955";//PeopleSoft Number
	private static Statement statement;

	public BoutiqueCoffee() {
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";

			dbconn = DriverManager.getConnection(url, username, pass);
			dbconn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//@return the auto-generated ID of this store or -1 if the operation is not possible or failed
	public int addStore(String name, String address, String storeType, long gpsLong, long gpsLat) {
		try {
			String query = "Insert Into Store values(1,'"+ name + "','" + address + "','" + storeType + "'," + gpsLong + "," + gpsLat + ")";
			String returnID[] = { "Store_ID" };
			PreparedStatement ps = dbconn.prepareStatement(query, returnID);
			ps.execute();
			ResultSet result = ps.getGeneratedKeys();
			if(result.next()){
				System.out.println(result.getInt(1));
				return result.getInt(1);
			}
		} catch (SQLException e) {
			return -1;
		}
		return -1;
	}

	//@return the auto-generated ID of this coffee or -1 if the operation is not possible or failed
	public int addCoffee(String name, String description, int intensity,double price, double rewardPoints, double redeemPoints) {
		try {
			String query = "insert into Coffee values(1,'" + name + "','" + description + "'," + intensity + ","+price+"," + rewardPoints + "," + redeemPoints + ")";
			String returnID[] = { "Coffee_ID" };
			PreparedStatement ps = dbconn.prepareStatement(query, returnID);
			ps.execute();
			ResultSet result = ps.getGeneratedKeys();
			if(result.next()){
				System.out.println(result.getInt(1));
				return result.getInt(1);
			}
		} catch (SQLException e) {
			return -1;
		}


		return -1;
	}

	//@return 1 if the operation succeeded or -1 if the operation is not possible or failed
	//Did not test
	public int offerCoffee(int storeId, int coffeeId) {
		try {
			PreparedStatement stmt;

			String query = "INSERT INTO offercoffee VALUES ( " + storeId + " , " + coffeeId + ")";
			stmt = dbconn.prepareStatement(query);
			stmt.execute();

		} catch (SQLException e) {
			return -1;
		}
		return 1;
	}

	//@return the auto-generated ID of this promotion or -1 if the operation is not possible or failed
	public int addPromotion(String name, Date startDate, Date endDate) {
		try {
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
			String query = "insert into Promotion values(1,'" + name + "',to_date('" + df.format(startDate) + "','DD-MON-YYYY HH24:MI:SS'), to_date('" + df.format(endDate) + "','DD-MON-YYYY HH24:MI:SS') )";
			String returnID[] = { "Promotion_ID" };
			PreparedStatement ps = dbconn.prepareStatement(query, returnID);
			ps.execute();
			ResultSet result = ps.getGeneratedKeys();
			System.out.println("Here");
			if(result.next()) {
				System.out.println(result.getInt(1));
				return result.getInt(1);
			}
		} catch (SQLException e) {
			return -1;
		}

		return -1;
	}

	//Did not test
	//@return 1 if the operation succeeded or -1 if the operation is not possible or failed
	public int promoteFor(int promotionId, int coFFeeId) {
		try {
			statement = dbconn.createStatement();
			String query = "insert into promoteFor values("+ promotionId+" , " + coFFeeId +" )";
			statement.execute(query);
		} catch (SQLException e) {
		   return -1;
		}
		return 1;
	}

	//Did not test
	//@return 1 if the operation succeeded or -1 if the operation is not possible or failed
	public int hasPromotion(int storeId, int promotionId) {
		try {

			String query = "insert into hasPromotion values("+ storeId+" , " + promotionId +" )";
			PreparedStatement stmt = dbconn.prepareStatement(query);
			stmt.execute();
		} catch (SQLException e) {
			return -1;
		}
		return 1;
	}

	//@return the auto-generated ID of this member level or -1 if the operation is not possible or failed
	public int addMemberLevel(String name, double boosterFactor) {
		try {
			String query = "insert into MemberLevel values(1,'"+ name+"' , " + boosterFactor +" )";
			String returnID[] = { "MemberLevel_ID" };
			PreparedStatement ps = dbconn.prepareStatement(query, returnID);
			ps.execute();
			ResultSet result = ps.getGeneratedKeys();
			if(result.next()){
				System.out.println(result.getInt(1));
				return result.getInt(1);
			}
		} catch (SQLException e) {
			return -1;
		}
		return -1;


	}

	//@return the auto-generated ID of this customer or -1 if the operation is not possible or failed
	public int addCustomer(String FirstName, String lastName, String email, int memberLevelId, double totalPoints) {
		try {
			String query = "insert into Customer values(1,'"+ FirstName+"' , '" + lastName +"','"+email+"' , " + memberLevelId+","+totalPoints+" )";
			String returnID[] = { "Customer_ID" };
			PreparedStatement ps = dbconn.prepareStatement(query, returnID);
			ps.execute();
			ResultSet result = ps.getGeneratedKeys();
			if(result.next()) {
				System.out.println(result.getInt(1));
				return result.getInt(1);
			}
		} catch (SQLException e) {
			return -1;
		}
		return -1;

	}

	//@return the auto-generated ID of this purchase or -1 if the operation is not possible or failed

	//Probably need testing after I modified the auto-generated ID in this method?? The ID thats generated should work without fail though.

	public int addPurchase(int customerId, int storeId, Date purchaseTime, List<Integer> coffeeIds, List<Integer> purchaseQuantities, List<Integer> redeemQuantities) {
		try {
			if(customerId<0 || storeId<0)
				return -1;

			dbconn.setAutoCommit(false);
			Iterator it = coffeeIds.iterator();
			statement = dbconn.createStatement();
			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
			String query = "INSERT INTO PURCHASE VALUES(1,"+customerId+","+storeId+",to_date('"+df.format(purchaseTime)+"','DD-MON-YYYY HH24:MI:SS'))";
			int purchaseID=0;

			//Old Code:
				/* statement.executeQuery(query);
				ResultSet id = statement.executeQuery("SELECT PURCHASE_SEQ.currval FROM DUAL");
				id.next();
				purchaseID = id.getInt(1);
				*/

			//Newer Code that has one less query
			String returnID[] = { "Purchase_ID" };
			PreparedStatement ps = dbconn.prepareStatement(query, returnID);
			ps.execute();
			ResultSet result = ps.getGeneratedKeys();
			if(result.next()) {
				System.out.println(result.getInt(1));
				purchaseID = result.getInt(1);
			}

			Iterator coffeeIDs = coffeeIds.iterator();
			Iterator purchaseAmt = purchaseQuantities.iterator();
			Iterator redeem = redeemQuantities.iterator();
			while(coffeeIDs.hasNext()){
				query = "INSERT INTO BUYCOFFEE VALUES("+ purchaseID+","+coffeeIDs.next()+","+purchaseAmt.next()+","+redeem.next()+")";
				statement.executeQuery(query);
			}
			dbconn.commit();
			dbconn.setAutoCommit(true);

			return purchaseID;

		} catch (SQLException e) {
			try {
				dbconn.rollback();

			} catch (SQLException e1) {

				return -1;
			}

			return -1;
		}
	}

	//@return a list of ID of all coffees in the database. It returns an empty list if no coffee is in the database or the operation failed
	public List<Integer> getCoffees() {
		List<Integer> coffees = new ArrayList<Integer>();
		try {
			statement = dbconn.createStatement();
			String query = "SELECT coffee_id FROM COFFEE";
			ResultSet results = statement.executeQuery(query);
			while(results.next()){
				coffees.add(results.getInt(1));
			}
		} catch (SQLException e) {
			return coffees;
		}
		return coffees;

	}

	//@return a list of ID of all coffees, each of which has both keywords in its name, in the database. It returns an empty
	//list if no coffee satisfying the conditions is in the database or the operation failed
	public List<Integer> getCoffeesByKeywords(String keyword1, String keyword2) {
		List<Integer> coffeeList = new ArrayList<Integer>();
		try{
			CallableStatement stmt = dbconn.prepareCall("BEGIN FIND_COFFEE_BY_DESC(?,?,?); END;");
			stmt.setString(1,keyword1);
			stmt.setString(2,keyword2);
			stmt.registerOutParameter(3, OracleTypes.CURSOR);
			stmt.execute();
			ResultSet resultSet= ((OracleCallableStatement)stmt).getCursor(3);
			while(resultSet.next()){
			System.out.println(resultSet.getInt("COFFEE_ID"));
			coffeeList.add(resultSet.getInt("COFFEE_ID"));
			}
		}catch(Exception e){
			return coffeeList;
		}
		return coffeeList;
	}


	//@return the total points of the customer identified by the customerId or -1 if the operation is not possible or failed
	public double getPointsByCustomerId(int customerId) {
		ResultSet results ;
		if(customerId<0)
			return-1;
		try {
			statement = dbconn.createStatement();
			String query = "SELECT TOTAL_POINTS FROM CUSTOMER WHERE Customer_id = "+customerId;
			results = statement.executeQuery(query);
			if(results.next()) return results.getInt(1);
		} catch (SQLException e) {
			return -1;
		}
		return -1;
	}

	//Need to add into a list and return.
	public List<Integer> getTopKStoresInPastXMonth(int k, int x) {
		try {
			if(k < 0 || x<0)
				return new ArrayList<>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH,-x);
			dbconn.setAutoCommit(false);

			statement = dbconn.createStatement();

			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
			Date d = cal.getTime();//intialize your date to any date
			Date dateBefore = new Date(d.getTime() - x * 24 * 3600 * 1000  );
			String query = "CREATE OR REPLACE VIEW SALES (Total, StoreID) AS \n" +
								"SELECT SUM(TOTAL) as TOTAL, Store_ID as StoreID \n" +
								"FROM " +
									"(SELECT (BC.Purchase_Quantity * C.Price) as TOTAL, P.Store_ID as Store_ID " +
									"FROM PURCHASE P " +
									"JOIN BUYCOFFEE BC ON P.Purchase_ID = BC.Purchase_ID \n" +
									"JOIN COFFEE C ON BC.COFFEE_ID = C.COFFEE_ID \n" +
									"WHERE BC.Purchase_Quantity > 0 AND P.PURCHASE_TIME>to_date('"+df.format(d)+"','DD-MON-YYYY HH24:MI:SS')) " +
								"GROUP BY Store_ID " +
								"ORDER BY TOTAL DESC";
			System.out.println(query);

			statement.executeQuery(query);

			statement.setFetchSize(50);

			ResultSet resultSet;
			CallableStatement stmt = dbconn.prepareCall("BEGIN STORE_EARNING_RANK(?,?); END;");
			stmt.setInt(1,k);
            List<Integer> storeids = new ArrayList<>();
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			resultSet= ((OracleCallableStatement)stmt).getCursor(2);
			while (resultSet.next()){
			   storeids.add(resultSet.getInt(1));
			}
			return storeids;
		} catch (SQLException e) {

		}

		return new ArrayList<>();
	}

	//Need to add to a list and return
	public List<Integer> getTopKCustomersInPastXMonth(int k, int x) {
		try {
			if(k < 0 || x<0)
				return new ArrayList<>();
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH,-x);
			dbconn.setAutoCommit(false);

			statement = dbconn.createStatement();

			DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
			Date d = cal.getTime();//intialize your date to any date
			Date dateBefore = new Date(d.getTime() - x * 24 * 3600 * 1000  );
			String query = "CREATE OR REPLACE VIEW CUST_RANK (CUSTOMER_ID, TOTAL) AS SELECT CUSTOMER_ID, SUM(TOTAL) AS TOTAL FROM (SELECT (CO.PRICE*B.PURCHASE_QUANTITY) AS TOTAL, C.CUSTOMER_ID AS CUSTOMER_ID FROM CUSTOMER C JOIN PURCHASE P ON C.CUSTOMER_ID=P.CUSTOMER_ID JOIN BUYCOFFEE B ON B.PURCHASE_ID = P.PURCHASE_ID JOIN COFFEE CO ON CO.COFFEE_ID = B.COFFEE_ID WHERE B.PURCHASE_QUANTITY>0 AND P.PURCHASE_TIME>to_date('"+df.format(d)+"','DD-MON-YYYY HH24:MI:SS')) GROUP BY CUSTOMER_ID ORDER BY TOTAL DESC";
			statement.executeQuery(query);
			statement.setFetchSize(50);

			ResultSet resultSet;
			CallableStatement stmt = dbconn.prepareCall("BEGIN customer_spent(?,?); END;");
			stmt.setInt(1,k);

			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			resultSet= ((OracleCallableStatement)stmt).getCursor(2);

			List<Integer> cstomer = new ArrayList<>();
			while(resultSet.next()){
			   cstomer.add(resultSet.getInt("CUSTOMER_ID"));
			}
			return  cstomer;
		} catch (SQLException e) {
			return new ArrayList<>();
		}
	}

	public static void main(String[] args) {
		BoutiqueCoffee db = new BoutiqueCoffee();
		System.out.println(db.addStore("New Store","Liberty Avenue","Test); DROP TABLE * CASCADE CONSTRAINTS --",300,500));
		//System.out.println(db.addCoffee("Testfee","Fortnite Cena Johnny", 3, 50.3, 2, 2));

		//Testing addPromotion
		//Date dateNow = Calendar.getInstance().getTime();//intialize your date to any date
		//Date dateBefore = new Date(dateNow.getTime() - 30 * 24 * 3600 * 1000  );
		//System.out.println(db.addPromotion("Not Recently Added", dateBefore, dateNow));

		//System.out.println(db.addMemberLevel("Testing Member Level", 54.3));

		//System.out.println(db.addCustomer("John", "Cena", "FortniteHero@GG.EZ", 2, 3001));

		//List<Integer> coffee = db.getCoffees();
		//for(int i = 0; i<coffee.size(); i++){
		//	System.out.println(coffee.get(i));
		//}


		//List<Integer> coffeeList = db.getCoffeesByKeywords("Free","Fortnite");
		//System.out.println("First Call: None should print");
		//for(int i = 0; i<coffeeList.size(); i++){
		//	System.out.println(coffeeList.get(i));
		//}
		//System.out.println("Second Call: Something should print");
		//db.getCoffeesByKeywords("Cena","Fortnite");
		//for(int i = 0; i<coffeeList.size(); i++){
		//	System.out.println(coffeeList.get(i));
		//}

		//db.getTopKCustomersInPastXMonth(1,78);

		//System.out.println(db.getPointsByCustomerId(8));
	}
}
