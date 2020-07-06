package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class squadTest {
    @Test
    public void squad_instantiatesCorrectly_true() {
        squad testSquad = new squad("nerdsClub", 5, "Computer illiteracy");
        assertEquals(true, testSquad instanceof squad);
    }

    @Test
    public void getName_squadInstantiatesWithName_String() {
        squad testSquad = new squad("nerdsClub", 5, "Computer illiteracy");
        assertEquals("nerdsClub", testSquad.getName());
    }

    @Test
    public void getSize_squadInstantiatesWithSize_int() {
        squad testSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        assertEquals(5, testSquad.getSize());
    }

    @Test
    public void getCause_squadInstantiatesWithCause_String() {
        squad testSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        assertEquals("nerdsClubs", testSquad.getCause());
    }

    @Test
    public void all_returnsAllInstancesOfSquad_true() {
        squad firstSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        squad secondSquad = new squad("Geeks", 4, "Java literacy");
        assertEquals(true, squad.all().contains(firstSquad));
        assertEquals(true, squad.all().contains(secondSquad));
    }

    @Test
    public void clear_emptiesAllSquadsFromList_0() {
        squad testSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        squad.clear();
        assertEquals(squad.all().size(), 0);
    }

    @Test
    public void getId_squadsInstantiatesWithAnId_1() {
        squad.clear();
        squad testSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        assertEquals(1, testSquad.getId());
    }

    @Test
    public void find_returnsSquadWithSameId_secondSquad() {
        squad.clear();
        squad firstSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        squad secondSquad = new squad("Geeks", 4, "Java literacy ");
        assertEquals(squad.find(secondSquad.getId()), secondSquad);
    }

    @Test
    public void getHeroes_initiallyReturnsEmptyList_ArrayList() {
        squad.clear();
        squad testSquad = new squad("nerdsClubs", 5, "Computer iliteracy");
        assertEquals(0, testSquad.getHeroes().size());
    }

    @Test
    public void addHero_addsHeroToList_true() {
        squad testSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        Hero testHero = new Hero("prodigy", 20, "intelligent", "Power failure");
        testSquad.addHero(testHero);
        assertTrue(testSquad.getHeroes().contains(testHero));
    }

    @Test
    public void heroAlreadyExists_checksIfHeroExistsinSquads_true() {
        squad testSquad = new squad("nerdsClubs", 5, "Computer illiteracy");
        Hero testHero = new Hero("prodigy", 20, "intelligent", "power outage");
        testSquad.addHero(testHero);
        assertTrue(testSquad.heroAlreadyExists(testHero));


    }


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}