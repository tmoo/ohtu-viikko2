/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tuomo
 */
public class StatisticsTest {
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchWorks() {
        Player pl = stats.search("Lemieux");
        assertEquals(String.format("%-20s","Lemieux") + " PIT 45 + 54 = 99", pl.toString());
    }
    
    @Test
    public void searchWorksIfDoesntContain() {
        Player pl = stats.search("rölli");
        assertEquals(null, pl);
    }
    
    @Test
    public void teamWorks() {
        List<Player> players = stats.team("PIT");
        assertEquals(stats.search("Lemieux"), players.get(0));
    }
    
    @Test
    public void howManyWorks() {
        List<Player> players = stats.topScorers(2);
        assertEquals(stats.search("Gretzky"), players.get(0));
        assertEquals(stats.search("Lemieux"), players.get(1));
    }
}
