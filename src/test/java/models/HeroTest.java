package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeroTest {
    @Test
    public void Hero_instantiatesCorrectly_true() {
        Hero myHero = new Hero("mavel",45,"mavel","mavel");
        assertEquals(true, myHero instanceof Hero);
    }
    @Test
    public void getName_forHeroesName_String() {
        Hero myHero = new Hero("Bety",45,"mavel","mavel");
        String expected = "bety";
        assertEquals(expected, myHero.getName());
    }
    @Test
    public void getAge_forHeroesName_String() {
        Hero myHero = new Hero("bety",45,"mavel","mavel");
        int expected = 65;
        assertEquals(expected, myHero.getAge());
    }
    @Test
    public void getWeakness_forHeroesName_String() {
        Hero myHero = new Hero("bety",45,"mavel","mavel");
        String expected = "mavel";
        assertEquals(expected, myHero.getWeakness());
    }
    @Test
    public void getPower_forHeroesName_String() {
        Hero myHero = new Hero("bety",45,"mavel","mavel");
        String expected = "mavel";
        assertEquals(expected, myHero.getPower());
    }
    @Test
    public void getId_forHeroesName_int() {
        Hero.clear();
        Hero myHero = new Hero("bety",45,"mavel","mavel");
        assertEquals(1, myHero.getId());
    }



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}