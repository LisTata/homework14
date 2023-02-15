package smoker;


import java.util.concurrent.Semaphore;

public class Smoker extends Thread {
    protected final Semaphore smoker;
    protected final Semaphore barman;

    private final String smokerType;

    public Smoker(Semaphore smoker, Semaphore barman, String smokerType) {
        this.smoker = smoker;
        this.barman = barman;
        this.smokerType = smokerType;
    }

    @Override
    public void run() {
        while (true) {
            try {
                smoker.acquire();
                makeCigarette();
                barman.release();
                smokeCigarette();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void makeCigarette() {
        System.out.format("<< %s >> is making cigarette\n", smokerType);
        try {
            Thread.sleep(Constants.sleepTimeMlsMakeCigarette);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.format("<< %s >> has completed to make cigarette - Table is clean now\n", smokerType);
    }

    private void smokeCigarette() {
        System.out.format("<< %s >> is smoking now\n", smokerType);
        try {
            Thread.sleep(Constants.sleepTimeMlsSmokeCigarette);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.format("<< %s >> has finished smoking the cigarette\n", smokerType);
    }
}