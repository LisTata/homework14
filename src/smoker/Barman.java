package smoker;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Barman extends Thread {
    private final Random rnd = new Random();
    private final Table table;
    private final Semaphore barman;

    public Barman(Table table, Semaphore barman) {
        this.table = table;
        this.barman = barman;
    }

    @Override
    public void run() {
        while (true) {
            try {
                barman.acquire();
                provideIngredients();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void provideIngredients() {
        int temp = rnd.nextInt(3);
        if (temp == 0) {
            System.out.println("<< Barman >> puts paper and matches on the table");
            table.putPaper();
            table.putMatches();
        }
        if (temp == 1) {
            System.out.println("<< Barman >> puts pushing tobacco and matches on the table");
            table.putTobacco();
            table.putMatches();
        }
        if (temp == 2) {
            System.out.println("<< Barman >> puts pushing tobacco and paper on the table");
            table.putTobacco();
            table.putPaper();
        }
    }

}