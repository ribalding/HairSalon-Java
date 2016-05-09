import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    String layout = "templates/layout.vtl";
    staticFileLocation("/public");

    get("/", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      List<Stylist> stylistList = Stylist.all();
      model.put("stylistList", stylistList);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/addStylist", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      String stylist_name = request.queryParams("stylist_name");
      String speciality = request.queryParams("speciality");
      int price = Integer.parseInt(request.queryParams("price"));
      Stylist newStylist = new Stylist(stylist_name, speciality, price);
      newStylist.save();
      Boolean confirmStylist = true;
      model.put("confirmStylist", confirmStylist);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/addClient", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      String client_name = request.queryParams("client_name");
      int stylist_id = Integer.parseInt(request.queryParams("stylist"));
      Client newClient = new Client(client_name, stylist_id);
      newClient.save();
      Boolean confirmClient = true;
      model.put("confirmClient", confirmClient);

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      List<Stylist> stylistList = Stylist.all();
      model.put("stylistList", stylistList);
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylists/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist newStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("showStylist", newStylist);
      Boolean confirmShowStylist = true;
      model.put("confirmShowStylist", confirmShowStylist);

      List<Stylist> stylistList = Stylist.all();
      model.put("stylistList", stylistList);

      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("editStylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/stylists.vtl");

      Stylist newStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("showStylist", newStylist);
      Boolean confirmShowStylist = true;
      model.put("confirmShowStylist", confirmShowStylist);

      Boolean confirmShowEditStylist = true;
      model.put("confirmShowEditStylist", confirmShowEditStylist);

      List<Stylist> stylistList = Stylist.all();
      model.put("stylistList", stylistList);
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("confirmStylist/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();

      Stylist newStylist = Stylist.find(Integer.parseInt(request.params(":id")));
      model.put("showStylist", newStylist);

      String stylist_name = request.queryParams("stylist_name");
      String speciality = request.queryParams("speciality");
      int price = Integer.parseInt(request.queryParams("price"));
      newStylist.edit(stylist_name, speciality, price);

      List<Stylist> stylistList = Stylist.all();
      model.put("stylistList", stylistList);
      model.put("template", "templates/stylists.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }


}
