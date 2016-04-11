package teamwork;

import java.util.*;
import java.util.logging.StreamHandler;
import java.util.regex.Pattern;

public class Bomber {

    private int x;
    private int y;
    private int[][] field;
    private List<String> checkedFields = new ArrayList<String>();
    private int countShot = 0;
    public static final String DETONATION = "Detonation";
    public static final String WINNER = "Winner!";
    public static final String MISS = "GOOD WAY!";
    public static final String AGAIN = "Again";

    public Bomber() {
        this(10, 10);
    }

    public Bomber(int x, int y) {
        this.x = x;
        this.y = y;
        this.field = new int[x][y];
    }

    public int[][] fillField(int countMine) {
        countShot = 0;
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

    public String checkShot(int x, int y) {
        if (checkedFields.contains(new String(x + "" + y))) {
            return AGAIN;
        }
        checkedFields.add(new String(x + "" + y));
        countShot++;
        if (field[x][y] == 1) {
            return DETONATION;
        }
        if (field[x][y] == 0 && countShot == 10) {
            return WINNER;
        }
        return MISS;
    }

    public void init() {
        render();
        for (int i = 0; i < 10; i++) {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите точку выстрела в формате [x,y] , где x и y - это цифры от 0 до 9: ");
            try {
                char[] xy = parseUserData(in.next(Pattern.compile("\\[[0-9],[0-9]\\]")));
                String result = checkShot(Character.getNumericValue(xy[0]), Character.getNumericValue(xy[1]));
                if (result.equals(AGAIN)) {
                    --i;
                    System.out.println("Вы попали в то же место. Выстрел не считается.");
                    continue;
                } else if (result.equals(DETONATION) || result.equals(WINNER)) {
                    System.out.print("\n\n\n");
                    renderAll();
                    System.out.print("\n\n\n");
                    System.out.println(result);
                    return;
                }
                render();
                System.out.println(result);
            } catch (InputMismatchException e) {
                --i;
                System.out.println("Введены координаты в неверном формате.");
                continue;
            }
        }
        return;
    }

    private char[] parseUserData(String data) {
        char x = data.charAt(1);
        char y = data.charAt(3);
        char[] xy = {x, y};
        return xy;
    }

    public void render() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (checkedFields.contains(new String(i + "" + j))) {
                    System.out.print("|   +   |");
                } else {
                    System.out.print("| [" + i + "," + j + "] |");
                }
            }
            System.out.println("\n");
        }
    }

    private void renderAll() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (field[i][j] == 0) {
                    System.out.print("|   +   |");
                } else {
                    System.out.print("|   -   |");
                }
            }
            System.out.println("\n");
        }
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

    public static void main(String[] args) {
        Bomber bomber = new Bomber();
        bomber.fillField(25);
        bomber.init();
    }
}
