package smoker;



import java.util.concurrent.Semaphore;

public class Runner {
    static Semaphore barmanSem = new Semaphore(1);
    static Semaphore smokerWithTobaccoSem = new Semaphore(0);
    static Semaphore smokerWithPaperSem = new Semaphore(0);
    static Semaphore smokerWithMatchesSem = new Semaphore(0);

    public static void main(String[] args) {
        Table table = new Table(smokerWithTobaccoSem, smokerWithPaperSem, smokerWithMatchesSem);
        new Barman(table, barmanSem).start();
        new Smoker(smokerWithTobaccoSem, barmanSem, Constants.smokerWithTobacco).start();
        new Smoker(smokerWithPaperSem, barmanSem, Constants.smokerWithPaper).start();
        new Smoker(smokerWithMatchesSem, barmanSem, Constants.smokerWithMatches).start();
    }

}