package teamwork;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Serega on 06.04.2016.
 */
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
}
