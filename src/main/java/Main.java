import Models.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Product pr = new Product("Banana","Vegitable","Jingo",50,100,1);
        //var userjo = new JSONObject(pr);
        //System.out.println(userjo);
        ArrayList<Product> prodList = new ArrayList<Product>();
        prodList.add(new Product("Banana","Vegitable","Jingo",50,100,1));
        prodList.add(new Product("Cucumber","Vegitable","Fingo",10,200,2));
        prodList.add(new Product("Cun","Weapon","S&W",1,900,10));
        prodList.add(new Product("Chear","Mebl","Gus",250,1500,3));
        prodList.add(new Product("Jacket","Clows","Nike",0.2,1000,11));
        prodList.add(new Product("Shues","Clows","Adidas",1,1700,7));
        //JSONArray jarray = new JSONArray(prodList.toArray());
        //System.out.println(jarray);
        saveToJson(prodList);
    }
    public static void saveToJson(ArrayList<Product> list) {

        JSONArray jarray = new JSONArray(list.toArray());
        try {
            FileWriter writer = new FileWriter(new File("mydb.json"));
            writer.write(jarray.toString(4));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}