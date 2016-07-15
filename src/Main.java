public class Main {

    public static void main(String[] args) {


        int numberOfThreads = Integer.parseInt(args[0]);
//        int numberOfThreads = 3;

        if(numberOfThreads > 8 || numberOfThreads < 1){
            System.out.println("Niepoprawna ilosc watkow!");
            return;
        }

        AdressModelList adressModelList = new AdressModelList();
//        adressModelList.getImport("import.csv");
        adressModelList.getImport(args[1]);

        Runnable coordinatesConn = new CoordinatesConn(adressModelList.getaQueue(), adressModelList.getAdressModelList());



        Thread threads_pool[] =  new Thread[numberOfThreads];

        for (int i = 0; i< numberOfThreads; i++){
            threads_pool[i] = new Thread(coordinatesConn);
        }

        for (int i = 0; i< numberOfThreads; i++){
            threads_pool[i].start();
             }

        for (int i = 0; i< numberOfThreads; i++){
            try {
                threads_pool[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        DatabaseConn dbConn = new DatabaseConn();
        adressModelList.writeToDatabase(dbConn);
        dbConn.closeConnection();

    }
}
