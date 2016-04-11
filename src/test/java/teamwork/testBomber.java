package teamwork;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
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
    public void testCheckFillField() {
        Bomber bomber = new Bomber(10, 10);
        int[][] field = bomber.fillField(25);
        assertEquals(10, field.length);
        assertEquals(10, field[0].length);
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(field[i][j] == 0 || field[i][j] == 1);
                sum += field[i][j];
            }
        }
        assertEquals(25, sum);
    }

    @Test
    public void testCheckWinner() {
        Bomber bomber = new Bomber();
        bomber.fillField(25);
        List<Point> points = new ArrayList<Point>();
        int counter = 0;
        while (counter < 10) {
            int x = new Random().nextInt(10);
            int y = new Random().nextInt(10);
            if (bomber.getField()[x][y] == 0 && !points.contains(new Point(x, y))) {
                points.add(new Point(x, y));
                counter++;
                if (counter < 10) {
                    assertEquals(Bomber.MISS, bomber.checkShot(x, y));
                } else {
                    assertEquals(Bomber.WINNER, bomber.checkShot(x, y));
                }
            }
        }
    }

    @Test
    public void testRender() {
        Bomber bomber = new Bomber(12,9);
        bomber.fillField(25);
        List<Point> points = new ArrayList<Point>();
        checkRender(bomber,points);
        int counter = 0;
        while (counter < 3) {
            int x = new Random().nextInt(bomber.getX());
            int y = new Random().nextInt(bomber.getY());
            if (bomber.getField()[x][y] == 0 && !points.contains(new Point(x, y))) {
                points.add(new Point(x, y));
                counter++;
                bomber.checkShot(x, y);
                checkRender(bomber,points);
            }
        }
    }

    public void checkRender(Bomber bomber, List<Point> points) {
        PrintStream old=System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream pw = new PrintStream(baos);
        System.setOut(pw);
        bomber.render();
        System.setOut(old);
        ByteArrayInputStream bais= new ByteArrayInputStream(baos.toByteArray());
        BufferedReader br = new BufferedReader(new InputStreamReader(bais));
        int[][] field = bomber.getField();
        for (int i = 0; i < bomber.getX() ; i++) {
            try {
                String line = br.readLine();
                StringBuilder testLine = new StringBuilder();
                for (int j = 0; j < bomber.getY(); j++) {
                    if (points.contains(new Point(i,j))) {
                        testLine.append("|   +   |");
                    } else {
                        testLine.append("| [").append(i).append(",").append(j).append("] |");
                    }
                }
                assertEquals(testLine.toString(),line);
                assertEquals("",br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
