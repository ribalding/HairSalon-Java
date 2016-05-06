import java.util.List;
import org.sql2o.*;
import java.util.Arrays;


public class Stylist {
  private int id;
  private String stylist_name;
  private String speciality;
  private int price;

  public Stylist(String name, String style_speciality, int style_price) {
    stylist_name = name;
    speciality = style_speciality;
    price = style_price;
  }

  public String getName(){
    return stylist_name;
  }

  public int getId(){
    return id;
  }

  public String getSpeciality(){
    return speciality;
  }

  public int getPrice(){
    return price;
  }

  @Override
  public boolean equals(Object otherStylist) {
    if (!(otherStylist instanceof Stylist)) {
      return false;
    } else {
      Stylist newStylist = (Stylist) otherStylist;
      return this.getName().equals(newStylist.getName()) &&
             this.getPrice() == newStylist.getPrice() &&
             this.getSpeciality().equals(newStylist.getSpeciality()) &&
             this.getId() == newStylist.getId();
    }
  }

  public static List<Stylist> all(){
    String sql = "SELECT * FROM stylists";
    try(Connection con = DB.sql2o.open()){
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }

  public void save(){
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO stylists (stylist_name, speciality, price) VALUES (:stylist_name, :speciality, :price)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("stylist_name", this.stylist_name)
      .addParameter("speciality", this.speciality)
      .addParameter("price", this.price)
      .executeUpdate()
      .getKey();
    }
  }

  public static Stylist find(int id){
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists WHERE id=:id";
      return con.createQuery(sql)
       .addParameter("id", id)
       .executeAndFetchFirst(Stylist.class);
    }
  }
}
