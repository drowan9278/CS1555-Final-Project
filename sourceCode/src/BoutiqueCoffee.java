

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

/**
 * This was created for CS1555 Final Project By Arie, Bin , and David
 * Team Number: P25
 */
public class BoutiqueCoffee {

    private static Connection dbconn;
    private static String username = "DAR172";//username
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

    public int addStore(String name, String address, String storeType, long gpsLong, long gpsLat) {
        try {
            statement = dbconn.createStatement();
            String query = "Insert Into Store values(1,'"+ name + "','" + address + "','" + storeType + "'," + gpsLong + "," + gpsLat + ")";
            statement.executeQuery(query);
        } catch (SQLException e) {
            return -1;
        }
        return 1;
    }


    public int addCoffee(String name, String description, int intensity,double price, double rewardPoints, double redeemPoints) {
        try {
            statement = dbconn.createStatement();
            String query = "insert into Coffee values(1,'" + name + "','" + description + "'," + intensity + ","+price+"," + rewardPoints + "," + redeemPoints + ")";
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
            String query = "insert into MemberLevel values(1,'"+ name+"' , " + boosterFactor +" )";
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
            statement.executeQuery(query);
            ResultSet results = statement.executeQuery("SELECT CUSTOMER_SEQ.currval from DUAL");
            results.next();
            return results.getInt(1);
        } catch (SQLException e) {
            return -1;
        }

    }
    //Needs a function to return the auto generater ID
    public int addPurchase(int customerId, int storeId, Date purchaseTime, List<Integer> coffeeIds, List<Integer> purchaseQuantities, List<Integer> redeemQuantities) {
        try {
            dbconn.setAutoCommit(false);
            Iterator it = coffeeIds.iterator();
            statement = dbconn.createStatement();
            DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
            String query = "INSERT INTO PURCHASE VALUES(1,"+customerId+","+storeId+",to_date('"+df.format(purchaseTime)+"','DD-MON-YYYY HH24:MI:SS'))";
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
                e.printStackTrace();
            } catch (SQLException e1) {
                e.printStackTrace();
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
        try {
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
            stmt.setInt(1,0);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);
            stmt.execute();
            resultSet= ((OracleCallableStatement)stmt).getCursor(2);
            resultSet.next();
            do{
               System.out.println( resultSet.getInt("CUSTOMER_ID"));
            }while((resultSet.next()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        BoutiqueCoffee db = new BoutiqueCoffee();
        db.getTopKCustomersInPastXMonth(2,78);
    }
}

