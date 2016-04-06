package teamwork;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

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
    public void checkFillField(){
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
}
