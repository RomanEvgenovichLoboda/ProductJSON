import Models.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Product pr = new Product("Banana","Vegitable","Jingo",50,100,1);
        //var userjo = new JSONObject(pr);
        //System.out.println(userjo);
        //ArrayList<Product> prodList = new ArrayList<Product>();
        prodList.add(new Product("Banana","Vegitable","Jingo",50,100,1));
        prodList.add(new Product("Cucumber","Vegitable","Fingo",10,200,2));
        prodList.add(new Product("Cun","Weapon","S&W",1,900,10));
        prodList.add(new Product("Chear","Mebl","Gus",250,1500,3));
        prodList.add(new Product("Jacket","Clows","Nike",0.2,1000,11));
        prodList.add(new Product("Shues","Clows","Adidas",1,1700,7));
        //JSONArray jarray = new JSONArray(prodList.toArray());
        //System.out.println(jarray);
        //saveToJson(prodList);
        menu();
    }
    public static Scanner console = new Scanner(System.in);
    public static ArrayList<Product> prodList = new ArrayList<Product>();
    public static ArrayList<Product> manufactList = new ArrayList<Product>();
    public static ArrayList<String> categoryList = new ArrayList<String>();
    public static ArrayList<Product> cheaper1000List = new ArrayList<Product>();
    public static ArrayList<Product> priceBelowAverageList = new ArrayList<Product>();
    public static void menu(){
        String menu;
        while (true) {
            try{
                System.out.print("\n\t\t\t<-Menu->\n\t\t1 = Show All Products\n\t\t2 = Show All Categories\n\t\t" +
                        "3 = Search By Manufacturer\n\t\t4 = Price < 1000\n\t\t5 = Price below average\n\t\t0 = Exit: ");
                menu=console.nextLine();
                switch (menu) {
                    case "1" -> {
                        System.out.println("\n\t\t<-All Products->");
                        prodList.forEach(x->x.showInfo());
                    }
                    case "2" -> {
                        System.out.println("\n\t\t<-All Categories->");
                        setCategories();
                        categoryList.forEach(x->System.out.println(x));
                    }
                    case "3" -> {
                        System.out.print("\n Enter Manufacturer: ");
                        String name= console.nextLine();
                        setManufactList(name);
                    }
                    case "4" -> {
                        setCheaper1000List();
                        System.out.println("\n\t\t<-Cheaper 1000->");
                        cheaper1000List.forEach(x->x.showInfo());
                    }
                    case "5"->{
                        setPriceBelowAverageList();
                        System.out.println("\n\t\t<-PriceBelowAverage->");
                        priceBelowAverageList.forEach(x->x.showInfo());
                    }
                    case "0" -> {
                        saveToJson();
                        return;
                    }
                    default -> System.out.println("\n\tInvalid Input !");
                }
            }
            catch (Exception exception){
                System.out.println("\n\tExeption is :" + exception.getMessage());
            }
        }
    }
    public static void setCategories(){
        for (Product pr:prodList) {
            boolean isExist = false;
            for (String name:categoryList) {
                if(pr.getCateg().equals(name)){
                    isExist=true;
                }
            }
            if(!isExist){
                categoryList.add(pr.getCateg());
            }
        }
    }
    public static void setManufactList(String name){
        manufactList.clear();
        for (Product pr:prodList) {
            if(pr.getManFact().equals(name)){
                manufactList.add(pr);
            }
        }
        if(manufactList.isEmpty()){
            System.out.println("\n\t\tNot Found");
        }
        else {
            manufactList.forEach(x->x.showInfo());
        }
    }
    public static void setCheaper1000List(){
        cheaper1000List.clear();
        for (Product pr:prodList) {
            if(pr.getPrice()<1000){
                cheaper1000List.add(pr);
            }
        }
    }
    public static void setPriceBelowAverageList(){
        priceBelowAverageList.clear();
        double sum = 0;
        for (Product pr:prodList) { sum+=pr.getPrice(); }
        double avarage = sum/prodList.size();
        for (Product pr:prodList) {
            if(pr.getPrice()<avarage){
                priceBelowAverageList.add(pr);
            }
        }
    }
    public static void saveToJson() {

        JSONArray jarray = new JSONArray(prodList.toArray());
        try {
            FileWriter writer = new FileWriter(new File("mydb.json"));
            writer.write(jarray.toString(4));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}