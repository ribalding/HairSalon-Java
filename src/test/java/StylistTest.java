import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StylistTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylist_instantiatesCorrectly(){
    Stylist newStylist = new Stylist("Ryan", "Dumb Stuff", 1);
    assertEquals(true, newStylist instanceof Stylist);
  }

  @Test
  public void getPrice_returnsCorrectPrice(){
    Stylist newStylist = new Stylist("Ryan", "Dumb Stuff", 1);
    assertEquals(1, newStylist.getPrice());
  }

  @Test
  public void save_savesCorrectly(){
    Stylist newStylist = new Stylist("Ryan", "Dumb stuff", 1);
    newStylist.save();
    assertEquals(1, Stylist.all().size());
  }

  @Test
  public void find_returnsCorrectStylist(){
    Stylist newStylist = new Stylist("Ryan", "Dumb Stuff", 1);
    newStylist.save();
    Stylist foundStylist = Stylist.find(newStylist.getId());
    assertTrue(foundStylist.equals(newStylist));
  }
}
