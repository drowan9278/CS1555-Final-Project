import oracle.sql.TIMESTAMP;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by inspi on 4/12/2018.
 */
public class BCbenchmark {
    public static void main(String[] args){
        BoutiqueCoffee db = new BoutiqueCoffee();
        //addCoffees
        for(int x = 0; x<20;x++){
            int val = db.addCoffee("Coffee"+x,"Number: "+x,x,x*3.2,2 + x/10,10);
            assert (val ==1);
        }
        //add memberlevels
        for(int x = 0; x<20;x++){
            int val = db.addMemberLevel("Name"+x,(1)+x/10);
            assert(val ==1);
        }
        for(int x =0;x<20;x++){
            int val = db.addStore("Store#"+x,x+" test Lane","Large", (x+1)*7, (x+4)*3);
            assert(val == 1);
        }
        for(int x = 0; x<20;x++){
            int val = db.addCustomer("Name#"+x,"LastName#"+x,x+"@gmail.com",1,x);

        }
        List<Integer> coffeeids = new ArrayList<>();
        List<Integer> quantity = new ArrayList<>();
        List<Integer> redeem = new ArrayList<>();
        for(int x =0; x<20;x++){
            coffeeids.add(x);
            quantity.add(x);
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
            db.addPurchase(x,x,date,coffeeids,quantity,redeem);
        }

    }
}
