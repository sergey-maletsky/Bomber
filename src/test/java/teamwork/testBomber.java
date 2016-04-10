package teamwork;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class testBomber {

    @Test
    public void testCreate() {
        assertNotNull(new Bomber());
    }

    @Test
    public void checkFill() {
        Bomber bomber = new Bomber();
        int[][] field = bomber.fillField(25);
        assertNotNull(field);
    }

    @Test
    public void testCheckFillField(){
        Bomber bomber = new Bomber(10,10);
        int[][] field = bomber.fillField(25);
        assertEquals(10,field.length);
        assertEquals(10,field[0].length);
        int sum=0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                assertTrue(field[i][j]==0||field[i][j]==1);
                sum+=field[i][j];
            }
        }
        assertEquals(25,sum);

    }

    @Test
    public void testCheckWinner() {
        Bomber bomber = new Bomber();
        bomber.fillField(25);

        int counter = 0;
        while (counter < 10){
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if(bomber.getField()[x][y] == 0) {
                counter++;
                if(counter < 10) {
                    assertEquals("Miss!", bomber.checkShot(x, y));
                }
                else {
                    assertEquals("Winner!", bomber.checkShot(x, y));
                }
            }
        }
    }
}
