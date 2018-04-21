import oracle.sql.TIMESTAMP;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;

/**
 * Created by inspi on 4/12/2018.
 */
public class BCbenchmark {
    public static void main(String[] args){
        BoutiqueCoffee db = new BoutiqueCoffee();
        //addCoffees
        for(int x = 0; x<21;x++){
            int val = db.addCoffee("Coffee"+x,"Number: "+x,x,x*3.2+1,2 + x/10,10);
            assert (val ==1);
            System.out.println(" Coffee: "+ val);
        }
        //add memberlevels
        for(int x = 0; x<20;x++){
            int val = db.addMemberLevel("Name"+x,(1)+x/10);
            System.out.println("Member Level: "+ val);
            assert(val ==1);
        }
        for(int x =0;x<20;x++){
            int val = db.addStore("Store#"+x,x+" test Lane","Large", (x+1)*7, (x+4)*3);
            assert(val == 1);
            System.out.println("Store: "+ val);
        }
       for(int x = 0; x<20;x++){
          int val = db.addCustomer("Name#"+x,"LastName#"+x,x+"@gmail.com",1,x +1);
           System.out.println("Customer ID: "+ val);
       }
        List<Integer> coffeeids = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        List<Integer> redeem = new ArrayList<>();
        for(int x =0; x<20;x++){
            coffeeids.add(x+1);
            quantity.add(x+1);
            redeem.add(1);
        }
        for(int x = 0;x<5;x++){
            Date date = null;
            DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
            try {
                 date = df.parse(x+"-FEB-2015 7:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Purchase: " + db.addPurchase( + x+1,x+1,date,coffeeids,quantity,redeem));
        }
        for(int x = 0;x<20;x++){
            Date date = null;
            DateFormat df = new SimpleDateFormat("dd-MMM-YYYY HH:mm:ss");
            try {
                date = df.parse(x+"-FEB-2015 7:00:00");
            } catch (ParseException e) {
                e.printStackTrace();
            }
           System.out.println("Promo: "+ db.addPromotion("Promo"+x, date,date));
        }
        for(int x = 1; x<21;x++){
           System.out.println("OfferCoffee: " + db.offerCoffee(x,x));
           System.out.println("HasPromotion: " + db.hasPromotion(x,x));
           System.out.println("PromoteFor: "+ db.promoteFor(x,x));
        }

        List list = db.getTopKCustomersInPastXMonth(4,72);
        List slist = db.getTopKStoresInPastXMonth(4,72);
        Iterator it2 = slist.iterator();
        Iterator it = list.iterator();
        while(it.hasNext() && it2.hasNext()){
            System.out.println("Top Customer: " + it.next());
            System.out.println("Top Store:    " + it2.next());
        }

        System.out.println("Customer Points: "+ db.getPointsByCustomerId(1) + " Coffee Keyword c and o id: "+ db.getCoffeesByKeywords("c","o"));
        list = db.getCoffeesByKeywords("u","m");
        it2 = list.iterator();
        while(it2.hasNext()){
            System.out.println("Coffee Keyword: "+it2.next());
        }
        list = db.getCoffees();
        it2 = list.iterator();
        while(it2.hasNext()){
            System.out.println("Coffee ID " + it2.next());
        }

    }
}
