import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;


public class CoordinatesConn implements Runnable {
    private Queue<Coordinates> aQueue;
    private ArrayList<AdressModel> adressModelList;

    public CoordinatesConn(Queue<Coordinates> aQueue, ArrayList<AdressModel> adressModelList) {
        this.aQueue = aQueue;
        this.adressModelList = adressModelList;
    }

    public String getJson(double lat, double lon) {
        BufferedReader reader = null;

        String urlAdress = String.format("http://geo2.osm.gpsserwer.pl/reverse.php?format=json&lat=%.13f&lon=%.13f", lat, lon);
        urlAdress = urlAdress.replace(",", ".");
        System.out.println(urlAdress);
        String inputLine = null;
        try {
            URL url = new URL(urlAdress);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            inputLine = in.readLine();

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputLine;
    }

    public void resolveCoordinates() {
        while (true) {
            Coordinates cord = null;
            synchronized (aQueue) {
                if (aQueue.isEmpty()) {
                    return;
                } else {
                    cord = aQueue.poll();
                }
            }
            String json = getJson(cord.lat, cord.lon);
            System.out.println("wykonuje watek: "+Thread.currentThread().getName());

            synchronized (this) {
                adressModelList.add(AdressModel.getAdressModel(json));
            }

        }
    }

    @Override
    public void run() {
        resolveCoordinates();
    }
}

