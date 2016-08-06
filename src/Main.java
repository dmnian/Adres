import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
//        int numberOfThreads = Integer.parseInt(args[0]);
        int numberOfThreads = 8;

        if (numberOfThreads > 8 || numberOfThreads < 1) {
            System.out.println("Niepoprawna ilosc watkow!");
            return;
        }

        AdressModelList adressModelList = new AdressModelList();
        adressModelList.getImport("import.csv");

        Runnable coordinatesConn = new CoordinatesConn(adressModelList.getaQueue(), adressModelList.getAdressModelList());
        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            pool.submit(coordinatesConn);
        }
        pool.shutdown();

        DatabaseConn dbConn = new DatabaseConn();
        adressModelList.writeToDatabase(dbConn);
        dbConn.closeConnection();
    }
}
