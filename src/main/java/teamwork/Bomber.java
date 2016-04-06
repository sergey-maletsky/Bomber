package teamwork;

import java.util.Random;

public class Bomber {

    private int x;
    private int y;
    private int[][] field;

    public Bomber() {
        this.x = 10;
        this.y = 10;
        this.field = new int[x][y];
    }

    public Bomber(int x, int y) {
        this.x = x;
        this.y = y;
        this.field = new int[x][y];
    }

    public static void main(String[] args) {
        Bomber bomber = new Bomber();
        bomber.fillField(25);
    }

    int[][] fillField(int countMine) {
        Random mine = new Random();
        int counter = 0;

        label:
        while (counter < countMine) {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (counter >= countMine) {
                        break label;
                    }
                    if (mine.nextInt(2) == 1 && field[i][j] != 1) {
                        counter++;
                        field[i][j] = 1;
                    }
                }
            }
        }
        return field;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int[][] getField() {
        return field;
    }
}
