package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class squad<Hero> {
    private String mName;
    private int mSize;
    private String mCause;
    private static List<squad> instances = new ArrayList<squad>();
    private int mId;
    private List<Hero> mHeroes;

    public squad(String name, int size, String cause) {
        mName = name;
        mSize = size;
        mCause = cause;
        instances.add(this);
        mId = instances.size();
        mHeroes = new ArrayList<Hero>();
    }

    public String getName() {
        return mName;
    }

    public int getSize() {
        return mSize;
    }

    public String getCause() {
        return mCause;
    }

    public static List<squad> all() {
        return instances;
    }

    public static void clear() {
        instances.clear();
    }

    public int getId() {
        return mId;
    }

    public static squad find(int id) {
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
        for (squad squad : instances) {
            for (Object hero : squad.getHeroes()) {
                if (Objects.equals(hero.equals(hero), newHero.equals(hero))) {
                    exists = true;
                }
            }
        }
        return exists;
    }
}