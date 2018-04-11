

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
    public static void BoutiqueCoffee(){
        try {
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
            String url = "jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass";

            dbconn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int addStore(String name, String address, String storeType, long gpsLong, long gpsLat){
        try {
            statement = dbconn.createStatement();
            String query = "insert into Store values(1,'"+ name+"','"+address+"','"+storeType+"',"+ gpsLong+","+gpsLat+");";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public int addCoffee(String name, String description, int intensity, double rewardPoints, double redeemPoints)
    {
        try {
            statement = dbconn.createStatement();
            String query = "insert into Store values(1,'"+ name+"','"+description+"',"+intensity+","+ rewardPoints+","+redeemPoints+");";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;
    }

    public int offerCoffee(int storeId, int coffeeId)
    {
        try {
            statement = dbconn.createStatement();
            String query = "insert into Store values("+storeId+"," + coffeeId + ");";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return storeId;
    }

    public int addPromotion(String name, Date startDate, Date endDate){
        try {
            statement = dbconn.createStatement();
            String query = "insert into Store values(1,'"+name+"',to_date('"+startDate.toString()+"','DD-MON-YYYY HH24:MI:SS'), to_date('"+startDate.toString()+"','DD-MON-YYYY HH24:MI:SS') );";
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
    public static void main(String[] args){

    }
}

