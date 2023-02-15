package Models;

public class Category {
    protected int Id;
    protected String Name;
    public Category(String name){
        Id=0;
        this.Name=name;
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
    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("name='").append(Name).append('\'');
        sb.append(", Id=").append(Id);
        sb.append('}');
        return sb.toString();
    }
}
