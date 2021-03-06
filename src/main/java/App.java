import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");



        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());



        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int age = Integer.parseInt(request.queryParams("age"));
            String name =request.queryParams("name");
            String power =request.queryParams("power");
            String weakness =request.queryParams("weakness");
            Hero newHero = new Hero(name, age, power, weakness);
//            model.put("heroes", heroes);
            response.redirect("/success");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Hero hero = Hero.find(Integer.parseInt(request.params(":id")));
            model.put("hero", hero);
            model.put("template", "templates/hero.hbs");
            return new ModelAndView(model, "hero.hbs");
        }, new HandlebarsTemplateEngine());

        get("/heroes",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("hero-form.hbs", "/hero-form.hbs");
            return new ModelAndView(model, "hero-form.hbs");
        }, new HandlebarsTemplateEngine());




        get("/squadsForm", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "squad-form.hbs");
        }, new HandlebarsTemplateEngine());




        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int size = Integer.parseInt(request.queryParams("size"));
            Squad newSquad= new Squad(name, size);
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> allSquads = Squad.all();
            model.put("allSquads", allSquads);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());

        get("/success", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());



        get("/squads/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
            model.put("squad", squad);
            model.put("template", "templates/squad.hbs");
            return new ModelAndView(model, "squad.hbs");
        }, new HandlebarsTemplateEngine());

        post("squads/:id/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad squad = Squad.find(Integer.parseInt(request.params(":id")));
            model.put("squad", squad);
            model.put("template", "templates/squadHeroesForm.hbs");
            return new ModelAndView(model, "squadHeroesForm.hbs");
        }, new HandlebarsTemplateEngine());



        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Squad squad = Squad.find(Integer.parseInt(request.queryParams("squadId")));
            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            Hero newHero = new Hero(name, age, power, weakness);




            if (squad.heroAlreadyExists(newHero)) {
                String heroExists = "Hero " + name + " is already in the squad";
                model.put("heroExists", heroExists);
            }


            else{
                squad.addHero(newHero);
            }

            model.put("squad", squad);
            model.put("template", "templates/squadHeroesSuccess.hbs");
            return new ModelAndView(model, "squadHeroesSuccess.hbs");
        }, new HandlebarsTemplateEngine());
    }
}