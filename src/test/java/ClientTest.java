import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class ClientTest{

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly(){
    Client newClient = new Client("Ryan", 1);
    assertEquals(true, newClient instanceof Client);
  }

  @Test
  public void save_savesCorrectly(){
    Client newClient = new Client("Ryan", 1);
    newClient.save();
    assertEquals(1, Client.all().size());
  }

  @Test
  public void find_returnsCorrectClient(){
    Client newClient = new Client("Ryan", 1);
    newClient.save();
    Client foundClient = Client.find(newClient.getId());
    assertTrue(foundClient.equals(newClient));
  }
}
