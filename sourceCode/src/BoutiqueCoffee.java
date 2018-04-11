

import java.util.Date;
import java.util.List;
import java.sql.*;

/**
 * This was created for CS1555 Final Project By Arie, Bin , and David
 * Team Number: P25
 */
public class BoutiqueCoffee {

    private static Connection dbconn;
    private static String username = "apd29";//username
    private static String  pass = "4118451";//PeopleSoft Number
    private static Statement statement;
    public static void main(String[] args){
        try {
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";

            dbconn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement = dbconn.createStatement(); //create an instance
            String query = "SELECT TABLE_NAME FROM USER_TABLES";
            ResultSet resultSet = statement.executeQuery(query); //run the query on the DB table
      /*the results in resultSet have an odd quality.  The first row in result
      set is not relevant data, but rather a place holder.  This enables us to
      use a while loop to go through all the records.  We must move the pointer
      forward once using resultSet.next() or you will get errors*/
            int counter = 1;
            while(resultSet.next()) //this not only keeps track of if another record
            //exists but moves us forward to the first record
            {
                System.out.println("Record " + counter + ": " +
                        resultSet.getString(1));//since the first item was of type
                        //string, we use getString of the
                        //resultSet class to access it.
                        //Notice the one, that is the
                        //position of the answer in the
                        //resulting table

                counter++;


            }
            dbconn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public int addStore(String name, String address, String storeType, long gpsLong, long gpsLat){

        return 0;
    }


    public int addCoffee(String name, String description, int intensity, double rewardPoints, double redeemPoints)
    {

        return intensity;
    }

    public int offerCoffee(int storeId, int coffeeId)
    {

        return storeId;
    }

    public int addPromotion(String name, Date startDate, Date endDate){

        return 0;
    }

    public int promoteFor(int promotionId, int coFFeeId){

        return promotionId;
    }

    public int hasPromotion(int storeId, int promotionId){

        return storeId;
    }

    public int addMemberLevel(String name, double boosterFactor){

        return 0;
    }

    public int addCustomer(String FirstName, String lastName, String email, int memberLevelId, double totalPoints){

        return memberLevelId;
    }

    public int addPurchase(int customerId, int storeId, Date purchaseTime, List<Integer> coffeeIds, List<Integer> purchaseQuantities, List<Integer> redeemQuantities)
    {

        return customerId;
    }
    public List<Integer> getCoffees(){

        return null;
    }
    public List<Integer> getCoffeesByKeywords(String keyword1, String keyword2){

        return null;
    }

    public double getPointsByCustomerId(int customerId){

        return 0;
    }

    public List<Integer> getTopKStoresInPastXMonth(int k, int x){

        return null;
    }
    public List<Integer> getTopKCustomersInPastXMonth(int k, int x){

        return null;
    }
}

