

import javax.xml.transform.Result;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.*;

/**
 * This was created for CS1555 Final Project By Arie, Bin , and David
 * Team Number: P25
 */
public class BoutiqueCoffee {

    private static Connection dbconn;
    private static String username = "apd29";//username
    private static String pass = "4118451";//PeopleSoft Number
    private static Statement statement;

    public BoutiqueCoffee() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";

            dbconn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addStore(String name, String address, String storeType, long gpsLong, long gpsLat) {
        try {
            statement = dbconn.createStatement();
            String query = "Insert Into Store values(1:"+ name + "','" + address + "','" + storeType + "'," + gpsLong + "," + gpsLat + ");";
            statement.executeQuery(query);
        } catch (SQLException e) {
            return -1;
        }
        return 1;
    }


    public int addCoffee(String name, String description, int intensity, double rewardPoints, double redeemPoints) {
        try {
            statement = dbconn.createStatement();
            String query = "insert into Coffee values(1,'" + name + "','" + description + "'," + intensity + "," + rewardPoints + "," + redeemPoints + ")";
            statement.executeQuery(query);
        } catch (SQLException e) {
            return -1;
        }


        return 1;
    }

    public int offerCoffee(int storeId, int coffeeId) {
        try {
            statement = dbconn.createStatement();
            String query = "INSERT INTO offercoffee VALUES ( " + storeId + " , " + coffeeId + ")";
            ResultSet result = statement.executeQuery(query);

        } catch (SQLException e) {
            return -1;
        }
        return 1;
    }

    public int addPromotion(String name, Date startDate, Date endDate) {
        try {
            statement = dbconn.createStatement();
            String query = "insert into Promotion values(1,'" + name + "',to_date('" + startDate.toString() + "','DD-MON-YYYY HH24:MI:SS'), to_date('" + endDate.toString() + "','DD-MON-YYYY HH24:MI:SS') )";
            statement.execute(query);
        } catch (SQLException e) {
            return -1;
        }

        return 1;
    }

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

    public int hasPromotion(int storeId, int promotionId) {
        try {
            statement = dbconn.createStatement();
            String query = "insert into hasPromotion values("+ storeId+" , " + promotionId +" )";
            statement.execute(query);
        } catch (SQLException e) {
            return -1;
        }
        return 1;
    }

    public int addMemberLevel(String name, double boosterFactor) {
        try {
            statement = dbconn.createStatement();
            String query = "insert into MemberLevel values(1,"+ name+" , " + boosterFactor +" )";
            statement.execute(query);
        } catch (SQLException e) {
            return -1;
        }
        return 1;


    }

    public int addCustomer(String FirstName, String lastName, String email, int memberLevelId, double totalPoints) {

        try {
            statement = dbconn.createStatement();
            String query = "insert into Customer values(1,'"+ FirstName+"' , '" + lastName +"','"+email+"' , " + memberLevelId+","+totalPoints+" )";
            statement.execute(query);
            ResultSet results = statement.executeQuery("SELECT CUSTOMER_SEQ.currval from DUAL");
            results.next();
            return results.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

    }
    //Needs a function to return the auto generater ID
    public int addPurchase(int customerId, int storeId, Date purchaseTime, List<Integer> coffeeIds, List<Integer> purchaseQuantities, List<Integer> redeemQuantities) {
        try {
            dbconn.setAutoCommit(false);
            Iterator it = coffeeIds.iterator();
            statement = dbconn.createStatement();
            String query = "INSERT INTO PURCHASE VALUES(1,"+customerId+","+storeId+",'"+purchaseTime.toString()+"')";
            int purchaseID;
            statement.executeQuery(query);
            ResultSet id = statement.executeQuery("SELECT PURCHASE_SEQ.currval FROM DUAL");
            id.next();
            purchaseID = id.getInt(1);
            Iterator coffeeIDs = coffeeIds.iterator();
            Iterator purchaseAmt = purchaseQuantities.iterator();
            Iterator redeem = redeemQuantities.iterator();
            while(coffeeIDs.hasNext()){
                query = "INSERT INTO BUYCOFFEE VALUES("+ purchaseID+","+coffeeIDs.next()+","+purchaseAmt.next()+","+redeem.next()+")";
                statement.executeQuery(query);
            }
            dbconn.commit();
            dbconn.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                dbconn.rollback();
            } catch (SQLException e1) {
                return -1;
            }
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public List<Integer> getCoffees() {
        List<Integer> coffees = null;
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

    public List<Integer> getCoffeesByKeywords(String keyword1, String keyword2) {

        return null;
    }

    public double getPointsByCustomerId(int customerId) throws SQLException {
        ResultSet results = null;
        try {
            statement = dbconn.createStatement();
            String query = "SELECT TOTAL_POINTS FROM CUSTOMER WHERE Customer_id = "+customerId;
            results = statement.executeQuery(query);
        } catch (SQLException e) {
            return -1;
        }
        if(results.next())
            return results.getInt(1);
        return -1 ;

    }

    public List<Integer> getTopKStoresInPastXMonth(int k, int x) {

        return null;
    }

    public List<Integer> getTopKCustomersInPastXMonth(int k, int x) {

        return null;
    }

    public static void main(String[] args) {
        BoutiqueCoffee db = new BoutiqueCoffee();
        try {
            if(db.getPointsByCustomerId(3) == -1) System.out.println("FAIL");
            else
                System.out.println("PASS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int counter = 1;
            statement = dbconn.createStatement(); //create an instance
            String query = "SELECT * FROM Customer WHERE CUSTOMER_ID = 5"; //sample query one

            ResultSet resultSet = statement.executeQuery(query); //run the query on the DB table
            while (resultSet.next())
            {
                System.out.println("Record " + counter + ": " +
                        resultSet.getString(1) + ", " +
                        resultSet.getString(2) + ", " +
                        resultSet.getString(6));
                counter++;
            }
            System.out.println("Customer ID: "+db.addCustomer("David","Rowan","tt@g",1,0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

