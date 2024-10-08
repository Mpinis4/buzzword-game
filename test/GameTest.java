import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;


public class GameTest extends TestCase {
    @Test
    public void testAdvanceGame(){
        Game a = new Game();
        assertEquals(0,a.advanceGame());
        a.setCurrentLap(15);
        a.setLapType(LapType.BET);
        assertEquals(2,a.advanceGame());
        a.setCurrentLap(1);
        a.setCurrentQuestion(2561);
        assertEquals(1,a.advanceGame());
        a.setCurrentQuestion(2);
        assertEquals(0,a.advanceGame());
    }

    @Test
    public void testGiveAnswer(){
        Game a = new Game();
        ArrayList<String> names = new ArrayList<>();
        names.add("test1");
        names.add("test2");
        a.addPlayers(names);
        a.setLapType(LapType.BET);
        int b = a.giveAnswer(true,0,250);
        assertEquals(250,b);
        b = a.giveAnswer(false,0,750);
        assertEquals(-750,b);

        a.setLapType(LapType.QUICK_ANSWER);
        b = a.giveAnswer(true,0,0);
        assertEquals(1000,b);
        b = a.giveAnswer(true,0,1);
        assertEquals(500,b);
    }

}
