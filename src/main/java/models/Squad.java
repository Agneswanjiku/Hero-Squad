package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Squad {
    private String mName;
    private int mSize;
    private static List<Squad> instances = new ArrayList<Squad>();
    private int mId;
    private List<Hero> mHeroes;

    public Squad(String name, int size) {
        mName = name;
        mSize = size;
        instances.add(this);
        mId = instances.size();
        mHeroes = new ArrayList<Hero>();
    }

    public String getmName() {
        return mName;
    }

    public int getSize() {
        return mSize;
    }


    public static List<Squad> all() {
        return instances;
    }

    public static void clear() {
        instances.clear();
    }

    public int getId() {
        return mId;
    }

    public static Squad find(int id) {
        return instances.get(id - 1);
    }

    public List<Hero> getHeroes() {
        return mHeroes;
    }

    public void addHero(Hero hero) {
        mHeroes.add(hero);
    }

    public boolean heroAlreadyExists(Hero newHero) {
        boolean exists = false;
        for (Squad squad : instances) {
            for (Object hero : squad.getHeroes()) {
                if (Objects.equals(hero.equals(hero), newHero.equals(hero))) {
                    exists = true;
                }
            }
        }
        return exists;
    }
}