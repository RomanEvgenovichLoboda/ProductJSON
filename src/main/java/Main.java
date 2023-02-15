import Models.Product;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        prodList.add(new Product("Banana","Vegitable","Jingo",50,100,1));
        prodList.add(new Product("Cucumber","Vegitable","Fingo",10,200,2));
        prodList.add(new Product("Cun","Weapon","S&W",1,900,10));
        prodList.add(new Product("Chear","Mebl","Gus",250,1500,3));
        prodList.add(new Product("Jacket","Clows","Nike",0.2,1000,11));
        prodList.add(new Product("Shues","Clows","Adidas",1,1700,7));
        prodList.add(new Product("Banana","Vegitable","Jingo",50,100,1));
        prodList.add(new Product("Cucumber","Vegitable","Fingo",10,200,2));
        prodList.add(new Product("Cun","Weapon","S&W",1,900,10));
        prodList.add(new Product("Chear","Mebl","Gus",250,1500,3));
        prodList.add(new Product("Jacket","Clows","Nike",0.2,1000,11));
        prodList.add(new Product("Shues","Clows","Adidas",1,1700,7));
        menu();
    }
    public static Scanner console = new Scanner(System.in);
    public static ArrayList<Product> prodList = new ArrayList<Product>();
    public static ArrayList<Product> manufactList = new ArrayList<Product>();
    public static ArrayList<String> categoryList = new ArrayList<String>();
    public static ArrayList<Product> cheaper1000List = new ArrayList<Product>();
    public static ArrayList<Product> priceBelowAverageList = new ArrayList<Product>();
    public static ArrayList<Product> priceBelowAverageFromUnPopCategList = new ArrayList<Product>();
    public static ArrayList<Product> unPopManInMostPopCatList = new ArrayList<Product>();
    public static  ArrayList<Product>pr1_5Wght500List = new ArrayList<Product>();

    public static void menu(){
        String menu;
        while (true) {
            try{
                System.out.print("\n\t\t\t<-Menu->\n\t\t1 = Show All Products\n\t\t2 = Show All Categories\n\t\t" +
                        "3 = Search By Manufacturer\n\t\t4 = Price < 1000\n\t\t5 = Price below average\n\t\t6 = Price Below Average From UnPopCategory\n\t\t" +
                        "7 = UnPopManInMostPopCat\n\t\t8 = Product Weight < 1 Kg\n\t\t9 = Price 500 - 100 & Wght>500\n\t\t0 = Exit: ");
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
                        searchManufactList(name);
                    }
                    case "4" -> {
                        setCheaper1000List();
                        System.out.println("\n\t\t<-Cheaper 1000->");
                        cheaper1000List.forEach(x->x.showInfo());
                    }
                    case "5"->{
                        System.out.println("\n\t\t<-PriceBelowAverage->");
                        priceBelowAverageList=getPriceBelowAverageList();
                        priceBelowAverageList.forEach(x->x.showInfo());
                    }
                    case "6"->{
                        priceBelowAverageFromUnPopCategList=getPriceBelowAverageFromUnPopCategList();
                        System.out.println("\n\t\t<-PriceBelowAverageFromUnPopCateg->");
                        priceBelowAverageFromUnPopCategList.forEach(x->x.showInfo());
                    }
                    case "7"->{
                        unPopManInMostPopCatList=getUnPopManInMostPopCat();
                        System.out.println("\n\t\t<-UnPopManInMostPopCat->");
                        unPopManInMostPopCatList.forEach(x->x.showInfo());
                    }
                    case "8"->{
                        System.out.println("\n\t\t<-Product Weight < 1 Kg->");
                        Product pr = getProdWeight1Kg();
                        pr.showInfo();
                    }
                    case "9"->{
                        pr1_5Wght500List = getPr1_5Wght500();
                        System.out.println("\n\t\t<-Pr1_5Wght500->");
                        pr1_5Wght500List.forEach(x->x.showInfo());
                    }
                    case "0" -> {
                        saveToJson();
                        saveRezultToJson();
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
    public static void searchManufactList(String name){
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
    public static ArrayList<Product> getPriceBelowAverageList(){
        ArrayList<Product>tempList=new ArrayList<Product>();
        double sum = 0;
        for (Product pr:prodList) { sum+=pr.getPrice(); }
        double avarage = sum/prodList.size();
        for (Product pr:prodList) {
            if(pr.getPrice()<avarage){
                tempList.add(pr);
            }
        }
        return tempList;
    }
public static ArrayList<Product> getPriceBelowAverageFromUnPopCategList(){
        setCategories();
        ArrayList<Product>prList = new ArrayList<Product>();
        String cat="";
        int rtng=1000;
        double prc=0;
    for (String categ:categoryList) {
        String name = categ;
        int rat = 0;
        for (Product pr:prodList) {
            if(pr.getCateg().equals(name)){
                rat+= pr.getRating();
            }
        }
        if(rat<rtng){
            cat=name;
            rtng=rat;
        }
    }
    int count;
    for (Product pr:prodList) {
        if(pr.getCateg().equals(cat)){
            prc+=pr.getPrice();
            prList.add(pr);
        }
    }
    double avg = prc/prList.size();
    ArrayList<Product> myList = new ArrayList<Product>();
    for (Product pr:prList) {
        if(pr.getPrice()>avg){
            myList.add(pr);
        }
    }
        return myList;
}
public static ArrayList<Product> getUnPopManInMostPopCat(){
        setCategories();
        int pop=0;
        String category="";
    for (String ct:categoryList) {
        String ctgr = ct;
        int rtng = 0;
        for (Product pr:prodList) {
            if(pr.getCateg().equals(ctgr)){
                rtng+= pr.getRating();
            }
        }
        if(rtng>pop){
            category=ctgr;
            pop=rtng;
        }
    }
    String manufact = "";
    int rtng2=1000;
    for (Product pr:prodList) {
        String man = pr.getManFact();
        int rt = 0;
        for (Product pr1:prodList) {
            if(pr1.getCateg().equals(category)&&pr1.getManFact().equals(man)){
                rt+=pr1.getRating();
            }
        }
        if(rt<rtng2&&rt!=0){
            manufact=man;
            rtng2=rt;
        }
    }
    ArrayList<Product>myProdList=new ArrayList<Product>();
    for (Product pr:prodList) {
        if(pr.getManFact().equals(manufact)&&pr.getCateg().equals(category)){
            myProdList.add(pr);
        }
    }

return myProdList;
}
public static Product getProdWeight1Kg(){
    for (Product p:prodList) {
        if(p.getWeight()>1){
            return p;
        }
    }
    return null;
}
public static ArrayList<Product>getPr1_5Wght500(){
        ArrayList<Product>pList = new ArrayList<>();
    for (Product pr:prodList) {
        if(pr.getPrice()<=1000&&pr.getPrice()>=100&&pr.getWeight()<0.9){
            pList.add(pr);
        }
    }
    return pList;
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
    public static void saveRezultToJson() {

        JSONArray jarray = new JSONArray();

        setCategories();
        setCheaper1000List();
        priceBelowAverageList= getPriceBelowAverageList();
        priceBelowAverageFromUnPopCategList= getPriceBelowAverageFromUnPopCategList();
        unPopManInMostPopCatList = getUnPopManInMostPopCat();
        pr1_5Wght500List = getPr1_5Wght500();
        jarray.putAll(categoryList.toArray());
        jarray.putAll(cheaper1000List.toArray());
        jarray.putAll(priceBelowAverageList.toArray());
        jarray.putAll(priceBelowAverageFromUnPopCategList.toArray());
        jarray.putAll(unPopManInMostPopCatList.toArray());
        jarray.putAll(pr1_5Wght500List.toArray());
        try {
            FileWriter writer = new FileWriter(new File("res.json"));
            writer.write(jarray.toString(4));

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}