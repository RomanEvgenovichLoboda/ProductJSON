package Models;

public class Product {
    protected int Id;
    protected String Name;
    protected Category Categ;
    protected Manufacturer ManFact;
    protected double Weight;
    protected double Price;
    protected int Rating;


    public Product(String name,String cat,String mf,double weight,double price,int rating) {
        Id = 0;
        Name = name;
        Categ=new Category(cat);
        ManFact=new Manufacturer(mf);
        Weight=weight;
        Price=price;
        Rating=rating;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCateg() {
        return Categ.getName() ;
    }

    public void setCateg(String categ) {
        Categ.setName(categ);
    }

    public String getManFact() {
        return ManFact.getName();
    }

    public void setManFact(String manFact) {
        ManFact.setName(manFact);
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append(", Id=").append(Id);
        sb.append("name='").append(Name).append('\'');

        sb.append(", Category=").append(getCateg()).append('\'');
        sb.append(", Manufacturer=").append(getManFact()).append('\'');
        sb.append(", Weight=").append(Weight);
        sb.append(", Price=").append(Price);
        sb.append(", Rating=").append(Rating);
        sb.append('}');
        return sb.toString();
    }
}
