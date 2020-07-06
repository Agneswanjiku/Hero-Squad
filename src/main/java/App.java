import models.squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.hbs";


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
            model.put("template", "templates/index.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());



        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("heroes", Hero.all());
            model.put("template", "templates/heroes.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/heroes/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Hero hero = Hero.find(Integer.parseInt(request.params(":id")));
            model.put("hero", hero);
            model.put("template", "templates/hero.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());



        get("squads/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/squadForm.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());




        post("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int size = Integer.parseInt(request.queryParams("size"));
            String cause = request.queryParams("cause");
            squad newSquad= new squad(name,size,cause);
            model.put("template", "templates/squadSuccess.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("squads", squad.all());
            model.put("template", "templates/squads.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());


        get("/squads/:id",(request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            squad squad = models.squad.find(Integer.parseInt(request.params(":id")));
            model.put("squad", squad);
            model.put("template", "templates/squad.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());

        get("squads/:id/heroes/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            squad squad = models.squad.find(Integer.parseInt(request.params(":id")));
            model.put("squad", squad);
            model.put("template", "templates/squadHeroesForm.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());



        post("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();

            squad squad = models.squad.find(Integer.parseInt(request.queryParams("squadId")));

            String name = request.queryParams("name");
            int age = Integer.parseInt(request.queryParams("age"));
            String power = request.queryParams("power");
            String weakness = request.queryParams("weakness");
            Hero newHero = new Hero(name, age, power, weakness);


            if (squad.heroAlreadyExists(newHero)) {
                String heroExists = "Hero " + name + " is already in the squad";
                model.put("heroExists", heroExists);
            }

            else if (squad.getHeroes().size() >= squad.getSize()) {
                String sizeMet = "Squad is full";
                model.put("sizeMet", sizeMet);
            }

            else{
                squad.addHero(newHero);
            }

            model.put("squad", squad);
            model.put("template", "templates/squadHeroesSuccess.hbs");
            return new ModelAndView(model, layout);
        }, new HandlebarsTemplateEngine());
    }
}